package com.example.moim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://localhost/Register.php";
    private Map<String, String> map;


    public RegisterRequest(String userName, String userNik, String userID, String userPW, String userPhone, String userEmail , Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userName",userName);
        map.put("userNik", userNik);
        map.put("userID", userID);
        map.put("userPW", userPW);
        map.put("userPhone", userPhone);
        map.put("userEmail", userEmail);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}