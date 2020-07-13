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
import android.widget.EditText;

import com.example.geekomatique.Activities.HomeActivity;
import com.example.geekomatique.Activities.Prestations;
import com.example.geekomatique.R;

public class AddingPrestation extends AppCompatActivity {
 //Cette activité doit prendre en charge l'ajout des services ou prestations proposé par l'administrateur

    EditText prestationName, prestationPrice;
    Button returnBtn, validationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_prestation);

        //On implemente les differents composants presents dans l'activité
        prestationName = findViewById(R.id.PrestationName);
        prestationPrice = findViewById(R.id.PrestationPrice);

        returnBtn = findViewById(R.id.ReturnBut);
        validationBtn = findViewById(R.id.ValidateBtn);

        //On set les listeners des boutons
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
        Intent intent = new Intent(this, Prestations.class);



        startActivity(intent);
    }
}
