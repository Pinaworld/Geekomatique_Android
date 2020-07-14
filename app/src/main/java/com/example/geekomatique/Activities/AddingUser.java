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
import android.widget.Spinner;

import com.example.geekomatique.R;

public class AddingUser extends AppCompatActivity {

    //Cette activité doit prendre en charge l'ajout des administrateurs par l'administrateur

    private EditText lastName;
    private EditText firstName;
    private EditText phoneField;
    private EditText emailField;
    Spinner spinnerRole;
    Button returnBut;
    Button validationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_user);

        //On implemente les differents composants presents dans l'activité
        lastName = findViewById(R.id.lastName);
        firstName = findViewById(R.id.firstName);
        phoneField = findViewById(R.id.phoneField);
        emailField = findViewById(R.id.emailField);

        spinnerRole = findViewById(R.id.spinnerRole);

        returnBut = findViewById(R.id.ReturnBut);
        validationBtn = findViewById(R.id.Validate);

        //On set les listeners des boutons
        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
