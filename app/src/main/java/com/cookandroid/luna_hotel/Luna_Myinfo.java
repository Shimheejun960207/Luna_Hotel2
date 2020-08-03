package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_Myinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_myinfo);

        ImageView image_gender;
        image_gender = (ImageView) findViewById(R.id.image_gender);

        TextView text_uesr_name,text_uesr_id,text_uesr_email,text_uesr_phone;
        text_uesr_email = (TextView)findViewById(R.id.text_uesr_email); // 유저의 이메일 담는곳
        text_uesr_id = (TextView)findViewById(R.id.text_uesr_id);     // 유저의 아이디
        text_uesr_phone = (TextView)findViewById(R.id.text_uesr_phone); //유저의 폰번호
        text_uesr_name= (TextView)findViewById(R.id.text_uesr_name);   // 유저의 이름


        LinearLayout lay_reser_check,lay_edit_profile,lay_change_password,lay_remove_member;

        lay_reser_check = (LinearLayout)findViewById(R.id.lay_reser_check);
        lay_edit_profile = (LinearLayout)findViewById(R.id.lay_edit_profile);
        lay_change_password = (LinearLayout)findViewById(R.id.lay_change_password);
        lay_remove_member = (LinearLayout)findViewById(R.id.lay_remove_member);


        Button btn_back,btn_lunalogo;
        btn_lunalogo = (Button)findViewById(R.id.btn_lunalogo);
        btn_back = (Button)findViewById(R.id.btn_back);


        // image_gender 사용설명
        // 젠더 값  -> 주민번호 뒷자리 1자리 값이 1 or 3이면
        // image_gender.setImageResource(R.drawable.man); 프로필사진 남자로

        // 젠더 값  -> 주민번호 뒷자리 1자리 값이 2 or 4 이면
        // image_gender.setImageResource(R.drawable.lady); // 프로필사진 여자로



        // 비밀번호 변경 이벤트
        lay_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Change_Password.class);
                startActivity(intent);
            }
        });

        // 정보수정 이벤트
        lay_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Edit_Profile.class);
                startActivity(intent);

            }
        });

        // 회원탈퇴 이벤트
        lay_remove_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Member_Remove.class);
                startActivity(intent);
            }
        });

        // 예약확인
        lay_reser_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Reservation_Check.class);
                startActivity(intent);
            }
        });

        //로고클릭
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
