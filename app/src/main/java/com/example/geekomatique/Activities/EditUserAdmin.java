/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : EditUserAdmin.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;

public class EditUserAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_admin);

        Button ReturnBut = findViewById(R.id.ReturnBut);

        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText phoneField = (EditText) findViewById(R.id.phoneField);
        EditText email = (EditText) findViewById(R.id.email);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int valueIdClicked = extras.getInt("id");//Récupération du champs de l'id

            getAdminUserById(valueIdClicked);
        }

        Button validationBtn = findViewById(R.id.Validate);

        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateEditUser(v);
            }
        });
    }

    private void getAdminUserById(int id){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                Log.i("getAdminUserById", result.toString());
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/user/" + id, callback);

    }

    public void ReturnAdminUser(View view) {
        Intent intent = new Intent(this, AdminUser.class);
        startActivity(intent);
    }

    public void ValidateEditUser(View view) {
        Intent intent = new Intent(this, AdminUser.class);




        startActivity(intent);
    }



}
