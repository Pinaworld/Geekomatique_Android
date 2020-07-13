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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.geekomatique.Adapters.PrestationsAdapter;
import com.example.geekomatique.Adapters.UsersAdapter;
import com.example.geekomatique.Helpers.HTTPRequestHelper;
import com.example.geekomatique.Helpers.JSONHelper;
import com.example.geekomatique.R;
import com.example.geekomatique.VolleyJSONArrayCallback;

import org.json.JSONArray;

public class AdminUser extends AppCompatActivity {
//Cette activité doit prendre en charge l'afficharge des adminstrateurs

    Button returnBut, ToErase, addingUserBtn, ModifyOwnInfoBtn;
    RecyclerView AdminUserRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);


        //On implemente les differents composants presents dans l'activité
        addingUserBtn = findViewById(R.id.AddingUserBtn);
        returnBut = findViewById(R.id.ReturnBut);
        ModifyOwnInfoBtn = findViewById(R.id.ModifyOwnInfoBtn);

        ToErase = findViewById(R.id.YODOO); //RECYCLERVIEW OnclickListener // A SUPP

        getAllAdminUser();

        ToErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUserActivity(v);
            }
        });
    }


    private void getAllAdminUser(){ //On va get les Users admin dans l'API
        VolleyJSONArrayCallback callback =  new VolleyJSONArrayCallback(){
            @Override
            public void onResponse(JSONArray result) {
                Log.i("getAllAdminUser", result.toString());

               /* AdminUserRecyclerView = findViewById(R.id.AdminUserList);//On remplie le recyclerview avec les users
                AdminUserRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                final RecyclerView.Adapter adapter = new UsersAdapter(getApplicationContext(), JSONHelper.userListFromJSONArray(result) );
                AdminUserRecyclerView.setAdapter(adapter); */

            }
        };
        HTTPRequestHelper.getRequest(getApplicationContext(), getString(R.string.api_url) + "/user/employee", callback);
        //Cette requete recupére les AdminUSers
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

        int valueIdClicked = 52; //GET FROM RECYCLERVIEW

        Intent intent = new Intent(this, EditUserAdmin.class); //On va envoyer la valeur de l'id dans l'intent de l'activité suivante
        intent.putExtra("id", valueIdClicked); //valueIdClicked a comme clé "id", on va le récuperer grâce à la clé
        startActivity(intent);

    }

}
