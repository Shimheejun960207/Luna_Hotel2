package com.cookandroid.luna_hotel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class Login_gloval extends Application {

    // 전역변수 쓰는 클래스입니다
    // 메니페스트 파일 에플리케이션 태그에 android name 에 클래스 이름 추가해야 전역변수 사용할 수 있습니다.

    public static String login_id;  // 아이디 전역변수 입니다.
    public static String login_password; // 비밀번호 전역변수 입니다.
    public static String Login_resName;  // 예약확인 할 때 예약자 명을 불러오기 위한 전역변수 입니다.
    public static String Login_userProfile; // 카카오 프로필
    public static String Login_Email; // 카카오 이메일
    public static int Login_kakao;  //카카오 로그인 유무 변수

// 추가한부분

    public static int pageNum = 0;
    // 페이지 변수이며 내정보와 예약확인 이동 버그를 잡는데 쓰입니다.

    //카카오 API
    // 카카오 API 코드
    private static volatile Login_gloval instance = null;
    private static volatile Activity currentActivity = null;



    private static class KakaoSDKAdapter extends KakaoAdapter {
        // 카카오 로그인 세션을 불러올 때의 설정값을 설정하는 부분.
        public ISessionConfig getSessionConfig() {

            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
                    //로그인을 어떤 방식으로 할지 지정
                    //KAKAO_LOGIN_ALL: 모든 로그인방식을 사용하고 싶을때 지정.
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                    // SDK 로그인시 사용되는 WebView에서 pause와 resume시에 Timer를 설정하여 CPU소모를 절약한다.
                    // true 를 리턴할경우 webview로그인을 사용하는 화면서 모든 webview에 onPause와 onResume 시에 Timer를 설정해 주어야 한다.
                    // 지정하지 않을 시 false로 설정된다.
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                    // 로그인시 access token과 refresh token을 저장할 때의 암호화 여부를 결정한다.
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                    // 일반 사용자가 아닌 Kakao와 제휴된 앱에서만 사용되는 값으로, 값을 채워주지 않을경우 ApprovalType.INDIVIDUAL 값을 사용하게 된다.
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                    // Kakao SDK 에서 사용되는 WebView에서 email 입력폼의 데이터를 저장할지 여부를 결정한다.
                    // true일 경우, 다음번에 다시 로그인 시 email 폼을 누르면 이전에 입력했던 이메일이 나타난다.
                }
            };
        }



        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return Login_gloval.getGlobalApplicationContext();
                }
            };
        }
    }




    public static Login_gloval getGlobalApplicationContext() {
        if(instance == null) {
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }





}
