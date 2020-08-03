package com.cookandroid.luna_hotel;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class Luna_Change_Password extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_change_password);

        Button  btn_back,btn_next;

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_back = (Button) findViewById(R.id.btn_back);

        EditText  edit_password, edit_passwordcheck;
        edit_password = (EditText) findViewById(R.id.edit_password);
        edit_passwordcheck = (EditText) findViewById(R.id.edit_passwordcheck);

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

        //글자수 6개로 설정
        InputFilter[] filters2 = new InputFilter[]{
                new InputFilter.LengthFilter(6), filter

        };

        //글자수 1개로 설정
        InputFilter[] filters3 = new InputFilter[]{
                new InputFilter.LengthFilter(1), filter

        };

        //글자수 3개로 설정
        InputFilter[] filters4 = new InputFilter[]{
                new InputFilter.LengthFilter(3), filter

        };

        //글자수 4개로 설정
        InputFilter[] filters5 = new InputFilter[]{
                new InputFilter.LengthFilter(4), filter

        };

        edit_password.setFilters(new InputFilter[]{filter});
        edit_password.setFilters(filters);
        edit_passwordcheck.setFilters(new InputFilter[]{filter});
        edit_password.setFilters(filters);



        // 좌측 상단 뒤로가기버튼 클릭시 창이 꺼짐 + 애니메이션 이벤트
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        // 변경하기 이벤트
        // 비밀번호랑 비밀번호확인 값 같은지 체크 등등 조건문 작성해서 넘기기
        // 대화상자로 변경묻는거 필요

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
