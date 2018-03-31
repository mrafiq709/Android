package com.rafiq.androidphpmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvUsername, tvEmail, tvId;
    EditText etUsername, etEmail, etId;
    Button btnUpdate;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvId = (TextView) findViewById(R.id.tvId);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etId = (EditText) findViewById(R.id.etId);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        tvUsername.setText(SharedPrefManager.getInstance(this).getUserName());
        tvEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        tvId.setText(SharedPrefManager.getInstance(this).getId());

        btnUpdate.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this,"You Clicked Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuAllUser:
                startActivity(new Intent(this, AllUsers.class));
                break;
        }
        return true;
    }

    public void updateUser(){
        final String username = etUsername.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String id = etId.getText().toString().trim();

        progressDialog.setMessage("Updating User...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            Log.d("ddd", jsonObject.getString("tometo"));
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
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("username", username);
                params.put("email", email);
                params.put("id", id);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == btnUpdate) {
            updateUser();
        }
    }
}
