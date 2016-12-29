package com.iceman.itpsm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    String username;
    String password;
    EditText ETUsername;
    EditText ETPassword;
    Button BTNLogin;
    ConnectionClass koneksi;
    String querylogin;

    //DatabaseParameter
    String IDK;
    String NameK;
    String PositionK;
    String PhotoK;
    String MobilePhoneK;
    String PasswordK;

    //Untuk Define Menu Samping
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    //Untuk Define Menu Samping

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Untuk Define Menu Samping
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        //mDrawerLayout.addDrawerListener(mToggle);
        //mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Untuk Define Menu Samping

        ETUsername = (EditText) findViewById(R.id.ETUsername);
        ETPassword = (EditText) findViewById(R.id.ETPassword);
        BTNLogin = (Button) findViewById(R.id.BTNLogin);

        koneksi = new ConnectionClass();
    }

    //Supaya tombol menu kiri atas bisa di klik
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(mToggle.onOptionsItemSelected(item))
//        {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    //Supaya tombol menu kiri atas bisa di klik

    public void login(View view) {

        username = ETUsername.getText().toString();
        password = ETPassword.getText().toString();

        if (username == null || username.isEmpty()) {
            Toast.makeText(this, "Please Insert Username first !", Toast.LENGTH_SHORT).show();
        } else if (password == null || password.isEmpty()) {
            Toast.makeText(this, "Please Insert Password !", Toast.LENGTH_SHORT).show();
        } else {
            DoLogin doLogin = new DoLogin();
            doLogin.execute("");
        }
    }

    public class DoLogin extends AsyncTask<String, String, String> {
        String z = "";

        @Override
        protected String doInBackground(String... params) {

            Connection kon = koneksi.CONN();

            if (kon == null) {
                z = "Error koneksi SQL";
            } else {
                querylogin = "select ID," +
                        "Name," +
                        "Position," +
                        "Photo," +
                        "MobilePhone," +
                        "Password " +
                        "from ITProduction..Master_PIC " +
                        "where ID =" + "'" + username + "'";

                Statement stmt = null;
                Log.d("Disini", querylogin);

                try {
                    stmt = kon.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    ResultSet RS = stmt.executeQuery(querylogin);

                    if (RS.next()) {
                        StringBuilder ID = new StringBuilder();
                        StringBuilder Name = new StringBuilder();
                        StringBuilder Position = new StringBuilder();
                        StringBuilder Photo = new StringBuilder();
                        StringBuilder MobilePhone = new StringBuilder();
                        StringBuilder Password = new StringBuilder();

                        String ID1 = RS.getString(1);
                        String Name1 = RS.getString(2);
                        String Position1 = RS.getString(3);
                        String Photo1 = RS.getString(4);
                        String MobilePhone1 = RS.getString(5);
                        String Password1 = RS.getString(6);

                        ID.append(ID1);
                        Name.append(Name1);
                        Position.append(Position1);
                        Photo.append(Photo1);
                        MobilePhone.append(MobilePhone1);
                        Password.append(Password1);

                        IDK = ID.toString();
                        NameK = Name.toString();
                        PositionK = Position.toString();
                        PhotoK = Photo.toString();
                        MobilePhoneK = MobilePhone.toString();
                        PasswordK = Password.toString();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return z;
        }

        protected void onPostExecute(String result) {
            if (password.equals(PasswordK)) {
                matchpassword();
            } else {
                wrongpassword();
            }
        }
    }

    public void wrongpassword() {
        Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show();
    }

    public void matchpassword() {
        Intent intentlayarutama = new Intent(this, LayarUtama.class);
        intentlayarutama.putExtra("Login", username);
        startActivity(intentlayarutama);
    }
}