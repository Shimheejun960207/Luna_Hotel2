package com.cookandroid.luna_hotel;

import android.app.Application;
import android.content.SharedPreferences;

public class Login_gloval extends Application {

    // 전역변수 쓰는 클래스입니다
    // 메니페스트 파일 에플리케이션 태그에 android name 에 클래스 이름 추가해야 전역변수 사용할 수 있습니다.

    public static String login_id;  // 아이디 전역변수 입니다.
    public static String login_password; // 비밀번호 전역변수 입니다.
    public static String Login_resName;  // 예약확인 할 때 예약자 명을 불러오기 위한 전역변수 입니다.



    @Override
    public void onCreate(){
        super.onCreate();
    }
}
