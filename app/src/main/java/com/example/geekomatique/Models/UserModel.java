/*
 * Copyright (c) 2020
 * Project: Geekomatique
 * File : UserModel.java
 * Edited by pinbe
 */

package com.example.geekomatique.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserModel implements Serializable {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String login;
    private String roleName;
    private Integer roleID;
    private Integer credentialId;

    public UserModel(Integer id, String email, String firstName, String lastName, String phoneNumber, String login, String roleName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.roleName = roleName;
    }

    public UserModel(String email, String firstName, String lastName, String phoneNumber, String login) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.login = login;
    }

    public UserModel(Integer id, String email, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public HashMap<String, String> getUserHashMap(){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("email", getEmail());

        hashMap.put("lastname", getLastName());
        hashMap.put("fisrtname", getFirstName());
        hashMap.put("phoneNumber", getPhoneNumber());
        hashMap.put("userCredentialsId",String.valueOf(getCredentialId()));
        return hashMap;
    }

    public HashMap<String, String> getUserCredentialsHashMap(String password){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("email", getEmail());

        hashMap.put("password", password);
        hashMap.put("login", getLogin());
        hashMap.put("role_id", String.valueOf(getRoleID()));
        return hashMap;
    }
}
