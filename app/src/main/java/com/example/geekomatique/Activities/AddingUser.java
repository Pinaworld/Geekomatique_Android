/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AddingUser.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.Models.RoleModel;
import com.example.geekomatique.Models.UserModel;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddingUser extends AppCompatActivity {

    //Cette activité doit prendre en charge l'ajout des administrateurs par l'administrateur

    private EditText lastName;
    private EditText firstName;
    private EditText phoneField;
    private EditText emailField;
    private EditText loginField;
    private EditText psswdField;
    private List<RoleModel> listRole;

    Spinner spinnerRole;
    Button returnBut;
    Button validationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_user);

        List<RoleModel> listRole = new ArrayList<>();


        //On implemente les differents composants presents dans l'activité
        lastName = findViewById(R.id.lastName);
        firstName = findViewById(R.id.firstName);
        phoneField = findViewById(R.id.phoneField);
        emailField = findViewById(R.id.emailField);
        loginField = findViewById(R.id.loginField);
        psswdField = findViewById(R.id.psswdField);

        spinnerRole = findViewById(R.id.spinnerRole);

        returnBut = findViewById(R.id.ReturnBut);
        validationBtn = findViewById(R.id.Validate);

        //On set les listeners des boutons
        validationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddingNewUser();
            }
        });
        getAllRole();
    }

    private void refreshSpinnerAdapter(){
        ArrayAdapter userAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listRole);
        spinnerRole.setAdapter(userAdapter);
    }


    private void getAllRole() {
        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                listRole = JSONHelper.roleFromJSONArray(result);
                refreshSpinnerAdapter();
            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(),getString(R.string.api_url)+ "/role", callback);
    }

    public void AddingNewUser(){

        String lastNamePost = lastName.getText().toString();
        String firstNamePost = firstName.getText().toString();
        String phoneFieldPost = phoneField.getText().toString();
        String emailFieldPost = emailField.getText().toString();
        String loginFieldPost = loginField.getText().toString();
        String psswdFieldPost = psswdField.getText().toString();
        RoleModel role = (RoleModel) spinnerRole.getSelectedItem();
        Integer roleId = role.getId();
        String roleName = role.getName();
        UserModel userModel = new UserModel(firstNamePost,lastNamePost, phoneFieldPost,  loginFieldPost, roleName, emailFieldPost, roleId);
        userModel.setPassword(psswdFieldPost);
        userExist(userModel);
    }

    public void createUser(UserModel user){
        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject result) {
                ValidateNewUser();
            }
        };
        HTTPRequestHelper.postRequest(getApplicationContext(),
                getString(R.string.api_url) + "/user/" ,callback,
                JSONHelper.makeUserJSONObject(user));
    }
    public void createUserCredentials(UserModel user){
        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject result) {
                try{
                    user.setCredentialId(result.getInt("id"));
                }catch(JSONException e){
                    Log.i("except", e.getMessage());
                }
                createUser(user);
            }
        };
        HTTPRequestHelper.postRequest(getApplicationContext(),
                getString(R.string.api_url) + "/user/credentials/" ,callback,
                JSONHelper.makeUserCredentialsJSONObject(user.getPassword(),user.getLogin(), user.getRoleID()));
    }
    public void userExist(UserModel user){
        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                try{
                    if(!result.getJSONObject(0).getBoolean("exists")){
                        createUserCredentials(user);
                    }
                }catch(JSONException e){
                    Log.i("except", e.getMessage());
                }

            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(),
                getString(R.string.api_url) + "/authenticate/exists/" + user.getLogin() + "/" + user.getEmail(), callback);
    }

    public void ReturnAdminUser(View view) {
        Intent intent = new Intent(this, AdminUser.class);
        startActivity(intent);
    }

    public void ValidateNewUser() {

        Toast toast = Toast.makeText(getApplicationContext(), "L'utilisateur a été créé !", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, AdminUser.class);
        startActivity(intent);
    }
}
