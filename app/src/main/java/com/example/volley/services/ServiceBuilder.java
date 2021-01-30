package com.example.volley.services;

import android.view.View;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "https://gadsapi.herokuapp.com";
    private static ServiceBuilder INSTANCE;
    //create logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    //create OkHttp client
    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder().addInterceptor(logger);
    private static final Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());
    private static Retrofit retrofit = builder.build();


    public static <s> s buildService(Class<s> serviceType){
        return retrofit.create(serviceType);
    }

    public static ServiceBuilder getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ServiceBuilder();
        }
        return INSTANCE;
    }
}
