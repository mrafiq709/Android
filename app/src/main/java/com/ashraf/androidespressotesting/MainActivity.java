package com.ashraf.androidespressotesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /*
    * Et-> EditText
    * Btn -> Button
    * Tv -> TextView
    * */

    private String successMessage = "Sign In Successful ^-^";
    private EditText userMailEt, userPasswordEt;
    private Button sigInInBtn;
    private TextView errorTv;
    private ValidationCheck mValidationCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize all variable
        initializeAll();
        sigInInBtn.setOnClickListener(sigInListener);
    }

    private void initializeAll() {
        userMailEt = findViewById(R.id.userEmailEt);
        userPasswordEt = findViewById(R.id.userPasswordEt);
        sigInInBtn = findViewById(R.id.signInBtn);
        errorTv = findViewById(R.id.errorTv);
        mValidationCheck = new ValidationCheck();
    }

    private View.OnClickListener sigInListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            errorTv.setVisibility(View.VISIBLE);
            if (mValidationCheck.isValidEmail(userMailEt.getText().toString())){
                if (mValidationCheck.isValidPassword(userPasswordEt.getText().toString())){
                    errorTv.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    errorTv.setText(successMessage);
                }else {
                    errorTv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    errorTv.setText("Password must contain mix of upper and lower case letters as well as digits and one special character (6-20)");
                }
            }else {
                errorTv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                errorTv.setText("Wrong email address");
            }

        }
    };

}
