package com.cookandroid.luna_hotel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.AlteredCharSequence;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class Luna_Info_Room extends AppCompatActivity {

    Button btn_menu, btn_lunalogo2, btn_res;
    TextView text_luxury_room,text_single_room;
    TextView text_info_luxury,text_info_single;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_info_room);

        text_single_room= (TextView) findViewById(R.id.text_single_room);
        text_luxury_room = (TextView) findViewById(R.id.text_luxury_room);
        text_info_luxury = (TextView) findViewById(R.id.text_info_luxury);
        text_info_single = (TextView) findViewById(R.id.text_info_single);


        btn_menu = (Button)findViewById(R.id.btn_menu);
        btn_lunalogo2 = (Button) findViewById(R.id.btn_lunalogo2);
        btn_res = (Button)findViewById(R.id.btn_res);


        // 네모칸에 텍스트뷰가 가장먼저 앞으로 표시되게 하는 메소드입니다.
        text_luxury_room.bringToFront();
        text_single_room.bringToFront();

        text_info_luxury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 싱글룸 자세히 보기 이벤트
        text_info_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 예약하기 버튼 클릭 메소드
        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Login_gloval.login_id == null) {    // 널값이면 로그인이 필요하다는 뜻입니다
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_Info_Room.this);
                    builder.setMessage("로그인이 필요합니다.");

                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), Luna_Login.class);
                            startActivity(intent);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent HotelInfo_Intent = new Intent(getApplicationContext(), Luna_Reservation_Room.class);
                    startActivity(HotelInfo_Intent);
                }
            }
        });


        //메뉴버튼 클릭 메소드
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_menu.class);
                startActivity(Intent);
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
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
