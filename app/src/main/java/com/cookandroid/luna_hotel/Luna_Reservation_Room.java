package com.cookandroid.luna_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class Luna_Reservation_Room extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_reservation_room);

        final Button btn_next, btn_lunalogo2, btn_menu2;
        final RadioButton Rbtn_single, Rbtn_luxury;

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_lunalogo2 = (Button) findViewById(R.id.btn_lunalogo2);
        btn_menu2 = (Button) findViewById(R.id.btn_menu2);
        Rbtn_single = (RadioButton) findViewById(R.id.Rbtn_single);
        Rbtn_luxury = (RadioButton) findViewById(R.id.Rbtn_luxury);

        // 버튼 모두 비활성화
        Rbtn_single.setChecked(false);
        Rbtn_luxury.setChecked(false);


        // 아무것도 선택안되어 있는 상태의 다음으로 이벤트
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast accountToast = Toast.makeText(Luna_Reservation_Room.this,"객실을 선택해 주세요.",Toast.LENGTH_SHORT);
                accountToast.show();
            }
        });


        // 싱글룸 버튼 선택
        Rbtn_single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isCheckd) {
                if(isCheckd){
                    Rbtn_luxury.setChecked(false); // 럭셔리 버튼은 비활성화
                    // 싱글룸 변수는 1이다
                    final int roomNum = 1;
                    btn_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Intent = new Intent(getApplicationContext(), Luna_Reservation_Date.class);
                            Intent.putExtra("roomNum",roomNum);
                            startActivity(Intent);
                        }
                    });
                }
                else{ }
            }
        });

        // 럭셔리룸 버튼 선택
        Rbtn_luxury.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckd) {
                if(isCheckd){
                    Rbtn_single.setChecked(false); // 싱글룸 비활성화
                    //럭셔리룸 변수는 2이다
                    final int roomNum = 2;
                    btn_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Intent = new Intent(getApplicationContext(), Luna_Reservation_Date.class);
                            Intent.putExtra("roomNum",roomNum);
                            startActivity(Intent);
                        }
                    });
                }
                else{ }
            }
        });

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




    }
}
