package com.examen.tlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.examen.tlist.ui.home.HomeActivity;
import com.examen.tlist.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.key_prefs), Context.MODE_PRIVATE);

        user = prefs.getString("email", null);
        if (user!=null){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("email", user);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}