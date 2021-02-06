package com.rafiq.androidphpmysql;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by RAFIQ on 2/18/2018.
 */

public class Adapter extends ArrayAdapter {
    Context context;
    String[] ID;
    String[] Name;
    String[] Email;

    public Adapter(Context c,String[] id, String[] name, String[] email) {
        super(c, R.layout.single_row, name);
        this.context = c;
        this.ID = id;
        this.Name = name;
        this.Email = email;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        MyViewHolder holder = null;
        if(row==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
            Log.d("Adapter","Creating new row");
        }
        else
        {
            holder = (MyViewHolder) row.getTag();
            Log.d("Adapter","Recycling");
        }

        TextView txID = row.findViewById(R.id.userID);
        TextView txName = row.findViewById(R.id.userName);
        TextView txEmail = row.findViewById(R.id.userEmail);

        //holder.imageView.setImageResource(images[position]);
        //holder.title.setText(title_array[position]);
        //holder.desc.setText(description_array[position]);

        holder.userID.setText(ID[position]);
        holder.userName.setText(Name[position]);
        holder.userEmail.setText(Email[position]);

        return row;
    }
}
