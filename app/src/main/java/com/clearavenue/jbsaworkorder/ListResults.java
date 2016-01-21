package com.clearavenue.jbsaworkorder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ListResults extends AppCompatActivity {


    TextView selectedBase;
    TextView selectedLocation;
    TextView selectedPartner;
    String workOrder;

    private ListView resultList;

    private int resultListSize;

    ArrayList<SearchResults> noDup;
    List<DummyData> results = new ArrayList<>();

    CSVReader data = new CSVReader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data =  bundle.getParcelable("list");

        selectedBase = (TextView) findViewById(R.id.selectedBase);
        selectedLocation = (TextView) findViewById(R.id.selectedLocation);
        selectedPartner = (TextView) findViewById(R.id.selectedPartner);

        selectedBase.append(data.getBase());
        selectedPartner.append(data.getPartner());
        selectedLocation.append(data.getLocation());
        resultList = (ListView) findViewById(R.id.listView);

        initResultSelector();

        resultList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                workOrder = results.get(position).getWorkOrderNumber();
            }
        });
    }

    public void initResultSelector() {
        results = data.getResults(this);
        resultList.setAdapter(new CustomListAdapter(this, results));

    }
    private void nextClick() {
        Intent intent = new Intent(ListResults.this, FoundResult.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        intent.putExtra("key", data.getResult(this,workOrder));
        startActivity(intent);
    }

    private void backClick() {
        Intent intent = new Intent(ListResults.this, EnterPartner.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("list", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button_listresult:
                nextClick();
                break;
            case R.id.back_button_listresult:
                backClick();
                break;
        }
    }
}
