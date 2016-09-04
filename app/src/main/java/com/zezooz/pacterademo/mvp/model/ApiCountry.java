package com.zezooz.pacterademo.mvp.model;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by nick on 16/9/3.
 */
public interface ApiCountry {
    String API_BASE_URL = "https://dl.dropboxusercontent.com/";

    @GET("u/{countryId}/facts.json")
    Observable<Country> getCountry(@Path("countryId") String countryId);
}
