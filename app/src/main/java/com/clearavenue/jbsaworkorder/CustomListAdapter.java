package com.clearavenue.jbsaworkorder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by shane.hogan on 1/16/2016.
 */
public class CustomListAdapter  extends BaseAdapter {

    private List<DummyData> searchArrayList;
    private LayoutInflater inflater;

    public CustomListAdapter(Context context, List<DummyData> results){
        searchArrayList = results;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        String title = searchArrayList.get(position).getWorkOrderTitle();
        String wonr = searchArrayList.get(position).getWorkOrderNumber();
        String status = searchArrayList.get(position).getWorkOrderStatus();
        String date = String.valueOf(searchArrayList.get(position).getDateOpened());

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.mylist, null);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.list_WOtitle);
                holder.wonr = (TextView) convertView.findViewById(R.id.list_WONR);
                holder.date = (TextView) convertView.findViewById(R.id.list_WOdate);
                holder.status = (TextView) convertView.findViewById(R.id.list_WOStatus);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(title);
            holder.wonr.setText(wonr);
            holder.date.setText(date);
            holder.status.setText(status);

            return convertView;

    }

    static class ViewHolder {
        TextView title;
        TextView wonr;
        TextView date;
        TextView status;
    }
}
