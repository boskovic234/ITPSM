package com.iceman.itpsm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Boskovic on 12/25/2016.
 */

public class LayarUtama extends AppCompatActivity {

    TextView TVUsername;
    String Username;

    //Untuk Define Menu Samping
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    //Untuk Define Menu Samping

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layar_utama);

        TVUsername = (TextView) findViewById(R.id.TVName);

        //MENU DRAWER
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view) ;
        navigationView.setNavigationItemSelectedListener(this);
        //MENU DRAWER

        Intent intent = getIntent();
        Username = intent.getStringExtra("Nama");
        TVUsername.setText("Welcome, "+ Username);
        TVUsername.startAnimation(AnimationUtils.loadAnimation(LayarUtama.this, android.R.anim.slide_in_left));

        //TVUsername = R.string.usrnav;
    }

    //Supaya tombol menu kiri atas bisa di klik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Supaya tombol menu kiri atas bisa di klik

    public boolean onNavigationItemSelected (MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_logout)
        {
            Toast.makeText(this, "LOGOUT TRUE", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "LOGOUT FALSE", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
