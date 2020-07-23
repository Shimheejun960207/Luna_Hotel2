package com.cookandroid.luna_hotel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_setting);

        Button btn_back,btn_lunalogo2,btn_member_remove,btn_cash_remove,btn_rnjsgks,btn_apply;
        Switch switch_SMS_adver, switch_SMS_reser;

        btn_apply = (Button) findViewById(R.id.btn_apply);
        btn_cash_remove  = (Button) findViewById(R.id.btn_cash_remove);
        btn_lunalogo2  = (Button) findViewById(R.id.btn_lunalogo2);
        btn_member_remove  = (Button) findViewById(R.id.btn_member_remove);
        btn_back  = (Button) findViewById(R.id.btn_back);
        btn_rnjsgks  = (Button) findViewById(R.id.btn_rnjsgks);

        switch_SMS_adver  = (Switch) findViewById(R.id.switch_SMS_adver);
        switch_SMS_reser  = (Switch) findViewById(R.id.switch_SMS_reser);


        //좌측상단 뒤로가기 버튼 이벤트
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        // 로고클릭 이벤트
        btn_lunalogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home_Intent = new Intent(getApplicationContext(),Luna_Main.class);
                startActivity(Home_Intent);
            }
        });

        //권한설정 버튼  -> os에서 막은거라서 앱의 정보까지는 들어갈수없습니다.
         btn_rnjsgks.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent rnjsgks_Intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                 startActivity(rnjsgks_Intent);
             }
         });

        // 케시삭제 버튼 동작 -> 케시진짜삭제하면 큰일나서 그냥 흉내만 냈어요
         btn_cash_remove.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast CashToast = Toast.makeText(Luna_Setting.this,"케시 삭제 완료",Toast.LENGTH_SHORT);
                 CashToast.show();
             }
         });

        // 광고수신 스위치 토스트메시지 출력
        switch_SMS_adver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){
                    Toast adverToast = Toast.makeText(Luna_Setting.this,"SMS 광고를 수신합니다.",Toast.LENGTH_SHORT);
                    adverToast.show();
                }
                else
                {
                    Toast adverToast = Toast.makeText(Luna_Setting.this,"SMS 광고를 수신하지 않습니다.",Toast.LENGTH_SHORT);
                    adverToast.show();
                }
            }
        });
        // 예약알림 스위치 토스트메시지 출력
        switch_SMS_reser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){
                    Toast reserToast = Toast.makeText(Luna_Setting.this,"SMS 예약 알림을 받습니다.",Toast.LENGTH_SHORT);
                    reserToast.show();
                }
                else
                {
                    Toast reserToast = Toast.makeText(Luna_Setting.this,"SMS 예약 알림을 받지 않습니다.",Toast.LENGTH_SHORT);
                    reserToast.show();
                }
            }
        });

        // 적용버튼 클릭 메소드
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast applyToast = Toast.makeText(Luna_Setting.this,"설정이 적용되었습니다.",Toast.LENGTH_SHORT);
                applyToast.show();
               finish(); // 창이 종료됩니다.
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });




        // 회원 탈퇴 버튼 클릭 이벤트입니다.
        // 누르면 로그인여부부터 확인해야함.
        btn_member_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Member_Remove.class);
                startActivity(Intent);
            }
        });



    }
    // 취소버튼 누를때 생기는 애니메이션
    @Override
    public void onBackPressed(){

        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

    }

}
