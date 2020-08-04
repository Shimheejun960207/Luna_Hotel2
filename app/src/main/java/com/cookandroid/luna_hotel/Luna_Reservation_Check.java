package com.cookandroid.luna_hotel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Luna_Reservation_Check extends AppCompatActivity {

    TextView text_reser01, text_name_reser01, text_roomname_reser01, text_date_reser01, text_price_reser01;
    Button btn_back, btn_lunalogo, btn_cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_reservation_check);

        text_date_reser01 = (TextView) findViewById(R.id.text_date_reser01);
        text_name_reser01 = (TextView) findViewById(R.id.text_name_reser01);
        text_price_reser01 = (TextView) findViewById(R.id.text_roomname_reser01);
        text_reser01 = (TextView) findViewById(R.id.text_reser01);
        text_roomname_reser01 = (TextView) findViewById(R.id.text_roomname_reser01);

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_cancle = (Button)findViewById(R.id.btn_cancle);
        btn_lunalogo = (Button)findViewById(R.id.btn_lunalogo);

        // DB서버에서 사용자의 예약정보를 받아옵니다
        // 각각 맞는 텍스트 ID값에 setText() 함수를 사용하여 출력할수 있습니다
        // ex) edit1.setText("dasdasd")
        // 만약에 예약을 하지않았다면
        // 예약01 의 텍스트뷰 내용이  "예약된 정보가 없습니다" 로 변경되야합니다.
        // 그리고 나머지 밑에 4개 텍스트뷰 위젯은 보이지 않아야합니다.
        // 안보이는 코드는 ex) edit.setVisibility(View.INVISIBLE); 입니다.

        //뒤로버튼 클릭 메소드
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });


        // 예약취소 버튼 이벤트이며 대화상자가 출력됩니다.
        btn_cancle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder dlg = new AlertDialog.Builder(Luna_Reservation_Check.this); //  대화상자 생성
               dlg.setTitle("예약취소"); // 대화상자 제목
               dlg.setMessage("취소하면 다시 복구할 수 없습니다"); // 대화상자 내용

               // 확인을 누르면 예약이 취소된다 여기에 예약테이블의 칼럼이 삭제되어야한다.
               dlg.setNegativeButton("취소",null); // 취소버튼이며 누르면 아무일도 일어나지않음
               dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() { // 확인 버튼이며 누르면 해당 코드실행
                   @Override
                   public void onClick(DialogInterface dialogInterface, int which) {
                       Toast applyToast = Toast.makeText(Luna_Reservation_Check.this,"예약이 취소되었습니다..",Toast.LENGTH_SHORT);
                       applyToast.show();
                       finish();
                   }
               });
               dlg.show();
           }
        });


        // 로고버튼 이벤트
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent Intent = new Intent(getApplicationContext(),Luna_Main.class);
               startActivity(Intent);
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
