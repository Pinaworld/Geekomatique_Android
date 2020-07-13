/*
 * Copyright (c) 2020
 * Project: Geekomatique_Android
 * File : AppointmentsAdapter.java
 * Edited by lamyg
 */

package com.example.geekomatique.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.MailService;
import com.example.geekomatique.Models.AppointmentModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.MyViewHolder> {

    Context context;
    List<AppointmentModel> appointments;

    public AppointmentsAdapter(Context context, List<AppointmentModel> appointments) {
        this.context = context;
        this.appointments = appointments;
        Log.i("adapt", appointments.toString());
    }

    @Override
    public AppointmentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.appointment_row, parent,false);
        return new AppointmentsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AppointmentsAdapter.MyViewHolder myViewHolder, int position) {
        final AppointmentModel appointment = appointments.get(position);

        myViewHolder.appointmentDecriptionRow.setText(appointment.getDescription());
        myViewHolder.appointmentStatusRow.setText((appointment.isDone()) ? "Terminé": "En cours");
        myViewHolder.appointmentDateRow.setText(appointment.getDate());

        if(appointment.isDone()){
            myViewHolder.deleteBtn.setVisibility(View.GONE);
            myViewHolder.finishAppButton.setVisibility(View.GONE);
            myViewHolder.validateAppButton.setVisibility(View.GONE);
        }
        else if(appointment.isValidate()){
            myViewHolder.validateAppButton.setVisibility(View.GONE);
        }
        else{
            myViewHolder.finishAppButton.setVisibility(View.GONE);

        }

        if(appointment.isValidate()){
            myViewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
                        @Override
                        public void onResponse(JSONArray result) {
                            appointments.remove(position);
                            notifyDataSetChanged();
                        }
                    };

                    HTTPRequestHelper.deleteRequest(context,"https://geekomatique.fr:5000"+ "/appointment/" + appointment.getId(), callback);
                }
            });
        }
        else{
            myViewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
                        @Override
                        public void onResponse(JSONArray result) {
                            VolleyJSONArrayCallback callback2 = new VolleyJSONArrayCallback(){
                                @Override
                                public void onResponse(JSONArray result) {
                                    String message =   "Bonjour, <br ><br > Votre rendez-vous du "+ appointment.getDate() +" a été totalement annulé en raison d'une indisponibilité du technicien." +
                                            "<br ><br > Nous vous invitons à prendre un nouveau rendez-vous sur https://geekomatique.fr.<br ><br > " +
                                            "Nous sommes désolé pour ce désagréement.<br ><br >";
                                    String email = "";
                                    try{
                                        email = result.getJSONObject(0).getString("email");
                                    }catch (JSONException ex){

                                    }

                                    MailService.sendMailToClient(context, "Rendez-vous Annulé", message, email, new VolleyJSONObjectCallback() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            appointments.remove(position);
                                            notifyDataSetChanged();
                                        }
                                    });
                                }

                            };
                            HTTPRequestHelper.getRequest(context,"https://geekomatique.fr:5000"+ "/user/" + appointment.getUserId(), callback2);
                        }

                    };
                    HTTPRequestHelper.deleteRequest(context,"https://geekomatique.fr:5000"+ "/appointment/" + appointment.getId(), callback);
                }
            });
        }


        myViewHolder.validateAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback(){
                    @Override
                    public void onResponse(JSONObject result) {
                        VolleyJSONArrayCallback callback2 = new VolleyJSONArrayCallback(){
                            @Override
                            public void onResponse(JSONArray result) {
                                String message = "Bonjour, <br><br>Votre rendez-vous du " + appointment.getDate() + " est validé. <br><br>" +
                                        (appointment.isRemote() ? "Un technicien se présentera chez vous à la date indiquée." : "Un technicien vous contactera au numéro de téléphone de votre compte à la date indiquée.<br><br> assurez vous d\'être disponible.");
                                String email = "";
                                try{
                                    email = result.getJSONObject(0).getString("email");
                                }catch (JSONException ex){

                                }

                                MailService.sendMailToClient(context, "Confirmation de rendez-vous", message, email, new VolleyJSONObjectCallback() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        appointment.setValidate(true);
                                        notifyDataSetChanged();
                                    }
                                });
                            }

                        };
                        HTTPRequestHelper.getRequest(context,"https://geekomatique.fr:5000"+ "/user/" + appointment.getUserId(), callback2);
                    }
                };
                HTTPRequestHelper.putRequest(context,"https://geekomatique.fr:5000"+ "/appointment/validate/" + appointment.getId(), callback, new JSONObject());
            }
        });

        myViewHolder.finishAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback(){
                    @Override
                    public void onResponse(JSONObject result) {
                        VolleyJSONArrayCallback callback2 = new VolleyJSONArrayCallback(){
                            @Override
                            public void onResponse(JSONArray result) {
                                String message = "Votre rendez-vous du " + appointment.getDate() +
                                        " est validé. <br><br>" + (appointment.isRemote() ? "Un technicien se présentera chez vous à la date indiquée." : "Un technicien vous contactera au numéro de téléphone de votre compte à la date indiquée.<br><br> assurez vous d\'être disponible.");
                                String email = "";
                                try{
                                    email = result.getJSONObject(0).getString("email");
                                }catch (JSONException ex){

                                }

                                MailService.sendMailToClient(context, "Confirmation de rendez-vous", message, email, new VolleyJSONObjectCallback() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        appointment.setValidate(true);
                                        notifyDataSetChanged();
                                    }
                                });
                            }

                        };
                        HTTPRequestHelper.getRequest(context,"https://geekomatique.fr:5000"+ "/user/" + appointment.getUserId(), callback2);
                    }

                };
                HTTPRequestHelper.putRequest(context,"https://geekomatique.fr:5000"+ "/appointment/validate/" + appointment.getId(), callback, new JSONObject());

            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout appointmentRow;
        TextView appointmentDecriptionRow;
        TextView appointmentStatusRow;
        TextView appointmentDateRow;
        Button deleteBtn;
        Button validateAppButton;
        Button finishAppButton;

        public MyViewHolder(View itemView) {
            super(itemView);

            appointmentRow = itemView.findViewById(R.id.appointmentRow);
            appointmentDecriptionRow = itemView.findViewById(R.id.appointmentDescriptionRow);
            appointmentStatusRow = itemView.findViewById(R.id.appointmentStatusRow);
            appointmentDateRow = itemView.findViewById(R.id.appointmentDateRow);
            deleteBtn = itemView.findViewById(R.id.deleteAppBtn);
            validateAppButton = itemView.findViewById(R.id.validateAppButton);
            finishAppButton = itemView.findViewById(R.id.finishAppButton);
        }
    }

}