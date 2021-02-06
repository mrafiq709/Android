package com.example.myapplication;

import android.os.StrictMode;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "RRR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Log.d(TAG, "onCreate: Before");
        networkCall();
        Log.d(TAG, "onCreate: After");
    }

    private void networkCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<UserListModel> call = service.getUserList();

        try {
            UserListModel result = call.execute().body();
            Log.d(TAG, "networkCall: " + result.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flingIt(View view) {
        // Get a reference to the view
        ImageView emoji = findViewById(R.id.emoji);

        // Pass it to the constructor
        FlingAnimation flingAnimation = new FlingAnimation(emoji, DynamicAnimation.X);

        flingAnimation.setStartVelocity(500f);
        flingAnimation.setFriction(0.5f);
        flingAnimation.start();
    }

    public void bounce(View view) {
        // Get a reference to the view
        final ImageView emoji = findViewById(R.id.emoji);

        // Pass it to the constructor
        SpringAnimation springAnimation = new SpringAnimation(emoji, DynamicAnimation.X);

        SpringForce springForce = new SpringForce();
        springForce.setFinalPosition(emoji.getX());
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springForce.setStiffness(SpringForce.STIFFNESS_LOW);
        springAnimation.setSpring(springForce);
        springAnimation.setStartVelocity(2000f);
        springAnimation.start();

        // Change icon before animation starts
        emoji.setImageResource(
                R.drawable.happy_2);

        // Start animation
        springAnimation.start();

        springAnimation.addEndListener(
                new DynamicAnimation.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(DynamicAnimation animation,
                                               boolean canceled,
                                               float value, float velocity) {
                        // Change icon after animation ends
                        emoji.setImageResource(
                                R.drawable.happy);
                    }
                });
    }

    public void stretch(View view) {
        final ImageView emoji = findViewById(R.id.emoji);

        FloatPropertyCompat<View> scale =
                new FloatPropertyCompat<View>("scale") {
                    @Override
                    public float getValue(View view) {
                        // return the value of any one property
                        return view.getScaleX();
                    }

                    @Override
                    public void setValue(View view, float value) {
                        // Apply the same value to two properties
                        view.setScaleX(value);
                        view.setScaleY(value);
                    }
                };

        scale.setValue(emoji, -3f);
        SpringAnimation stretchAnimation =
                new SpringAnimation(emoji, scale);

        stretchAnimation.setMinimumVisibleChange(
                DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE);
    }
}
