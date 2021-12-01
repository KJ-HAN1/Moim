package com.example.moim;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText id, pw;
    Button sign_in, sign_up;

    char result2;
    private static String IP_ADDRESS = "192.168.0.128";
    private static String TAG = "phptest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.ID);
        pw = findViewById(R.id.PW);

        sign_in = (Button)findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ID = id.getText().toString();
                String PW = pw.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/login.php", ID,PW);
                Log.d("qq",ID+PW);
            }
        });
        sign_up = (Button)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, user_signupActivity.class);
                startActivity(intent);
            }
        });
    }
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,
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
                Intent intent = new Intent(MainActivity.this, MainView.class);
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
            String ID = (String)params[1];
            String PW = (String)params[2];

            String serverURL = (String)params[0];
            serverURL = serverURL+"?" + "ID=" + ID + "&PW=" + PW ;
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

/*
public class RegisterActicity extends AppCompatActivity {












    private static String IP_ADDRESS = "192.168.0.15";
    private static String TAG = "phptest";

    private EditText et_name;
    private EditText et_nik;
    private EditText et_id;
    private EditText et_pw;
    private EditText et_phone;
    private EditText et_email;
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acticity);

        et_name = (EditText)findViewById(R.id.editName);
        et_nik = (EditText)findViewById(R.id.editNik);
        et_id = (EditText)findViewById(R.id.editID);
        et_pw = (EditText)findViewById(R.id.editPw);
        et_phone = (EditText)findViewById(R.id.editPhone);
        et_email = (EditText)findViewById(R.id.editEmail);

        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());


        Button buttonInsert = (Button)findViewById(R.id.btn_Submit);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = et_name.getText().toString();
                String userNik = et_nik.getText().toString();
                String userID = et_id.getText().toString();
                String userPW = et_pw.getText().toString();
                String userPhone = et_phone.getText().toString();
                String userEmail = et_email.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insert.php", userName, userNik, userID, userPW, userPhone, userEmail);


                et_name.setText("");
                et_nik.setText("");
                et_id.setText("");
                et_pw.setText("");
                et_phone.setText("");
                et_email.setText("");

                finish();
            }

        });

    }



    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(RegisterActicity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String userName = (String)params[1];
            String userNik = (String)params[2];
            String userID = (String)params[3];
            String userPW = (String)params[4];
            String userPhone = (String)params[5];
            String userEmail = (String)params[6];

            String serverURL = (String)params[0];
            String postParameters = "userName=" + userName + "&userNik=" + userNik + "&userID=" + userID + "&userPW=" + userPW + "&userPhone=" + userPhone + "&userEmail=" + userEmail;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
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
