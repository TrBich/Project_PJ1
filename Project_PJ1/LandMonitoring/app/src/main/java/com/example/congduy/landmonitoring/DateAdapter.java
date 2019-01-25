package com.example.congduy.landmonitoring;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateAdapter extends ArrayAdapter<Date> {
    private List<Date> items;
    SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DateAdapter(@NonNull Context context, int resource, @NonNull List<Date> objects) {
        super(context, resource, objects);
        this.items = objects;
    }


    public String getTextSelected(int position){
        if(position< 0 || position >= items.size()) return "";
        return mDateFormat.format(items.get(position));
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public Date getItem(int position) {

        return items.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_spinner, parent, false);
        }

        Date item = getItem(position);
        ((TextView)convertView.findViewById(R.id.tvItem)).setText(mDateFormat.format(item));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
