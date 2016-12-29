package com.iceman.itpsm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETUsername = (EditText) findViewById(R.id.ETUsername);
        ETPassword = (EditText) findViewById(R.id.ETPassword);
        BTNLogin = (Button) findViewById(R.id.BTNLogin);

        koneksi = new ConnectionClass();
    }

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