package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class StartFind extends AppCompatActivity {
    CSVReader data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_find);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data =  bundle.getParcelable("list");
    }

    private void knowClick(){
        Intent intent = new Intent(StartFind.this, EnterWo.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void dontKnowClick(){
        Intent intent = new Intent(StartFind.this, EnterBase.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void logoutClick(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.know_num:
                knowClick();
                break;
            case R.id.dont_know_num:
                dontKnowClick();
                break;
            case R.id.log_out:
                logoutClick();
                break;
        }
    }
}
