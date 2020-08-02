package com.cookandroid.luna_hotel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Luna_menu extends AppCompatActivity {

    TextView menu_id, menu_email, btn_login_logout,btn_new_acoout,text_hi;
    Button btn_back, btn_res_check, btn_setting, btn_room, btn_hotel_info,btn_myinfo,btn_call,
            btn_news,btn_promotion,btn_faq,btn_chat,btn_clause,btn_establishment,btn_hotel_local_info;

    // 뷰페이저
    private ViewPager viewPager;
    private TextViewPagerAdapter pagerAdapter;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.
    // 여기까지


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_menu);

        // 회원 정보를 읽어오기 위한 SharedPreferences 선언
        SharedPreferences info = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = info.edit();

        menu_id = (TextView) findViewById(R.id.menu_id);
        menu_email = (TextView) findViewById(R.id.menu_email);
        btn_login_logout = (TextView )findViewById(R.id.btn_login_logout);
        btn_new_acoout = (TextView)findViewById(R.id.btn_new_account);
        text_hi = (TextView)findViewById(R.id.text_hi);

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_setting = (Button)findViewById(R.id.btn_setting);
        btn_call = (Button)findViewById(R.id.btn_call);
        btn_myinfo = (Button) findViewById(R.id.btn_myinfo);
        btn_res_check = (Button)findViewById(R.id.btn_res_check);
        btn_hotel_info = (Button)findViewById(R.id.btn_hotel_info);
        btn_room = (Button)findViewById(R.id.btn_room);

        btn_news = (Button)findViewById(R.id.btn_news);
        btn_promotion = (Button)findViewById(R.id.btn_promotion);
        btn_faq = (Button)findViewById(R.id.btn_faq);
        btn_chat= (Button)findViewById(R.id.btn_chat);
        btn_clause = (Button)findViewById(R.id.btn_clause);
        btn_establishment = (Button)findViewById(R.id.btn_establishment);
        btn_hotel_local_info = (Button)findViewById(R.id.btn_hotel_local_info);


        // 엑티비티가 호출됨과 동시에 조건문으로 전역변수의 값여부를 확인합니다
        if (Login_gloval.login_id == null) {    // 널값이면 로그인이 필요하다는 뜻입니다
            btn_new_acoout.setVisibility(View.VISIBLE); // 회원가입 버튼이 보이게함
            text_hi.setVisibility(View.INVISIBLE); // 안녕하세요 문구가 안보이게함
            btn_login_logout.setText("로그인");
            btn_login_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(getApplicationContext(), Luna_Login.class);
                    startActivity(Intent);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
            });
        } else {
            menu_id.setText((info.getString("userName", "")) + "님");
            menu_email.setText(info.getString("userEmail", ""));
            btn_new_acoout.setVisibility(View.INVISIBLE); // 회원가입 버튼이 안보이게함
            text_hi.setVisibility(View.VISIBLE); // 안녕하세요 문구가 보이게함
            btn_login_logout.setText("로그아웃"); // 값이 있다면 로그인이 되어있는 상태입니다
            btn_login_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // 로그아웃 버튼을 누르면 전연변수의 모든 값이 null로 초기화됩니다.
                    Toast accountToast = Toast.makeText(Luna_menu.this,"로그아웃 되셨습니다.",Toast.LENGTH_SHORT);
                    accountToast.show();
                    Login_gloval.login_id =null;
                    Login_gloval.login_password=null;
                    // eatior 이거 뭔지 설명좀
                    editor.clear();
                    editor.commit();

                    Intent goMain = new Intent(getApplicationContext(), Luna_Login.class);
                    startActivity(goMain);

                    finish();
                }
            });
        }

        // 뷰페이저 코드
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new TextViewPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(currentPage == 4) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        // 여기까지

        // 좌측 상단 뒤로가기버튼 클릭시 창이 꺼짐 + 애니메이션 이벤트
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });




        // 예약확인 버튼 이벤트
        // 로그인 여부를 항상 물어봐야함
        // 로그인이 안되어있다면 로그인을 하라는 대화상자가 출력되야함
        btn_res_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그인 여부를 확인허는 if ~ else 문
                if (Login_gloval.login_id == null) {    // 널값이면 로그인이 필요하다는 뜻입니다
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_menu.this);
                    builder.setMessage("로그인이 필요합니다.");
                    builder.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), Luna_Login.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("취소",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent Intent = new Intent(getApplicationContext(),Luna_Reservation_Check.class);
                    startActivity(Intent);
                }
            }
        });
        // 내 정보 버튼 이벤트
        // 로그인 여부를 항상 물어봐야함
        // 로그인이 안되어있다면 로그인을 하라는 대화상자가 출력되야함
        btn_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그인 여부를 확인허는 if ~ else 문
                if (Login_gloval.login_id == null) {    // 널값이면 로그인이 필요하다는 뜻입니다
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_menu.this);
                    builder.setMessage("로그인이 필요합니다.");
                    builder.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), Luna_Login.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("취소",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    // 여기에 내정보인텐트 삽입
                }
            }
        });


        // 환경설정 버튼 이벤트
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Setting.class);
                startActivity(Intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        // 호텔 소개 버튼 이벤트
        btn_hotel_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Info_Hotel.class);
                startActivity(Intent);
            }
        });


        // 객실안내 버튼 이벤트
        btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Info_Room.class);
                startActivity(Intent);
            }
        });

        // 지점 안내
        btn_hotel_local_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 프로모션
        btn_promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // FAQ
        btn_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 전화하기
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tt = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01038266515"));
                startActivity(tt);
            }
        });
        // 시설안내
        btn_establishment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 이용약관
        btn_clause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 1대1 상담
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 공지사항
        btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 회원가입 버튼 이벤트
        btn_new_acoout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_NewAccount.class);
                startActivity(Intent);
            }
        });




    }




    // 취소버튼 누를때 생기는 애니메이션
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }
}
