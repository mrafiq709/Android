package com.rafiq.intentfilter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First intent to use ACTION_VIEW action with correct data
        Button startBrowser_a = findViewById(R.id.start_browser_a);
        startBrowser_a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://www.example.com"));
                startActivity(i);
            }
        });

        // Second intent to use LAUNCH action with correct data
        Button startBrowser_b = findViewById(R.id.start_browser_b);
        startBrowser_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent("com.example.intentfilter.LAUNCH",
                        Uri.parse("http://www.example.com"));
                startActivity(i);
            }
        });

        // Third intent to use LAUNCH action with incorrect data
        Button startBrowser_c = findViewById(R.id.start_browser_c);
        startBrowser_c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent("com.example.intentfilter.LAUNCH",
                        Uri.parse("https://www.example.com"));
                startActivity(i);
            }
        });
    }
}
