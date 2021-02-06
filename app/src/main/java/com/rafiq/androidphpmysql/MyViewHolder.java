package com.rafiq.androidphpmysql;

import android.view.View;
import android.widget.TextView;

/**
 * Created by RAFIQ on 2/18/2018.
 */

public class MyViewHolder {

    TextView userID;
    TextView userName;
    TextView userEmail;

    public MyViewHolder(View v){
        userID = v.findViewById(R.id.userID);
        userName = v.findViewById(R.id.userName);
        userEmail = v.findViewById(R.id.userEmail);
    }
}
