/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : EditUserAdmin.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.Models.DisponibilitiesModel;
import com.example.geekomatique.Models.UserModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;

import java.util.List;

public class EditUserAdmin extends AppCompatActivity {
//Cette activité va prendre en charge la gestion des administrateurs



    private List<UserModel> UserModel;
    EditText lastName, firstName, phoneField, email;
    Button validationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_admin);

        //On genere les differents composants du Layout
        Button ReturnBut = findViewById(R.id.ReturnBut);

        lastName = findViewById(R.id.lastName);
        firstName =  findViewById(R.id.firstName);
        phoneField =  findViewById(R.id.phoneField);
        email =  findViewById(R.id.email);



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.getBoolean("keystring", true);

        validationBtn = findViewById(R.id.Validate);

        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateEditUser(v);
            }
        });
    }

    private void getAdminUserInfo(){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {

                Log.i("getAdminUserInfo", result.toString());
                UserModel = JSONHelper.userListFromJSONArray(result);
                setUserInfo();
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/user/", callback);
        //Cette requete recupére les disponibilités de l'administrateur
    }

    private void setUserInfo() {
        //On affiche les inforamtions de l'admin
        UserModel.forEach((disponibility) -> {
            firstName.setText("P");
            lastName.setText("P");
            phoneField.setText("P");
            email.setText("P");
        });
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
