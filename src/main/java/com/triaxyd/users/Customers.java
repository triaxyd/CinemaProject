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
    //private List<Reservations> reservationsUser;

    public Customers() {
        //reservationsUser = new ArrayList<>();
        this.setRole("Customer");
    }


    public void setName(String name){this.name=name;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public int getId(){return this.id;}


    public void makeReservation(String provoliId) {

    }

    public void viewReservations() {
    }

}




