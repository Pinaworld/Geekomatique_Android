/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : CalendarAppointments.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;

import com.example.geekomatique.Adapters.AppointmentsAdapter;
import com.example.geekomatique.Adapters.PrestationsAdapter;
import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

public class CalendarAppointments extends AppCompatActivity {
//Cette activité va prendre en charge l'affichage de tout les rendez-vous pour l'administration
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_appointments);

        getAllAppointments();

        //On genere les differents composants du Layout
        recyclerView = (RecyclerView) findViewById(R.id.appointmentList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }


    private void getAllAppointments(){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                Log.i("getAllAppointments", result.toString());

                final RecyclerView.Adapter adapter = new AppointmentsAdapter(getApplicationContext(), JSONHelper.appointmentListFromJSONArray(result));
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

    public void ListAppointmentbyDayActivity(View view) {

        int valueIdClicked = 52; //GET FROM RECYCLERVIEW
        Intent intent = new Intent(this, Appointment.class); //On va envoyer la valeur de l'id dans l'intent de l'activité suivante
        intent.putExtra("id", valueIdClicked); //valueIdClicked a comme clé "id", on va le récuperer grâce à la clé
        startActivity(intent);
    }
}
