package com.example.broilr;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mattlauer on 2014-10-22.
 */
public class FoodProfile implements Parcelable {
    private String imgURL;
    private String name;
    private String age;
    private String lastActive;
    private String bio;
    private int profileID;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
        this.lastActive = lastActive;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imgURL);
        dest.writeString(this.name);
        dest.writeString(this.age);
        dest.writeString(this.lastActive);
        dest.writeString(this.bio);
        dest.writeInt(this.profileID);
    }

    public FoodProfile() {
    }

    private FoodProfile(Parcel in) {
        this.imgURL = in.readString();
        this.name = in.readString();
        this.age = in.readString();
        this.lastActive = in.readString();
        this.bio = in.readString();
        this.profileID = in.readInt();
    }

    public static final Parcelable.Creator<FoodProfile> CREATOR = new Parcelable.Creator<FoodProfile>() {
        public FoodProfile createFromParcel(Parcel source) {
            return new FoodProfile(source);
        }

        public FoodProfile[] newArray(int size) {
            return new FoodProfile[size];
        }
    };
}
