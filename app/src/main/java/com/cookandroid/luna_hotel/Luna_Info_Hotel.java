package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_Info_Hotel extends AppCompatActivity {

    Button btn_menu, btn_lunalogo;
    TextView hotel_info_line1, welcome_line1, vision_line1, misson_line1, luna_hotel_line1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_info_hotel);

        btn_menu = (Button)findViewById(R.id.btn_menu);
        btn_lunalogo = (Button) findViewById(R.id.btn_lunalogo);

        hotel_info_line1 = (TextView)findViewById(R.id.Hotel_info_line1);
        welcome_line1 = (TextView)findViewById(R.id.Welcom_line1);
        vision_line1 = (TextView)findViewById(R.id.Vison_line1);
        misson_line1 = (TextView)findViewById(R.id.Misson_line1);
        luna_hotel_line1 = (TextView)findViewById(R.id.LunaHotel_line1);

        //밑줄 색깔 코딩
        welcome_line1.setText(Html.fromHtml("<font color=#540dcb><u>" + "Welcome to the LunaHotel" + "</u></font>"));
        vision_line1.setText(Html.fromHtml("<font color=#540dcb><u>" + "Vision" + "</u></font>"));
        hotel_info_line1.setText(Html.fromHtml("</b><font color=#540dcb><u>" + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + "</u></font></b>"));
        misson_line1.setText(Html.fromHtml("<font color=#540dcb><u>" + "Misson" + "</u></font>"));
        luna_hotel_line1.setText(Html.fromHtml("</b><font color=#540dcb><u>" + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + "</u></font></b>"));


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

        // 로고를 누르면 홈화면으로 이동하는 코드
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home_Intent = new Intent(getApplicationContext(), Luna_Main.class);
                startActivity(Home_Intent);
            }
        });
    }
}

