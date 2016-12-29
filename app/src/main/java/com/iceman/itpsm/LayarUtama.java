package com.iceman.itpsm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Boskovic on 12/25/2016.
 */

public class LayarUtama extends AppCompatActivity {

    TextView TVUsername;
    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layar_utama);

        //TVUsername = (TextView) findViewById(R.id.TVUsername);

        Intent intent = getIntent();
        Username = intent.getStringExtra("Login");

        //TVUsername.setText("Welcome, " + Username);

    }

}
