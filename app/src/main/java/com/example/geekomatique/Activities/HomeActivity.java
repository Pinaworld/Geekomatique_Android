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
import android.widget.TextView;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //getMailAdress();


    }

/**
    private void getMailAdress(){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                TextView mailAdress = (TextView) findViewById(R.id.MailShow);
                try {

                    mailAdress.setText(String.valueOf( result.getString("lastname")));
                } catch (JSONException e) {
                    Log.i(" teste", e.toString());

                }

                Log.i("getMailAdress", result.toString());

            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/user/role/admin", callback);
    } */


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
