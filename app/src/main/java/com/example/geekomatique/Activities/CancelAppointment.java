/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : CancelAppointment.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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


public class CancelAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_appointment);

        TextView appointmentTitle = (TextView) findViewById(R.id.appointmentTitle);
        TextView appointmentTime = (TextView) findViewById(R.id.appointmentTime);
        TextView appointmentAdress = (TextView) findViewById(R.id.appointmentAdress );
        TextView customerMail = (TextView) findViewById(R.id.customerMail);
        TextView customerComment = (TextView) findViewById(R.id.customerComment);


        EditText cancelReason = (EditText) findViewById(R.id.CancelReason);

        String date_n = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.actualDate);
        date.setText(date_n);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int valueIdClicked = extras.getInt("id");
            showAppointment(valueIdClicked);
        }


        Button cancelAppointment = findViewById(R.id.CancelAppointmentButt);
        cancelAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelAppointment(v);
            }
        });
    }

    private void showAppointment(int id){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                Log.i("showAppointment", result.toString());
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/appointment/" + id, callback);

    }

    public void ReturnAppointmentActivity(View view) {
        Intent intent = new Intent(this, Appointment.class);
        startActivity(intent);
    }

    public void CancelAppointment(View view){

        Toast toastConfirmedCancel = Toast.makeText(getApplicationContext(), "Rendez-vous annulé !", Toast.LENGTH_SHORT);
        toastConfirmedCancel.show();

        ReturnAppointmentActivity(view);
    }

}
