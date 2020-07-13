/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : JSONHelper.java
 * Edited by pinbe
 */

package com.example.geekomatique.Helpers;

import android.util.Log;

import com.example.geekomatique.Activities.Appointment;
import com.example.geekomatique.Models.AddressModel;
import com.example.geekomatique.Models.AppointmentModel;
import com.example.geekomatique.Models.DisponibilitiesModel;
import com.example.geekomatique.Models.PrestationsModel;
import com.example.geekomatique.Models.UserModel;
import com.example.geekomatique.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONHelper {

    public static List<AppointmentModel> appointmentListFromJSONArray(JSONArray appointmentJSONArray) {
        List<AppointmentModel> appointmentList = new ArrayList<>();

        if(appointmentJSONArray != null) {
            for (int i = 0; i < appointmentJSONArray.length(); i++) {

                try {
                    appointmentList.add(appointmentFromJSONObject(appointmentJSONArray.getJSONObject(i)));
                } catch (JSONException exception) {
                    Log.i("jsonexcept", exception.toString());

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
                    Log.i("jsonexcept", exception.toString());

                }
            }
            return disponibilitiesModelList;
        }

        return null;
    }

    public static List<PrestationsModel> prestationsListFromJSONArray(JSONArray prestationsJSONArray) {
        List<PrestationsModel> prestationsList = new ArrayList<>();

        if(prestationsJSONArray != null) {
            for (int i = 0; i < prestationsJSONArray.length(); i++) {
                try {
                  prestationsList.add(prestationFromJSONObject(prestationsJSONArray.getJSONObject(i)));
                } catch (JSONException exception) {
                    Log.i("jsonexcept", exception.toString());

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
                    Log.i("jsonexcept", exception.toString());

                }
            }
            return userList ;
        }

        return null;
    }

    public static AppointmentModel appointmentFromJSONObject(JSONObject appointmentJSONObject) {
        AppointmentModel appointment = null;
        try {
            int id = appointmentJSONObject.getInt("id");
            int userId = appointmentJSONObject.getInt("user_id");
            String description = appointmentJSONObject.getString("description");
            Boolean remote = appointmentJSONObject.getBoolean("remote");
            Boolean done = appointmentJSONObject.getBoolean("done");
            Boolean validate = appointmentJSONObject.getBoolean("validate");
            Boolean canceled = appointmentJSONObject.getBoolean("canceled");
            String date = appointmentJSONObject.getString("date");
            int addressId = appointmentJSONObject.optInt("address_id");
            int addressInvoiceId = appointmentJSONObject.optInt("address_invoice_id");

            appointment = new AppointmentModel(id, date, description, remote, done, userId, addressId, addressInvoiceId, canceled, validate);

        } catch (JSONException exception) {
            Log.i("jsonexcept", exception.toString());
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
            Log.i("jsonexcept", exception.toString());

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
                    Log.i("jsonexcept", exception.toString());

                }
            }

        return prestations;
    }

    public static UserModel userFromJSONObject(JSONObject userJSONObject) {

        UserModel user = null;

        if(userJSONObject != null) {
                try {
                    Integer id = userJSONObject.getInt("id");
                    String email = userJSONObject.getString("email");
                    String firstName= userJSONObject.getString("firstName");
                    String lastName= userJSONObject.getString("lastName");
                    String login = userJSONObject.getString("login");
                    String roleName = userJSONObject.getString("role_name");
                    Integer roleID = userJSONObject.getInt("role_id");
                    String phoneNumber = userJSONObject.getString("phoneNumber");

                    user = new UserModel(id, firstName, lastName, phoneNumber, login, roleName, email, roleID, id);

                } catch (JSONException exception) {
                    Log.i("jsonexcept", exception.toString());

                }

        }
        return user;
    }

    public static AddressModel addressListFromJSONObject(JSONObject userJSONObject) {

        AddressModel address = null;

        if(userJSONObject != null) {
            try {
                Integer id = userJSONObject.getInt("id");
                String city = userJSONObject.getString("city");
                String street= userJSONObject.getString("street");
                Integer zipcode= userJSONObject.getInt("zipcode");
                String country = userJSONObject.getString("country");
                String type = userJSONObject.getString("type");
                Integer userId = userJSONObject.getInt("user_id");

                address = new AddressModel(id, city, street, zipcode, country, type, userId);

            } catch (JSONException exception) {
                Log.i("jsonexcept", exception.toString());
            }

        }
        return address;
    }



    public static JSONObject makePdfJSONObject(JSONObject invoice){
        JSONObject invoicePdf = new JSONObject();
        JSONObject  company = makeCompanyJSONObject();

        try{
            invoicePdf.put("company", company);
            invoicePdf.put("invoice", invoice);
        }catch (JSONException ex){
            Log.i("parseex", ex.getMessage());
        }

        return invoicePdf;
    }

    public static JSONObject makeInvoiceJSONObject(UserModel user, List<PrestationsModel> services, AddressModel address, AppointmentModel appointment){
        JSONObject invoice = new JSONObject();

        try{
            invoice.put("rendered_services_date", appointment.getDate());
            invoice.put("city", address.getCity());
            invoice.put("street", address.getStreet());
            invoice.put("user_name", user.getFirstName() + " " + user.getLastName());
            invoice.put("zipcode", address.getZipcode());
            invoice.put("phone_number", user.getPhoneNumber());
            invoice.put("country", address.getCountry());
            invoice.put("appointment_id", appointment.getId());
            invoice.put("user_id", address.getCity());
            invoice.put("services", makeServicesJSONArray(services));

        }catch (JSONException ex){
            Log.i("parseex", ex.getMessage());
        }

        return invoice;
    }

    public static JSONArray makeServicesJSONArray(List<PrestationsModel> services){
        JSONArray servicesArray = new JSONArray();

        services.forEach((service) ->{
            servicesArray.put(makeServiceJSONObject(service));
        });

        return servicesArray;
    }

    public static JSONObject makeServiceJSONObject(PrestationsModel service){
        JSONObject serviceObject = new JSONObject();

        try{
            serviceObject.put("name", service.getName());
            serviceObject.put("price", service.getPrice());
        }
        catch(JSONException ex){
            Log.i("parsejson", ex.getMessage());
        }

        return new JSONObject();
    }

    public static JSONObject makeCompanyJSONObject(){
        JSONObject  company = new JSONObject();
        try{
            company.put("name", R.string.company_name);
            company.put("siret", R.string.company_siret);
            company.put("city", R.string.company_city);
            company.put("zipcode", R.string.company_zipcode);
            company.put("street", R.string.company_street);
            company.put("vat_number", R.string.company_vat_number);
            company.put("vat_percent", R.string.company_vat_percent);
        }
        catch(JSONException ex){
            Log.i("parsejson", ex.getMessage());
        }

        return company;
    }

}
