package com.app.instagramdemo.model.repository;

import android.util.Log;

import com.app.instagramdemo.dao.ProfileClient;
import com.app.instagramdemo.dao.RetrofitClient;
import com.app.instagramdemo.model.entity.HomeModel;
import com.app.instagramdemo.model.entity.ProfileModel;
import com.google.gson.Gson;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    private static final String GET_PROFILE = "GET_PROFILE";
    private static final String GET_PROFILE_ERROR = "GET_PROFILE_ERROR";
    private static final String GET_HOME = "GET_HOME";
    private static final String GET_HOME_ERROR = "GET_HOME_ERROR";

    private MutableLiveData<ProfileModel> profileModelResponse = new MutableLiveData<>();
    private MutableLiveData<HomeModel> homeModelResponse = new MutableLiveData<>();
    private ProfileClient profileClient = null;

    public ProfileRepository() {
        profileClient = getProfileClient();
    }

    private ProfileClient getProfileClient() {
        if (profileClient == null) {
            profileClient = RetrofitClient.getInstance().create(ProfileClient.class);
            return profileClient;
        } else {
            return profileClient;
        }
    }

    public MutableLiveData<ProfileModel> getProfileData() {

        Call<ProfileModel> call = profileClient.getProfile();
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                Log.d(GET_PROFILE, response.code() + ", " + new Gson().toJson(response.body()));

                if (response.code() == 200) {
                    profileModelResponse.setValue(response.body());
                } else {
                    profileModelResponse.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Log.d(GET_PROFILE_ERROR, t.getMessage());
                profileModelResponse.setValue(null);
            }
        });
        return profileModelResponse;
    }

    public MutableLiveData<HomeModel> getHomeData() {

        Call<HomeModel> call = profileClient.getHome();
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                Log.d(GET_HOME, response.code() + ", " + new Gson().toJson(response.body()));
                if (response.code() == 200 && response.body().getStatus()) {
                    homeModelResponse.setValue(response.body());
                } else {
                    homeModelResponse.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Log.d(GET_HOME_ERROR, t.getMessage());
                homeModelResponse.setValue(null);
            }
        });
        return homeModelResponse;
    }

}
