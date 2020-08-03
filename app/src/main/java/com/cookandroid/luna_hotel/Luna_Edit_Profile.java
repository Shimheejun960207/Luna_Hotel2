package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class Luna_Edit_Profile extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_edit_profile);

        Button btn_next,btn_lunalogo,btn_back;
        EditText edit_name,edit_hp1,edit_hp2,edit_hp3,edit_email1,edit_email2,edit_email3;

        btn_lunalogo = (Button)findViewById(R.id.btn_lunalogo);
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_next= (Button)findViewById(R.id.btn_next);

        edit_hp1 = (EditText) findViewById(R.id.edit_hp1);
        edit_hp2 = (EditText) findViewById(R.id.edit_hp2);
        edit_hp3 = (EditText) findViewById(R.id.edit_hp3);
        edit_email1 = (EditText) findViewById(R.id.edit_email1);
        edit_email2 = (EditText) findViewById(R.id.edit_email2);
        edit_email3 = (EditText) findViewById(R.id.edit_email3);
        edit_name = (EditText) findViewById(R.id.edit_name);

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
        // 각 EditText에 글자 수 제한 및 허용되는 문자 설정
        // 이름 적는 칸 빼곤 한글 안되게, 즉 전부 영어랑 숫자만 가능

        edit_hp1.setFilters(new InputFilter[]{filter});
        edit_hp1.setFilters(filters4);
        edit_hp2.setFilters(new InputFilter[]{filter});
        edit_hp2.setFilters(filters5);
        edit_hp3.setFilters(new InputFilter[]{filter});
        edit_hp3.setFilters(filters5);
        edit_email1.setFilters(new InputFilter[]{filter});
        edit_email1.setFilters(filters);
        edit_email2.setFilters(new InputFilter[]{filter});
        edit_email2.setFilters(filters2);
        edit_email3.setFilters(new InputFilter[]{filter});
        edit_email3.setFilters(filters5);



        // 여기까지 기존 코드 가져온건데 나머지는 내가 잘몰라서 안넣었음
        // 여기에 해야하는 사항들 적어둠
        // 1. 변경전의 기존 유저의 정보가 edittext 에 들어가 있어야함
        // 내 핸드폰번호가 010 3826 6515 라면
        // 회원정보변경 페이지에 들어왔을때 이미 핸드폰번호 에디트 텍스트에 내 번호가 기입되야함
        // 그러고 나서 자유롭게 수정가능하게 한다.
        // 2. 핸드폰 번호 길이 이메일 형식이 맞는지 확인하는 코드 넣어줄것.



        //  변경하기 버튼 이벤트
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //여기에 변경되는 회원정보드 코드들어가야함
                // 추가로 핸드폰 번호 길이 랑 이메일 형식이 맞는 조건문 넣어야함
                // 대화상자로 변경할거냐는 물음이 표시되어야함
                // 성공적으로 적용되면 적용됬다고 대화상자 나오고 다시 내정보로 이동되어야함

            }
        });
        // 로고클릭
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Main.class);
                startActivity(intent);
            }
        });

        // 뒤로가기
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });


    }
}
