package com.rafiq.loginwithfacebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtName, txtEmail;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView = findViewById(R.id.imageView);
        btnLogout = findViewById(R.id.logout);
        txtName = findViewById(R.id.name);
        txtEmail = findViewById(R.id.email);

        String firstname = getIntent().getExtras().getString("firstname");
        String lastname = getIntent().getExtras().getString("lastname");
        String email = getIntent().getExtras().getString("email");
        String image = getIntent().getExtras().getString("Image");

        if (firstname != null && lastname != null && email != null && image != null){
            txtName.setText(firstname + " " + lastname);
            txtEmail.setText(email);
            Glide.with(SecondActivity.this).load(image).into(imageView);
        }else {
            Toast.makeText(SecondActivity.this, "Data Not Found", Toast.LENGTH_LONG).show();
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent logout = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(logout);
                finish();
            }
        });

    }
}
