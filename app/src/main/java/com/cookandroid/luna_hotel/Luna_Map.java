package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Luna_Map extends AppCompatActivity implements OnMapReadyCallback {
   // 지도 변수 선언
    GoogleMap gMap;
    MapFragment mapFrag;
    TextView map_text;
    Button btn_lunalogo2, btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_map);

        btn_lunalogo2 = (Button)findViewById(R.id.btn_lunalogo2);
        btn_menu = (Button)findViewById(R.id.btn_menu);

        map_text = (TextView)findViewById(R.id.map_text);

        // 지도 변수 대입
        mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.googlemap);
        mapFrag.getMapAsync(this);

        // 오시는길에 밑줄 생기는 코드
        SpannableString content = new SpannableString("- 오시는길 -");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        map_text.setText(content);


        //메뉴버튼 메소드
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_menu.class);
                startActivity(Intent);
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        // 로고버튼 클릭 메소드
        btn_lunalogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home_Intent = new Intent(getApplicationContext(),Luna_Main.class);
                startActivity(Home_Intent);
            }
        });
    }


    // 추상메소드 초기화 - 구글맵 초기값 설정 -
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.819509,127.521652),17));

        // marker 표시
        // market 의 위치, 타이틀, 짧은설명 추가 가능.
        MarkerOptions marker = new MarkerOptions();
        marker .position(new LatLng(37.819509, 127.521652))
                .title("호텔 루나")
                .snippet("Hotel Luna");
        googleMap.addMarker(marker).showInfoWindow();
    }
}
