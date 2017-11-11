package com.example.abhilasha.marvelsfacts.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.abhilasha.marvelsfacts.R;
import com.example.abhilasha.marvelsfacts.activities.ExhibitDetailActivity;
import com.example.abhilasha.marvelsfacts.adapter.ExhibitsAdapter;
import com.example.abhilasha.marvelsfacts.models.SuperHero;
import com.example.abhilasha.marvelsfacts.utils.SuperHeroApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ExhibitsListFragment extends ListFragment {
    private ExhibitsAdapter mExhibitsAdaptern;

    public static ExhibitsListFragment getInstance() {
        ExhibitsListFragment fragment = new ExhibitsListFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListShown(false);
        mExhibitsAdaptern=new ExhibitsAdapter(getActivity(),0);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.super_hero ) )
                .build();

        SuperHeroApiInterface superHeroApiInterface = restAdapter.create(SuperHeroApiInterface.class );

        superHeroApiInterface.getStreams( new Callback<List<SuperHero>>() {
            @Override
            public void success(List<SuperHero> superheroes, Response response) {
                if( superheroes == null || superheroes.isEmpty() )
                    return;


                for( SuperHero superHero : superheroes ) {
                    mExhibitsAdaptern.add(superHero);
                }
                mExhibitsAdaptern.notifyDataSetChanged();
                setListAdapter(mExhibitsAdaptern);
                setListShown(true);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e( "Zoo", "Retrofit error " + error.getMessage() );
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent( getActivity(), ExhibitDetailActivity.class );
        intent.putExtra( ExhibitDetailActivity.EXTRA_SUPERHERO, mExhibitsAdaptern.getItem( position ) );

        startActivity( intent );
    }
}