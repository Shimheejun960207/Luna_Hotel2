package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_Account_Search extends AppCompatActivity {

    Button  btn_bottom_login, btn_search, btn_back, btn_lunalogo2;
    TextView text_result_id, text_result_password;
    EditText edit_S_name, edit_S_personNumber, edit_S_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_account_search);

        btn_lunalogo2 = (Button)findViewById(R.id.btn_lunalogo2);
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_bottom_login = (Button) findViewById(R.id.btn_bottom_login);
        btn_search = (Button) findViewById(R.id.btn_search);

        text_result_id = (TextView) findViewById(R.id.text_result_id);
        text_result_password = (TextView) findViewById(R.id.text_result_password);

        edit_S_Email = (EditText) findViewById(R.id.edit_S_Email);
        edit_S_name = (EditText) findViewById(R.id.edit_S_name);
        edit_S_personNumber = (EditText) findViewById(R.id.edit_S_personNumber);


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


        // 조회 버튼 이벤트 3개의 에디트텍스트 값을 디비서버에 조회하는 코드가 필요함
        // 만약 조회되는 값이 없다면 토스트메시지로 조회된 값이 없다고 떠야함
        // 만약 있다면 ID : + ID이름 과 Password : + 패스워드  형식으로 출력되게 해야함
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
