package com.rafiq.intentfilter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by RAFIQ on 3/25/2018.
 */

public class CustomActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        TextView label = findViewById(R.id.show_data);

        Uri url = getIntent().getData();
        label.setText(url.toString());
    }

}
