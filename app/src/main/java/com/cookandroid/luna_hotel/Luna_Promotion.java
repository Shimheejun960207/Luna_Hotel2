package com.cookandroid.luna_hotel;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Luna_Promotion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luna_promotion);

    }

    // 취소버튼 누를때 생기는 애니메이션
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
