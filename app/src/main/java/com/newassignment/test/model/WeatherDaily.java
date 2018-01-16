package com.newassignment.test.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lenovo on 1/14/2018.
 */

public class WeatherDaily implements Serializable {
   public int dt;
   public String name;
   public String rain;
   public String snow;
   public float lat;
   public float lon;
   public Temp temp;

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSnow() {
        return snow;
    }

    public void setSnow(String snow) {
        this.snow = snow;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public ArrayList<Weather> getWeatherlist() {
        return weatherlist;
    }

    public void setWeatherlist(ArrayList<Weather> weatherlist) {
        this.weatherlist = weatherlist;
    }

    public ArrayList<Weather> weatherlist;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }





}
