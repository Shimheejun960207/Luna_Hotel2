package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Luna_Map_Seoul extends AppCompatActivity implements OnMapReadyCallback {
   // 지도 변수 선언
    GoogleMap gMap;
    MapFragment mapFrag;
    Button btn_lunalogo, btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_map_seoul);

        btn_lunalogo = (Button)findViewById(R.id.btn_lunalogo);
        btn_menu = (Button)findViewById(R.id.btn_menu);


        // 지도 변수 대입
        mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.googlemap);
        mapFrag.getMapAsync(this);




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
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
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
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.510690, 127.002029),17));

        // marker 표시
        // market 의 위치, 타이틀, 짧은설명 추가 가능.
        MarkerOptions marker = new MarkerOptions();
        marker .position(new LatLng(37.510690, 127.002029))
                .title("호텔 루나 서울 ")
                .snippet("Hotel Luna -Seoul-");
        googleMap.addMarker(marker).showInfoWindow();
    }
}