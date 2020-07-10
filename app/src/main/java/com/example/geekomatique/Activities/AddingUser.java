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
import android.widget.Button;
import android.widget.EditText;

import com.example.geekomatique.R;

public class AddingUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_user);

        EditText lastName = findViewById(R.id.lastName);
        EditText firstName = findViewById(R.id.firstName);
        EditText phoneField = findViewById(R.id.phoneField);
        EditText emailField = findViewById(R.id.emailField);

        Button returnBut = findViewById(R.id.ReturnBut);
        Button validationBtn = findViewById(R.id.Validate);

        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateNewUser(v);
            }
        });
    }

    public void ReturnAdminUser(View view) {
        Intent intent = new Intent(this, AdminUser.class);
        startActivity(intent);
    }

    public void ValidateNewUser(View view) {
        Intent intent = new Intent(this, AdminUser.class);


        startActivity(intent);
    }
}
