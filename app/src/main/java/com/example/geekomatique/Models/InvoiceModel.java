package com.example.geekomatique.Models;

import java.io.Serializable;
import java.util.HashMap;

public class InvoiceModel implements Serializable
{

    private Integer id;
    private String invoiceNumber;
    private String invoiceDate;
    private String renderedServicesDate;
    private Boolean paid;
    private String city;
    private String street;
    private String userName;
    private Integer zipcode;
    private String country;
    private String email;
    private String phoneNumber;
    private Integer appoIntegermentId;
    private Integer userId;
    private String base64;

    public InvoiceModel(String invoiceNumber, String invoiceDate, String renderedServicesDate,
                        Boolean paid, String city, String street, String userName, Integer zipcode,
                        String country, String email, String phoneNumber, Integer appoIntegermentId,
                        Integer userId, String base64) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.renderedServicesDate = renderedServicesDate;
        this.paid = paid;
        this.city = city;
        this.street = street;
        this.userName = userName;
        this.zipcode = zipcode;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.appoIntegermentId = appoIntegermentId;
        this.userId = userId;
        this.base64 = base64;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getRenderedServicesDate() {
        return renderedServicesDate;
    }

    public void setRenderedServicesDate(String renderedServicesDate) {
        this.renderedServicesDate = renderedServicesDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAppoIntegermentId() {
        return appoIntegermentId;
    }

    public void setAppoIntegermentId(Integer appoIntegermentId) {
        this.appoIntegermentId = appoIntegermentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public HashMap<String, String> getInvoiceHashMap(String password){
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("invoice_date", getInvoiceDate());
        hashMap.put("rendered_services_date", getRenderedServicesDate());
        hashMap.put("paid", String.valueOf(getPaid()));
        hashMap.put("city", getCity());
        hashMap.put("street", getStreet());
        hashMap.put("user_name", getUserName());
        hashMap.put("zipcode", String.valueOf(getZipcode()));
        hashMap.put("country", getCountry());
        hashMap.put("email", getEmail());
        hashMap.put("phone_number", getPhoneNumber());
        hashMap.put("appoIntegerment_id", String.valueOf(getAppoIntegermentId()));
        hashMap.put("user_id", String.valueOf(getUserId()));

        return hashMap;
    }

}