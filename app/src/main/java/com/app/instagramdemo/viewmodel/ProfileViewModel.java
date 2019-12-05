package com.app.instagramdemo.viewmodel;

import android.app.Application;

import com.app.instagramdemo.model.entity.HomeModel;
import com.app.instagramdemo.model.entity.ProfileModel;
import com.app.instagramdemo.model.repository.ProfileRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepository profileRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository();
    }

    public MutableLiveData<ProfileModel> getProfileData() {
        return profileRepository.getProfileData();
    }

    public MutableLiveData<HomeModel> getHomeData() {
        return profileRepository.getHomeData();
    }
}
