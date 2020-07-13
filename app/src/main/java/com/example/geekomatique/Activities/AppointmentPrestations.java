/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AppointmentPrestations.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.geekomatique.R;

import org.w3c.dom.Text;

public class AppointmentPrestations extends AppCompatActivity {

//Cette activité doit donner les prestations effectués par l'admin en vue d'envoyer la facture

    EditText quantity_presta;

    Spinner spinnerPresta;

    Button ValidateBtn, AddPrestaBtn;

    ListView listServiceAdded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_prestations);

        quantity_presta = findViewById(R.id.quantity_presta);
        spinnerPresta = findViewById(R.id.spinnerPresta);
        ValidateBtn = findViewById(R.id.ValidateBtn);
        AddPrestaBtn = findViewById(R.id.AddPrestaBtn);
        listServiceAdded = findViewById(R.id.listServiceAdded);


        ValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidatePrestaAppointment(v);
            }
        });
    }


    public void ValidatePrestaAppointment(View view){
        Intent intent = new Intent(this, Appointment.class);


        startActivity(intent);
    }

    public void UpdateListServiceAdded(){

    }

    public void ReturCalendarAppointment(View view){
        Intent intent = new Intent(this, CalendarAppointments.class);


        startActivity(intent);
    }

}
