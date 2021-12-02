package com.example.moim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;


public class RegisterActicity extends AppCompatActivity {

    private static String IP_ADDRESS = "192.168.0.15";
    private static String TAG = "phptest";

    private EditText et_name, et_nik, et_id, et_pw, et_phone, et_email;
    private TextView mTextViewResult;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acticity);

        et_name = (EditText)findViewById(R.id.editName);
        et_nik = (EditText)findViewById(R.id.editNik);
        et_id = (EditText)findViewById(R.id.editID);
        et_pw = (EditText)findViewById(R.id.editPw);
        et_phone = (EditText)findViewById(R.id.editPhone); //id 찾기
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
                task.execute("http://" + IP_ADDRESS + "/loginrequset/insert.php", userName,userNik,userID,userPW,userPhone,userEmail);

                name = et_name.getText().toString();
                et_nik.setText("");
                et_id.setText("");
                et_pw.setText("");
                et_phone.setText("");
                et_email.setText("");



                Toast.makeText(getApplicationContext(), ""+name +" 님의 회원가입이 완료 되었습니다.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RegisterActicity.this, MainLogin.class);
                startActivity(intent);

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