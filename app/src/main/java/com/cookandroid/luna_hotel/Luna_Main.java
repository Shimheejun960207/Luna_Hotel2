package com.cookandroid.luna_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Luna_Main extends AppCompatActivity {

    // 취소버튼 두번누르면 종료되는 코드 실행을 위한 객체 선언입니다.
    private long backKeyPressedTime = 0;
    private Toast toast;

    Button btn_hotel, btn_hotelreser, btn_menu, btn_lunalogo, btn_setting, btn_room, btn_map, btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_main);

        btn_call = (Button) findViewById(R.id.btn_call);
        btn_hotel= (Button)findViewById(R.id.btn_hotel);
        btn_hotelreser = (Button) findViewById(R.id.btn_hotelreser);
        btn_lunalogo = (Button) findViewById(R.id.btn_lunalogo);
        btn_map = (Button) findViewById(R.id.btn_map);
        btn_menu = (Button) findViewById(R.id.btn_menu);
        btn_setting = (Button) findViewById(R.id.btn_setting);
        btn_room = (Button)findViewById(R.id.btn_room);


        // 메뉴버튼 클릭시 실행되는 코드입니다
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_menu.class);
                startActivity(Intent);
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });


        // 로고 클릭시 실행되는 코드입니다 근데 메인화면이라 이거 코딩안해도되요 그냥 형식상해놨어요
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        // 설정버튼 클릭시 실행되는 코드입니다.
        btn_setting.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent Setting_Intent = new Intent(getApplicationContext(),Luna_Setting.class);
               startActivity(Setting_Intent);
               overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
           }
        });


        // 호텔소개 버튼을 클릭시 실행되는 코드입니다.
        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HotelInfo_Intent = new Intent(getApplicationContext(),Luna_Info_Hotel.class);
                startActivity(HotelInfo_Intent);
            }
        });


        // 예약하기 클릭시 실행되는 코드입니다.
        // 로그인이 안되어있다면 로그인해주세요 라는 대화상자가 출력되야 합니다.
        btn_hotelreser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Login_gloval.login_id == null) {    // 널값이면 로그인이 필요하다는 뜻입니다
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_Main.this);
                    builder.setMessage("로그인이 필요합니다.");

                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), Luna_Login.class);
                            startActivity(intent);
                       }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
               } else {
                    Intent HotelInfo_Intent = new Intent(getApplicationContext(), Luna_Reservation_Room.class);
                    startActivity(HotelInfo_Intent);
               }
            }
        });


        // 객실안내 클릭시 실행되는 코드입니다.
        btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RoomInfo_Intent = new Intent(getApplicationContext(),Luna_Info_Room.class);
                startActivity(RoomInfo_Intent);
            }
        });


        //오시는길 클릭시 실행되는 코드입니다
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MapInfo_Intent = new Intent(getApplicationContext(),Luna_Map.class);
                startActivity(MapInfo_Intent);
            }
        });


        // 전화하기 클릭시 실행되는 코드입니다.
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CallIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-3826-6515"));
                startActivity(CallIntent);
            }
        });
    }


    // 취소버튼 동작 코드입니다
    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishAffinity();
            System.runFinalization();
            System.exit(0);
        }
    }
}
