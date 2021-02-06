package com.rafiq.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.rafiq.retrofit.pojo.Carcompany;
import com.rafiq.retrofit.pojo.CompanyList;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    // NOTE: Global Declaration
    List<Carcompany> list_car_company;  // Hold the list of car companies
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView);
        restCall();
    }

    private void restCall() {


        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Rest.BASEURL)
                .build();
        Rest.api_carCompanies api = adapter.create(Rest.api_carCompanies.class);

        //Defining the method
        api.getData(new Callback<CompanyList>() {
            @Override
            public void success(CompanyList car_list_response, Response response) {
                if (car_list_response != null) {

                    list_car_company = car_list_response.getCarcompanies();  // Takes list of car from Response

                    //Loads List View
                    Adapter arrayAdapter = new Adapter(getBaseContext(), list_car_company);
                    lv.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Failed to Connect REST", "" + error.getCause());
            }
        });

        //---------------------*** END REST ***-----------------------------------------------------//

    }


}
