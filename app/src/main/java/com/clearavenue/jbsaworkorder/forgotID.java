package com.clearavenue.jbsaworkorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class forgotID extends AppCompatActivity implements View.OnClickListener{

    private EditText mEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_id);
        mEmailView = (EditText) findViewById(R.id.emailField);

    }

    private void contactSupportClick(){
        startActivity(new Intent("app.Contact"));
    }

    private void attemptSubmit(){
        /**
         * Checks for blank email
         */
       if(mEmailView.getText().toString().trim().equals("")){
           mEmailView.setError("Email Address is required.");
       }
       /**
        * Checks for 'valid' email address (contains @)
        */
       else if(!mEmailView.getText().toString().trim().contains("@")){
           mEmailView.setError("Email Address is invalid.");
       }
        else{
           //Need to add in send email or something to actually send an email... Right now, jsut loads the login page again.
           //Intent i = new Intent(getApplicationContext(), LoginActivity.class);
           //startActivity(i);
           startActivity(new Intent("app.Success"));
       }
    }

    private void homeClick() {
        startActivity(new Intent(this, LoginActivity.class));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contactSupport:
                contactSupportClick();
                break;
            case R.id.submitButton:
                attemptSubmit();
                break;
            case R.id.homeButton:
                homeClick();
                break;
        }
    }


}
