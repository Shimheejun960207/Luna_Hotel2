package com.cookandroid.luna_hotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Luna_Subscription extends AppCompatActivity {

    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_subscription);

        btn_login = (Button) findViewById(R.id.btn_login);






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
