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
                    JSONObject jsonobject = appointmentJSONArray.getJSONObject(i);

                    long id = jsonobject.getLong("id");
                    long userId = jsonobject.getLong("userId");
                    String description = jsonobject.getString("description");
                    Boolean remote = jsonobject.getBoolean("remote");
                    Boolean done = jsonobject.getBoolean("done");
                    Boolean validate = jsonobject.getBoolean("validate");
                    Boolean canceled = jsonobject.getBoolean("canceled ");
                    String date = jsonobject.getString("date") ;
                    long addressId = jsonobject.getLong("addressId ");

                    Appointment appointment = new Appointment(id, date, description, remote, done, userId, addressId, canceled, validate);
                    appointmentList.add(appointment);
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
                    JSONObject jsonobject = disponibilitiesJSONArray.getJSONObject(i);

                    long id = jsonobject.getLong("id");
                    String start = jsonobject.getString("start");
                    //Time start = new Time(formatter.parse("start").getTime());
                    String end = jsonobject.getString("end");
                    String title = jsonobject.getString("title");
                    Long day_number = jsonobject.getLong("day_number");
                    Long employee_id = jsonobject.getLong("employee_id");

                    Disponibilities disponibilities = new Disponibilities(id, start, end, title, day_number, employee_id);
                    disponibilitiesList.add(disponibilities);
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
                    JSONObject jsonobject = prestationsJSONArray.getJSONObject(i);

                    long id = jsonobject.getLong("id");
                    String name = jsonobject.getString("name");
                    String price = jsonobject.getString("price");

                    Prestations prestations= new Prestations(id, name, price);
                    prestationsList .add(prestations);
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
                    JSONObject jsonobject = userJSONArray.getJSONObject(i);

                    long id = jsonobject.getLong("id");
                    String email = jsonobject.getString("email");
                    String firstName= jsonobject.getString("firstName");
                    String lastName= jsonobject.getString("lastName");
                    String login = jsonobject.getString("login");
                    String roleName = jsonobject.getString("roleName");

                    User user = new User(id, email, firstName,lastName, login,roleName );
                    userList.add(user);
                } catch (JSONException exception) {

                }
            }
            return userList ;
        }

        return null;
    }

}
