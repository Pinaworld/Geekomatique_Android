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
import android.widget.TextView;
import 	android.widget.CalendarView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.geekomatique.Helpers.AuthenticatorHelper;
import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.MyAdapter;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CalendarAppointments extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_appointments);

        getAllAppointments();

        recyclerView = (RecyclerView) findViewById(R.id.appointmentList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /* specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);*/

    }


    private void getAllAppointments(){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                Log.i("getAllAppointments", result.toString());

            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/appointment/", callback);
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
