package com.triaxyd.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Admins extends Users {

    private String name;
    private int id;
    public Admins() {this.setRole("Admin");}


    public void setName(String name){this.name = name;}
    public void setId(int id){this.id=id;}

    public String getName(){return this.name;}
    public int getId(){return this.id;}


    public Users createUser() {
        return null;
    }

    public void updateUser(List<Users> users) {

    }

    public void deleteUser(List<Users> users, Users userInControl) {

    }

    public void searchUser(List<Users> users) {

    }

    public void viewAllUsers(List<Users> users) {

    }

    public Users registerAdmin() {
        return null;
    }



}