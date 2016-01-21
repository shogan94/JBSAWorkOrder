package com.clearavenue.jbsaworkorder;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.commons.csv.CSVParser;


/**
 * Created by shane.hogan on 1/15/2016.
 */
@SuppressWarnings("ALL")
public class CSVReader extends AppCompatActivity implements com.clearavenue.jbsaworkorder.Reader, Parcelable {

    List<DummyData> allRecords = new ArrayList<DummyData>();
    private String base;
    private String location;
    private String partner;

    public static final Parcelable.Creator<CSVReader> CREATOR = new Parcelable.Creator<CSVReader>(){
        @Override
        public CSVReader createFromParcel(Parcel source){
            return new CSVReader(source);
        }

        @Override
        public CSVReader[] newArray(int size) {
            return new CSVReader[size];
        }

    };

    private CSVReader(Parcel in){
            //allRecords = new ArrayList<DummyData>();
            base = in.readString();
            location = in.readString();
            partner = in.readString();
            in.readList(allRecords, DummyData.class.getClassLoader());

    }

    public CSVReader() {
        this.base = "";
        this.location = "";
        this.partner ="";
        this.allRecords = new ArrayList<DummyData>();
    }


    public void populateRecords(Context c){
        try {
            InputStream iS = c.getAssets().open("dummy_data.csv");
            CSVParser parser = new CSVParser(new InputStreamReader(iS), CSVFormat.DEFAULT.withHeader());

            List<CSVRecord> list = parser.getRecords();
            this.allRecords = (List<DummyData>) CollectionUtils.collect(list, new Transformer() {
                @Override
                public Object transform(Object input
                ) {
                    CSVRecord record = (CSVRecord) input;
                    return new DummyData(record.get("INST"), record.get("ORGN"), record.get("WONR"), record.get("FACIDNR"), record.get("WOTITLE"), record.get("WOSTATUS"), Integer.valueOf(record.get("PIZZATRKRCODE")), Integer.valueOf(record.get("DATE OPENED")), Double.valueOf(record.get("TOTAL COST")));
                }
            });

            parser.close();
        }
        catch (ExceptionInInitializerError | IOException e){
            e.printStackTrace();
        }
        Log.w("app", "populated" + allRecords.get(0).getOrganization());
    }
    public void setBase(String base){
        this.base = base;
    }
    public String getBase(){
        return base;
    }
    public void setPartner(String partner){
        this.partner = partner;
    }
    public String getPartner(){
        return partner;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return  location;
    }

    @Override
    public List<String> getBases(Context c) {
        List<String> bases = new ArrayList<>();
        bases.add("Joint Base San Antonio");
        return bases;
    }

    @Override
    public List<String> getLocations(Context c) {

        List<String> locations;
        locations = (List<String>) CollectionUtils.collect(allRecords, new Transformer() {
            @Override
            public Object transform(Object input) {
                DummyData k = (DummyData) input;
                return k.getInstallation();
            }
        });
        locations = new ArrayList<>(new LinkedHashSet<>(locations));
        return locations;
    }

    @Override
    public List<String> getPartners(Context c) {
        List<String> partners;
//        partners = (List<String>) CollectionUtils.select(allRecords, new Predicate() {
//            @Override
//            public boolean evaluate(Object object) {
//                DummyData records = (DummyData) object;
//                return records.getInstallation().equals(location);
//            }
//        });
        partners = (List<String>) CollectionUtils.collect(allRecords, new Transformer() {
            @Override
            public Object transform(Object input) {
                DummyData records = (DummyData) input;
                if(records.getInstallation().equals(location)){
                    return records.getOrganization();
                }
                return "";
            }
        });
        partners = new ArrayList<>(new LinkedHashSet<>(partners)); //Erase duplicates
        partners.remove(partners.indexOf("")); //Erase empty spot
        return partners;
    }

    @Override
    public List<DummyData> getResults(Context c) {
        List<DummyData> results;
        results = (List<DummyData>) CollectionUtils.select(allRecords, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                DummyData record = (DummyData) object;
                return (record.getInstallation().equals(location) && record.getOrganization().equals(partner));
            }
        });
        return results;
    }

    @Override
    public DummyData getResult(Context c, final String woNumber) {
        DummyData result;
        result = (DummyData) CollectionUtils.find(allRecords, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                DummyData wonr = (DummyData) object;
                return wonr.getWorkOrderNumber().equals(woNumber);
            }
        });
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(base);
        dest.writeString(location);
        dest.writeString(partner);
        dest.writeList(allRecords);
        //dest.writeParcelable(data, flags);
    }

}
