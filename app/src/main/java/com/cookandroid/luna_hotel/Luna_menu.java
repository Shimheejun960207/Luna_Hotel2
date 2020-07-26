package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_menu extends AppCompatActivity {

    TextView menu_id, menu_email;
    Button btn_back,btn_reser_p,btn_check,btn_setting_p,btn_hotel_p,btn_room_p,btn_map_p,btn_login_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_menu);

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_check = (Button)findViewById(R.id.btn_check);
        btn_hotel_p = (Button)findViewById(R.id.btn_hotel_p);
        btn_login_logout = (Button)findViewById(R.id.btn_login_logout);
        btn_map_p = (Button)findViewById(R.id.btn_map_p);
        btn_reser_p = (Button)findViewById(R.id.btn_reser_p);
        btn_room_p = (Button)findViewById(R.id.btn_room_p);
        btn_setting_p = (Button)findViewById(R.id.btn_setting_p);


        // 엑티비티가 호출됨과 동시에 조건문으로 전역변수의 값여부를 확인합니다
        if (Login_gloval.login_id == null) // 널값이면 로그인이 필요하다는 뜻입니다
        {
            btn_login_logout.setText("로그인");
            btn_login_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(),Luna_Login.class);
                    startActivity(Intent);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

                }
            });

        }
        else
        {
            btn_login_logout.setText("로그아웃"); // 값이 있다면 로그인이 되어있는 상태입니다
            btn_login_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // 로그아웃 버튼을 누르면 전연변수의 모든 값이 null로 초기화됩니다.
                    Toast accountToast = Toast.makeText(Luna_menu.this,"로그아웃 되셨습니다..",Toast.LENGTH_SHORT);
                    accountToast.show();
                    Login_gloval.login_id =null;
                    Login_gloval.login_password=null;
                    finish();
                }
            });

        }

        // 좌측 상단 뒤로가기버튼 클릭시 창이 꺼짐 + 애니메이션 이벤트
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        //  예약하기 버튼 이벤트
        // 로그인 여부를 항상 물어봐야함
        // 로그인이 안되어있다면 로그인을 하라는 대화상자가 출력되야함
        btn_reser_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 예약확인 버튼 이벤트
        // 로그인 여부를 항상 물어봐야함
        // 로그인이 안되어있다면 로그인을 하라는 대화상자가 출력되야함
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Reservation_Check.class);
                startActivity(Intent);
            }
        });

        // 환경설정 버튼 이벤트
        btn_setting_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Setting.class);
                startActivity(Intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        // 호텔 소개 버튼 이벤트
        btn_hotel_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Info_Hotel.class);
                startActivity(Intent);
            }
        });

        // 오시는길 버튼 이벤트
        btn_map_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Map.class);
                startActivity(Intent);
            }
        });

        // 호텔소개 버튼 이벤트
        btn_room_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Info_Room.class);
                startActivity(Intent);
            }
        });


    }
    // 취소버튼 누를때 생기는 애니메이션
    @Override
    public void onBackPressed(){


        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

    }
}
