/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : AppoIntegerment.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

import android.util.Log;

import java.util.Date;

public class AppointmentModel {

    private Integer id;
    private String date;
    private String description;
    private boolean remote;
    private boolean done;
    private Integer userId;
    private Integer adressId;
    private Integer adressInvoiceId;
    private boolean canceled;
    private boolean validate;

    public AppointmentModel(Integer id, String date, String description, boolean remote, boolean done, Integer userId, Integer adressId, Integer adressInvoiceId, boolean canceled, boolean validate) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.remote = remote;
        this.done = done;
        this.userId = userId;
        this.adressId = adressId;
        this.adressInvoiceId = adressInvoiceId;
        this.canceled = canceled;
        this.validate = validate;
    }

    public AppointmentModel(String date, String description, boolean remote, boolean done, Integer userId, Integer adressId, Integer adressInvoiceId, boolean canceled, boolean validate) {
        this.date = date;
        this.description = description;
        this.remote = remote;
        this.done = done;
        this.userId = userId;
        this.adressId = adressId;
        this.adressInvoiceId = adressInvoiceId;
        this.canceled = canceled;
        this.validate = validate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdressId() {
        return adressId;
    }

    public void setAdressId(Integer adressId) {
        this.adressId = adressId;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Integer getAdressInvoiceId() {
        return adressInvoiceId;
    }

    public void setAdressInvoiceId(Integer adressInvoiceId) {
        this.adressInvoiceId = adressInvoiceId;
    }
}
