package com.example.abhilasha.marvelsfacts.utils;

import com.example.abhilasha.marvelsfacts.models.SuperHero;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


public interface SuperHeroApiInterface {

    @GET( "/superhero.json" )
    void getStreams( Callback<List<SuperHero>> callback );

}

