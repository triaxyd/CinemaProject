package com.triaxyd.cinema;

import com.triaxyd.users.Users;

import java.io.Serializable;

public class Reservations {

    private int provoles_movies_id,provoles_cinemas_id,customers_id,num_of_seats;
    private String provoles_movies_name;
    CinemaDAO cinemaDAO = new CinemaDAO();

    public Reservations(){}

    public Reservations(int provoles_movies_id,int provoles_cinemas_id,int customers_id,int num_of_seats){
        this.provoles_movies_id = provoles_movies_id;
        this.provoles_movies_name = cinemaDAO.getMovie(provoles_movies_id).getMovieTitle();
        this.provoles_cinemas_id = provoles_cinemas_id;
        this.customers_id = customers_id;
        this.num_of_seats = num_of_seats;
    }

    public void setProvoles_movies_id(int provoles_movies_id){this.provoles_movies_id = provoles_movies_id;}
    public void setProvoles_movies_name(String provoles_movies_name){this.provoles_movies_name = provoles_movies_name;}
    public void setProvoles_cinemas_id(int provoles_cinemas_id){this.provoles_cinemas_id = provoles_cinemas_id;}
    public void setCustomers_id(int customers_id){this.customers_id = customers_id;}
    public void setNum_of_seats(int num_of_seats){this.num_of_seats=num_of_seats;}

    public int getProvoles_movies_id(){return this.provoles_movies_id;}
    public String getProvoles_movies_name(){return this.provoles_movies_name;}
    public int getProvoles_cinemas_id(){return this.provoles_cinemas_id;}
    public int getCustomers_id(){return this.customers_id;}
    public int getNum_of_seats(){return this.num_of_seats;}

}