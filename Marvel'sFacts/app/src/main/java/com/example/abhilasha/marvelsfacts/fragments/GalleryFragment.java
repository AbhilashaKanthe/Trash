package com.example.abhilasha.marvelsfacts.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.abhilasha.marvelsfacts.R;
import com.example.abhilasha.marvelsfacts.activities.GallerDetailActivity;
import com.example.abhilasha.marvelsfacts.adapter.GalleryImageAdapter;
import com.example.abhilasha.marvelsfacts.models.GalleryImage;
import com.example.abhilasha.marvelsfacts.utils.GalleryApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView mGridView;
    private GalleryImageAdapter mGalleryImageAdapter;

    public static GalleryFragment getInstance() {
        GalleryFragment fragment = new GalleryFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_grid,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGridView=(GridView)view.findViewById(R.id.grid);
        mGridView=(GridView)view.findViewById(R.id.grid);

        mGridView.setOnItemClickListener(this);
        mGridView.setDrawSelectorOnTop(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGalleryImageAdapter=new GalleryImageAdapter(getActivity(),0);
        mGridView.setAdapter(mGalleryImageAdapter);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint( getString( R.string.gallery_feed ) )
                .build();

        GalleryApiInterface galleryApiInterface = restAdapter.create( GalleryApiInterface.class );

        galleryApiInterface.getStreams( new Callback<List<GalleryImage>>() {
            @Override
            public void success(List<GalleryImage> galleryImages, Response response) {
                if( galleryImages == null || galleryImages.isEmpty() )
                    return;

                for( GalleryImage image : galleryImages ) {
                    Log.e("SuperHero", image.getThumbnail() );
                    mGalleryImageAdapter.add(image);
                }
                mGalleryImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }



        @Override
        public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
            GalleryImage image = (GalleryImage) parent.getItemAtPosition(position);
            Intent intent = new Intent(getActivity(), GallerDetailActivity.class);
            intent.putExtra(GallerDetailActivity.EXTRA_IMAGE, image.getImage());
            intent.putExtra(GallerDetailActivity.EXTRA_CAPTION, image.getCaption());
            startActivity(intent);
        }
    }



