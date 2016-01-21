package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class FoundResult extends AppCompatActivity {

    CSVReader data = new CSVReader();
    TextView title;
    TextView initDate;
    TextView woNumber;
    TextView facID;
    TextView cost;
    ImageView pizzaTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_result);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data =  bundle.getParcelable("list");

        DummyData order = getIntent().getExtras().getParcelable("key");

        title = (TextView) findViewById(R.id.textView11);
        assert order != null;
        title.append(order.getWorkOrderTitle() + "");

        initDate = (TextView) findViewById(R.id.textView9);
        initDate.append(order.getDateOpened()+"");

        woNumber = (TextView) findViewById(R.id.textView6);
        woNumber.append(order.getWorkOrderNumber());

        facID = (TextView)findViewById(R.id.textView7);
        facID.append(order.getFacilityIDNumber());

        cost = (TextView) findViewById(R.id.textView10);
        cost.append(order.getTotalCost() + "");

        pizzaTracker = (ImageView) findViewById(R.id.imageView5);
        switch(order.getPizzaTrackerCode()){
            case 1:
                pizzaTracker.setImageResource(R.drawable.tracker_one);
                break;
            case 2:
                pizzaTracker.setImageResource(R.drawable.tracker_two);
                break;
            case 3:
                pizzaTracker.setImageResource(R.drawable.tracker_three);
                break;
            case 4:
                pizzaTracker.setImageResource(R.drawable.tracker_four);
                break;
            case 5:
                pizzaTracker.setImageResource(R.drawable.tracker_five);
                break;
            case 6:
                pizzaTracker.setImageResource(R.drawable.tracker_six);
                break;
        }
    }

    private void homeClick(){
        Intent intent = new Intent(FoundResult.this, StartFind.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void backClick(){
        finish();
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button_foundresult:
                backClick();
                break;
            case R.id.home_button_foundresult:
                homeClick();
                break;

        }
    }
}
