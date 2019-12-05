package com.app.instagramdemo.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitClient = null;
    public static final String BASE_URL = "http://i0sa.com/bit/task/";

    public static Retrofit getInstance() {
        if (retrofitClient != null) {
            return retrofitClient;
        } else {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofitClient;
        }
    }
}
