package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_Account_Search extends AppCompatActivity {

    Button  btn_bottom_login, btn_search, btn_menu, btn_lunalogo2;
    TextView text_result_id, text_result_password;
    EditText edit_S_name, edit_S_personNumber, edit_S_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_account_search);

        btn_lunalogo2 = (Button)findViewById(R.id.btn_lunalogo2);
        btn_menu = (Button)findViewById(R.id.btn_menu);
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

        // 메뉴버튼 이벤트입니다
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_menu.class);
                startActivity(Intent);
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
