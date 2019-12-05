package com.app.instagramdemo.view;

import android.content.Intent;
import android.os.Bundle;

import com.app.instagramdemo.R;
import com.app.instagramdemo.model.entity.Photo;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FullPhotoActivity extends AppCompatActivity {

    // Constants
    public static final String PHOTO_DATA = "PHOTO_DATA";

    // UI components
    @BindView(R.id.full_photo_imageView)
    AppCompatImageView photoImageView;
    @BindView(R.id.full_photo_title_textView)
    AppCompatTextView photoTitleTextView;

    private Photo photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_photo);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PHOTO_DATA)) {
            if (intent.getParcelableExtra(PHOTO_DATA) != null) {
                photo = intent.getParcelableExtra(PHOTO_DATA);
                displayPhoto(photo);
            }
        }
    }

    private void displayPhoto(Photo photo) {
        Glide.with(this)
                .load(photo.getImage())
                .into(photoImageView);
        photoTitleTextView.setText(photo.getTitle());
    }
}
