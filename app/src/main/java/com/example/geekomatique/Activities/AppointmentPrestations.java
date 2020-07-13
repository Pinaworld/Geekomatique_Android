/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AppointmentPrestations.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.MailService;
import com.example.geekomatique.Models.AddressModel;
import com.example.geekomatique.Models.AppointmentModel;
import com.example.geekomatique.Models.PrestationsModel;
import com.example.geekomatique.Models.UserModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class AppointmentPrestations extends AppCompatActivity {

//Cette activité doit donner les prestations effectués par l'admin en vue d'envoyer la facture

    EditText quantity_presta;
    Spinner spinnerPresta;
    Button ValidateBtn, AddPrestaBtn;
    ListView listServiceAdded;
    AppointmentModel appointment;
    List<PrestationsModel> services;
    List<PrestationsModel> selectedServices;
    AddressModel address;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_prestations);
        appointment = (AppointmentModel) getIntent().getSerializableExtra("appointment");
        quantity_presta = findViewById(R.id.quantity_presta);
        spinnerPresta = findViewById(R.id.spinnerPresta);
        ValidateBtn = findViewById(R.id.ValidateBtn);
        AddPrestaBtn = findViewById(R.id.AddPrestaBtn);
        listServiceAdded = findViewById(R.id.listServiceAdded);

        selectedServices = new ArrayList<>();

        ArrayList<Prestations> arrayListPresta = new ArrayList<>();
        ArrayAdapter arrayAdapterPresta = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListPresta);

        listServiceAdded.setAdapter(arrayAdapterPresta);

        ArrayAdapter servicesAdapter = new ArrayAdapter(this, R.layout.spinner);
        spinnerPresta.setAdapter(servicesAdapter);

        ValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidatePrestaAppointment(v);
            }
        });

        AddPrestaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String presta = spinnerPresta.getTag().toString();
                String quantity = quantity_presta.getText().toString();

                if( quantity == null){
                    quantity = "1";
                }

                UpdateListServiceAdded(arrayAdapterPresta, arrayListPresta, quantity, presta);
            }
        });

        getUser();
        getAppointmentAddress();
        getAllServices();
    }

    private void getAllServices() {
        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
              services = JSONHelper.prestationsListFromJSONArray(result);
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/service/", callback);
    }

    private void getAppointmentAddress() {
        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                try{
                    address = JSONHelper.addressListFromJSONObject(result.getJSONObject(0));
                }catch (JSONException ex){
                    Log.i("jsonparse", ex.getMessage());
                }
            }
        };

        HTTPRequestHelper.getRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/address/" + appointment.getId(), callback);
    }

    private void getUser(){
        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                try{
                    user = JSONHelper.userFromJSONObject(result.getJSONObject(0));
                }catch (JSONException ex){
                    Log.i("jsonparse", ex.getMessage());
                }
            }
        };

        HTTPRequestHelper.getRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/user/" + appointment.getUserId(), callback);
    }


    public void ValidatePrestaAppointment(View view){
        if(selectedServices.size() > 0) {
            createInvoice();
        }
        else {

        }
    }

    public void UpdateListServiceAdded(ArrayAdapter arrayAdapterPresta, ArrayList arrayListPresta,String quantity, String presta){

        arrayListPresta.add("Préstation : " + presta + " \n" + "Quantité : " + quantity);

        arrayAdapterPresta.notifyDataSetChanged();

    }

    public void sendEmailWithAttachment(JSONObject invoice){
        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                String message = "Votre rendez-vous du " + appointment.getDate() +
                        " est validé. <br><br>" + (appointment.isRemote() ? "Un technicien se présentera chez vous à la date indiquée." : "Un technicien vous contactera au numéro de téléphone de votre compte à la date indiquée.<br><br> assurez vous d\'être disponible.");

                MailService.sendMailToWithAttachment(getApplicationContext(), "Confirmation de rendez-vous", message, user.getEmail(), invoice, new VolleyJSONObjectCallback() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                });
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/mail/send/invoice" + appointment.getUserId(), callback);
    }

    public void generatePdf(JSONObject invoice){
        JSONObject invoicePdf = JSONHelper.makePdfJSONObject(invoice);

        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback(){
            @Override
            public void onResponse(JSONObject result) {
                try{
                    invoice.put("Invoice_Base64", result.getString("Invoice_Base64"));
                    sendEmailWithAttachment(invoice);
                }catch (JSONException ex){
                    Log.i("jsonex", ex.getMessage());
                }
            }
        };

        HTTPRequestHelper.postRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/invoice/generate", callback, invoicePdf);
    }

    public void createInvoice(){
        JSONObject invoice = JSONHelper.makeInvoiceJSONObject(user, selectedServices, address, appointment);

        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback(){
            @Override
            public void onResponse(JSONObject result) {
                try{
                    invoice.put("invoice_number", result.getString("invoice_number"));
                    invoice.put("invoice_date", result.getString("invoice_date"));
                }catch (JSONException ex){
                    Log.i("jsonex", ex.getMessage());
                }

                generatePdf(invoice);
            }
        };

        HTTPRequestHelper.postRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/invoice", callback, invoice);
    }


    public void ReturCalendarAppointment(View view){
        Intent intent = new Intent(this, CalendarAppointments.class);
        startActivity(intent);
    }

}
