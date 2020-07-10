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
import android.widget.Button;

import com.example.geekomatique.Activities.HomeActivity;
import com.example.geekomatique.Activities.Prestations;
import com.example.geekomatique.R;

public class AddingPrestation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_prestation);

        Button validationBtn = findViewById(R.id.ValidateBtn);

        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateAddingPrestation(v);
            }
        });
    }

    public void ReturnPrestation(View view) {
        Intent intent = new Intent(this, Prestations.class);
        startActivity(intent);
    }

    public void ValidateAddingPrestation(View view){


    }
}
