package com.app.instagramdemo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Counts implements Parcelable {

    @SerializedName("posts")
    @Expose
    private Long posts;
    @SerializedName("followers")
    @Expose
    private Long followers;
    @SerializedName("following")
    @Expose
    private Long following;


    protected Counts(Parcel in) {
        if (in.readByte() == 0) {
            posts = null;
        } else {
            posts = in.readLong();
        }
        if (in.readByte() == 0) {
            followers = null;
        } else {
            followers = in.readLong();
        }
        if (in.readByte() == 0) {
            following = null;
        } else {
            following = in.readLong();
        }
    }

    public static final Creator<Counts> CREATOR = new Creator<Counts>() {
        @Override
        public Counts createFromParcel(Parcel in) {
            return new Counts(in);
        }

        @Override
        public Counts[] newArray(int size) {
            return new Counts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        if (posts == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(posts);
        }
        if (followers == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(followers);
        }
        if (following == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(following);
        }
    }

}
