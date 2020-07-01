/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : CalendarAppointments.java
 * Edited by pinbe
 */

package com.example.geekomatique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import 	android.widget.CalendarView;

import org.w3c.dom.Text;

public class CalendarAppointments extends AppCompatActivity {

    CalendarAppointments calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_appointments);

        /**calender = (CalendarAppointments)
                findViewById(R.id.calender);*/


        TextView mailAdress = (TextView) findViewById(R.id.MailShow);
        mailAdress.setText("juan@pino.com");
    }


    public void getDate(){

    }
    public void ReturnHomeAtivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void ListAppointmentbyDayActivity(View view) {
        Intent intent = new Intent(this, Appointment.class);
        startActivity(intent);
    }
}
