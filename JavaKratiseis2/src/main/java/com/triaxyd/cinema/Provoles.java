package com.triaxyd.cinema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Provoles implements Serializable {
    //private static final long serialVersionUID = -756982741976214996L; //fix serialize/deserialize error
    //private List<Reservations> reservations;
    //provoliNumberOfReservations,provoliSeatsLeft , provoliFilm,provoliCinema,provoliStartDate,provoliEndDate ,provoliIsAvailable

    private int moviesId,cinemaId,contentAdminId,id;

    private String moviesName;

    public Provoles(){}

    public Provoles(Cinemas cinema, Movies film){
        /*
        reservations = new ArrayList<>();
        provoliIsAvailable = true;
        provoliFilm= film.getMovieTitle();
        provoliCinema= cinema.getCinemaName();
        //provoliSeatsLeft = cinema.getCinemaSeats();

         */
    }


    public void setMoviesId(int moviesId){this.moviesId=moviesId;}
    public void setCinemaId(int cinemaId){this.cinemaId=cinemaId;}
    public void setContentAdminId(int contentAdminId){this.contentAdminId = contentAdminId;}
    public void setId(int id){this.id=id;}
    public void setMoviesName(String moviesName){this.moviesName=moviesName;}

    public int getMoviesId(){return this.id;}
    public int getCinemaId(){return this.cinemaId;}
    public int getContentAdminId(){return this.contentAdminId;}
    public int getId(){return this.id;}
    public String getMoviesName(){return this.moviesName;}





}