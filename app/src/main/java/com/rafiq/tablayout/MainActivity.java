package com.rafiq.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("সিলেট"));
        tabLayout.addTab(tabLayout.newTab().setText("চট্টগ্রাম"));
        tabLayout.addTab(tabLayout.newTab().setText("ময়মনসিংহ"));

        final ViewPager viewPager = findViewById(R.id.pager);
    }
}
