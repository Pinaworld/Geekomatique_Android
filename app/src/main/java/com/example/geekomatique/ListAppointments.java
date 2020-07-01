/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : ListAppointments.java
 * Edited by pinbe
 */

package com.example.geekomatique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
