package com.iceman.itpsm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

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

        //TVUsername = (TextView) findViewById(R.id.TVUsername);

        //Untuk Define Menu Samping
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Untuk Define Menu Samping

        Intent intent = getIntent();
        Username = intent.getStringExtra("Login");

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

}
