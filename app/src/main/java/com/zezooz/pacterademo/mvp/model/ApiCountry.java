package com.zezooz.pacterademo.mvp.model;

import android.database.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nick on 16/9/3.
 */
public interface ApiCountry {
    String SERVICE_POINT = "https://dl.dropboxusercontent.com/";

    @GET("u/{countryId}/facts.json")
    Observable<Country> getCountry(@Path("countryId") String countryId);
}
