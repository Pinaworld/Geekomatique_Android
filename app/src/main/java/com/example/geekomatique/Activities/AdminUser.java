/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AdminUser.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;

public class AdminUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        getAllAdminUser();
    }


    private void getAllAdminUser(){
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                Log.i("getAllAdminUser", result.toString());

            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/user/employee", callback);
    }

    public void ReturnHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void AddingUserActivity(View view) {
        Intent intent = new Intent(this, AddingUser.class);
        startActivity(intent);
    }

    public void EditUserActivity(View view) {
        Intent intent = new Intent(this, EditUserAdmin.class);
        startActivity(intent);
    }

}
