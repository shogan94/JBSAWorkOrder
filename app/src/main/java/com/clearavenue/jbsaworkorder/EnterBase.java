package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static com.clearavenue.jbsaworkorder.R.id.baseSpinner;

public class EnterBase extends AppCompatActivity {

    private Spinner baseList;
    private int baseListSize;

    CSVReader data = new CSVReader();
    List<String> bases = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_base);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data =  bundle.getParcelable("list");

        baseList = (Spinner) findViewById(baseSpinner);
        initBaseSelector();
    }

    public void initBaseSelector(){
        bases = data.getBases(this);

        bases.add("Base");
        baseListSize = bases.size() - 1;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                bases){
            @Override
            public int getCount() {
                return baseListSize;
            }
        };

        baseList.setAdapter(arrayAdapter);
        baseList.setSelection(baseListSize);
    }


    private void nextClick() {
        data.setBase("Joint Base San Antonio");
        Intent intent = new Intent(EnterBase.this, EnterLocation.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void backClick(){
        Intent intent = new Intent(EnterBase.this, StartFind.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_button_enterbase:
                nextClick();
                break;
            case R.id.back_button_enterbase:
                backClick();
                break;

        }
    }
}
