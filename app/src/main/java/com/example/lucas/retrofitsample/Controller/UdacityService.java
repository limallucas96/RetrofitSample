package com.example.lucas.retrofitsample.Controller;

import com.example.lucas.retrofitsample.Model.UdacityCatalog;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lucas on 19/06/2018.
 */

public interface UdacityService {

    public static final String BASE_URL = "https://www.udacity.com/public-api/v0/";

    @GET("courses")
    Call<UdacityCatalog> listCatalog();
}
