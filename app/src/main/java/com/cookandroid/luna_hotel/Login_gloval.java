package com.cookandroid.luna_hotel;

import android.app.Application;

public class Login_gloval extends Application {

    // 전역변수 쓰는 클래스입니다
    public static String login_id;  // 아이디 전역변수 입니다.
    public static String login_password; // 비밀번호 전역변수 입니다.

    @Override
    public void onCreate(){
        super.onCreate();
    }
}
