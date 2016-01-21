package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }

    private void homeClick(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void backClick(){
        startActivity(new Intent("app.forgotID"));
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_button:
                homeClick();
                break;
            case R.id.back_button:
                backClick();
                break;

        }
    }
}
