package com.example.mohamed.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MealStep  implements Serializable, Parcelable {
    @SerializedName("id")
   private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("videoURL")
    private String videoURL;
    @SerializedName("shortDescription")
    private String shortDescription;

    public MealStep(int id, String description, String videoURL, String shortDescription) {
        this.id = id;
        this.description = description;
        this.videoURL = videoURL;
        this.shortDescription = shortDescription;
    }

    protected MealStep(Parcel in) {
        id = in.readInt();
        description = in.readString();
        videoURL = in.readString();
        shortDescription = in.readString();
    }

    public static final Creator<MealStep> CREATOR = new Creator<MealStep>() {
        @Override
        public MealStep createFromParcel(Parcel in) {
            return new MealStep(in);
        }

        @Override
        public MealStep[] newArray(int size) {
            return new MealStep[size];
        }
    };

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(shortDescription);
    }
}
