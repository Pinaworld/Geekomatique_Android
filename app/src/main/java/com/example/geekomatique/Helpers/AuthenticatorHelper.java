/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AuthenticatorHelper.java
 * Edited by pinbe
 */

package com.example.geekomatique.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class AuthenticatorHelper {


    public static void authenticate(final Context context, String url, Map<String, String> params, final VolleyJSONObjectCallback callback){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Test connect", response.toString());
                        callback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Test connect", error.toString());
                        parseErrorMEssage(error, context);
                    }
                }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                // will Volley use, we have to handle and store session cookies manually
                saveToken( response.headers.get("Set-Cookie"), context);
                return super.parseNetworkResponse(response);
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    private static void saveToken(String token, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        Log.i("tolken", token);
        editor.commit();
    }

    private static void parseErrorMEssage(VolleyError error, Context context){
        if(error.networkResponse != null){
            String body = "";
            //get status code here
            String statusCode = String.valueOf(error.networkResponse.statusCode);
            //get response body and parse with appropriate encoding
            if(error.networkResponse.data!=null) {
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(context, body, Toast.LENGTH_LONG).show();
        }
    }

}
