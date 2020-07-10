/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Disponibilities.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.geekomatique.Helpers.AuthenticatorHelper;
import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Disponibilities extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilities);

        EditText day_1_Monday_Start = (EditText) findViewById(R.id.MondayStart);
        EditText day_1_Monday_End = (EditText) findViewById(R.id.MondayEnd);

        EditText day_2_Thursday_Start = (EditText) findViewById(R.id.tuesdayStart);
        EditText day_2_Thursday_End = (EditText) findViewById(R.id.tuesdayEnd);

        EditText day_3_Wednesday_Start = (EditText) findViewById(R.id.wednesdayStart);
        EditText day_3_Wednesday_End = (EditText) findViewById(R.id.wednesdayEnd);

        EditText day_4_Thursday_Start = (EditText) findViewById(R.id.thursdayStart);
        EditText day_4_Thursday_End = (EditText) findViewById(R.id.thursdayEnd);

        EditText day_5_Friday_Start = (EditText) findViewById(R.id.fridayStart);
        EditText day_5_Friday_End = (EditText) findViewById(R.id.fridayEnd);

        EditText day_6_Saturday_Start = (EditText) findViewById(R.id.saturdayStart);
        EditText day_6_Saturday_End = (EditText) findViewById(R.id.saturdayEnd);

        getAllDisponibilities();

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

            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/time_slot/", callback);
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
