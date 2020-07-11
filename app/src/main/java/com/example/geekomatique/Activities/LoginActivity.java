/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : LoginActivity.java
 * Edited by pinbe
 */

package com.example.geekomatique.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.geekomatique.Helpers.AuthenticatorHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    private EditText inputLogin, inputPassword;
    private Button btnLogin;

    private boolean authenticated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userIsAuthenticated();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputLogin = findViewById(R.id.LoginField);
        inputPassword = findViewById(R.id.PassdLoginField);
        btnLogin = findViewById(R.id.BtnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

    }

    private void userIsAuthenticated() {
        SharedPreferences prefs = this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

        String login = prefs.getString("LOGIN_PSEUDO", "");
        String password = prefs.getString("LOGIN_PWD", "");

        if(!login.isEmpty() || !password.isEmpty()){
            authenticated = true;
            authenticate(login, password);
        }
    }

    private void attemptLogin() {
        String login = inputLogin.getText().toString();
        final String password = inputPassword.getText().toString();
        boolean inputsAreValid = validateInputs(login, password);

        //authenticate user
        if(inputsAreValid) {
            authenticate(login, password);
        }
    }

    private boolean validateInputs(String login, String password){

        if (TextUtils.isEmpty(login)) {
            Toast.makeText(getApplicationContext(), R.string.enter_login, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.enter_passwd, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void authenticate(final String login, final String password){

        String url = getString(R.string.api_url) + "/authenticate/";
        Map<String, String> params = makeHashMap(login, password);

        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
                if(!authenticated){

                    try {
                        this.saveUserSharedPreferences(login,password, Long.valueOf(response.getString("id_user")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                finish();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }


            private void saveUserSharedPreferences(String login, String password, int userId) {
                SharedPreferences prefs = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("LOGIN_PSEUDO", login);
                editor.putString("LOGIN_PWD", password);
                editor.commit();
                prefs = getSharedPreferences("USER", Context.MODE_PRIVATE);
                editor = prefs.edit();
                editor.putLong("USERID", userId);
                editor.commit();
            }
        };

        AuthenticatorHelper.authenticate(getApplicationContext(), url, params, callback);
    }

    private Map<String, String> makeHashMap(String login, String password){
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("password", password);
        hashMap.put("name", login);

        return hashMap;
    }

}
