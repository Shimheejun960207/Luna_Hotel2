package com.cookandroid.luna_hotel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class Luna_Reservation_pay extends AppCompatActivity {



    private  int hotel_number = 0; // 호텔 지점 번호입니다


    // 너가 디비로 보내야 할 변수들 목록임 너 입맞에 맞게 가져가셈

    private int tnrqkr = 0; // 숙박 몇박 변수
    private String put_checkin =null; // 예약한 날짜를 보낼 변수 ex 20200417
    private String put_checkout=null; // 예약한 날짜를 보낼 변수  ex 20200418
    private int roomNum = 0; // 방번호

    private  String date_s_in,month_s_in,year_s_in; //  체크인 날짜 변환하는 변수  date 2자리 month 2자리 year 4자리임
    private  String date_s_out,month_s_out,year_s_out; //  체크인 날짜 변환하는 변수  date 2자리 month 2자리 year 4자리임

    private  int date_int = 0;  // 이건 필요업는듯이제

    private int total_price = 0;   // 총가격
    private int room_price = 0;   // 방가격
    private String room_name = null; // 선택한 방이름
    private String hotel_name = null; // 선택한 호텔 지점 이름
    private  String total_price_string = null; // 스트링형 돈 변수 ex) 250.000 (. 표시 되게 담은거)

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_reservation_pay);

        // 이전화면에서 보낸 데이터 불러오기
        Intent intent = getIntent();
        tnrqkr = intent.getIntExtra("tnrqkr",0);
        date_int = intent.getIntExtra("date_int",0);
        //체크인 변수
        date_s_in = intent.getStringExtra("date_s_in");
        month_s_in = intent.getStringExtra("month_s_in");
        year_s_in = intent.getStringExtra("year_s_in");
        put_checkin = intent.getStringExtra("put_checkin");
        // 체크아웃 변수
        date_s_out = intent.getStringExtra("date_s_out");
        month_s_out = intent.getStringExtra("month_s_out");
        year_s_out = intent.getStringExtra("year_s_out");
        put_checkout = intent.getStringExtra("put_checkout");
        // 지점 호텔 번호를 불러온다.
        hotel_number = intent.getIntExtra("hotel_number",0);
        // 객실 번호 불러온다
        roomNum = intent.getIntExtra("roomNum",0);

        Button  btn_lunalogo,btn_back,btn_setting, btn_next;
        btn_lunalogo = (Button)findViewById(R.id.btn_lunalogo);
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_setting = (Button)findViewById(R.id.btn_setting);
        btn_next = (Button) findViewById(R.id. btn_next);

        final TextView text_checkin_mmdd,text_checkin_yy,text_checkout_mmdd,text_checkout_yy,text_tnrqkr,text_total,text_room_name,text_hotel_name;
        text_checkin_mmdd = (TextView) findViewById(R.id.text_checkin_mmdd);
        text_checkout_mmdd = (TextView) findViewById(R.id.text_checkout_mmdd);
        text_checkout_yy = (TextView) findViewById(R.id.text_checkout_yy);
        text_checkin_yy = (TextView) findViewById(R.id.text_checkin_yy);
        text_tnrqkr = (TextView) findViewById(R.id.text_tnrqkr);
        text_total = (TextView)findViewById(R.id.text_total) ;
        text_room_name = (TextView)findViewById(R.id.text_room_name) ;
        text_hotel_name = (TextView)findViewById(R.id.text_hotel_name) ;


        final LinearLayout lay_card;
        lay_card = (LinearLayout) findViewById(R.id.lay_card);

        final Spinner spn_card_name,spn_pay_name;
        spn_card_name = (Spinner)findViewById(R.id.spn_card_name);
        spn_pay_name = (Spinner)findViewById(R.id.spn_pay_name);

        //뒤로버튼 클릭 메소드
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        // 메인화면으로 가는 로고버튼튼
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Home_Intent = new Intent(getApplicationContext(),Luna_Main.class);
                startActivity(Home_Intent);
            }
        });



        // 날짜 표시하는곳에 텍스트 담기.
        text_checkin_mmdd.setText(month_s_in + "월" + date_s_in + "일");
        text_checkin_yy.setText(year_s_in + "년");
        text_checkout_mmdd.setText(month_s_out + "월" + date_s_out + "일");
        text_checkout_yy.setText(year_s_out + "년");
        text_tnrqkr.setText(tnrqkr + "박");

        // 방번호를 체크해서 방이름을 부여한다

        if (roomNum == 1) // 싱글룸
        {
            room_name = "Single Room";
            room_price = 150000;
            text_room_name.setText("- " + room_name + " -");
        }
        else if (roomNum == 2) // 더블룸
        {
            room_name = "Double Room";
            room_price = 200000;
            text_room_name.setText("- " + room_name + " -");
        }
        else if (roomNum == 3) // 패밀리
        {
            room_name = "Family Room";
            room_price = 250000;
            text_room_name.setText("- " + room_name + " -");
        }
        else  // 럭셔리
        {
            room_name = "Luxury Room";
            room_price = 300000;
            text_room_name.setText("- " + room_name + " -");
        }


        // 호텔번호를 체크해서 호텔이름을 부여한다
        if (hotel_number == 1) // 서울
        {
            hotel_name = "호텔 루나 서울";

            text_hotel_name.setText("- " +  hotel_name + " -");
        }
        else if (hotel_number == 2) // 부산
        {
            hotel_name = "호텔 루나 부산";

            text_hotel_name.setText("- " +  hotel_name + " -");
        }
        else if (hotel_number == 3) // 제주
        {
            hotel_name = "호텔 루나 제주";

            text_hotel_name.setText("- " +  hotel_name + " -");
        }
        else  // 속초
        {
            hotel_name = "호텔 루나 속초";

            text_hotel_name.setText("- " +  hotel_name + " -");
        }


        // 결제 방식 선택
        spn_pay_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int number, long l) {
                DecimalFormat formatter = new DecimalFormat("###,###"); //회계표시 실질적으로 적용 시는 문장
                //  String formattedStringPrice = formatter.format(result); //회계표시 적용
                // Money.setText(formattedStringPrice); // 결제금액 변경
                if (number == 1) // 삼성페이
                {

                    total_price = (room_price * tnrqkr) / 10 * 9;
                    // 회계 표시 를한다
                    String formattedStringPrice = formatter.format(total_price);
                    // 총 가격 텍스트뷰에 담는다.
                    text_total.setText(formattedStringPrice);
                    lay_card.setVisibility(View.INVISIBLE);
                    // 스트링 변수에 총액 담기
                    total_price_string = text_total.getText().toString();


                }
                else if (number == 3) //신용카드 선택
                {

                    total_price = (room_price * tnrqkr);
                    // 회계 표시 를한다
                    String formattedStringPrice = formatter.format(total_price);
                    // 총 가격 텍스트뷰에 담는다.
                    text_total.setText(formattedStringPrice);
                    lay_card.setVisibility(View.VISIBLE);
                    total_price_string = text_total.getText().toString();
                    // 카드선택 이벤트
                    spn_card_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
                 else
                {

                    total_price = (room_price * tnrqkr);
                    // 회계 표시 를한다
                    String formattedStringPrice = formatter.format(total_price);
                    // 총 가격 텍스트뷰에 담는다.
                    text_total.setText(formattedStringPrice);
                    lay_card.setVisibility(View.INVISIBLE);
                    total_price_string = text_total.getText().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        //다음으로 클릭 이벤트 1. 예약확인 물어봄. 2. 예약됬다고 뜸 -> 메인으로 이동
    btn_next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Luna_Reservation_pay.this);
            builder.setMessage("예약 하시겠습니까?");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // 범준아 여기에 예약 DB 넣으면된다. 변수는 위에서 봐라 주석달았다.



                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_Reservation_pay.this);
                    builder.setMessage("예약되셨습니다. ");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), Luna_Main.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            builder.setNegativeButton("취소",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    });



    }


    // 취소버튼 누를때 생기는 애니메이션
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
