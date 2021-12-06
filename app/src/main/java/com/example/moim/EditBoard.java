package com.example.moim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moim.Fragment.FragBoard;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EditBoard extends AppCompatActivity {

    private static String IP_ADDRESS = "192.168.0.15";
    private static String TAG = "board";

    private EditText et_title, et_content;
    private TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_board);

        et_title = (EditText)findViewById(R.id.title_et);
        et_content = (EditText)findViewById(R.id.content_et);


        t = (TextView)findViewById(R.id.textv);



        t.setMovementMethod(new ScrollingMovementMethod());


        Button buttonInsert = (Button)findViewById(R.id.btn_submitBoard);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_title.getText().toString();
                String content = et_content.getText().toString();


                EditBoard.Insert task = new EditBoard.Insert();
                task.execute("http://" + IP_ADDRESS + "/insertboard/insertboard2.php", name, content);

                et_title.getText().toString();
                et_content.setText("");



                Toast.makeText(getApplicationContext(), "글 작성 완료.", Toast.LENGTH_LONG).show();
                finish();

                /*Intent intent = new Intent(EditBoard.this, FragBoard.class);
                startActivity(intent);*/

            }
        });

    }



    class Insert extends AsyncTask<String, Void, String> {
        ProgressDialog progressDia;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDia = ProgressDialog.show(EditBoard.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDia.dismiss();
            t.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String name = (String)params[1];
            String content = (String)params[2];


            String serverURL = (String)params[0];
            String postParameters = "name=" + name + "&content=" + content;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(500);
                httpURLConnection.setConnectTimeout(500);
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
                Log.d(TAG, "Insert: Error ", e);
                return new String("Error: " + e.getMessage());
            }

        }
    }


}