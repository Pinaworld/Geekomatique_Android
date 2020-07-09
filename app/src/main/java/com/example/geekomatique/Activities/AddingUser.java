/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AddingUser.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.geekomatique.R;

public class AddingUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_user);
    }

    public void ReturnAdminUser(View view) {
        Intent intent = new Intent(this, AdminUser.class);
        startActivity(intent);
    }

}
