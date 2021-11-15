package com.example.moim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.moim.Fragment.FragBoard;
import com.example.moim.Fragment.FragFriend;
import com.example.moim.Fragment.FragRoom;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.core.Tag;

public class MainMoim extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Fragment fragment_friend;
    Fragment fragment_room;
    Fragment fragment_board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_moim);

        fragment_friend = new FragFriend();
        fragment_room = new FragRoom();
        fragment_board = new FragBoard();

        bottomNavigationView = findViewById(R.id.bottomNav);

        //처음 화면 fragment_friend로 나오게 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment_friend).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView;

        bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Log.i(TAG, "바텀 네비게이션 클릭");

                switch(item.getItemId()){
                    case R.id.friend:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment_friend).commitAllowingStateLoss();
                        return true;

                    case R.id.room:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment_room).commitAllowingStateLoss();
                        return true;

                    case R.id.board:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment_board).commitAllowingStateLoss();
                        return true;

                }
                return true;
            }
        });
    }
}