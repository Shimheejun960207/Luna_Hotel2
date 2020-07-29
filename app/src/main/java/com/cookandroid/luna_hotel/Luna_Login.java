package com.cookandroid.luna_hotel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Luna_Login extends AppCompatActivity {

    Button btn_Login, btn_account_find, btn_Sign_up, btn_menu, btn_lunalogo, btn_setting;
    EditText user_id, user_pw;

    private String jsonString;
    ArrayList<UserInfo> infoArrayList;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_login);

        btn_Login = (Button) findViewById(R.id.Btn_login);
        btn_account_find = (Button) findViewById(R.id.Btn_account_find);
        btn_Sign_up = (Button) findViewById(R.id.Btn_Sign_Up);
        btn_lunalogo = (Button)findViewById(R.id.btn_lunalogo);
        btn_setting = (Button)findViewById(R.id.btn_setting);
        btn_menu = (Button)findViewById(R.id.btn_menu);

        user_id = (EditText) findViewById(R.id.Id_info);
        user_pw = (EditText) findViewById(R.id.Password_info);

        //하얀색 밑줄
        btn_Login.setText(Html.fromHtml("<font color=#f0f0f0><u>" + "로그인" + "</u></font>"));
        btn_account_find.setText(Html.fromHtml("<font color=#f0f0f0><u>" + "계정찾기" + "</u></font>"));
        btn_Sign_up.setText(Html.fromHtml("<font color=#f0f0f0><u>" + "회원가입" + "</u></font>"));

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

        //아이디 영문과 숫자만 허용, 글자수 제한설정
        user_id.setFilters(new InputFilter[]{filter});
        user_pw.setFilters(filters);

        //패스워드 영문과 숫자만 허용, 글자수 제한설정
        user_pw.setFilters(new InputFilter[]{filter});
        user_pw.setFilters(filters);


        // 메뉴 클릭 이벤트
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_menu.class);
                startActivity(Intent);
                //액티비티 전환 애니메이션 설정하는 부분
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });


        // 로고클릭 이벤트
        btn_lunalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Main.class);
                startActivity(Intent);
            }
        });


        // 설정 클릭 이벤트
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Setting_Intent = new Intent(getApplicationContext(),Luna_Setting.class);
                startActivity(Setting_Intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });


       //로그인 하기 위한 버튼
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID = user_id.getText().toString();
                final String userPW = user_pw.getText().toString();

                // 서버의 PHP로 아이디와 비밀번호를 검색하는 과정
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                Toast.makeText(getApplicationContext(), "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show();

                                String userID = jsonResponse.getString("userID");
                                String userPW = jsonResponse.getString("userPW");

                                Intent loginintent = new Intent(Luna_Login.this, Luna_Main.class);
                                //  전연변수에 로그인에 성공한 아이디와 비밀번호 값을 보낸다.
                                Login_gloval.login_id = userID;
                                Login_gloval.login_password = userPW;
                                // 여기까지.
                                Luna_Login.this.startActivity(loginintent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Luna_Login.this);
                                dialog = builder.setMessage("계정이 존재하지 않거나 비밀번호가 일치하지 않습니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                return;
                            }
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 통해 서버와 통신합니다. 값들을 큐에 넣어 전송합니다.
                LoginRequest loginRequest = new LoginRequest(userID, userPW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Luna_Login.this);
                queue.add(loginRequest);


                // 로그인 직후 DB에 저장되어 있는 회원의 데이터를 가져오기 위한 부분입니다.
                final JsonParse jsonParse = new JsonParse();            // AsyncTask 생성
                jsonParse.execute("http://52.78.74.201/GetInfo.php");   // AsyncTask 실행
            }
        });


        //계정찾기 하기 위한 버튼
        btn_account_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Luna_Account_Search.class);
                startActivity(Intent);
            }

        });


        //회원가입 하기 위한 버튼
        btn_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Setting_Intent = new Intent(getApplicationContext(),Luna_NewAccount.class);
                startActivity(Setting_Intent);
            }

        });
    }


    // 이 밑은 로그인 할 때 로그인 정보로 회원 정보를 가져오는 구문입니다.
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    // AsyncTask 클래스 여기다가 구현.
    public class JsonParse extends AsyncTask<String, Void, String> {
        String TAG = "JsonParse";

        @Override
        protected String doInBackground(String... strings) {
            // execute의 매개변수를 받아와서 사용합니다.
            String url = strings[0];

            try {
                // 따옴표 안의 userID= 부분을 통해 DB에서 해당 아이디로 쿼리를 실행합니다.
                String selectData = "userID=" + user_id.getText().toString();

                // 어플에서 데이터 전송 준비
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) serverURL.openConnection();

                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(selectData.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                // 어플에서 데이터 전송 시작
                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();
                Log.d(TAG, sb.toString().trim());

                // 받아온 JSON의 공백을 trim()으로 제거합니다.
                return sb.toString().trim();
            } catch(Exception e) {
                Log.d(TAG, "InsertData : Error", e);
                String errorString = e.toString();
                return null;
            }
        }

        @Override
        // doInBackgroundString에서 return한 값을 받습니다.
        protected void onPostExecute(String fromdoInBackgroundString) {
            super.onPostExecute(fromdoInBackgroundString);

            if(fromdoInBackgroundString == null) {
                // doInBackgroundString에서 return받은 값이 null값일 경우 에러로 토스트 출력.
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            else {
                // null이 아니라면, 밑 문장들 실행
                jsonString = fromdoInBackgroundString;
                // ArrayList에 값을 넣기 위해 doParse() 메소드 실행.
                // doParse() 메소드는 밑에 onProgressUpdate 부분에 넣음.
                infoArrayList = doParse();

                // 단말기에 간단한 정보를 저장하는 SharedPreferences 객체를 PRIVATE모드로 선언
                SharedPreferences info = getSharedPreferences("info", MODE_PRIVATE);
                // SharedPreferences 를 제어하기 위한 Editor 선언
                SharedPreferences.Editor editor = info.edit();

                // Editor를 이용해서 userCODE, userID, userName... 등등의 이름으로 회원 정보 저장
                editor.putString("userCODE", infoArrayList.get(0).getCODE());
                editor.putString("userID", infoArrayList.get(0).getID());
                editor.putString("userName", infoArrayList.get(0).getName());
                editor.putString("userPW", infoArrayList.get(0).getPW());
                editor.putString("userSsn", infoArrayList.get(0).getSsn());
                editor.putString("userGender", infoArrayList.get(0).getGender());
                editor.putString("userHP", infoArrayList.get(0).getHP());
                editor.putString("userEmail", infoArrayList.get(0).getEmail());

                // Editor로 작업한 내용들을 커밋.
                editor.commit();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        private ArrayList<UserInfo> doParse() {
            // ArrayList에 값을 넣기 위해 임시 ArrayList를 만듬
            ArrayList<UserInfo> tmpArray = new ArrayList<UserInfo>();

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                // PHP 구문을 통해 받은 결과인 result라는 이름의 JSONArray를 받아옴.
                JSONArray jsonArray = jsonObject.getJSONArray("result");

                // 값들을 복사해서 넣는 과정.
                for(int i = 0; i < jsonArray.length(); i++) {
                    UserInfo tmpinfo = new UserInfo();
                    JSONObject item = jsonArray.getJSONObject(i);

                    tmpinfo.setCODE(item.getString("userCODE"));
                    tmpinfo.setID(item.getString("userID"));
                    tmpinfo.setName(item.getString("userName"));
                    tmpinfo.setPW(item.getString("userPW"));
                    tmpinfo.setSsn(item.getString("userSsn"));
                    tmpinfo.setGender(item.getString("userGender"));
                    tmpinfo.setHP(item.getString("userHP"));
                    tmpinfo.setEmail(item.getString("userEmail"));

                    tmpArray.add(tmpinfo);
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }
            // JSON을 가공하여 tmpArray에 넣고 반환.
            return tmpArray;
        }
    }
}
