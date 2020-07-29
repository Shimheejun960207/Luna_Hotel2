package com.cookandroid.luna_hotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Luna_Subscription extends AppCompatActivity {

    Button btn_login, btn_lunalogo2, btn_menu2, btn_setting2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_subscription);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_lunalogo2 = (Button) findViewById(R.id.btn_lunalogo2);
        btn_menu2 = (Button) findViewById(R.id.btn_menu2);
        btn_setting2 = (Button) findViewById(R.id.btn_setting2);

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


        // 로그인 클릭시 실행되는 코드입니다.
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), Luna_Login.class);
                startActivity(Intent);
            }
        });
    }


    // 예약하기와 마찬가지로 뒤로가기를 누르면 회원정보가 그대로 남아있어서
    // 데이터베이스에 무리가 갈 것이 예측되므로 뒤로가기를 막아뒀습니다!
    @Override
    public void onBackPressed() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Subscription.this);
        dlg.setMessage("이 화면을 벗어나면 로그인 화면으로 이동됩니다.");
        dlg.setPositiveButton("이동", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent Home_Intent = new Intent(getApplicationContext(), Luna_Login.class);
                startActivity(Home_Intent);
            }
        });
        dlg.setNegativeButton("취소",null);
        dlg.show();
    }
}
