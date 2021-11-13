package com.example.moim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);

        //MainLogin layout의 registerButton을 눌렀을때 register액티비티로 화면 전환 (code 17~26)
        TextView registerButton = (TextView) findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainLogin.this, RegisterActicity.class);
                MainLogin.this.startActivity(registerIntent);
            }
        });
        //로그인 db 구현전 로그인버튼을 누르면 임시로 화면이동.
        TextView loginButton = (TextView) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainLogin.this, MainMoim.class);
                MainLogin.this.startActivity(registerIntent);

            }
        });
    }
}