package com.example.geekomatique.Activities;/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : DisponibilitiesModel.java
 * Edited by pinbe
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.Models.DisponibilitiesModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;

import java.util.List;


public class Disponibilities extends AppCompatActivity {

    //Cette activité va prendre en compte la gestion des disponibilités horaires par l'administrateur
    private EditText day_1_Monday_Start;
    private EditText day_1_Monday_End;
    private EditText day_2_Thursday_Start;
    private EditText day_2_Thursday_End;
    private EditText day_3_Wednesday_Start;
    private EditText day_3_Wednesday_End;
    private EditText day_4_Thursday_Start;
    private EditText day_4_Thursday_End;
    private EditText day_5_Friday_Start;
    private EditText day_5_Friday_End;
    private EditText day_6_Saturday_Start;
    private EditText day_6_Saturday_End;
    private List<DisponibilitiesModel> disponibilitiesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilities);

        //On genere les differents composants du Layout
        day_1_Monday_Start = findViewById(R.id.MondayStart);
        day_1_Monday_End = findViewById(R.id.MondayEnd);

        day_2_Thursday_Start = findViewById(R.id.tuesdayStart);
        day_2_Thursday_End = findViewById(R.id.tuesdayEnd);

        day_3_Wednesday_Start = findViewById(R.id.wednesdayStart);
        day_3_Wednesday_End = findViewById(R.id.wednesdayEnd);

        day_4_Thursday_Start = findViewById(R.id.thursdayStart);
        day_4_Thursday_End = findViewById(R.id.thursdayEnd);

        day_5_Friday_Start = findViewById(R.id.fridayStart);
        day_5_Friday_End = findViewById(R.id.fridayEnd);

        day_6_Saturday_Start = findViewById(R.id.saturdayStart);
        day_6_Saturday_End = findViewById(R.id.saturdayEnd);
        getAllDisponibilities();

        //On associe au button un Listener qui va faire appel à une fonction pour valider
        Button validationBtn = findViewById(R.id.ValidateDispo);
        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDisponibilities();
            }
        });
    }

    private void getAllDisponibilities(){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {

                Log.i("getAllDisponibilities", result.toString());
                disponibilitiesList = JSONHelper.disponibilitiesListFromJSONArray(result);
                setDisponibilities();
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/time_slot/", callback);
        //Cette requete recupére les disponibilités de l'administrateur
    }

    private void setDisponibilities() {
        //On affiche les disponibilités dans chaques champs
        disponibilitiesList.forEach((disponibility) -> {
            switch (disponibility.getDay_number()) {
                case 1:
                    day_1_Monday_Start.setText(disponibility.getStart());
                    day_1_Monday_End.setText(disponibility.getEnd());
                    break;
                case 2:
                    day_2_Thursday_Start.setText(disponibility.getStart());
                    day_2_Thursday_End.setText(disponibility.getEnd());
                    break;
                case 3:
                    day_3_Wednesday_Start.setText(disponibility.getStart());
                    day_3_Wednesday_End.setText(disponibility.getEnd());
                    break;
                case 4:
                    day_4_Thursday_Start.setText(disponibility.getStart());
                    day_4_Thursday_End.setText(disponibility.getEnd());
                    break;
                case 5:
                    day_5_Friday_Start.setText(disponibility.getStart());
                    day_5_Friday_End.setText(disponibility.getEnd());
                    break;
                case 6:
                    day_6_Saturday_Start.setText(disponibility.getStart());
                    day_6_Saturday_End.setText(disponibility.getEnd());
                    break;
            }
        });
    }

    public void ReturnHomeAtivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void UpdateDisponibilities(){
        Intent intent = new Intent(this, HomeActivity.class);

        //PUT REQUEST

        startActivity(intent);
    }
}
