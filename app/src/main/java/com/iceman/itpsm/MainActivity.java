package com.iceman.itpsm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    String username;
    String password;
    EditText ETUsername;
    EditText ETPassword;
    Button BTNLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETUsername = (EditText) findViewById(R.id.ETUsername);
        ETPassword = (EditText) findViewById(R.id.ETPassword);
        BTNLogin = (Button) findViewById(R.id.BTNLogin);

    }

    public void login(View view) {

        Intent intentlayarutama = new Intent(this, LayarUtama.class);

        username = ETUsername.getText().toString();
        password = ETPassword.getText().toString();

        if (username == null || username.isEmpty()) {
            Toast.makeText(this, "Please Insert Username first !", Toast.LENGTH_SHORT).show();
        } else if (password == null || password.isEmpty()) {
            Toast.makeText(this, "Please Insert Password !", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("Disni", username);
            intentlayarutama.putExtra("Login", username);
            startActivity(intentlayarutama);
        }
    }
}