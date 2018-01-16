package com.newassignment.test.view;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.newassignment.retrofitexample.R;
import com.newassignment.test.network.Api;
import com.newassignment.test.adapter.ListAdapter;
import com.newassignment.test.controller.MyApplication;
import com.newassignment.test.model.WeatherDaily;
import com.newassignment.test.utils.Utils;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

import static com.newassignment.test.utils.Utils.parsing;

public class MainActivity extends AppCompatActivity {

    //injecting retrofit
    @Inject
    Retrofit retrofit;
    RecyclerView listView;
    private LinearLayoutManager layoutmanager;
    private ListAdapter adapter;
    private Location loc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((MyApplication) getApplication()).getNetComponent().inject(this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);


        listView = (RecyclerView) findViewById(R.id.listViewHeroes);
        layoutmanager = new LinearLayoutManager(MainActivity.this);
        listView.setLayoutManager(layoutmanager);
        loc=Utils.getLatLong(MainActivity.this);
        Log.e("LATLONG","latlong"+ loc.getLongitude() + " "+ loc.getLatitude());
        getHeroes();
    }

    private void getHeroes() {
        final Api api = retrofit.create(Api.class);
        Call<List<WeatherDaily>> call = api.getHeroes();
        String url= "http://api.openweathermap.org/data/2.5/find?lat="+loc.getLatitude()+"&lon="+loc.getLongitude()+"&cnt=50&appid=137bdc2aab9644244af959a4da3a77a0";
        Log.e("LATLONG","latlong"+ url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("RESPONSE","RESPONSE"+response.toString());
                adapter = new ListAdapter(MainActivity.this,parsing(response.toString()) );
                listView.setAdapter(adapter);


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Do you want to exit the App?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();

                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();    }
}
