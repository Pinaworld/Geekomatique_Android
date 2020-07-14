/*
 * Copyright (c) 2020
 * Project: Geekomatique_Android
 * File : MailService.java
 * Edited by lamyg
 */

package com.example.geekomatique;

import android.content.Context;

import com.example.geekomatique.Helpers.HTTPRequestHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class MailService {

    public static void sendMailToAdmin(Context context, String subject, String message, String email, VolleyJSONObjectCallback callback){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("message", message);
            jsonObject.put("senderMail", email);
            jsonObject.put("subject", subject);
        }
        catch (JSONException exception){

        }

        HTTPRequestHelper.postRequest(context,"https://geekomatique.fr:5000"+ "/mail/send/admin", callback, jsonObject);
    }

    public static void sendMailToClient(Context context,String subject, String message, String email, VolleyJSONObjectCallback callback){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("message", message);
            jsonObject.put("receiverMail", email);
            jsonObject.put("subject", subject);
        }
        catch (JSONException exception){

        }
        HTTPRequestHelper.postRequest(context,"https://geekomatique.fr:5000"+ "/mail/send/user", callback, jsonObject);
    }

    public static void sendMailToWithAttachment(Context context, String subject, String message, String email, JSONObject invoice, VolleyJSONObjectCallback callback){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("message", message);
            jsonObject.put("receiverMail", email);
            jsonObject.put("subject", subject);
            jsonObject.put("invoiceBase64", invoice.getString("Invoice_Base64"));
            jsonObject.put("invoiceNumber", invoice.getString("invoice_number"));
        }
        catch (JSONException exception){

        }
        HTTPRequestHelper.postRequest(context,"https://geekomatique.fr:5000"+ "/mail/send/invoice", callback, jsonObject);
    }
}
