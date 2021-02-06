package com.rafiq.recordaudio.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rafiq.recordaudio.R;
import com.rafiq.recordaudio.Utility.RunTimePermission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Runtime Permission checking
        if (!RunTimePermission.checkRequiredPermissions(this)){
            RunTimePermission.displayWhenNeverAskAgainDialog(this);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ManualRecord.class));
            }
        });
    }
}
