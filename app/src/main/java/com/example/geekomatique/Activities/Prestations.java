/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : PrestationsModel.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.Adapters.PrestationsAdapter;
import com.example.geekomatique.Models.PrestationsModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Prestations extends AppCompatActivity {
//Cette activité doit gerer les prestations proposé par l'admin

    private RecyclerView prestationsRecyclerView;
    private EditText prestationName, prestationPrice;
    private Button returnBtn, validationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestations);

        //On implemente les differents composants presents dans l'activité
        prestationName = findViewById(R.id.PrestationName);
        prestationPrice = findViewById(R.id.PrestationPrice);

        returnBtn = findViewById(R.id.ReturnBut);
        validationBtn = findViewById(R.id.addingPrestaBtn);

        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddingPrestation();
            }
        });

        getAllService();
    }

    private void getAllService(){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                Log.i("getAllService", result.toString());

                prestationsRecyclerView = findViewById(R.id.prestationsAdapterView);//On remplie le recyclerview avec les prestations
                prestationsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                final RecyclerView.Adapter adapter = new PrestationsAdapter(getApplicationContext(), JSONHelper.prestationsListFromJSONArray(result) );
                prestationsRecyclerView.setAdapter(adapter);
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/service/", callback);
        //Requette pour get les prestations
    }


    public void ReturnHomeAtivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void AddingPrestation() {
        VolleyJSONObjectCallback callback1 = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {

            }
        };
        String prestaName = prestationName.getText().toString();
        String prestaValuePrice = prestationPrice.getText().toString();
        //int prestaPrice =Integer.parseInt(prestaValuePrice);
        PrestationsModel prestaNew = new PrestationsModel(prestaName, prestaValuePrice);

        boolean inputsAreValid = validateInputs(prestaName, prestaValuePrice);
        if(inputsAreValid) {
            HTTPRequestHelper.postRequest(getApplicationContext(), getString(R.string.api_url) + "/service/", callback1, JSONHelper.makeServiceJSONObject(prestaNew));

            Toast toastConfirmedCancel = Toast.makeText(getApplicationContext(), "Prestation ajoutée !", Toast.LENGTH_SHORT);
            toastConfirmedCancel.show();

            Intent intent = new Intent(this, Prestations.class);
            startActivity(intent);
        }

    }

    private boolean validateInputs(String prestaName, String prestaValuePrice){//Si les entrées sont vides, on envoie un message sur l'entrée manquante

        if (TextUtils.isEmpty(prestaName)) {
            Toast.makeText(getApplicationContext(), "Choisissez une prestation.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(prestaValuePrice)) {
            Toast.makeText(getApplicationContext(), "Entrez un montant.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
