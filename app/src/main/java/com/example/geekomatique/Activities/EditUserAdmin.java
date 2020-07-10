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
import android.view.View;
import android.widget.Button;

import com.example.geekomatique.R;

public class EditUserAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_admin);

        Button validationBtn = findViewById(R.id.Validate);

        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateEditUser(v);
            }
        });
    }

    public void ReturnAdminUser(View view) {
        Intent intent = new Intent(this, AdminUser.class);
        startActivity(intent);
    }

    public void ValidateEditUser(View view) {

    }



}
