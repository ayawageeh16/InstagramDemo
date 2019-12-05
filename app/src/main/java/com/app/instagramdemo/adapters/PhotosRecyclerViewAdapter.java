package com.app.instagramdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.instagramdemo.R;
import com.app.instagramdemo.model.entity.Photo;
import com.app.instagramdemo.view.FullPhotoActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.PhotosViewHolder> {

    private List<Photo> photos = new ArrayList<>();
    private Context context;

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    public PhotosRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_card, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        holder.bind(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo_card)
        CardView photoCard;
        @BindView(R.id.photo_imageView)
        AppCompatImageView photoImage;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            photoCard.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, FullPhotoActivity.class);
                    intent.putExtra(FullPhotoActivity.PHOTO_DATA, photos.get(position));
                    context.startActivity(intent);
                }
            });
        }

        public void bind(Photo photo) {
            Glide.with(context)
                    .load(photo.getImage())
                    .placeholder(R.drawable.photo_placeholder)
                    .into(photoImage);
        }
    }
}
