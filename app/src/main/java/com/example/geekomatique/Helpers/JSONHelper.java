/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : JSONHelper.java
 * Edited by pinbe
 */

package com.example.geekomatique.Helpers;

import com.example.geekomatique.Models.Appointment;
import com.example.geekomatique.Models.Disponibilities;
import com.example.geekomatique.Models.Prestations;
import com.example.geekomatique.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONHelper {

    public static List<Appointment> appointmentListFromJSONObject(JSONArray appointmentJSONArray) {
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


    public static List<Disponibilities> disponibilitiesListFromJSONObject(JSONArray disponibilitiesJSONArray) {
        List<Disponibilities> disponibilitiesList = new ArrayList<>();

        if(disponibilitiesJSONArray != null) {
            for (int i = 0; i < disponibilitiesJSONArray.length(); i++) {
                try {
                    disponibilitiesList.add(disponibilitiesFromJSONObject(disponibilitiesJSONArray.getJSONObject(i)));

                } catch (JSONException exception) {

                }
            }
            return disponibilitiesList;
        }

        return null;
    }

    public static List<Prestations> prestationsListFromJSONObject(JSONArray prestationsJSONArray) {
        List<Prestations> prestationsList = new ArrayList<>();

        if(prestationsJSONArray != null) {
            for (int i = 0; i < prestationsJSONArray.length(); i++) {
                try {
                  prestationsList.add(prestationsFromJSONObject(prestationsJSONArray.getJSONObject(i)));
                } catch (JSONException exception) {

                }
            }
            return prestationsList ;
        }

        return null;
    }


    public static List<User> userListFromJSONObject(JSONArray userJSONArray) {
        List<User> userList = new ArrayList<>();

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

            appointment = new Appointment(id, date, description, remote, done, userId, addressId, canceled, validate);

        } catch (JSONException exception) {

        }
        return appointment;
    }

    public static Disponibilities disponibilitiesFromJSONObject(JSONObject disponibilityJSONobject){

        Disponibilities disponibilities = null;

        try {
            int id = disponibilityJSONobject.getInt("id");
            String start = disponibilityJSONobject.getString("start");
            String end = disponibilityJSONobject.getString("end");
            String title = disponibilityJSONobject.getString("title");
            int day_number = disponibilityJSONobject.getInt("day_number");
            int employee_id = disponibilityJSONobject.getInt("employee_id");

            disponibilities = new Disponibilities(id, start, end, title, day_number, employee_id);

        } catch (JSONException exception) {

        }
        return disponibilities;
    }


    public static Prestations prestationsFromJSONObject(JSONObject prestationsJSONObject) {

        Prestations prestations = null;

        if(prestationsJSONObject != null) {
                try {

                    int id = prestationsJSONObject.getInt("id");
                    String name = prestationsJSONObject.getString("name");
                    String price = prestationsJSONObject.getString("price");

                    prestations= new Prestations(id, name, price);

                } catch (JSONException exception) {

                }
            }

        return prestations;
    }

    public static User userFromJSONObject(JSONObject userJSONObejct) {

        User user = null;

        if(userJSONObejct != null) {
                try {
                    int id = userJSONObejct.getInt("id");
                    String email = userJSONObejct.getString("email");
                    String firstName= userJSONObejct.getString("firstName");
                    String lastName= userJSONObejct.getString("lastName");
                    String login = userJSONObejct.getString("login");
                    String roleName = userJSONObejct.getString("roleName");
                    String phoneNumber = userJSONObejct.getString("phoneNumber");

                    user = new User(id, email, firstName,lastName, phoneNumber, login, roleName );

                } catch (JSONException exception) {

                }

        }
        return user;
    }

}
