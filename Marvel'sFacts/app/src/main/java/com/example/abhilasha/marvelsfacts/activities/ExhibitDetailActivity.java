package com.example.abhilasha.marvelsfacts.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhilasha.marvelsfacts.R;
import com.example.abhilasha.marvelsfacts.models.SuperHero;
import com.squareup.picasso.Picasso;



public class ExhibitDetailActivity extends AppCompatActivity{

    public static final String EXTRA_SUPERHERO="extra_superhero";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_detail);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SuperHero superHero=getIntent().getExtras().getParcelable("EXTRA_SUPERHERO");
        TextView description=(TextView)findViewById(R.id.description);
        ImageView image=(ImageView)findViewById(R.id.image);
        description.setText(superHero.getDescription());
        Picasso.with(this).load(superHero.getImage()).into(image);



    }

}
