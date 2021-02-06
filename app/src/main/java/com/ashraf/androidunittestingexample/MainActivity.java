package com.ashraf.androidunittestingexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private Button signInBtn;
    private TextView errorTv;
    private ValidationCheck validationCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeAll();
    }

    private void initializeAll() {
        userEmail = findViewById(R.id.userEmailEt);
        userPassword = findViewById(R.id.userPasswordEt);
        signInBtn = findViewById(R.id.signInBtn);
        errorTv = findViewById(R.id.errorTv);
        signInBtn.setOnClickListener(signInBtnListener);
        validationCheck = new ValidationCheck();
    }

    View.OnClickListener signInBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validationCheck.isValidEmail(userEmail.getText().toString())){
                if (validationCheck.isValidPassword(userPassword.getText().toString())){
                    showToast("Login in successful (^^) ");
                    errorTv.setVisibility(View.GONE);
                }else {
                    showToast("Password must contain mix of upper and lower case letters as well as digits and one special character (6-20)");
                    errorTv.setVisibility(View.VISIBLE);
                }
            }else {
                showToast("Invalid email address !!");
                errorTv.setVisibility(View.VISIBLE);
            }
        }
    };

    public void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        errorTv.setText(message);
    }
}
