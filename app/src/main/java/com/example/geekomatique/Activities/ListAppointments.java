/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : ListAppointments.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ListAppointments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_appointments);

        String date_n = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        TextView actualDate  = (TextView) findViewById(R.id.ActualDate);
        actualDate.setText(date_n);
    }

    public void ReturnAppointmentAct(View view) {
        Intent intent = new Intent(this, CalendarAppointments.class);
        startActivity(intent);
    }


    public void AppointmentByIdActivity(View view) {
        Intent intent = new Intent(this, Appointment.class);
        startActivity(intent);
    }
}