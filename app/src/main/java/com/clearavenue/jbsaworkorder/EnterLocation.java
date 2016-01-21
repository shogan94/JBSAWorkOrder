package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.csv.CSVRecord;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static com.clearavenue.jbsaworkorder.R.id.base;
import static com.clearavenue.jbsaworkorder.R.id.baseSpinner;
import static com.clearavenue.jbsaworkorder.R.id.locationSpinner;
import static com.clearavenue.jbsaworkorder.R.id.log_out;

public class EnterLocation extends AppCompatActivity {

    private Spinner locationList;
    private TextView selectedBase;
    private int locationListSize;
    List<String> locations = new ArrayList<String>();

    String choice;
    CSVReader data = new CSVReader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_location);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data =  bundle.getParcelable("list");

        selectedBase = (TextView) findViewById(base);
        selectedBase.setText(data.getBase());

        initLocationSelector();
        locationList = (Spinner) findViewById(locationSpinner);

        locationList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.w("myApp", "" + locations.get(position));
                data.setLocation(locations.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    public void initLocationSelector(){

        locations = data.getLocations(this);
        locations.add("Location");
        locationList = (Spinner) findViewById(locationSpinner);

        locationListSize = locations.size() - 1;

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
               locations){
            @Override
            public int getCount() {
                return locationListSize;
            }
            @Override
            public boolean isEnabled(int position) {
                // TODO Auto-generated method stub
                String value = locations.get(position);

                if (!value.equals("Lackland")) {
                    return false;
                }
                return true;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                // TODO Auto-generated method stub
                View mView = super.getDropDownView(position, convertView, parent);
                TextView mTextView = (TextView) mView;
                String value = locations.get(position);
                if (!value.equals("Lackland")) {
                    mTextView.setTextColor(Color.GRAY);
                }
                 else {
                    mTextView.setTextColor(Color.BLACK);
                }
                return mView;
            }
        };

        locationList.setAdapter(arrayAdapter);
        locationList.setSelection(locationListSize);
    }

    private void nextClick(){
        Intent intent = new Intent(EnterLocation.this, EnterPartner.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void backClick(){
        Intent intent = new Intent(EnterLocation.this, EnterBase.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_button_enterlocation:
                nextClick();
                break;
            case R.id.back_button_enterlocation:
                backClick();
                break;
        }
    }

}
