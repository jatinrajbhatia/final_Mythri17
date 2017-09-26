package com.jss.jssatenmythri2017_18.activity.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.jss.jssatenmythri2017_18.R;
import com.jss.jssatenmythri2017_18.activity.About;
import com.jss.jssatenmythri2017_18.activity.About_app;
import com.jss.jssatenmythri2017_18.activity.Games;
import com.jss.jssatenmythri2017_18.activity.LoginActivity;
import com.jss.jssatenmythri2017_18.activity.Notification;
import com.jss.jssatenmythri2017_18.activity.Support;
import com.jss.jssatenmythri2017_18.activity.coordinator.Coordinater_nav_activity;
import com.jss.jssatenmythri2017_18.activity.coordinator.Your_Events;

/**
 * Created by ashwa on 9/25/2017.
 */

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharedPreferences;
    final String LOGIN_KEY="logged_in";
    final String PREF_NAME = "mythri-2016";
    String USERNAME = "useername";
    String MYTHRI_ID = "mythri_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        android.support.v4.app.FragmentManager transaction =getSupportFragmentManager();
        transaction.beginTransaction().replace(R.id.coordinater_frame,new About()).commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
    }

    int bakcounter;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(bakcounter < 2){
                Toast.makeText(getApplicationContext(),"Press Back again to exit",Toast.LENGTH_SHORT).show();
                bakcounter++;
                return;
            }
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.home){
            android.support.v4.app.FragmentManager transaction =getSupportFragmentManager();
            transaction.beginTransaction().replace(R.id.coordinater_frame,new About()).commit();
        }else if (id == R.id.view_fixtures) {
            android.support.v4.app.FragmentManager transaction =getSupportFragmentManager();
            transaction.beginTransaction().replace(R.id.coordinater_frame,new ChooseGame()).commit();
        }else if (id == R.id.view_all) {
            android.support.v4.app.FragmentManager transaction =getSupportFragmentManager();
            transaction.beginTransaction().replace(R.id.coordinater_frame,new GetAllUsers()).commit();
        }else if (id == R.id.register_coordinator) {
           startActivity(new Intent(AdminActivity.this, RegisterCoordinator.class));
        }
        else if(id == R.id.logout){
            clearPref();
        }else if(id == R.id.support){
            android.support.v4.app.FragmentManager transaction =getSupportFragmentManager();
            transaction.beginTransaction().replace(R.id.coordinater_frame,new Support()).commit();
        } else if (id == R.id.share) {
            shareapp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void shareapp() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "JSS Mythri 2017-18 android app.\nClick on link to download: " +
                "https://i.diawi.com/WpMHeq");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Jss Mythri 2017-18");
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
    private void clearPref() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_KEY,false);
        editor.apply();
        startActivity(new Intent(AdminActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
