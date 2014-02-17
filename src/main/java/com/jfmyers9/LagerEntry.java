package com.jfmyers9;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.inject.Inject;

public class LagerEntry implements Parcelable {
    private String name, rating, aroma, appearance, taste, image, created_at;
    private long id;

    public LagerEntry(String name, String rating, String aroma,
                      String appearance, String taste, String image) {
        this.name = name;
        this.rating = rating;
        this.aroma = aroma;
        this.appearance = appearance;
        this.taste = taste;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(rating);
        dest.writeString(aroma);
        dest.writeString(appearance);
        dest.writeString(taste);
        dest.writeString(image);
        dest.writeString(created_at);
        dest.writeLong(id);
    }

    private LagerEntry(Parcel in) {
        name = in.readString();
        rating = in.readString();
        aroma = in.readString();
        appearance = in.readString();
        taste = in.readString();
        image = in.readString();
        created_at = in.readString();
        id = in.readLong();
    }

    public static final Parcelable.Creator<LagerEntry> CREATOR = new Parcelable.Creator<LagerEntry>() {
        @Override
        public LagerEntry createFromParcel(Parcel source) {
            return new LagerEntry(source);
        }

        @Override
        public LagerEntry[] newArray(int size) {
            return new LagerEntry[size];
        }
    };
}
