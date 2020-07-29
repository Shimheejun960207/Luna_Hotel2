package com.cookandroid.luna_hotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class Luna_NewAccount2 extends AppCompatActivity {

    // 이거 onCreate 안에 들어가 있길래 제가 빼놨습니다. (김범준)
    Button btn_join, btn_check,btn_lunalogo2,btn_menu2,btn_setting2;
    EditText edit_id,edit_name,edit_password,edit_passwordcheck,edit_ssn,edit_gender,edit_hp1,edit_hp2,edit_hp3,edit_email1,edit_email2,edit_email3;
    CheckBox checkbox_id;

    // 회원가입 시 뜨는 알림 창을 사용하기 위한 AlertDialog
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_newaccount2);

        checkbox_id = (CheckBox) findViewById(R.id.checkbox_id);
        btn_check = (Button) findViewById(R.id.btn_check);
        btn_join = (Button) findViewById(R.id.btn_join);
        btn_lunalogo2 = (Button) findViewById(R.id.btn_lunalogo2);
        btn_menu2 = (Button) findViewById(R.id.btn_menu2);
        btn_setting2 = (Button) findViewById(R.id.btn_setting2);

        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_password = (EditText) findViewById(R.id.edit_password);
        edit_passwordcheck = (EditText) findViewById(R.id.edit_passwordcheck);
        edit_ssn = (EditText) findViewById(R.id.edit_ssn);
        edit_gender = (EditText) findViewById(R.id.edit_gender);
        edit_hp1 = (EditText) findViewById(R.id.edit_hp1);
        edit_hp2 = (EditText) findViewById(R.id.edit_hp2);
        edit_hp3 = (EditText) findViewById(R.id.edit_hp3);
        edit_email1 = (EditText) findViewById(R.id.edit_email1);
        edit_email2 = (EditText) findViewById(R.id.edit_email2);
        edit_email3 = (EditText) findViewById(R.id.edit_email3);


        //영문과 숫쟈 허용
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
                if (!ps.matcher(source).matches()) {
                    return "";
                }
                return null;
            }
        };

        //글자수 16개로 설정
        InputFilter[] filters = new InputFilter[]{
                new InputFilter.LengthFilter(16), filter

        };

        //글자수 6개로 설정
        InputFilter[] filters2 = new InputFilter[]{
                new InputFilter.LengthFilter(6), filter

        };

        //글자수 1개로 설정
        InputFilter[] filters3 = new InputFilter[]{
                new InputFilter.LengthFilter(1), filter

        };

        //글자수 3개로 설정
        InputFilter[] filters4 = new InputFilter[]{
                new InputFilter.LengthFilter(3), filter

        };

        //글자수 4개로 설정
        InputFilter[] filters5 = new InputFilter[]{
                new InputFilter.LengthFilter(4), filter

        };


        // 각 EditText에 글자 수 제한 및 허용되는 문자 설정
        // 이름 적는 칸 빼곤 한글 안되게, 즉 전부 영어랑 숫자만 가능
        edit_id.setFilters(new InputFilter[]{filter});
        edit_id.setFilters(filters);
        edit_password.setFilters(new InputFilter[]{filter});
        edit_password.setFilters(filters);
        edit_passwordcheck.setFilters(new InputFilter[]{filter});
        edit_password.setFilters(filters);
        edit_ssn.setFilters(new InputFilter[]{filter});
        edit_ssn.setFilters(filters2);
        edit_gender.setFilters(new InputFilter[]{filter});
        edit_gender.setFilters(filters3);
        edit_hp1.setFilters(new InputFilter[]{filter});
        edit_hp1.setFilters(filters4);
        edit_hp2.setFilters(new InputFilter[]{filter});
        edit_hp2.setFilters(filters5);
        edit_hp3.setFilters(new InputFilter[]{filter});
        edit_hp3.setFilters(filters5);
        edit_email1.setFilters(new InputFilter[]{filter});
        edit_email1.setFilters(filters);
        edit_email2.setFilters(new InputFilter[]{filter});
        edit_email2.setFilters(filters2);
        edit_email3.setFilters(new InputFilter[]{filter});
        edit_email3.setFilters(filters5);


        //중복확인 클릭시 실행되는 코드
        // 버튼을 누르면 ID 입력하는 곳의 edittext 값이랑 서버의 아이디값을
        // 모두 조회하여 같은 값이 있지 확인
        // 중복이 있다면 "이미 사용중인 아이디입니다" 라는 대화상자 출력
        // 없다면 "사용 가능한 아이디입니다" 대화상자가 출력되고 옆 체크박스에 체크가 되어야함
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String CheckID = edit_id.getText().toString();

                Pattern patternID = Pattern.compile("^[a-z0-9]+$");

                // 아이디 입력을 안하고 중복체크 버튼을 누를 시 뜨는 알림창
                if(CheckID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("아이디를 먼저 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_id.requestFocus();
                    return;
                }

                // 아이디에 대문자나 특수문자, 한글이 들어가 있을 시 뜨는 알림창
                if(!patternID.matcher(CheckID).matches()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("아이디는 영어 소문자 + 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_id.requestFocus();
                    return;
                }

                // 아이디 길이 제한, 4 이상 16 이하
                if((CheckID.length() < 4 || (CheckID.length() > 16))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("아이디는 4글자 이상, 16글자 이하만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_id.requestFocus();
                    return;
                }


                // 서버의 PHP로 아이디를 비교하는 과정
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // JSON 오브젝트를 만듬.
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                edit_id.setEnabled(false);
                                btn_check.setText("완료");
                                btn_check.setEnabled(false);
                                checkbox_id.setChecked(true);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                                dialog = builder.setMessage("이미 등록 된 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                            }
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 통해 서버와 통신합니다. 값들을 큐에 넣어 전송합니다.
                ValidateRequest validateRequest = new ValidateRequest(CheckID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Luna_NewAccount2.this);
                queue.add(validateRequest);


                // 여기에는 버튼을  처음에는 체크가 다시 해제되게 만들어야합니다.
                // 예를 들어 shj1996 으로 아이디 중복체크를 하여 체크가되었는데
                // shj1996 을 shj1997 로 고치면 중복체크가 된채로 회원가입이 그냥 진행되기때문입니다
                // checkbox_id.setChecked(false); 를 사용하여 해제를 합니다

                // 여기에는 중복된 아이디가 있으면  대화상자로 사용불가능한 아이디라고 출력이되야합니다.

                // 중복확인을 하여 사용가능한 아이디면 체크박스가 자동 채크됩니다.
            }
        });

        //가입하기 클릭시 실행되는 코드 // DB팀이 여기 코드를 잘짜야함
        // 가입하기 진행시 필요한점
        // 1. 비밀번호와 비밀번호확인 의 두 값이 같은가? O
        // 2. 아이디 중복확인 체크박스가 체크가 되어있는가? O
        // 3. 모든 에디트텍스트에 값이 기입이 되어있는가? O
        // 4. 이메일은 영어로 입력이 되어있는가? O
        // 5.  주민번호는 앞에 6자리를 정상적으로 입력하고 뒷자리는 1,2,3,4 만 입력햇는가? O
        // 6. 핸드폰번호는 앞 3자리  중간4자리 마지막4자리를 입력했는가? O
        // 7.  아이디는 특수문자가 들어가지않고 영문과 숫자조합으로만 되어있는가?
        // 위조건이 모두 충족되어야하며  충족이안되면  해당하는 에러의 대화상자가 출력되어야함.
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 단순 비밀번호 비교에만 사용하기 때문에 분류 해 두었습니다.
                // 데이터베이스로 전송하는 값이 아님!!! 단순 가입하기 진행 시 조건에만 사용
                String userPWC = edit_passwordcheck.getText().toString();


                // 단순 이메일 영어입력 확인에만 사용하기 때문에 분류 해 두었습니다.
                // 데이터베이스로 전송하는 값이 아님!!! 단순 가입하기 진행 시 조건에만 사용
                String userEmail1 = edit_email1.getText().toString();
                String userEmail2 = edit_email2.getText().toString();
                String userEmail3 = edit_email3.getText().toString();


                // 단순 전화번호 글자 수 제한에만 사용하기 때문에 분류 해 두었습니다.
                // 데이터베이스로 전송하는 값이 아님!!! 단순 가입하기 진행 시 조건에만 사용
                String userHP1 = edit_hp1.getText().toString();
                String userHP2 = edit_hp2.getText().toString();
                String userHP3 = edit_hp3.getText().toString();


                // 아이디 문자열 제한을 걸기 위한 패턴
                Pattern patternID = Pattern.compile("^[a-z0-9]+$");

                // 이메일 문자열 제한을 걸기 위한 패턴
                Pattern patternEmail = Pattern.compile("^[a-z0-9]+$");

                // 주민번호 앞 자리 문자열 제한을 걸기 위한 패턴
                Pattern patternSsn = Pattern.compile("^[0-9]+$");

                // 휴대폰 번호 문자열 제한을 걸기 위한 패턴
                Pattern patternHP = Pattern.compile("^[0-9]+$");


                // EditText에 있는 값을 String 값으로 가져옵니다.
                // 밑의 userID, userName, userPW, userSsn, userGender, userHP, userEmail
                // 이 값들이 데이터베이스로 전송되는 값입니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                String userID = edit_id.getText().toString();
                String userName = edit_name.getText().toString();
                String userPW = edit_password.getText().toString();
                String userSsn = edit_ssn.getText().toString();
                String userGender = edit_gender.getText().toString();
                String userHP = (edit_hp1.getText().toString()) + (edit_hp2.getText().toString()) + (edit_hp3.getText().toString());
                String userEmail = (edit_email1.getText().toString()) + "@" + (edit_email2.getText().toString()) + "." + (edit_email3.getText().toString());


                // 다음은 가입하기 클릭시 실행되는 조건에 대한 코드입니다.
                // 순서가 뒤죽박죽인 이유는 위에 위치한 코드가 먼저 실행되므로 부득이하게 뒤죽박죽이 되었습니다...

                // 3번. 모든 에디트텍스트에 값이 기입이 되어있는가? : 공백일 경우 뜨는 알림창
                if(userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("아이디를 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_id.requestFocus();
                    return;
                }

                if(userName.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("이름을 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_name.requestFocus();
                    return;
                }

                if(userPW.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("비밀번호를 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_password.requestFocus();
                    return;
                }

                if(edit_passwordcheck.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("비밀번호가 서로 일치하지 않습니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_passwordcheck.requestFocus();
                    return;
                }

                if(userSsn.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("주민번호를 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_ssn.requestFocus();
                    return;
                }

                if(userGender.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("주민번호를 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_gender.requestFocus();
                    return;
                }

                if(userHP.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("핸드폰 번호를 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_hp1.requestFocus();
                    return;
                }

                if(userEmail.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("이메일 주소를 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_email1.requestFocus();
                    return;
                }





                // 2번. 아이디 중복확인 체크박스가 체크가 되어있는가? : 체크박스 체크 여부 확인
                if(!checkbox_id.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("아이디 중복확인을 해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }





                // 1번. 비밀번호와 비밀번호확인 의 두 값이 같은가? : 비밀번호 비교
                if(!userPW.equals(userPWC)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("비밀번호가 서로 일치하지 않습니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_passwordcheck.requestFocus();
                    return;
                }





                // 7번. 아이디는 특수문자가 들어가지않고 영문과 숫자조합으로만 되어있는가? : 아이디 문자열 제한
                if(!patternID.matcher(userID).matches()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("아이디는 영어 소문자 + 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_id.requestFocus();
                    return;
                }

                if((userID.length() < 4 || (userID.length() > 16))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("아이디는 4글자 이상, 16글자 이하만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_id.requestFocus();
                    return;
                }





                // 5번. 주민번호는 앞에 6자리를 입력하고 뒷자리는 1,2,3,4만
                if(!(userSsn.length() == 6)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("주민번호 앞 자리는 6글자만 입력 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_ssn.requestFocus();
                    return;
                }

                if(!patternSsn.matcher(userSsn).matches()) {    // 이건 주민번호 앞 자리 숫자만 가능하게
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("주민번호 앞 자리는 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_email1.requestFocus();
                    return;
                }

                if(!(userGender.equals("1") || userGender.equals("2") || userGender.equals("3") || userGender.equals("4"))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("주민번호 뒷 자리 시작은 1,2,3,4만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_gender.requestFocus();
                    return;
                }





                // 6번. 핸드폰번호는 앞 3자리 중간 4자리 마지막 4자리를 입력했는가? : 글자 수 제한
                if(!(userHP1.length() == 3)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("핸드폰 번호 앞 자리는 3글자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_hp1.requestFocus();
                    return;
                }

                if(!patternHP.matcher(userHP1).matches()) {    // 이건 핸드폰 번호 숫자만 가능하게
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("핸드폰 번호는 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_hp1.requestFocus();
                    return;
                }

                if(!(userHP2.length() == 4)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("핸드폰 번호 중간 자리는 4글자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_hp2.requestFocus();
                    return;
                }

                if(!patternHP.matcher(userHP2).matches()) {    // 이건 핸드폰 번호 숫자만 가능하게
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("핸드폰 번호는 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_hp2.requestFocus();
                    return;
                }

                if(!(userHP3.length() == 4)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("핸드폰 번호 마지막 자리는 4글자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_hp3.requestFocus();
                    return;
                }

                if(!patternHP.matcher(userHP3).matches()) {    // 이건 핸드폰 번호 숫자만 가능하게
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("핸드폰 번호는 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_hp3.requestFocus();
                    return;
                }





                // 4번. 이메일은 영어로 입력이 되어있는가? : 허용 가능한 문자열만 패턴에 저장 후 비교
                if(!patternEmail.matcher(userEmail1).matches()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("이메일은 영어 소문자 + 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_email1.requestFocus();
                    return;
                }

                if(!patternEmail.matcher(userEmail2).matches()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("이메일은 영어 소문자 + 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_email2.requestFocus();
                    return;
                }

                if(!patternEmail.matcher(userEmail3).matches()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Luna_NewAccount2.this);
                    dialog = builder.setMessage("이메일은 영어 소문자 + 숫자만 가능합니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    edit_email3.requestFocus();
                    return;
                }





                // gotoDatabase 메소드를 호출합니다. gotoDatabase 메소드는 onCreate 밑에 있습니다!
                gotoDatabase(userID, userName, userPW, userSsn, userGender, userHP, userEmail);



                Intent Home_Intent = new Intent(getApplicationContext(), Luna_Subscription.class);
                startActivity(Home_Intent);

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

        // 설정버튼 클릭 메소드
        btn_setting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Setting_Intent = new Intent(getApplicationContext(),Luna_Setting.class);
                startActivity(Setting_Intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
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


    // gotoDatabase 함수 구현
    private void gotoDatabase(String userID, String userName, String userPW, String userSsn, String userGender, String userHP, String userEmail) {

        // InsertData 클래스 선언, AsyncTask를 이용해 백그라운드에서 인터넷 통신으로 값을 넘김
        class InsertData extends AsyncTask<String, Void, String> {

            // pre 형식 실행 시
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            // POST 형식 실행 시
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            // Background에서 해당 코드 실행
            // String link에 서버 주소 값을 저장한 후, 서버 html 폴더 안에 있는 Register.php를 읽어옴
            // Register.php 안에 있는 SQL 쿼리를 통해 값을 저장하게 됨.
            @Override
            protected String doInBackground(String... params) {

                try {
                    // String 값들을 배열에 넣습니다.
                    String userID = (String) params[0];
                    String userName = (String) params[1];
                    String userPW = (String) params[2];
                    String userSsn = (String) params[3];
                    String userGender = (String) params[4];
                    String userHP = (String) params[5];
                    String userEmail = (String) params[6];

                    // 서버 안에 있는 Register.php의 주소를 link 안에 저장합니다.
                    String link = "http://52.78.74.201/Register.php";

                    // data라는 String 형태 변수 안에 배열의 내용들을 쭉 나열하여 넣습니다.
                    String data = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(userID, "UTF-8");
                    data += "&" + URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
                    data += "&" + URLEncoder.encode("userPW", "UTF-8") + "=" + URLEncoder.encode(userPW, "UTF-8");
                    data += "&" + URLEncoder.encode("userSsn", "UTF-8") + "=" + URLEncoder.encode(userSsn, "UTF-8");
                    data += "&" + URLEncoder.encode("userGender", "UTF-8") + "=" + URLEncoder.encode(userGender, "UTF-8");
                    data += "&" + URLEncoder.encode("userHP", "UTF-8") + "=" + URLEncoder.encode(userHP, "UTF-8");
                    data += "&" + URLEncoder.encode("userEmail", "UTF-8") + "=" + URLEncoder.encode(userEmail, "UTF-8");

                    // URLConnection 으로 서버에 접속하고 데이터들을 전송합니다.
                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch(Exception e) {
                    return new String("Exception : " + e.getMessage());
                }
            }
        }

        // 백그라운드에서 실행할 수 있게 InsertData 클래스의 인스턴스를 생성하여 execute로 실행합니다.
        InsertData task = new InsertData();
        task.execute(userID, userName, userPW, userSsn, userGender, userHP, userEmail);

    }

}
