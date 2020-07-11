/*
 * Copyright (c) 2020
 * Project: Geekomatique_Android
 * File : AppointmentsAdapter.java
 * Edited by lamyg
 */

package com.example.geekomatique.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Models.Appointment;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.MyViewHolder> {

    Context context;
    List<Appointment> appointments;

    public AppointmentsAdapter(Context context, List<Appointment> appointments) {
        this.context = context;
        this.appointments = appointments;
    }

    @Override
    public AppointmentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.appointment_row, parent,false);
        return new AppointmentsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointmentsAdapter.MyViewHolder myViewHolder, final int position) {
        final Appointment appointment = appointments.get(position);

        myViewHolder.appointmentDecriptionRow.setText(appointment.getDescription());
        myViewHolder.appointmentStatusRow.setText((appointment.isDone()) ? "Terminé": "En cours");
        myViewHolder.appointmentDateRow.setText(appointment.getDate());

        if(appointment.isDone()){
            myViewHolder.deleteBtn.setVisibility(View.GONE);
            myViewHolder.finishAppButton.setVisibility(View.GONE);
            myViewHolder.validateAppButton.setVisibility(View.GONE);
        }
        if(appointment.isValidate()){
            myViewHolder.validateAppButton.setVisibility(View.GONE);
        }

        myViewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
                    @Override
                    public void onResponse(JSONArray result) {
                        Toast.makeText(context, "Le rendez-vous a bien été annulé.", Toast.LENGTH_SHORT).show();
                        appointments.remove(position);
                        notifyDataSetChanged();
                    }

                };

                HTTPRequestHelper.deleteRequest(context,"https://geekomatique.fr:5000"+ "/service/" + appointment.getId(), callback);
            }
        });

        myViewHolder.validateAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback(){
                    @Override
                    public void onResponse(JSONObject result) {
                        Toast.makeText(context, "Le rendez-vous a bien été validé.", Toast.LENGTH_SHORT).show();
                        appointment.setValidate(true);
                        notifyDataSetChanged();
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
                        Toast.makeText(context, "Le rendez-vous a bien été clôturé.", Toast.LENGTH_SHORT).show();
                        appointment.setDone(true);
                        notifyDataSetChanged();
                    }
                };

                HTTPRequestHelper.putRequest(context,"https://geekomatique.fr:5000"+ "/appointment/finish/" + appointment.getId(), callback, new JSONObject());

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
            deleteBtn = itemView.findViewById(R.id.validateAppButton);
            deleteBtn = itemView.findViewById(R.id.finishAppButton);
        }
    }

}