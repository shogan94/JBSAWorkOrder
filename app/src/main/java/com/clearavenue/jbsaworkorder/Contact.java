package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }


    private void homeClick(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButtonContact:
                homeClick();
                break;
        }
    }

}
