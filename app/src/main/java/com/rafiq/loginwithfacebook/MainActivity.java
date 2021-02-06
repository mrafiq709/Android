package com.rafiq.loginwithfacebook;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    URL profile_pic;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // code to print out the key hash
        /*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.rafiq.loginwithfacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        */

        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken: ", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        Bundle bFacebookData = getFacebookData(object);

                        String firstname = bFacebookData.getString("first_name");
                        String lastname = bFacebookData.getString("last_name");
                        String email = bFacebookData.getString("email");
                        String profilepic = bFacebookData.getString("profile_pic");
                        Intent in = new Intent(MainActivity.this, SecondActivity.class);
                        in.putExtra("firstname", firstname);
                        in.putExtra("lastname", lastname);
                        in.putExtra("email", email);
                        in.putExtra("Image", profilepic);
                        startActivity(in);
                        finish();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private Bundle getFacebookData(JSONObject object) {

        Bundle bundle = new Bundle();
        try {
            id = object.getString("id");
            profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=100&height=100");
            Log.i("profile_pic", profile_pic + "");
            bundle.putString("profile_pic", profile_pic.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        bundle.putString("idFacebook", id);
        if (object.has("first_name")){
            try {
                bundle.putString("first_name", object.getString("first_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (object.has("last_name")){
            try {
                bundle.putString("last_name", object.getString("last_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (object.has("email")){
            try {
                bundle.putString("email", object.getString("email"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bundle;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        loginButton.setVisibility(View.GONE);
    }
}
