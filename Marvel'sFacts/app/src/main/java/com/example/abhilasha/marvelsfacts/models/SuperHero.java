package com.example.abhilasha.marvelsfacts.models;

import android.os.Parcel;
import android.os.Parcelable;


public  class  SuperHero implements Parcelable {



    private String name;
    private String description;
    private String thumbnail;
    private String image;


   public  SuperHero(){
       SuperHero superhero=new SuperHero();
   }


   public SuperHero (String name, String description, String thumbnail, String image) {
    this.name = name;
    this.description = description;
    this.thumbnail = thumbnail;
    this.image = image;

   }


    public SuperHero(Parcel source) {
        name = source.readString();
        description = source.readString();
        thumbnail = source.readString();
        image = source.readString();
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;

    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        applyDefaultValues();

        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(thumbnail);
        dest.writeString(image);
    }

    private void applyDefaultValues() {
        if (name == null)
            name = "";
        if (description == null)
            description = "";
        if (thumbnail == null)
            thumbnail = "";
        if (image == null)
            image = "";
    }

    public static Creator<SuperHero> CREATOR = new Creator<SuperHero>() {

        @Override
        public SuperHero createFromParcel(Parcel source) {
            return new SuperHero(source);
        }

        @Override
        public SuperHero[] newArray(int size) {
            return new SuperHero[size];
        }


    };

}
