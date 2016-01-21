package com.clearavenue.jbsaworkorder;

import android.content.Context;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by shane.hogan on 1/19/2016.
 */
public interface Reader  {
     List<String> getBases(Context c);
     List<String> getLocations(Context c);
     List<String> getPartners(Context c);
     List<DummyData> getResults(Context c);
     DummyData getResult(Context c, String wonr);
}
