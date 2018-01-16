package com.newassignment.test.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newassignment.test.model.Temp;
import com.newassignment.test.model.Weather;
import com.newassignment.test.model.WeatherDaily;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Lenovo on 1/15/2018.
 */

public class Utils  {


    private static final int LOCATION_REFRESH_TIME = 0;
    private static final float LOCATION_REFRESH_DISTANCE = 0;
    public static Location[] location = {null};
    private static LocationManager mLocationManager;

    public static ArrayList<WeatherDaily> parsing(String data) {
        ArrayList<WeatherDaily> list = null;
        try {
            JSONObject object = new JSONObject(data);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            list = new ArrayList<>();

            for (int i = 0; i < object.getJSONArray("list").length(); i++) {
                String jsondata = object.getJSONArray("list").getJSONObject(i).toString();
                JSONObject obj = object.getJSONArray("list").getJSONObject(i);
                String name = obj.getString("name").replaceAll("\"", " ");
                String rain = obj.getString("rain").replaceAll("\"", " ");
                String snow = obj.getString("snow").replaceAll("\"", " ");
                JSONObject coord = obj.getJSONObject("coord");
                String lat = coord.getString("lat").replaceAll("\"", " ");
                String lon = coord.getString("lon").replaceAll("\"", " ");


                JSONObject mainobject = obj.getJSONObject("main");
                String temp = mainobject.getString("temp");
                String temp_min = mainobject.getString("temp_min").replaceAll("\"", " ");
                String temp_max = mainobject.getString("temp_max").replaceAll("\"", " ");
                String humidity = mainobject.getString("humidity").replaceAll("\"", " ");
                Temp temp1 = new Temp();
                temp1.setMax(Float.parseFloat(temp_max));
                temp1.setMin(Float.parseFloat(temp_min));
                temp1.setHumidity(Float.parseFloat(humidity));

                ArrayList<Weather> listweater = new ArrayList<>();
                for (int j = 0; j < obj.getJSONArray("weather").length(); j++) {
                    JSONObject weatherobj = obj.getJSONArray("weather").getJSONObject(j);
                    String id = weatherobj.getString("id").replaceAll("\"", " ");
                    String main = weatherobj.getString("main").replaceAll("\"", " ");
                    String description = weatherobj.getString("description").replaceAll("\"", " ");
                    String icon = weatherobj.getString("icon").replaceAll("\"", " ");
                    Weather weatherbean = new Weather();
                    weatherbean.setMain(main);
                    weatherbean.setIcon(icon);
                    weatherbean.setDescription(description);
                    weatherbean.setId(Integer.parseInt(id));

                    listweater.add(weatherbean);
                }

                WeatherDaily w = new WeatherDaily();
                w.setName(name);
                w.setTemp(temp1);
                w.setRain(rain);
                w.setSnow(snow);
                w.setLat(Float.parseFloat(lat));
                w.setLon(Float.parseFloat(lon));
                w.setWeatherlist(listweater);
                list.add(w);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static Location getLatLong(Context context) {
        Location l = new Location("");


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        l.setLatitude(Double.parseDouble(prefs.getString("user_lat","28.4")));
        l.setLongitude(Double.parseDouble(prefs.getString("user_long","77.0")));

        final LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location loc) {
                //your code here
                //ed.putString("city",location.getLatitude()+","+location.getLongitude());
                System.out.println("-------------------"+loc.toString());
                location[0] = loc;
                editor.putString("user_lat",loc.getLatitude()+"");
                editor.putString("user_long",loc.getLongitude()+"");
                editor.commit();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.v("i am in util loc", "--"+location[0]);
            return location[0];
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);
        //Log.v("i am outside util loc", location[0].toString());
        return location[0] == null?l:location[0];
    }

}
