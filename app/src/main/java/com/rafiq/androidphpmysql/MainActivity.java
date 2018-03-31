package com.rafiq.androidphpmysql;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtLogin;
    EditText editTextUsername, editTextEmail, editTextPassword;
    Button btnRegister;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));

            return;
        }

        editTextUsername = (EditText) findViewById(R.id.txtUsername);
        editTextEmail = (EditText) findViewById(R.id.txtEmail);
        editTextPassword = (EditText) findViewById(R.id.txtPassword);
        txtLogin = (TextView) findViewById(R.id.txtLogin);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        progressDialog = new ProgressDialog(this);

        btnRegister.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    public void registerUser(){

        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //Log.d("ddd", ""+ username + " " + email + " " + password);

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        //Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("ddd", ""+ error.getMessage() + "");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        //requestQueue.add(stringRequest);

        /* Instead of above two line we will use RequestHandler class, It is faster.
        [For more details search requestQueue in android developer site] */

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {

        if (v == btnRegister)
            registerUser();
        if (v == txtLogin) {
            startActivity(new Intent(this, LoginActivity.class
            ));
            finish();
        }
    }
}
