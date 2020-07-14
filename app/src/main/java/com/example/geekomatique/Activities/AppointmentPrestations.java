/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AppointmentPrestations.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
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
    TextView totalPriceTextView;
    Spinner spinnerPresta;
    Button ValidateBtn, AddPrestaBtn, resetListBtn;
    ListView listServiceAdded;
    AppointmentModel appointment;
    List<PrestationsModel> services;
    List<PrestationsModel> selectedServices;
    List<String> stringServices;
    AddressModel address;
    UserModel user;
    ProgressBar progressBarAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_prestations);
        appointment = (AppointmentModel) getIntent().getSerializableExtra("appointment");
        quantity_presta = findViewById(R.id.quantity_presta);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        spinnerPresta = findViewById(R.id.spinnerPresta);
        ValidateBtn = findViewById(R.id.ValidateBtn);
        AddPrestaBtn = findViewById(R.id.AddPrestaBtn);
        listServiceAdded = findViewById(R.id.listServiceAdded);
        resetListBtn = findViewById(R.id.resetListBtn);
        resetListBtn = findViewById(R.id.resetListBtn);
        stringServices = new ArrayList<>();
        selectedServices = new ArrayList<>();
        services = new ArrayList<>();

        totalPriceTextView.setText("0");

        ArrayAdapter arrayAdapterPresta = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringServices);
        listServiceAdded.setAdapter(arrayAdapterPresta);

        ValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidatePrestaAppointment();
            }
        });

        resetListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringServices = new ArrayList<>();
                selectedServices = new ArrayList<>();
                totalPriceTextView.setText("0");
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, stringServices);
                listServiceAdded.setAdapter(adapter);
            }
        });

        AddPrestaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer quantity = Integer.parseInt(quantity_presta.getText().toString());

                if(quantity > 0){
                    PrestationsModel service = (PrestationsModel) spinnerPresta.getSelectedItem();
                    service.setQuantity(quantity);
                    selectedServices.add(service);
                    String serviceString = service.getName() + " - " + service.getPrice() + "€" + " - Quantité: " + service.getQuantity();
                    stringServices.add(serviceString);
                    updatePrice(Integer.parseInt(service.getPrice()), service.getQuantity());

                    setListAdapter();
                }
            }
        });

        getUser();
        getAppointmentAddress();
        getAllServices();
    }

    private void updatePrice(Integer price, Integer quantity){
        Integer totalPrice = Integer.parseInt(totalPriceTextView.getText().toString());

        for(int i =0; i < quantity; i++){
            totalPrice += price;
        }

        totalPriceTextView.setText(totalPrice.toString());
    }

    private void setListAdapter(){
        ArrayAdapter arrayAdapterPresta = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringServices);
        listServiceAdded.setAdapter(arrayAdapterPresta);
    }

    private void refreshSpinnerAdapter(){
        ArrayAdapter servicesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, services);
        spinnerPresta.setAdapter(servicesAdapter);
    }

    private void getAllServices() {
        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
              services = JSONHelper.prestationsListFromJSONArray(result);
                refreshSpinnerAdapter();
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

        HTTPRequestHelper.getRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/address/" + appointment.getAdressInvoiceId(), callback);
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


    public void ValidatePrestaAppointment(){
        if(selectedServices.size() > 0) {
            createInvoice(getAllSelectedServices());
        }
        else{

        }
    }

    private List<PrestationsModel> getAllSelectedServices() {
        List<PrestationsModel> services = new ArrayList<>();
        selectedServices.forEach((service) ->{
            for(int i = 0; i < service.getQuantity(); i ++){
                services.add(service);
            }
        });

        return services;
    }

    public void sendEmailWithAttachment(JSONObject invoice){

        String message = "Votre rendez-vous du " + appointment.getDate() +
                " est validé. <br><br>" + (appointment.isRemote() ? "Un technicien se présentera chez vous à la date indiquée." : "Un technicien vous contactera au numéro de téléphone de votre compte à la date indiquée.<br><br> assurez vous d\'être disponible.");
        String subject = "Confirmation de rendez-vous";
        String email = user.getEmail();


        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback(){
            @Override
            public void onResponse(JSONObject result) {

                startActivity(new Intent(getApplicationContext(), CalendarAppointments.class));
            }
        };

        HTTPRequestHelper.postRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/mail/send/invoice", callback, JSONHelper.makeMailJSONObject(message, subject, email, invoice));
    }

    public void finishAppointment(JSONObject invoice){

        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                sendEmailWithAttachment(invoice);


                Toast toast = Toast.makeText( getApplicationContext(),"Le mail a bien été envoyé.", Toast.LENGTH_SHORT);
                toast.show();
                progressBarAP = (ProgressBar)findViewById(R.id.progressBar);
                progressBarAP.setVisibility(View.VISIBLE);
            }
        };

        HTTPRequestHelper.putRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/appointment/finish/" + appointment.getId(), callback, new JSONArray());
    }

    public void generatePdf(JSONObject invoice){
        JSONObject invoicePdf = JSONHelper.makePdfJSONObject(invoice, getApplicationContext());

        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback(){
            @Override
            public void onResponse(JSONObject result) {
                try{
                    invoice.put("Invoice_Base64", result.getString("Invoice_Base64"));
                    finishAppointment(invoice);
                }catch (JSONException ex){
                    Log.i("jsonex", ex.getMessage());
                }
            }
        };

        HTTPRequestHelper.postRequest(getApplicationContext(),"https://geekomatique.fr:5000"+ "/invoice/generate", callback, invoicePdf);
    }

    public void createInvoice(List<PrestationsModel> services){
        JSONObject invoice = JSONHelper.makeInvoiceJSONObject(user, services, address, appointment);

        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback(){
            @Override
            public void onResponse(JSONObject result) {
                try{
                    invoice.put("invoice_number", result.getString("invoice_number"));
                    invoice.put("invoice_date", result.getString("invoice_date"));
                    invoice.put("id", result.getString("id"));

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
