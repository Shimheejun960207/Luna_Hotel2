package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Luna_Info_Luxury extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_info_luxury);

        TextView text_info;
        Button btn_back,btn_lunalogo;

        text_info = (TextView) findViewById(R.id.text_info);
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_lunalogo = (Button) findViewById(R.id.btn_lunalogo);

        text_info.bringToFront();

        //뒤로버튼 클릭 메소드
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        // 로고를 누르면 홈화면으로 이동하면 코드
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home_Intent = new Intent(getApplicationContext(), Luna_Main.class);
                startActivity(Home_Intent);
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
