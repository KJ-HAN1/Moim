package com.example.moim.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.moim.EditBoard;
import com.example.moim.MainLogin;
import com.example.moim.MainMoim;
import com.example.moim.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragBoard extends Fragment {
    private View view;
    private String TAG = "프래그먼트";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_board, container, false);

        FloatingActionButton edit = (FloatingActionButton) v.findViewById(R.id.fbtn_edit);
        edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getActivity(), EditBoard.class);
                startActivity(edit);
            }
        });


        return v;

    }




}


