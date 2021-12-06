package com.example.moim.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moim.Del_friends;
import com.example.moim.FriendsAdd;
import com.example.moim.R;


public class FragFriend extends Fragment {

    private View view;
    private TextView tv_id;
    private String IDV;
    private String TAG = "프래그먼트";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v  = inflater.inflate(R.layout.frag_friend, container, false); // 프래그먼트에서 id 찾기 위한 함수

        tv_id = (TextView) v.findViewById(R.id.tv_id);



        /* Bundle extra = getArguments();
            if(extra != null){
                IDV = extra.getString("userID");
            }
            tv_id.setText(IDV);*/


        ImageView add = (ImageView) v.findViewById(R.id.addfr);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(getActivity(), FriendsAdd.class);
                startActivity(add);
            }
        });
        ImageView del = (ImageView) v.findViewById(R.id.del);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent del = new Intent(getActivity(), Del_friends.class);
                startActivity(del);
            }
        });



        return v;

    }




}

