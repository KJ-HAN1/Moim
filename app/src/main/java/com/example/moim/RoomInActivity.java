package com.example.moim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.moim.Fragment.FragRoom;

public class RoomInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_in);

        TextView back = (TextView) findViewById(R.id.btn_backroom);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}