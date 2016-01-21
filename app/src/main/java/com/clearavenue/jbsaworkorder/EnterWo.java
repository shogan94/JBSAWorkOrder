package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public class EnterWo extends AppCompatActivity implements Serializable{

    private EditText baseNumber;

    CSVReader data = new CSVReader();


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsForEmptyValue();
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_wo);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data =  bundle.getParcelable("list");

        baseNumber = (EditText) findViewById(R.id.baseNumberField);
        baseNumber.addTextChangedListener(textWatcher);
        checkFieldsForEmptyValue();

    }

    private void checkFieldsForEmptyValue() {
        Button nextButton = (Button) findViewById(R.id.next_button_enterwo);
        String string = baseNumber.getText().toString();

        if(string.length() != 5){
            nextButton.setEnabled(false);
        }else{
            nextButton.setEnabled(true);
        }
    }

    public DummyData findWo(){
        return data.getResult(this,baseNumber.getText().toString());
    }

    private void nextClick() {
        DummyData foundWorkOrder = findWo();
        if (foundWorkOrder != null) {
            startActivity(new Intent("app.FoundResult").putExtra("key", foundWorkOrder));
        }
        else{
            baseNumber.setError("Work Order number invalid.");
        }
    }

    private void backClick(){
        Intent intent = new Intent(EnterWo.this, StartFind.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_button_enterwo:
                nextClick();
                break;
            case R.id.back_button_enterwo:
                backClick();
                break;
        }
    }
}
