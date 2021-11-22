package com.example.moim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActicity extends AppCompatActivity {
    private EditText et_name, et_nik, et_id, et_pw, et_Phone, et_email;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 시작시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acticity);

        // 아이디 값 찾아주기
        et_id = findViewById(R.id.editID);
        et_nik = findViewById(R.id.editNik);
        et_name = findViewById(R.id.editName);
        et_pw = findViewById(R.id.editPw);
        et_Phone = findViewById(R.id.editPhone);
        et_email = findViewById(R.id.editEmail);

        // 회원가입 버튼 클릭 시 수행
        btn_register = findViewById(R.id.btn_Submit);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.

                String userName = et_name.getText().toString();
                String userNik = et_nik.getText().toString();
                String userID = et_id.getText().toString();
                String userPW = et_pw.getText().toString();
                String userEmail = et_email.getText().toString();

                String userPhone = et_Phone.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                finish();
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                RegisterRequest registerRequest = new RegisterRequest(userName,userNik,userID,userPW,userPhone,userEmail,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActicity.this);
                queue.add(registerRequest);

            }
        });

    }
}