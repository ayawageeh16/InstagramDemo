package com.app.instagramdemo.view;

import android.os.Bundle;

import com.app.instagramdemo.R;
import com.app.instagramdemo.adapters.PhotosRecyclerViewAdapter;
import com.app.instagramdemo.model.entity.HomeModel;
import com.app.instagramdemo.model.entity.ProfileModel;
import com.app.instagramdemo.util.NetworkConnection;
import com.app.instagramdemo.util.Util;
import com.app.instagramdemo.viewmodel.ProfileViewModel;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    // User details UI
    @BindView(R.id.profile_mainLayout)
    CoordinatorLayout mainLayout;
    @BindView(R.id.user_name_textView)
    AppCompatTextView userNameTextView;
    @BindView(R.id.user_address_textView)
    AppCompatTextView userAddressTextView;
    @BindView(R.id.user_bio_textView)
    AppCompatTextView userBioTextView;
    @BindView(R.id.user_profile_image)
    CircleImageView userProfileImage;

    // Info bar UI
    @BindView(R.id.posts_number_textView)
    AppCompatTextView postsNumberTextView;
    @BindView(R.id.followers_number_textView)
    AppCompatTextView followersNumberTextView;
    @BindView(R.id.following_number_textView)
    AppCompatTextView followingNumberTextView;

    // Photos UI
    @BindView(R.id.user_photos_recyclerView)
    RecyclerView photosRecyclerView;

    private ProfileViewModel profileViewModel;
    private PhotosRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().hide();
        setUpRecyclerView();

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        if (NetworkConnection.isConnected(this)) {
            getProfileData();
            getHomeData();
        } else {
            Util.showSnake(this, mainLayout, getString(R.string.offline_message));
        }
    }

    private void setUpRecyclerView() {
        adapter = new PhotosRecyclerViewAdapter(this);
        photosRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        photosRecyclerView.setHasFixedSize(true);
        photosRecyclerView.setAdapter(adapter);
    }

    private void getProfileData() {

        MutableLiveData<ProfileModel> profileResponse = profileViewModel.getProfileData();
        profileResponse.observe(this, profileModel -> {
            if (profileModel != null) {
                displayProfileData(profileModel);
            } else {
                Util.showSnake(MainActivity.this, mainLayout, getString(R.string.no_data_founded));
            }
        });
    }

    private void displayProfileData(ProfileModel profileModel) {
        userNameTextView.setText(profileModel.getUser().getFullName());
        userAddressTextView.setText(profileModel.getUser().getLocation());
        userBioTextView.setText(profileModel.getUser().getBio());
        Glide.with(this)
                .load(profileModel.getUser().getProfilePicture())
                .placeholder(R.drawable.ic_user)
                .into(userProfileImage);


        postsNumberTextView.setText(formatNumber(profileModel.getUser().getCounts().getPosts()));
        followersNumberTextView.setText(formatNumber(profileModel.getUser().getCounts().getFollowers()));
        followingNumberTextView.setText(formatNumber(profileModel.getUser().getCounts().getFollowing()));
    }

    private String formatNumber(Long value) {

        // hundreds
        if (value <= 999) {
            return String.valueOf(value);
        }
        // thousands
        else if (value >= 1000 && value <= 999999) {
            return (value / 1000) + "K";
        }
        // millions
        else if (value >= 1000000 && value <= 999999999) {
            return (value / 1000000) + "M";
        }
        // billions
        else if (value >= 1000000000 && value <= 999999999999L) {
            return (value / 1000000000) + "B";
        } else
            return String.valueOf(value);

    }

    private void getHomeData() {
        MutableLiveData<HomeModel> profileResponse = profileViewModel.getHomeData();
        profileResponse.observe(this, homeModel -> {
            if (homeModel != null) {
                adapter.setPhotos(homeModel.getPhotos());
            } else {
                Util.showSnake(MainActivity.this, mainLayout, getString(R.string.no_data_founded));
            }
        });
    }
}
