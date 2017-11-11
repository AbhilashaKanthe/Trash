package com.example.abhilasha.marvelsfacts.activities;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.abhilasha.marvelsfacts.events.DrawerSectionItemClickedEvent;
import com.example.abhilasha.marvelsfacts.R;
import com.example.abhilasha.marvelsfacts.fragments.ExhibitsListFragment;
import com.example.abhilasha.marvelsfacts.fragments.GalleryFragment;
import com.example.abhilasha.marvelsfacts.fragments.ZooMapFragment;
import com.example.abhilasha.marvelsfacts.utils.EventBus;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {
 Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    private  String mCurrentFragmentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);






        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_opened,R.string.draewr_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar()!=null)
                getSupportActionBar().setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportActionBar()!=null)
                    getSupportActionBar().setTitle(R.string.app_name);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        displayInitialFragment();
    }

    private void displayInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace( R.id.container, ExhibitsListFragment.getInstance() ).commit();
        mCurrentFragmentTitle = "Exhibits";
    }


    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getInstance().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onDrawerSectionItemClickEvent( DrawerSectionItemClickedEvent event ) {
        mDrawerLayout.closeDrawers();


        mDrawerLayout.closeDrawers();
        if (event==null || TextUtils.isEmpty(event.section)||event.section.equalsIgnoreCase(mCurrentFragmentTitle)){
            return;
        }
        Toast.makeText(this, "MainActivity: Section Clicked: " + event.section, Toast.LENGTH_SHORT).show();
        if( event.section.equalsIgnoreCase( "maps" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, ZooMapFragment.getInstance() ).commit();
        } else if( event.section.equalsIgnoreCase( "gallery" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, GalleryFragment.getInstance() ).commit();
        } else if( event.section.equalsIgnoreCase( "exhibits" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, ExhibitsListFragment.getInstance() ).commit();
        } else {
            return;
        }

        mCurrentFragmentTitle = event.section;
    }
    }


