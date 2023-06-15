package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
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


    public String makeReservation(String provoliId,int num_of_seats) {
        String message;
        CinemaDAO cinemaDAO = new CinemaDAO();
        Provoles provoli = cinemaDAO.getProvoli(Integer.parseInt(provoliId));
        if (provoli.getRemainingSeats()==0){
            //full theater
            message = provoli.getMovieName() + "at " + provoli.getCinemaId() +" is full";
        }else if(provoli.getRemainingSeats() < num_of_seats) {
            //cant make reservation the theater is full
            message = "Please select " + provoli.getRemainingSeats() + " or less seats";
        }else{
            Reservations reservation = new Reservations(provoli.getMovieId(),provoli.getCinemaId(),this.id,num_of_seats);
            message = "Reservation for " + provoli.getMovieName() + " at " + provoli.getCinemaId();
        }
        return message;
    }

    public void viewReservations() {
    }

}




