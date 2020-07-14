/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : HTTPRequestHelper.java
 * Edited by pinbe
 */

package com.example.geekomatique.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.geekomatique.VolleyJSONArrayCallback;
import com.example.geekomatique.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class HTTPRequestHelper {

    public static void getRequest(final Context context, String url, final VolleyJSONArrayCallback callback){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray();
                            jsonArray.put(jsonObject);
                            callback.onResponse(jsonArray);
                        }
                        catch(JSONException ex)
                        {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                callback.onResponse(jsonArray);

                            } catch (JSONException exception) {
                                Toast.makeText(context, exception.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if(networkResponse.statusCode == HttpURLConnection.HTTP_NOT_FOUND){
                            callback.onResponse(null);
                        }
                        else{
                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            parseErrorMEssage(error, context);
                        }
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("Cookie", getCookie(context));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void postRequest(final Context context, String url, final VolleyJSONObjectCallback callback, final JSONObject params) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try{

                            Log.i("post",  new String(error.networkResponse.data,"UTF-8"));
                        }catch (Exception ex){

                        }
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Cookie", getCookie(context));

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    public static void deleteRequest(final Context context, String url, final VolleyJSONArrayCallback callback){

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray = new JSONArray();
                        callback.onResponse(jsonArray);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("delete", error.toString());
                        parseErrorMEssage(error, context);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Cookie", getCookie(context));

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void putRequest(final Context context, String url, final VolleyJSONArrayCallback callback, final JSONArray params) {
        JsonArrayRequest jsonArrayCallback = new JsonArrayRequest(Request.Method.PUT, url, params,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("put", error.toString());
                        parseErrorMEssage(error, context);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Cookie", getCookie(context));

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayCallback);
    }

    public static void patchRequest(final Context context, String url, final VolleyJSONObjectCallback callback, final JSONObject params) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        parseErrorMEssage(error, context);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Cookie", getCookie(context));

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
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

    private static String getCookie(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }
}