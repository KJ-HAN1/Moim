package com.example.moim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    }
}