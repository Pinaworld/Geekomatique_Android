/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : HomeActivity.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {
//Cette activité est l'écran d'accueil de l'application

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //On genere les differents composants du Layout
        Button CalendarButt = (Button) findViewById(R.id.CalendarButt);
        Button DisponibilitiesButt = (Button) findViewById(R.id.DisponibilitiesButt);
        Button PrestationsButt = (Button) findViewById(R.id.PrestationsButt);
        Button AdminUserBtn = (Button) findViewById(R.id.AdminUserBtn);

    }

    public void CalendarActivity(View view) {
        Intent intent = new Intent(this, CalendarAppointments.class);
        startActivity(intent);
    }

    public void DisponibilitiesActivity(View view) {
        Intent intent = new Intent(this, Disponibilities.class);
        startActivity(intent);
    }

    public void PrestationsActivity(View view) {
        Intent intent = new Intent(this, Prestations.class);
        startActivity(intent);
    }

    public void AdminUserActivitiy(View view) {
        Intent intent = new Intent(this, AdminUser.class);
        startActivity(intent);
    }

    public void Disconnect(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
