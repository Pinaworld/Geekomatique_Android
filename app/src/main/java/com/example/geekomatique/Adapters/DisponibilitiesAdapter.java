/*
 * Copyright (c) 2020
 * Project: Geekomatique_Android
 * File : DisponibilitiesAdapter.java
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
import com.example.geekomatique.Models.Disponibilities;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;

import java.util.List;

public class DisponibilitiesAdapter extends RecyclerView.Adapter<DisponibilitiesAdapter.MyViewHolder> {

    Context context;
    List<Disponibilities> disponibilities;

    public DisponibilitiesAdapter(Context context, List<Disponibilities> disponibilities) {
        this.context = context;
        this.disponibilities = disponibilities;
    }

    @Override
    public DisponibilitiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.disponibilities_row, parent,false);
        return new DisponibilitiesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DisponibilitiesAdapter.MyViewHolder myViewHolder, final int position) {
        final Disponibilities disponibility = disponibilities.get(position);

        myViewHolder.disponibilityTitleRow.setText(disponibility.get());
        myViewHolder.disponibilityPriceRow.setText(disponibility.getPrice());

        myViewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
                    @Override
                    public void onResponse(JSONArray result) {
                        Toast.makeText(context, "Le service a bien été supprimé.", Toast.LENGTH_SHORT).show();
                        disponibilities.remove(position);
                        notifyDataSetChanged();
                    }

                };

                HTTPRequestHelper.deleteRequest(context,"https://geekomatique.fr:5000"+ "/service/" + disponibility.getId(), callback);
            }
        });
    }

    @Override
    public int getItemCount() {
        return disponibilities.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout disponibilityRow;
        TextView disponibilityTitleRow;
        TextView disponibilityPriceRow;
        Button deleteBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            disponibilityRow = itemView.findViewById(R.id.disponibilityRow);
            disponibilityTitleRow = itemView.findViewById(R.id.disponibilityTitleRow);
            disponibilityPriceRow = itemView.findViewById(R.id.disponibilityPriceRow);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

        }
    }

}
