package com.areatechservices.fieldreportapp;

import android.arch.persistence.room.Room;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.areatechservices.fieldreportapp.Fragments.HomeLandingFragment;
import com.areatechservices.fieldreportapp.Fragments.SurveyFragment;
import com.areatechservices.fieldreportapp.Services.ConnectivityChangeReciever;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public SurveyDatabase getSurveyDatabase() {
        return surveyDatabase;
    }

    public void setSurveyDatabase(SurveyDatabase surveyDatabase) {
        this.surveyDatabase = surveyDatabase;
    }

    private SurveyDatabase surveyDatabase;

    FragmentManager manager;
    FragmentTransaction transaction;
    ConnectivityChangeReciever connectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        connectivityReceiver = new ConnectivityChangeReciever();
        registerReceiver(connectivityReceiver,intentFilter);

        RoomDatabase db = new RoomDatabase(getApplicationContext());
        //create databse
        surveyDatabase = db.getSurveyDatabase();

        manager = getSupportFragmentManager();


      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeLandingFragment frag = new HomeLandingFragment();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, frag);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            HomeLandingFragment frag = new HomeLandingFragment();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentContainer, frag);
            transaction.commit();

        } else if (id == R.id.create_survey) {

            SurveyFragment frag = new SurveyFragment();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentContainer, frag);
            transaction.commit();

        } else if (id == R.id.list_survey) {

        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            unregisterReceiver(connectivityReceiver);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

    }
}
