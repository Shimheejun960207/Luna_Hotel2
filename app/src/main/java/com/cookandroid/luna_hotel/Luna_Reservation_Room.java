package com.cookandroid.luna_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Luna_Reservation_Room extends AppCompatActivity {

    Button btn_next, btn_lunalogo2, btn_back;
    RadioButton Rbtn_single, Rbtn_luxury,Rbtn_double;
    TextView text_info_luxury,text_info_double,text_info_single,text_info_family;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_reservation_room);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_lunalogo2 = (Button) findViewById(R.id.btn_lunalogo2);
        btn_back = (Button) findViewById(R.id.btn_back);

        text_info_luxury = (TextView) findViewById(R.id.text_info_luxury);
        text_info_single = (TextView) findViewById(R.id.text_info_single);
        text_info_family = (TextView) findViewById(R.id.text_info_family);
        text_info_double = (TextView) findViewById(R.id.text_info_double);
        Rbtn_single = (RadioButton) findViewById(R.id.Rbtn_single);
        Rbtn_luxury = (RadioButton) findViewById(R.id.Rbtn_luxury);
        Rbtn_double = (RadioButton) findViewById(R.id.Rbtn_double);

        // 버튼 모두 비활성화
        Rbtn_single.setChecked(false);
        Rbtn_luxury.setChecked(false);
        Rbtn_double.setChecked(false);

        // 럭셔리룸 자세히 보기 이벤트
        text_info_luxury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Info_Luxury.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

            }
        });
        // 패밀리룸 자세히 보기이벤트
        text_info_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Info_Family.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });



        // 싱글룸 자세히 보기 이벤트
        text_info_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Info_Single.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

            }
        });
        // 더블룸 자세히보기 이벤트
        text_info_double.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Luna_Info_Double.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

            }
        });


        // 아무것도 선택안되어 있는 상태의 다음으로 이벤트
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Room.this);
                dlg.setMessage("객실을 선택해 주세요.");
                dlg.setPositiveButton("확인",null);
                dlg.show();
            }
        });


        // 싱글룸 버튼 선택
        Rbtn_single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isCheckd) {
                if(isCheckd){
                    Rbtn_luxury.setChecked(false);
                    Rbtn_double.setChecked(false);
                     // 럭셔리 버튼은 비활성화
                    // 싱글룸 변수는 1이다
                    final int roomNum = 1;
                    btn_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Intent = new Intent(getApplicationContext(), Luna_Reservation_Date.class);
                            Intent.putExtra("roomNum",roomNum);
                            startActivity(Intent);
                            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        }
                    });
                } else {
                }
            }
        });


        // 럭셔리룸 버튼 선택
        Rbtn_double.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckd) {
                if(isCheckd){
                    Rbtn_single.setChecked(false);
                    Rbtn_luxury.setChecked(false);
                    // 싱글룸 비활성화
                    //더블룸 변수는 2이다
                    final int roomNum = 2;
                    btn_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Intent = new Intent(getApplicationContext(), Luna_Reservation_Date.class);
                            Intent.putExtra("roomNum",roomNum);
                            startActivity(Intent);
                            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        }
                    });
                } else {
                }
            }
        });

        // 럭셔리룸 버튼 선택
        Rbtn_luxury.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckd) {
                if(isCheckd){
                    Rbtn_single.setChecked(false);
                    Rbtn_double.setChecked(false);
                    // 싱글룸 비활성화
                    //럭셔리룸 변수는 4이다
                    final int roomNum = 4;
                    btn_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Intent = new Intent(getApplicationContext(), Luna_Reservation_Date.class);
                            Intent.putExtra("roomNum",roomNum);
                            startActivity(Intent);
                            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        }
                    });
                } else {
                }
            }
        });


// 좌측 상단 뒤로가기버튼 클릭시 창이 꺼짐 + 애니메이션 이벤트
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
