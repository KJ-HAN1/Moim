package com.example.moim.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.moim.EditBoard;
import com.example.moim.MainLogin;
import com.example.moim.MainMoim;
import com.example.moim.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class FragBoard extends Fragment {
    private View view;
    private String TAG = "프래그먼트";
    //서버에서 가져올 정보를 담을 변수들 선언
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "name";
    private static final String TAG_CONTENT = "content";
    JSONArray boards = null;

    ArrayList<HashMap<String, String>> boardList;

    ListView lv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_board, container, false);

        FloatingActionButton edit = (FloatingActionButton) v.findViewById(R.id.fbtn_edit);
        edit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getActivity(), EditBoard.class);
                startActivity(edit);
            }
        });

        //게시판 리스트
        lv = (ListView) v.findViewById(R.id.BoardNotice);
        boardList = new ArrayList<HashMap<String, String>>();
        getData("http://192.168.35.148/connect.php");


        return v;


    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            boards = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < boards.length(); i++) {
                JSONObject c = boards.getJSONObject(i);
                String name = c.getString(TAG_NAME);
                String content = c.getString(TAG_CONTENT);

                HashMap<String, String> boards = new HashMap<String, String>();

                boards.put(TAG_NAME, name);
                boards.put(TAG_CONTENT, content);

                boardList.add(boards);
            }
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(),
                    boardList,
                    R.layout.board_list_item,
                    new String[]{TAG_NAME, TAG_CONTENT},
                    new int[]{R.id.item_name, R.id.item_content}
            );
            lv.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getData(String string) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            protected String doInBackground(String... params) {

                String uri = params[0];
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                //myJSON = result;
                myJSON = TAG_RESULTS;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute("url");



    }


}

