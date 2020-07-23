package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_Member_Remove extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_member_remove);

        Button  btn_back,btn_lunalogo2,btn_remove;
        EditText edit_MR_password;

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_lunalogo2 = (Button)findViewById(R.id.btn_lunalogo2);
        btn_remove = (Button)findViewById(R.id.btn_remove);
        edit_MR_password = (EditText) findViewById(R.id.edit_MR_password);

        // 뒤로가는버튼
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish(); // 창이 종료됩니다.
            }
        });

        btn_lunalogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home_Intent = new Intent(getApplicationContext(),Luna_Main.class);
                startActivity(Home_Intent);
            }
        });



        // 조건문을 통하여 edit_MR_password 값이랑 디비의 회원비빌번호 값이랑 값이 일치하면
        // 해당 회원 칼럼을 삭제하는 코드가 들어가야함
        // 값이 다르면 토스트 메시지로 비밀번호가 일치하지 않는다는 문구가 떠야함
        // 일단은 그냥 버튼만 누르면 창이닫히면서 문구가 뜨게 설정해놓음
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast applyToast = Toast.makeText(Luna_Member_Remove.this,"탈퇴가 완료되었습니다.",Toast.LENGTH_SHORT);
                applyToast.show();
                finish(); // 창이 종료됩니다.
            }
        });


    }

}
