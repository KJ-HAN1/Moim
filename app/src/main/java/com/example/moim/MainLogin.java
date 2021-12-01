package com.example.moim;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;


import java.net.HttpURLConnection;
import java.net.URL;



public class MainLogin extends AppCompatActivity {


    EditText et_id, et_pw;
    Button btn_login;
    TextView btn_register;

    char result2;
    private static String IP_ADDRESS = "192.168.0.15";
    private static String TAG = "phptest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);

        et_id = findViewById(R.id.etid);
        et_pw = findViewById(R.id.etpw);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = et_id.getText().toString();
                String userPW = et_pw.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/loginRequset/login.php", userID,userPW);
                Log.d("qq",userID+userPW);
            }
        });
        btn_register = (TextView) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLogin.this, RegisterActicity.class);
                startActivity(intent);
            }
        });
    }
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainLogin.this,
                    "Please Wait", null, true, true);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("qq","\n\n"+result);
            progressDialog.dismiss();
            result2 = result.toString().charAt(0);
            Log.d("qq","\n\n"+result2);
            if(result2=='1')
            {
                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainLogin.this, MainMoim.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 다릅니다. ", Toast.LENGTH_LONG).show();
            }
            Log.d(TAG, "POST response  - " + result);
        }
        @Override
        protected String doInBackground(String... params) {
            String userID = (String)params[1];
            String userPW = (String)params[2];

            String serverURL = (String)params[0];
            serverURL = serverURL+"?" + "userID=" + userID + "&userPW=" + userPW ;
            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "GET response code - " + responseStatusCode);

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
                String line = null;

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
}