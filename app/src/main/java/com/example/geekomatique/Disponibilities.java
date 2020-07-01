/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Disponibilities.java
 * Edited by pinbe
 */

package com.example.geekomatique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Disponibilities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilities);
    }

    public void ReturnHomeAtivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

/**
    Spinner dropdown = findViewById(R.id.FirstDay);

    String[] itemsFirstDay = new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    String[] itemsLastDay = new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    String[] itemsFirstHour = new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    String[] itemsLastHour = new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
    ArrayAdapter<String> adapterFirstDay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsFirstDay);
    ArrayAdapter<String> adapterLastDay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsLastDay);
    ArrayAdapter<String> adapterFirstHour = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsFirstHour);
    ArrayAdapter<String> adapterLastHour = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsLastHour);
//set the spinners adapter to the previously created one.
//dropdown.setAdapter(adapter);*/
}
