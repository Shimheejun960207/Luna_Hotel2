package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class Luna_Info_Room extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_info_room);


        Button btn_menu2, btn_lunalogo2,btn_setting2,btn_res;
        TextView Room_info_text;

        Room_info_text = (TextView)findViewById(R.id.Room_info_text);
        btn_menu2 = (Button)findViewById(R.id.btn_menu2);
        btn_lunalogo2 = (Button) findViewById(R.id.btn_lunalogo2);
        btn_setting2 = (Button)findViewById(R.id.btn_setting2);
        btn_res = (Button)findViewById(R.id.btn_res);




        //메뉴버튼 클릭 메소드
        btn_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_menu.class);
                startActivity(Intent);
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        // 설정버튼 클릭 메소드
        btn_setting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Setting_Intent = new Intent(getApplicationContext(),Luna_Setting.class);
                startActivity(Setting_Intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        // 로고를 누르면 홈화면으로 이동하면 코드
       btn_lunalogo2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent Home_Intent = new Intent(getApplicationContext(), Luna_Main.class);
               startActivity(Home_Intent);
           }
       });
    }

}
