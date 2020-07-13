/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AppointmentModel.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        TextView appointmentTitle = (TextView) findViewById(R.id.appointmentTitle);
        TextView appointmentTime = (TextView) findViewById(R.id.appointmentTime);
        TextView appointmentAdress = (TextView) findViewById(R.id.appointmentAdress);
        TextView customerMail = (TextView) findViewById(R.id.customerMail);
        TextView customerComment = (TextView) findViewById(R.id.customerComment);


        String date_n = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.actualDate);
        date.setText(date_n);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int valueIdClicked = extras.getInt("id");//Récupération du champs de l'id

            getAppointmentById(valueIdClicked);
        }

        Button returnHome = (Button) findViewById(R.id.returnListAppointmentsButt);
        Button modifyAppointmentButt = (Button) findViewById(R.id.ModifyAppointmentButt);
        Button cancelAppointmentButt = (Button) findViewById(R.id.CancelAppointmentButt);
        Button sendBillButt = findViewById(R.id.SendBillButt);

        modifyAppointmentButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyAppointmentActivity(v);
            }
        });

        cancelAppointmentButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelAppointmentActivity(v);
            }
        });

        sendBillButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendBill(v);
            }
        });

    }

    private void getAppointmentById(int id){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                    Log.i("getApointmentById", result.toString());
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/appointment/" + id, callback);

    }

    public void ReturnListAppointmentActivity(View view) {
        Intent intent = new Intent(this, CalendarAppointments.class);
        startActivity(intent);
    }

    public void ModifyAppointmentActivity(View view) {
        int valueIdClicked = 52; //GET FROM RECYCLERVIEW

        Intent intent = new Intent(this, ModifyAppointment.class); //On va envoyer la valeur de l'id dans l'intent de l'activité suivante
        intent.putExtra("id", valueIdClicked); //valueIdClicked a comme clé "id", on va le récuperer grâce à la clé
        startActivity(intent);
    }
    public void CancelAppointmentActivity(View view) {
        int valueIdClicked = 52; //GET FROM RECYCLERVIEW

        Intent intent = new Intent(this, CancelAppointment.class); //On va envoyer la valeur de l'id dans l'intent de l'activité suivante
        intent.putExtra("id", valueIdClicked); //valueIdClicked a comme clé "id", on va le récuperer grâce à la clé
        startActivity(intent);
    }

    public void SendBill(View view) {
        Toast toastBillSent = Toast.makeText(getApplicationContext(), "Facture envoyée !", Toast.LENGTH_SHORT);
        toastBillSent.show();


    }

}
