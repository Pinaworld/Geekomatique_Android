/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Prestations.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.PrestationsAdapter;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;


public class Prestations extends AppCompatActivity {


    private RecyclerView prestationsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestations);


        EditText prestationName = findViewById(R.id.PrestationName);
        EditText prestationPrice = findViewById(R.id.PrestationPrice);

        Button returnBtn = findViewById(R.id.ReturnBut);
        Button validationBtn = findViewById(R.id.addingPrestaBtn);

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

                prestationsRecyclerView = findViewById(R.id.prestationsAdapterView);
                prestationsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                final RecyclerView.Adapter adapter = new PrestationsAdapter(getApplicationContext(), JSONHelper.prestationsListFromJSONObject(result) );
                prestationsRecyclerView.setAdapter(adapter);
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/service/", callback);
    }


    public void ReturnHomeAtivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void AddingPrestation() {

        Toast toastConfirmedCancel = Toast.makeText(getApplicationContext(), "Prestation ajoutée !", Toast.LENGTH_SHORT);
        toastConfirmedCancel.show();
    }
}
