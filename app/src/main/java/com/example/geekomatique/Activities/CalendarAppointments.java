/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : CalendarAppointments.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;

import com.example.geekomatique.Adapters.AppointmentsAdapter;
import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.Models.AppointmentModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import java.util.List;

public class CalendarAppointments extends AppCompatActivity {
//Cette activité va prendre en charge l'affichage de tout les rendez-vous pour l'administration
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_appointments);

        getAllAppointments(this);

        //On genere les differents composants du Layout
        recyclerView = (RecyclerView) findViewById(R.id.appointmentList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }


    private void getAllAppointments(Context context){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                List<AppointmentModel> appointmentList= JSONHelper.appointmentListFromJSONArray(result);
                final RecyclerView.Adapter adapter = new AppointmentsAdapter(context, appointmentList);
                recyclerView.setAdapter(adapter);
            }
        };

        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/appointment/", callback);
        //Cette requete doit recuperer les differents rendez-vous
    }

    public void ReturnHomeAtivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
