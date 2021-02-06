package com.rafiq.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rafiq.retrofit.pojo.Carcompany;

import java.util.List;

/**
 * Created by RAFIQ on 4/11/2018.
 */

public class Adapter extends BaseAdapter{
    List<Carcompany> companies;
    Context context;
    private static LayoutInflater inflater = null;

    public Adapter(Context mainActivity, List<Carcompany> c) {
        companies = c;
        context = mainActivity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return companies.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView;
        listItemView = inflater.inflate(R.layout.list_item, null);

        //Set the name of company in the list item textview
        TextView name = listItemView.findViewById(R.id.company_name);
        name.setText(companies.get(position).getCompanyName());
        return listItemView;
    }
}
