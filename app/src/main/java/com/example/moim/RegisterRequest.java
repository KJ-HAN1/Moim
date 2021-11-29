package com.example.moim;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://10.0.2.2:8080/Register.php"; //10.0.2.2는 다른컴퓨터에도 똑같이 URL사용
    private Map<String, String> parameters;


    public RegisterRequest(String userName, String userNik, String userID, String userPW, String userPhone, String userEmail ,String userGender ,String userMajor,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userName",userName);
        parameters.put("userNik", userNik);
        parameters.put("userID", userID);
        parameters.put("userPW", userPW);
        parameters.put("userPhone", userPhone + "" );
        parameters.put("userEmail", userEmail);
        parameters.put("userGender", userGender);
        parameters.put("userMajor", userMajor);




    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}