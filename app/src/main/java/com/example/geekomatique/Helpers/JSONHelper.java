/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : JSONHelper.java
 * Edited by pinbe
 */

package com.example.geekomatique.Helpers;

import android.util.Log;

import com.example.geekomatique.Models.Appointment;
import com.example.geekomatique.Models.DisponibilitiesModel;
import com.example.geekomatique.Models.PrestationsModel;
import com.example.geekomatique.Models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONHelper {

    public static List<Appointment> appointmentListFromJSONArray(JSONArray appointmentJSONArray) {
        List<Appointment> appointmentList = new ArrayList<>();

        if(appointmentJSONArray != null) {
            for (int i = 0; i < appointmentJSONArray.length(); i++) {

                try {
                    appointmentList.add(appointmentFromJSONObject(appointmentJSONArray.getJSONObject(i)));
                } catch (JSONException exception) {
                }
            }
            return appointmentList;
        }
        return null;
    }


    public static List<DisponibilitiesModel> disponibilitiesListFromJSONArray(JSONArray disponibilitiesJSONArray) {
        List<DisponibilitiesModel> disponibilitiesModelList = new ArrayList<>();

        if(disponibilitiesJSONArray != null) {
            for (int i = 0; i < disponibilitiesJSONArray.length(); i++) {
                try {
                    disponibilitiesModelList.add(disponibilityFromJSONObject(disponibilitiesJSONArray.getJSONObject(i)));

                } catch (JSONException exception) {

                }
            }
            return disponibilitiesModelList;
        }

        return null;
    }

    public static List<PrestationsModel> prestationsListFromJSONObject(JSONArray prestationsJSONArray) {
        List<PrestationsModel> prestationsList = new ArrayList<>();

        if(prestationsJSONArray != null) {
            for (int i = 0; i < prestationsJSONArray.length(); i++) {
                try {
                  prestationsList.add(prestationFromJSONObject(prestationsJSONArray.getJSONObject(i)));
                } catch (JSONException exception) {

                }
            }
            return prestationsList ;
        }

        return null;
    }


    public static List<UserModel> userListFromJSONArray(JSONArray userJSONArray) {
        List<UserModel> userList = new ArrayList<>();

        if(userJSONArray != null) {
            for (int i = 0; i < userJSONArray.length(); i++) {
                try {
                    userList.add(userFromJSONObject(userJSONArray.getJSONObject(i)));
                } catch (JSONException exception) {

                }
            }
            return userList ;
        }

        return null;
    }

    public static Appointment appointmentFromJSONObject(JSONObject appointmentJSONObject) {
        Log.i("Test", appointmentJSONObject.toString());

        Appointment appointment = null;
        try {
            int id = appointmentJSONObject.getInt("id");
            int userId = appointmentJSONObject.getInt("userId");
            String description = appointmentJSONObject.getString("description");
            Boolean remote = appointmentJSONObject.getBoolean("remote");
            Boolean done = appointmentJSONObject.getBoolean("done");
            Boolean validate = appointmentJSONObject.getBoolean("validate");
            Boolean canceled = appointmentJSONObject.getBoolean("canceled ");
            String date = appointmentJSONObject.getString("date") ;
            int addressId = appointmentJSONObject.getInt("addressId ");
            Log.i("Test", appointmentJSONObject.getString("description"));
            appointment = new Appointment(id, date, description, remote, done, userId, addressId, canceled, validate);
        } catch (JSONException exception) {

        }

        return appointment;
    }

    public static DisponibilitiesModel disponibilityFromJSONObject(JSONObject disponibilityJSONobject){

        DisponibilitiesModel disponibilitiesModel = null;

        try {
            int id = disponibilityJSONobject.getInt("id");
            String start = disponibilityJSONobject.getString("start");
            String end = disponibilityJSONobject.getString("end");
            String title = disponibilityJSONobject.getString("title");
            int day_number = disponibilityJSONobject.getInt("day_number");
            int employee_id = disponibilityJSONobject.getInt("employee_id");

            disponibilitiesModel = new DisponibilitiesModel(id, start, end, title, day_number, employee_id);

        } catch (JSONException exception) {

        }
        return disponibilitiesModel;
    }


    public static PrestationsModel prestationFromJSONObject(JSONObject prestationsJSONObject) {

        PrestationsModel prestations = null;

        if(prestationsJSONObject != null) {
                try {

                    int id = prestationsJSONObject.getInt("id");
                    String name = prestationsJSONObject.getString("name");
                    String price = prestationsJSONObject.getString("price");

                    prestations= new PrestationsModel(id, name, price);

                } catch (JSONException exception) {

                }
            }

        return prestations;
    }

    public static UserModel userFromJSONObject(JSONObject userJSONObecjt) {

        UserModel user = null;

        if(userJSONObecjt != null) {
                try {
                    int id = userJSONObecjt.getInt("id");
                    String email = userJSONObecjt.getString("email");
                    String firstName= userJSONObecjt.getString("firstName");
                    String lastName= userJSONObecjt.getString("lastName");
                    String login = userJSONObecjt.getString("login");
                    String roleName = userJSONObecjt.getString("roleName");
                    String phoneNumber = userJSONObecjt.getString("phoneNumber");

                    user = new UserModel(id, email, firstName,lastName, phoneNumber, login, roleName );

                } catch (JSONException exception) {

                }

        }
        return user;
    }

}
