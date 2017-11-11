package com.example.abhilasha.marvelsfacts.utils;

import com.example.abhilasha.marvelsfacts.models.GalleryImage;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


public interface GalleryApiInterface {

    @GET( "/gallery.json" )
    void getStreams( Callback<List<GalleryImage>> callback );

}
