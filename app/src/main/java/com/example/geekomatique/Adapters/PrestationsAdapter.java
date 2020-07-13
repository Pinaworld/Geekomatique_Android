/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : PrestationsAdapter.java
 * Edited by pinbe
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
import com.example.geekomatique.Models.PrestationsModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;

import java.util.List;

public class PrestationsAdapter extends RecyclerView.Adapter<PrestationsAdapter.MyViewHolder> {

    Context context;
    List<PrestationsModel> prestations;

    public PrestationsAdapter(Context context, List<PrestationsModel> prestations) {
        this.context = context;
        this.prestations = prestations;
    }

    @Override
    public PrestationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.prestations_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PrestationsAdapter.MyViewHolder myViewHolder, int position) {
        final PrestationsModel prestation = prestations.get(myViewHolder.getLayoutPosition());

        myViewHolder.prestationTitleRow.setText(prestation.getName());
        myViewHolder.prestationPriceRow.setText(prestation.getPrice());

        myViewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
                    @Override
                    public void onResponse(JSONArray result) {
                        Toast.makeText(context, "Le service a bien été supprimé.", Toast.LENGTH_SHORT).show();
                        prestations.remove(position);
                        notifyDataSetChanged();
                    }

                };
                    HTTPRequestHelper.deleteRequest(context,"https://geekomatique.fr:5000"+ "/service/" + prestation.getId(), callback);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prestations.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout prestationRow;
        TextView prestationTitleRow;
        TextView prestationPriceRow;
        Button deleteBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            prestationRow = itemView.findViewById(R.id.prestationRow);
            prestationTitleRow = itemView.findViewById(R.id.prestationTitleRow);
            prestationPriceRow = itemView.findViewById(R.id.prestationPriceRow);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

        }
    }

}
