/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Prestations.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;


public class Prestations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestations);

        getAllService();
    }

    private void getAllService(){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                Log.i("getAllService", result.toString());

            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/service/", callback);
    }


    public void ReturnHomeAtivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void AddingPrestationAtivity(View view) {
        Intent intent = new Intent(this, AddingPrestation.class);
        startActivity(intent);
    }
}
