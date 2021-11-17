package com.example.moim.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.moim.MainLogin;
import com.example.moim.MainMoim;
import com.example.moim.R;
import com.example.moim.RegisterActicity;
import com.example.moim.RoomInActivity;

import org.w3c.dom.Text;

public class FragRoom extends Fragment{
    private View view;
    private String TAG = "프래그먼트";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        View v = inflater.inflate(R.layout.frag_room, container, false);

        Button enter = (Button) v.findViewById(R.id.btn_enter);
        enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent enter = new Intent(getActivity(), RoomInActivity.class);
                startActivity(enter);
            }
        });

        return v;
    }

}

