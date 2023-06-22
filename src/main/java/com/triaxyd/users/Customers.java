package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
import com.triaxyd.cinema.Provoles;
import com.triaxyd.cinema.Reservations;
import com.triaxyd.database.DatabaseConnector;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.time.LocalDate;



public class Customers extends Users {

    private String name;
    private int id;

    public Customers() {
        this.setRole("Customer");
    }


    public void setName(String name){this.name=name;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public int getId(){return this.id;}


    public String makeReservation(int provoliId,int num_of_seats) {
        String message;
        CinemaDAO cinemaDAO = new CinemaDAO();
        Provoles provoli = cinemaDAO.getProvoli(provoliId);
        if (provoli.getNum_of_seats()==0){
            //full theatre
            message = provoli.getMovieName() + "at " + provoli.getCinemaId() +" is full";
        }else if(provoli.getNum_of_seats() < num_of_seats) {
            //cant make reservation more seats than available
            message = "Please select " + provoli.getNum_of_seats() + " or less seats";
        }else{
            Reservations reservation = new Reservations(provoli.getMovieId(),provoli.getCinemaId(),this.id,num_of_seats,provoli.getDate(),provoli.getStartTime());
            try{
                Connection connection = DatabaseConnector.connect();
                String sql = "INSERT INTO reservations VALUES (?,?,?,?,?,?,?)";
                PreparedStatement psMakeReservation = connection.prepareStatement(sql);
                psMakeReservation.setInt(1,reservation.getProvoles_movies_id());
                psMakeReservation.setString(2,reservation.getProvoles_movies_name());
                psMakeReservation.setInt(3,reservation.getProvoles_cinemas_id());
                psMakeReservation.setInt(4,reservation.getCustomers_id());
                psMakeReservation.setInt(5,num_of_seats);
                psMakeReservation.setDate(6, java.sql.Date.valueOf(reservation.getDate()));
                psMakeReservation.setTime(7, Time.valueOf(reservation.getTime()));


                psMakeReservation.executeUpdate();

                provoli.setRemainingSeats(num_of_seats);

                String sqlProvoli = "UPDATE Provoles SET NUM_OF_SEATS = ? WHERE MOVIES_ID = ? AND MOVIES_NAME = ? AND CINEMAS_ID = ? AND CONTENT_ADMIN_ID = ? AND PROVOLI_DATE = ? AND PROVOLI_START_TIME = ?";
                PreparedStatement psProvoli = connection.prepareStatement(sqlProvoli);
                psProvoli.setInt(1,provoli.getNum_of_seats());
                psProvoli.setInt(2,provoli.getMovieId());
                psProvoli.setString(3,provoli.getMovieName());
                psProvoli.setInt(4,provoli.getCinemaId());
                psProvoli.setInt(5,provoli.getContentAdminId());
                psProvoli.setDate(6,java.sql.Date.valueOf(provoli.getDate()));
                psProvoli.setTime(7,Time.valueOf(provoli.getStartTime()));

                psProvoli.executeUpdate();

                message = "Reservation completed";

            }catch(SQLException e){
                message = "You already have a reservation for this provoli";
            }
        }
        return message;
    }

    //public void viewReservations() {}

}




