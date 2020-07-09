/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AddingPrestation.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.geekomatique.Activities.HomeActivity;
import com.example.geekomatique.Activities.Prestations;
import com.example.geekomatique.R;

public class AddingPrestation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_prestation);
    }

    public void ReturnPrestation(View view) {
        Intent intent = new Intent(this, Prestations.class);
        startActivity(intent);
    }
}
