package com.app.instagramdemo.dao;

import com.app.instagramdemo.model.entity.HomeModel;
import com.app.instagramdemo.model.entity.ProfileModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileClient {

    @GET("profile")
    Call<ProfileModel> getProfile();

    @GET("home")
    Call<HomeModel> getHome();
}
