package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static com.clearavenue.jbsaworkorder.R.id.locationSpinner;
import static com.clearavenue.jbsaworkorder.R.id.partnerSpinner;

public class EnterPartner extends AppCompatActivity {

    private Spinner partnerList;
    private int partnerListSize;
    CSVReader data = new CSVReader();
    List<String> partners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_partner);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data =  bundle.getParcelable("list");

        partnerList = (Spinner) findViewById(partnerSpinner);

        initPartnerSelector();

        partnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                data.setPartner(partners.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    public void initPartnerSelector(){
        partners = data.getPartners(this);

        partners.add("Partner");
        partnerListSize = partners.size() - 1;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                partners){
            @Override
            public int getCount() {
                return partnerListSize;
            }
        };
        partnerList.setAdapter(arrayAdapter);
        partnerList.setSelection(partnerListSize);
    }

    private void nextClick(){
        Intent intent = new Intent(EnterPartner.this, ListResults.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);    }

    private void backClick(){
        Intent intent = new Intent(EnterPartner.this, EnterLocation.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_button_enterpartner:
                nextClick();
                break;
            case R.id.back_button_enterpartner:
                backClick();
                break;

        }
    }
}
