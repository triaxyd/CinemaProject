package com.triaxyd.users;

import com.triaxyd.cinema.Provoles;
import com.triaxyd.cinema.Reservations;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.time.LocalDate;



public class Customers extends Users {

    private String name;
    private int id;
    private List<Reservations> reservationsUser;

    public Customers() {
        reservationsUser = new ArrayList<>();
        this.setRole("Customer");
    }

    /*
    public Customers(String username, String password, String email , Date create_time, String role) {
        reservationsUser = new ArrayList<>();
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setCreationDate(create_time);
        this.setRole(role);
    }

     */

    public List<Reservations> getReservationsUser(){
        return reservationsUser;
    }

    public void setName(String name){this.name=name;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public int getId(){return this.id;}


    public void login() {
        System.out.println("--- Welcome, " + getUsername().toUpperCase() + " ---");
        this.setLoggedIn(true);
    }

    public void logout() {
        System.out.println("Customer " + getUsername().toUpperCase() + " logged out.");
        this.setLoggedIn(false);
    }

    public void showUserMenu() {

    }

    public void nowPlayingFilms(List<Provoles> provoles) {

    }

    public void makeReservation(List<Provoles> provoles, Users user) {

    }

    public void viewReservations(Users user) {
    }

}




