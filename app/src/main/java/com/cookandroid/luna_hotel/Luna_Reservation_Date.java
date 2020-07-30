package com.cookandroid.luna_hotel;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Luna_Reservation_Date extends AppCompatActivity {

    // 변수설정
    private int roomNum; // 방번호
    private int room_price = 0;   // 방가격

    // 범준아 여기있는 변수들이 DB 데이터에 넣어야하는 값이다
    private String room_name; // 방이름
    private int total_price = 0;   // 총가격
    private int tnrqkr = 0;  // 몇박인지 알려줌
    private String date_checkin; // 체크인 날짜
    private String date_checkout; // 체크아웃 날짜
    // 여기까지다

    private static final String TAG = "Luna_SelectDate";
    private TextView text_total;

    TextView text_roomname, text_checkin, text_checkout, text_date_result, T_date, T_year, T_month, sale;
    Button btn_checkin, btn_checkout, btn_menu2, btn_lunalogo2, btn_next;
    RadioButton Rbtn_SamsungPay, Rbtn_Cardpay;
    RadioGroup Rbtn_grouppay;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_reservation_date);

        Rbtn_grouppay = (RadioGroup) findViewById(R.id.Rbtn_GroupPay);

        Rbtn_SamsungPay = (RadioButton) findViewById(R.id.Rbtn_SamsungPay);
        Rbtn_Cardpay = (RadioButton) findViewById(R.id.Rbtn_CardPay);

        text_total = (TextView) findViewById(R.id.text_total); // 결제 금액
        sale = (TextView) findViewById(R.id.Sale); // 10% 할인!!

        // 텍스트 밑줄생성
        Rbtn_SamsungPay.setText(Html.fromHtml("<font color=#000000><u><b>" + "삼성페이" + "</b></u></font>"));
        Rbtn_Cardpay.setText(Html.fromHtml("<font color=#000000><u><b>" + "신용카드" + "</b></u></font>"));
        sale.setText(Html.fromHtml("<font color=#7272><u><b>" + "10% 할인!!" + "</b></u></font>"));

        // 날짜 담는 텍스트뷰입니다
        T_date = (TextView) findViewById(R.id.T_date);
        T_month = (TextView) findViewById(R.id.T_month);
        T_year = (TextView) findViewById(R.id.T_year);
        // 여기까지

        btn_checkin = (Button) findViewById(R.id.btn_checkin);
        btn_checkout = (Button) findViewById(R.id.btn_checkout);

        text_checkin = (TextView)findViewById(R.id.text_checkin);
        text_checkout = (TextView)findViewById(R.id.text_checkout);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_lunalogo2 = (Button) findViewById(R.id.btn_lunalogo2);
        btn_menu2 = (Button) findViewById(R.id.btn_menu2);

        text_roomname = (TextView) findViewById(R.id.text_roomname);
        text_date_result = (TextView)findViewById(R.id.text_date_result);


        // 객실선택 데이터 받아와서 그 데이터를 기반으로  선택한 객실의 결과가 나오게함
        Intent intent = getIntent();
        roomNum = intent.getIntExtra("roomNum",0);

        if (roomNum == 1 ) {
            room_name = "Single Room";
            text_roomname.setText("선택 객실 : " + room_name);
            room_price = 150000;
        } else {
            room_name = "Luxury Room";
            text_roomname.setText("선택 객실 : " + room_name);
            room_price = 300000;
        }


        Rbtn_grouppay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                DecimalFormat formatter = new DecimalFormat("###,###"); //회계표시 실질적으로 적용 시는 문장
               //  String formattedStringPrice = formatter.format(result); //회계표시 적용
                // Money.setText(formattedStringPrice); // 결제금액 변경

                if(tnrqkr == 0) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Date.this);
                    dlg.setMessage("먼저 날짜를 선택해주세요.");
                    dlg.setPositiveButton("확인",null);
                    dlg.show();
                    Rbtn_SamsungPay.setChecked(false);
                    Rbtn_Cardpay.setChecked(false);
                } else {
                    if (checkedId == R.id.Rbtn_SamsungPay) {
                        // 몇박몇일 텍스트뷰의  몇박 데이터를 tnrqkr 변수에 담는다
                        // total 변수에  가격*박 * 할인률을 넣는다
                        total_price = (room_price * tnrqkr) / 10 * 9;
                        // 회계 표시 를한다
                        String formattedStringPrice = formatter.format(total_price);
                        // 총 가격 텍스트뷰에 담는다.
                        text_total.setText(formattedStringPrice);
                    } else if (checkedId == R.id.Rbtn_CardPay) {
                        total_price = (room_price * tnrqkr);
                        String formattedStringPrice = formatter.format(total_price);
                        text_total.setText(formattedStringPrice);
                    } else {
                        total_price = (room_price * tnrqkr);
                        String formattedStringPrice = formatter.format(total_price);
                        text_total.setText(formattedStringPrice);
                    }
                }
            }
        });

        //Calendar를 이용하여 년, 월, 일, 시간, 분을 PICKER에 넣어준다.
        final Calendar cal = Calendar.getInstance();

        Log.e(TAG, cal.get(Calendar.YEAR)+"");
        Log.e(TAG, cal.get(Calendar.MONTH)+1+"");
        Log.e(TAG, cal.get(Calendar.DATE)+"");
        Log.e(TAG, cal.get(Calendar.HOUR_OF_DAY)+"");
        Log.e(TAG, cal.get(Calendar.MINUTE)+"");


        // 체크인 달력선택
        btn_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(Luna_Reservation_Date.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%d 년 %d 월 %d 일", year, month+1, date);
                        Toast.makeText(Luna_Reservation_Date.this, msg, Toast.LENGTH_SHORT).show();

                        // 텍스트뷰에 각각 일 월 년 의 데이터를 담는다
                        T_date.setText(String.format("%d",date));
                        T_month.setText(String.format("%d",month+1));
                        T_year.setText(String.format("%d",year));

                        // 정수형 변수에 그 데이터들을 각각 담는다
                        int date_int = Integer.parseInt(T_date.getText().toString());
                        int month_int = Integer.parseInt(T_month.getText().toString());
                        int year_int = Integer.parseInt(T_year.getText().toString());

                        // 스트링형 변수를 선언
                        String date_s,month_s;

                        // 10일 미만 10월 미만 데이터들 앞에 0을 붙여서 스트링으로 변환한다.
                        if(date_int < 10) {
                            date_s = "0"+date_int;
                        } else {
                            date_s = "" + date_int;
                        }

                        if(month_int < 10) {
                            month_s = "0"+ month_int;
                        } else {
                            month_s = "" + month_int;
                        }
                        // 변수3개를 연달아서 텍스트에 담는다

                        text_checkin.setText(year_int + month_s + date_s);

                        // 두날짜의 차이를 구해서 몇박몇일인지 구함
                        String strFormat = "yyyyMMdd";
                        String strStartDate = text_checkin.getText().toString();
                        String strEndDate = text_checkout.getText().toString();

                        //SimpleDateFormat 을 이용하여 startDate와 endDate의 Date 객체를 생성한다.
                        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
                        try{
                            Date startDate = sdf.parse(strStartDate);
                            Date endDate = sdf.parse(strEndDate);

                            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
                            long diffDay = (endDate.getTime() - startDate.getTime()) / (24*60*60*1000);
                            long diffDay2 = diffDay+1;

                            // 잘못된 날짜가 체크되면 실행되는 코드
                            if(diffDay > 0) {
                                if(diffDay2 > 10) {
                                    AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Date.this);
                                    dlg.setMessage("10일 이상은 불가능합니다.");
                                    dlg.setPositiveButton("확인",null);
                                    dlg.show();
                                    text_checkin.setText("");
                                    text_checkout.setText("");
                                }
                                text_date_result.setText("숙박일수 : "+ diffDay +"박" + diffDay2 + "일" );
                                tnrqkr = (int)diffDay;
                                total_price = 0;
                                text_total.setText("");
                            } else {
                                AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Date.this);
                                dlg.setMessage("체크아웃 날짜가 체크인 날짜 보다 앞에 있습니다.");
                                dlg.setPositiveButton("확인",null);
                                dlg.show();

                                text_checkin.setText("");
                                text_checkout.setText("");
                                total_price = 0;
                                text_total.setText("");
                            }
                        }catch(ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMinDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dialog.show();
            }
            }
        ); // 여기 까지 체크인 이벤트


        // 체크아웃 달력선택
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(Luna_Reservation_Date.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String msg = String.format("%d 년 %d 월 %d 일", year, month+1, date);
                        Toast.makeText(Luna_Reservation_Date.this, msg, Toast.LENGTH_SHORT).show();

                        // 텍스트뷰에 각각 일 월 년 의 데이터를 담는다
                        T_date.setText(String.format("%d",date));
                        T_month.setText(String.format("%d",month+1));
                        T_year.setText(String.format("%d",year));

                        // 정수형 변수에 그 데이터들을 각각 담는다
                        int date_int = Integer.parseInt(T_date.getText().toString());
                        int month_int = Integer.parseInt(T_month.getText().toString());
                        int year_int = Integer.parseInt(T_year.getText().toString());

                        // 스트링형 변수를 선언
                        String date_s,month_s;

                        // 10일 미만 10월 미만 데이터들 앞에 0을 붙여서 스트링으로 변환한다.
                        if(date_int < 10) {
                            date_s = "0"+date_int;
                        } else {
                            date_s = "" + date_int;
                        }

                        if(month_int < 10) {
                            month_s = "0"+ month_int;
                        } else {
                            month_s = "" + month_int;
                        }

                        // 변수3개를 연달아서 텍스트에 담는다
                        text_checkout.setText(year_int + month_s + date_s);

                        // 두날짜의 차이를 구해서 몇박몇일인지 구함
                        String strStartDate = text_checkin.getText().toString();
                        String strEndDate = text_checkout.getText().toString();
                        String strFormat = "yyyyMMdd";    //strStartDate 와 strEndDate 의 format

                        //SimpleDateFormat 을 이용하여 startDate와 endDate의 Date 객체를 생성한다.
                        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);

                        try {
                            Date startDate = sdf.parse(strStartDate);
                            Date endDate = sdf.parse(strEndDate);

                            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
                            long diffDay = (endDate.getTime() - startDate.getTime()) / (24*60*60*1000);
                            long diffDay2 = diffDay+1;

                            // 잘못된 날짜가 체크되면 실행되는 코드
                            if(diffDay > 0) {
                                if(diffDay2 > 10) {
                                    AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Date.this);
                                    dlg.setMessage("10일 이상은 불가능합니다.");
                                    dlg.setPositiveButton("확인",null);
                                    dlg.show();

                                    text_checkin.setText("");
                                    text_checkout.setText("");
                                    total_price = 0;
                                    text_total.setText("");
                                }
                                text_date_result.setText("숙박일수 : "+ diffDay +"박" + diffDay2 + "일" );
                                tnrqkr = (int)diffDay;
                            } else {
                                AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Date.this);
                                dlg.setMessage("체크아웃 날짜가 체크인 날짜 보다 앞에 있습니다.");
                                dlg.setPositiveButton("확인",null);
                                dlg.show();

                                text_checkin.setText("");
                                text_checkout.setText("");
                                total_price = 0;
                                text_total.setText("");
                            }
                        } catch(ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMinDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dialog.show();
            }
        });
        // 체크아웃 이벤트 끝


                // 다음으로 버튼 이벤트
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // 날짜선택 안하면 진행 못하게 막음
                        if(text_checkin.length() < 6 || text_checkout.length() < 6) {
                            AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Date.this);
                            dlg.setMessage("날짜를 선택해 주세요.");
                            dlg.setPositiveButton("확인",null);
                            dlg.show();
                        }
                        // 결제 선택 안하면 진행 못하게 막음
                        // total_price 가 0으로 되어 있으면 진행이 불가능
                        else if (total_price < 1) {
                            AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Date.this);
                            dlg.setMessage("결제방식을 선택해 주세요.");
                            dlg.setPositiveButton("확인",null);
                            dlg.show();
                        } else {
                            // 다음화면으로 넘어가짐 여기에 DB코드 넣어야할걸?

                            date_checkin = text_checkin.getText().toString(); // 체크인날짜
                            date_checkout = text_checkout.getText().toString(); // 체크아웃 날짜
                            Intent Intent = new Intent(getApplicationContext(),Luna_Reservation_Complete.class);
                            //  방 넘버, 체크인,체크아웃 날짜, 숙박일 보냄

                            Intent.putExtra("roomNum",roomNum); // 방번호
                            Intent.putExtra("date_checkin",date_checkin); // 체크인날짜
                            Intent.putExtra("date_checkout",date_checkout); // 체크아웃날짜
                            Intent.putExtra("tnrqkr",tnrqkr); // 숙박변수
                            Intent.putExtra("total_price",total_price); // 총 가격 변수
                            Intent.putExtra("room_name",room_name);
                            // 여기까지 복사
                            startActivity(Intent);
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
