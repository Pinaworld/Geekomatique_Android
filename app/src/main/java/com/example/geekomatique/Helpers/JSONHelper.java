/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : JSONHelper.java
 * Edited by pinbe
 */

package com.example.geekomatique.Helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.util.Log;

import com.example.geekomatique.Models.AddressModel;
import com.example.geekomatique.Models.AppointmentModel;
import com.example.geekomatique.Models.DisponibilitiesModel;
import com.example.geekomatique.Models.InvoiceModel;
import com.example.geekomatique.Models.PrestationsModel;
import com.example.geekomatique.Models.RoleModel;
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
                    String firstName= userJSONObject.getString("firstname");
                    String lastName= userJSONObject.getString("lastname");
                    String login = userJSONObject.getString("login");
                    String roleName = userJSONObject.getString("role_name");
                    Integer roleID = userJSONObject.getInt("role_id");
                    String phoneNumber = userJSONObject.getString("phone_number");

                    user = new UserModel(id, firstName, lastName, phoneNumber, login, roleName, email, roleID, id);

                } catch (JSONException exception) {
                    Log.i("jsonexcept", exception.toString());

                }

        }
        return user;
    }



    public static List<RoleModel> roleFromJSONArray(JSONArray jsonArray){
        List<RoleModel> roleModelList= new ArrayList<>();

        if(jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    roleModelList.add(roleFromJSONObject(jsonArray.getJSONObject(i)));
                } catch (JSONException exception) {
                    Log.i("jsonexcept", exception.toString());

                }
            }
            return roleModelList ;
        }

        return null;
    }

    public static RoleModel roleFromJSONObject(JSONObject roleJsonObject) {

        RoleModel roleModel= null;

        if(roleJsonObject != null) {
            try {

                int id = roleJsonObject.getInt("id");
                String name = roleJsonObject.getString("name");

                roleModel = new RoleModel(id, name);

            } catch (JSONException exception) {
                Log.i("jsonexcept", exception.toString());

            }
        }

        return roleModel;
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



    public static JSONObject makePdfJSONObject(JSONObject invoice, Context context){
        JSONObject invoicePdf = new JSONObject();
        JSONObject  company = makeCompanyJSONObject(context);

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
            invoice.put("email", user.getEmail());
            invoice.put("country", address.getCountry());
            invoice.put("appointment_id", appointment.getId());
            invoice.put("user_id", (int)address.getUserId());
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

    public static JSONObject makeUserJSONObject(UserModel userModel){
        JSONObject userObject = new JSONObject();

        try {
            userObject.put("firstname", userModel.getFirstName());
            userObject.put("lastname", userModel.getLastName());
            userObject.put("phoneNumber", userModel.getPhoneNumber());
            userObject.put("email", userModel.getEmail());
            userObject.put("userCredentialsId", userModel.getCredentialId());
        }
        catch(JSONException ex){
            Log.i("parsejson", ex.getMessage());
        }

        return userObject;
    }

    public static JSONObject makeUserCredentialsJSONObject(String passwd, String login, Integer roleId){
        JSONObject credentials = new JSONObject();

        try {
            credentials.put("password", passwd);
            credentials.put("login", login);
            credentials.put("role_id", roleId);
        }
        catch(JSONException ex){
            Log.i("parsejson", ex.getMessage());
        }

        return credentials;
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

        return serviceObject;
    }

    public static JSONObject makeCompanyJSONObject(Context context){
        JSONObject  company = new JSONObject();
        try{
            company.put("name", context.getResources().getString(R.string.company_name));
            company.put("siret", context.getResources().getString(R.string.company_siret));
            company.put("city", context.getResources().getString(R.string.company_city));
            company.put("zipcode", context.getResources().getString(R.string.company_zipcode));
            company.put("street", context.getResources().getString(R.string.company_street));
            company.put("vat_number", context.getResources().getString(R.string.company_vat_number));
            company.put("vat_percent", context.getResources().getString(R.string.company_vat_percent));
        }
        catch(JSONException ex){
            Log.i("parsejson", ex.getMessage());
        }

        return company;
    }

    public static JSONObject makeMailJSONObject(String message, String subject, String email, JSONObject invoice){
        JSONObject  mailObject = new JSONObject();
        try{
            mailObject.put("subject", subject);
            mailObject.put("message", message);
            mailObject.put("receiverMail", email);
            mailObject.put("invoiceBase64", invoice.get("Invoice_Base64"));
            mailObject.put("invoiceNumber", invoice.get("invoice_number"));

        }
        catch(JSONException ex){
            Log.i("parsejson", ex.getMessage());
        }

        return mailObject;
    }

}
