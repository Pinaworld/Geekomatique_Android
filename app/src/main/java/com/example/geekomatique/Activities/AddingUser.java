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
import android.text.TextUtils;
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

        boolean inputsAreValid = validateInputs(lastNamePost, firstNamePost,phoneFieldPost,emailFieldPost,loginFieldPost,psswdFieldPost);

        if(inputsAreValid) {
            UserModel userModel = new UserModel(firstNamePost,lastNamePost, phoneFieldPost,  loginFieldPost, roleName, emailFieldPost, roleId);
            userModel.setPassword(psswdFieldPost);
            userExist(userModel);
            Toast toast = Toast.makeText(getApplicationContext(), "Utilisateur "+ loginFieldPost + " crée !", Toast.LENGTH_SHORT );
            toast.show();

        }

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

    private boolean validateInputs(String lastName, String firstName, String phone, String email,String login, String psswd){//Si les entrées sont vides, on envoie un message sur l'entrée manquante

        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(getApplicationContext(), "Entrez le nom.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(getApplicationContext(), "Entrez le prénom.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "Entrez le téléphone.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Entrez le mail.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(login)) {
            Toast.makeText(getApplicationContext(), "Entrez le nom du compte.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(psswd)) {
            Toast.makeText(getApplicationContext(), "Entrez le mot de passe.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void ValidateNewUser() {

        Intent intent = new Intent(this, AdminUser.class);
        startActivity(intent);
    }
}
