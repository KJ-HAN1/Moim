package com.example.moim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.moim.Fragment.FragBoard;
import com.example.moim.Fragment.FragFriend;
import com.example.moim.Fragment.FragRoom;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    }
}