package com.rafiq.androidphpmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AllUsers extends AppCompatActivity implements View.OnClickListener{


    ListView list;
    InputStream is = null;
    String line = null;
    String result = null;
    String[] ID;
    String[] Name;
    String[] Email;

    EditText etID;
    Button btnRemove;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        list = (ListView) findViewById(R.id.allUserList);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        getData();

        Adapter adapter1 = new Adapter(this, ID, Name, Email);
        list.setAdapter(adapter1);

        etID = (EditText) findViewById(R.id.etID);
        btnRemove = (Button) findViewById(R.id.btnRemove);

        progressDialog = new ProgressDialog(this);

        btnRemove.setOnClickListener(this);

    }

    private void getData(){
        try {
            URL url = new URL(Constants.URL_ALL_USER);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            is = new  BufferedInputStream(con.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        try {
            while ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            is.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;

            ID = new String[ja.length()];
            Name = new String[ja.length()];
            Email = new String[ja.length()];

            for (int i=0; i< ja.length(); i++){
                jo = ja.getJSONObject(i);
                ID[i] = jo.getString("id");
                Name[i] = jo.getString("username");
                Email[i] = jo.getString("email");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        }

        return true;
    }

    public void removeUser(){
        final String id = etID.getText().toString().trim();

        progressDialog.setMessage("Removing User...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REMOVE_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

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
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        finish();
        startActivity(getIntent());
    }

    @Override
    public void onClick(View v) {
        if (v == btnRemove)
            removeUser();
    }
}
