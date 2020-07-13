/*
 * Copyright (c) 2020
 * Project: Geekomatique_Android
 * File : UsersAdapter.java
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
import com.example.geekomatique.Models.UserModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    Context context;
    List<UserModel> users;

    public UsersAdapter(Context context, List<UserModel> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_row, parent,false);
        return new UsersAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UsersAdapter.MyViewHolder myViewHolder, int position) {
        final UserModel user = users.get(myViewHolder.getLayoutPosition());

        //myViewHolder.userTitleRow.setText(user.getName());
        //myViewHolder.userPriceRow.setText(user.getPrice());

        myViewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
                    @Override
                    public void onResponse(JSONArray result) {
                        Toast.makeText(context, "Le service a bien été supprimé.", Toast.LENGTH_SHORT).show();
                        users.remove(myViewHolder.getLayoutPosition());
                        notifyDataSetChanged();
                    }
                };

                HTTPRequestHelper.deleteRequest(context,"https://geekomatique.fr:5000"+ "/service/" + user.getId(), callback);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout userRow;
        TextView userTitleRow;
        TextView userPriceRow;
        Button deleteBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            //userRow = itemView.findViewById(R.id.userRow);
            //userTitleRow = itemView.findViewById(R.id.userTitleRow);
            //userPriceRow = itemView.findViewById(R.id.userPriceRow);
            //deleteBtn = itemView.findViewById(R.id.deleteBtn);

        }
    }

}
