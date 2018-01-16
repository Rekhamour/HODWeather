package com.newassignment.test.network;

import com.newassignment.test.model.WeatherDaily;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {
    public  String apikey="137bdc2aab9644244af959a4da3a77a0";

    @GET("find?lat=28.4&lon=77.0&cnt=10&appid=137bdc2aab9644244af959a4da3a77a0")
    Call<List<WeatherDaily>> getHeroes();
}
