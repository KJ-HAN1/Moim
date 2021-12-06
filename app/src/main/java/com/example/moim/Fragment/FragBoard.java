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
    String myJSON2;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "name";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_RESULTS2 = "result2";
    private static final String TAG_NAME2 = "name";
    private static final String TAG_CONTENT2 = "content";
    JSONArray boards = null;
    JSONArray boards2 = null;

    ArrayList<HashMap<String, String>> boardList;
    ArrayList<HashMap<String, String>> boardList2;

    ListView lv; //공지사항
    ListView lv2; //일반게시판

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

        //공지 게시판 리스트
        lv = (ListView) v.findViewById(R.id.BoardNotice);
        lv2 = (ListView) v.findViewById(R.id.BoardContent);
        boardList = new ArrayList<HashMap<String, String>>();
        getData("http://192.168.0.15/connect/connect.php");
        //게시판 리스트
        boardList2 = new ArrayList<HashMap<String, String>>();
        getData2("http://192.168.0.15/connect/connect2.php");

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

                String uri = ("http://192.168.0.15/connect/connect.php");
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json);
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute("url");
    }

    protected void showList2() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON2);
            boards2 = jsonObj.getJSONArray(TAG_RESULTS2);

            for (int i = 0; i < boards2.length(); i++) {
                JSONObject c = boards2.getJSONObject(i);
                String name = c.getString(TAG_NAME2);
                String content = c.getString(TAG_CONTENT2);

                HashMap<String, String> boards2 = new HashMap<String, String>();

                boards2.put(TAG_NAME2, name);
                boards2.put(TAG_CONTENT2, content);

                boardList2.add(boards2);
            }
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(),
                    boardList2,
                    R.layout.board_list_item,
                    new String[]{TAG_NAME2, TAG_CONTENT2},
                    new int[]{R.id.item_name, R.id.item_content}
            );
            lv2.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getData2(String string) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            protected String doInBackground(String... params) {

                String uri = ("http://192.168.0.15/connect/connect2.php");
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json);
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON2 = result;
                showList2();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute("url");
    }

}

