package com.rafiq.progressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    public int cnt=0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView txtView = (TextView) findViewById(R.id.txtView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (cnt < 100){
                    cnt++;
                    android.os.SystemClock.sleep(50);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(cnt);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtView.setText("Complete Loading !");
                    }
                });
            }
        }).start();
    }
}
