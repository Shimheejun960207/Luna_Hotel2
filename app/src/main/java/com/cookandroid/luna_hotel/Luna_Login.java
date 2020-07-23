package com.cookandroid.luna_hotel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class Luna_Login extends AppCompatActivity {

    Button btn_Login, btn_account_find, btn_Sign_up;
    Button btn_menu,btn_lunalogo,btn_setting;
    EditText user_id, user_pw;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_login);

        btn_Login = (Button) findViewById(R.id.Btn_login);
        user_id = (EditText) findViewById(R.id.Id_info);
        user_pw = (EditText) findViewById(R.id.Password_info);
        btn_account_find = (Button) findViewById(R.id.Btn_account_find);
        btn_Sign_up = (Button) findViewById(R.id.Btn_Sign_Up);
        btn_lunalogo = (Button)findViewById(R.id.btn_lunalogo);
        btn_setting = (Button)findViewById(R.id.btn_setting);
        btn_menu = (Button)findViewById(R.id.btn_menu);


        //하얀색 밑줄
        btn_Login.setText(Html.fromHtml("<font color=#f0f0f0><u>" + "로그인" + "</u></font>"));
        btn_account_find.setText(Html.fromHtml("<font color=#f0f0f0><u>" + "계정찾기" + "</u></font>"));
        btn_Sign_up.setText(Html.fromHtml("<font color=#f0f0f0><u>" + "회원가입" + "</u></font>"));


        //영문과 숫쟈 허용
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
                if (!ps.matcher(source).matches()) {
                    return "";
                }
                return null;
            }
        };

        //글자수 16개로 설정
        InputFilter[] filters = new InputFilter[]{
                new InputFilter.LengthFilter(16), filter

        };

        //아이디 영문과 숫자만 허용, 글자수 제한설정
        user_id.setFilters(new InputFilter[]{filter});
        user_pw.setFilters(filters);

        //패스워드 영문과 숫자만 허용, 글자수 제한설정
        user_pw.setFilters(new InputFilter[]{filter});
        user_pw.setFilters(filters);


        // 메뉴 클릭 이벤트
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_menu.class);
                startActivity(Intent);
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        // 로고클릭 이벤트
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Main.class);
                startActivity(Intent);
            }
        });

        // 설정 클릭 이벤트
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Setting_Intent = new Intent(getApplicationContext(),Luna_Setting.class);
                startActivity(Setting_Intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });




       //로그인 하기 위한 버튼
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID = user_id.getText().toString();
                final String userPW = user_pw.getText().toString();

                // 서버의 PHP로 아이디와 비밀번호를 검색하는 과정
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                                String userID = jsonResponse.getString("userID");
                                String userPW = jsonResponse.getString("userPW");

                                Intent loginintent = new Intent(Luna_Login.this, Luna_menu.class);
                                loginintent.putExtra("userID", userID);
                                loginintent.putExtra("userPW", userPW);
                                Luna_Login.this.startActivity(loginintent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Luna_Login.this);
                                dialog = builder.setMessage("계정이 존재하지 않거나 비밀번호가 일치하지 않습니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                return;
                            }
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 통해 서버와 통신합니다. 값들을 큐에 넣어 전송합니다.
                LoginRequest loginRequest = new LoginRequest(userID, userPW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Luna_Login.this);
                queue.add(loginRequest);

            }
        });


        //계정찾기 하기 위한 버튼
        btn_account_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Intent = new Intent(getApplicationContext(),Luna_Account_Search.class);
                startActivity(Intent);

            }

        });

        //회원가입 하기 위한 버튼
        btn_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Setting_Intent = new Intent(getApplicationContext(),Luna_NewAccount.class);
                startActivity(Setting_Intent);

            }

        });
    }
    @Override
    public void onBackPressed(){


        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

    }
}
