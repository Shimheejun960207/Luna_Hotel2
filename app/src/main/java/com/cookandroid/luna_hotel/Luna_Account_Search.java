package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_Account_Search extends AppCompatActivity {

    Button  btn_bottom_login, btn_id_search, btn_back, btn_lunalogo2,btn_password_search;
    EditText edit_id_name, edit_id_email,edit_password_email,edit_password_id,edit_password_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_account_search);

        btn_lunalogo2 = (Button)findViewById(R.id.btn_lunalogo2);
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_bottom_login = (Button) findViewById(R.id.btn_bottom_login);
        btn_id_search = (Button) findViewById(R.id.btn_id_search);
        btn_password_search = (Button)findViewById(R.id.btn_password_search);
        edit_id_email = (EditText) findViewById(R.id.edit_id_email);
        edit_id_name = (EditText) findViewById(R.id.edit_id_name);


        // 로고버튼 이벤트입니다.
        btn_lunalogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Main.class);
                startActivity(Intent);
            }
        });

        // 좌측 상단 뒤로가기버튼 클릭시 창이 꺼짐 + 애니메이션 이벤트
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        // 하단버튼 로그인 버튼 이벤트
        btn_bottom_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Login.class);
                startActivity(Intent);
            }
        });


        // 아이디 찾기 버튼이며  이름과 이메일 값을 디비로 조회하여 아이디를 찾는다
        // 조회된 값이 없다면 대화상자로   "조회된 아이디가 없습니다." 라고 출력한다
        // 값이 있다면 대화상자로 "조회된 아이디는 **** 입니다." 라고 출력한다.
        btn_id_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 비밀번호 찾기 버튼이며  이름과 이메일 아이디 값을 디비로 조회하여 아이디를 찾는다
        // 조회된 값이 없다면 대화상자로   "조회된 값이 없습니다." 라고 출력한다
        // 값이 있다면 대화상자로 "조회된 비밀번호는 **** 입니다." 라고 출력한다.
        btn_password_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
