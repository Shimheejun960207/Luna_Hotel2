package com.cookandroid.luna_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class Luna_NewAccount extends AppCompatActivity {

    Button btn_join, btn_lunalogo2, btn_menu2, btn_setting2;
    RadioButton Rbtn_agree, Rbtn_nagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_newaccount);

        btn_join = (Button) findViewById(R.id.btn_join);
        btn_lunalogo2 = (Button) findViewById(R.id.btn_lunalogo2);
        btn_menu2 = (Button) findViewById(R.id.btn_menu2);
        btn_setting2 = (Button) findViewById(R.id.btn_setting2);

        Rbtn_agree = (RadioButton) findViewById(R.id.Rbtn_agree);
        Rbtn_nagree = (RadioButton) findViewById(R.id.Rbtn_nagree);


        // 아무것도 누르지 않고 진행하면 나오는 이벤트
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast accountToast = Toast.makeText(Luna_NewAccount.this,"약관을 동의해주세요.",Toast.LENGTH_SHORT);
                accountToast.show();
            }
        });


        // 동의 버튼 누르면 동의거절 버튼 비활성화 동의버튼 눌려야만 가입하기 버튼 활성화
        Rbtn_agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckd) {
                if(isCheckd){
                    Rbtn_nagree.setChecked(false);
                    // 다음화면으로 이동하는 코드
                    btn_join.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Intent = new Intent(getApplicationContext(), Luna_NewAccount2.class);
                            startActivity(Intent);
                        }
                    });
                }
            }
        });


        // 동의거절 버튼 누르면 동의버튼 비활성화 가입하기 버튼 비활성화 및 토스트메시지 출력
        Rbtn_nagree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckd) {
                if(isCheckd){
                    Rbtn_agree.setChecked(false);
                    // 동의하지않아 진행 불가능
                    btn_join.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast accountToast = Toast.makeText(Luna_NewAccount.this,"약관을 동의해주세요.",Toast.LENGTH_SHORT);
                            accountToast.show();
                        }
                    });
                }
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