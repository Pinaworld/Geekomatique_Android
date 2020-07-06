/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : Appointment.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        String date_n = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.actualDate);
        date.setText(date_n);
    }

    public void ReturnListAppointmentActivity(View view) {
        Intent intent = new Intent(this, CalendarAppointments.class);
        startActivity(intent);
    }
    public void ModifyAppointmentActivity(View view) {
        Intent intent = new Intent(this, ModifyAppointment.class);
        startActivity(intent);
    }
    public void CancelApointmentActivity(View view) {
        Intent intent = new Intent(this, CancelAppointment.class);
        startActivity(intent);
    }

    public void SendBill(View view){
        Toast toastBillSent = Toast.makeText(getApplicationContext(), "Facture envoyée !", Toast.LENGTH_SHORT);
        toastBillSent.show();

        /**   button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
         your handler code here
         }
         } */
    }

}
