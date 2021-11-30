package com.example.moim;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MainLogin extends AppCompatActivity {
    private static String TAG = "phplogin";

    private static final String TAG_JSON = "user";
    private static final String TAG_NAME = "userName";
    private static final String TAG_NIK = "userNik";
    private static final String TAG_ID = "userID";
    private static final String TAG_PW = "userPW";
    private static final String TAG_PHONE = "userPhone";
    private static final String TAG_EMAIL = "userEmail";


    ArrayList<HashMap<String, String>> mArrayList;

    private EditText mEditTextID, mEditTextPass;

    Button btn_login;
    TextView btn_register;

    private String mJsonString;
    private   AlertDialog dialog;

    private TextView mTextViewResult;
    Button btn_temp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);

        //임시버튼
        btn_temp = (Button) findViewById(R.id.temporary);
        btn_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, MainMoim.class);
                startActivity(intent);
            }
        });

/*
        mEditTextID = (EditText) findViewById(R.id.idText);
        mEditTextPass = (EditText) findViewById(R.id.passwordText);

        mEditTextPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

// 회원가입 버튼 클릭시
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLogin.this, MainMoim.class);
                startActivity(intent);
            }
        });


//login 버튼 클릭 시
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                mArrayList.clear();


                GetData task = new GetData();
                task.execute( mEditTextID.getText().toString(), mEditTextPass.getText().toString());

            }
        });


        mArrayList = new ArrayList<>();

    }

    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainLogin.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response - " + result);


        }


        @Override
        protected String doInBackground(String... params) {

            String userID = (String)params[0];
            String userPW = (String)params[1];

            String serverURL = "http://10.0.2.2:8080/loginRequset/query.php";
            String postParameters = "userID=" + userID + "&userPW=" + userPW ;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(2000);
                httpURLConnection.setConnectTimeout(2000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }

    private void showResult(){

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String userName = item.getString(TAG_NAME);
                String userNik = item.getString(TAG_NIK);
                String userID = item.getString(TAG_ID);
                String userPW = item.getString(TAG_PW);
                String userPhone = item.getString(TAG_PHONE);
                String userEmail = item.getString(TAG_EMAIL);



                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_NAME, userName);
                hashMap.put(TAG_NIK, userNik);
                hashMap.put(TAG_ID, userID);
                hashMap.put(TAG_PW, userPW);
                hashMap.put(TAG_PHONE, userPhone);
                hashMap.put(TAG_EMAIL, userEmail);


                mArrayList.add(hashMap);

                Intent intent = new Intent(MainLogin.this, MainMoim.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainLogin.this);


                dialog.show();


                return;
            }


            ListAdapter adapter = new SimpleAdapter(
                    MainLogin.this, mArrayList, R.layout.activity_mainlogin,
                    new String[]{TAG_NAME,TAG_NIK,TAG_ID,TAG_PW,TAG_PHONE,TAG_EMAIL},
                    new int[]{R.id.textName, R.id.textNik, R.id.textId,R.id.textPw,R.id.textphone,R.id.textEmail}
            );


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

*/
    }

}