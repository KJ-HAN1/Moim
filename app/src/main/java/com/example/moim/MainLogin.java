package com.example.moim;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainLogin extends AppCompatActivity {
    private AlertDialog dialog;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);



        TextView registerButton = (TextView)findViewById(R.id.btn_register);
        //버튼이 눌리면 RegisterActivity로 가게함
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainLogin.this, RegisterActicity.class);
                MainLogin.this.startActivity(registerIntent);

            }

        });

        Button button = (Button)findViewById(R.id.temporary);
        //메인 화면 임시 버튼
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Tp = new Intent(MainLogin.this, MainMoim.class);
                MainLogin.this.startActivity(Tp);
            }
        });
        /*


        final EditText editID = (EditText) findViewById(R.id.idText);
        final EditText editPW = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button)findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = editID.getText().toString();
                String userPW = editPW.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainLogin.this);
                                dialog = builder.setMessage("로그인에 성공했습니다")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                Intent intent = new Intent(MainLogin.this, MainMoim.class);
                                MainLogin.this.startActivity(intent);
                                finish();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainLogin.this);
                                dialog = builder.setMessage("계정을 다시 확인하세요")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                                dialog.show();
                                Intent intent = new Intent(MainLogin.this, MainMoim.class);
                                MainLogin.this.startActivity(intent);
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                };

                LoginRequest loginRequest = new LoginRequest(userID, userPW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainLogin.this);
                queue.add(loginRequest);

            }

        });

    }



    @Override

    protected void onStop(){

        super.onStop();

        if(dialog != null){//다이얼로그가 켜져있을때 함부로 종료가 되지 않게함

            dialog.dismiss();

            dialog = null;

        }
*/
    }


}
