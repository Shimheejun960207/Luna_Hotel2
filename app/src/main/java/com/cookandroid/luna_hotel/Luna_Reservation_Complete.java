package com.cookandroid.luna_hotel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class Luna_Reservation_Complete extends AppCompatActivity {



    // 변수선언

    private TextView text_name_info; // 나중에 로그인 정보 가져와서 이름 넣을때 사용
    private TextView text_total;
    private TextView text_subak_info;
    private TextView text_Room_info;


    private int roomNum; // 방번호
    private String room_name; // 방이름
    private int total_price = 0;   // 총가격
    private int tnrqkr = 0;  // 몇박몇일
    private  String date_checkin; // 체크인 날짜
    private  String date_checkout; // 체크아웃 날짜

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_reservation_complete);
        Button btn_menu2, btn_lunalogo2, btn_setting2,btn_main;
        btn_main = (Button) findViewById(R.id.btn_main) ;
        btn_lunalogo2 = (Button) findViewById(R.id.Btn_lunalogo2);
        btn_menu2 = (Button) findViewById(R.id.Btn_menu2);
        text_name_info = (TextView) findViewById(R.id.text_Name_info);
        text_Room_info = (TextView) findViewById(R.id.text_Room_info);
        text_subak_info = (TextView) findViewById(R.id.text_Subak_info);
        text_total = (TextView) findViewById(R.id.text_total);



        Intent intent = getIntent();
         roomNum = intent.getIntExtra("roomNum", 0);
         tnrqkr = intent.getIntExtra("tnrqkr", 0); // 숙박 몇박~
         date_checkin = intent.getStringExtra("date_checkin");
         date_checkout = intent.getStringExtra("date_checkout");
         total_price = intent.getIntExtra("total_price",0); //결제금액


        DecimalFormat formatter = new DecimalFormat("###,###"); // 회계표시
        String formattedStringPrice = formatter.format(total_price); //회계표시 적용

        text_total.setText(formattedStringPrice); //결제금액 화면에 표시

        // 방 번호를 이용하여 싱글룸과 럭셔리룸 스트링값 적용하기
        if (roomNum == 1) {
            room_name = "Single Room";
            text_Room_info.setText(room_name);

        } else {
            room_name = "Luxury Room";
            text_Room_info.setText(room_name);
        }
        // 예약된 날짜와 몇박몇일 출력
        text_subak_info.setText(date_checkin + " ~ " + date_checkout + "  " + tnrqkr + "박" + (tnrqkr + 1) + "일");


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


        // 로고를 누르면 홈화면으로 이동하면 코드
        btn_lunalogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home_Intent = new Intent(getApplicationContext(), Luna_Main.class);
                startActivity(Home_Intent);
            }
        });

        //메인으로 버튼 이벤트
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home_Intent = new Intent(getApplicationContext(), Luna_Main.class);
                startActivity(Home_Intent);
            }
        });



    }

    // 취소버튼 동작 코드입니다
    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Complete.this);
        dlg.setTitle("뒤로 갈 수 없음!!");
        dlg.setMessage("이미 예약이 완료되어 뒤로 갈 수 없습니다. 메인화면으로 이동합니다.");
        dlg.setPositiveButton("이동", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent Home_Intent = new Intent(getApplicationContext(), Luna_Main.class);
                startActivity(Home_Intent);
            }
        });
        dlg.setNegativeButton("취소",null);
        dlg.show();
    }

}

