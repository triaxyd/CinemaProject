package com.triaxyd.cinema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Provoles implements Serializable {
    private static final long serialVersionUID = -756982741976214996L; //fix serialize/deserialize error
    private List<Reservations> reservations;
    private int provoliId,provoliNumberOfReservations,provoliSeatsLeft;
    private String provoliFilm,provoliCinema,provoliStartDate,provoliEndDate;
    private boolean provoliIsAvailable;

    public Provoles(Cinemas cinema, Movies film){
        reservations = new ArrayList<>();
        provoliIsAvailable = true;
        provoliFilm= film.getFilmTitle();
        provoliCinema= cinema.getCinemaName();
        provoliSeatsLeft = cinema.getCinemaNumberOfSeats();
    }

    public void setProvoliId(int provoliId) { this.provoliId = provoliId;}
    public void setProvoliStartDate(String provoliStartDate) { this.provoliStartDate = provoliStartDate;}
    public void setProvoliEndDate(String provoliEndDate){
        this.provoliEndDate = provoliEndDate;
    }
    public void setProvoliIsAvailable(boolean provoliIsAvailable){
        this.provoliIsAvailable = provoliIsAvailable;
    }
    public void setProvoliNumberOfReservations(int provoliNumberOfReservations){this.provoliNumberOfReservations = provoliNumberOfReservations;}
    public void setProvoliNumberOfSeatsLeft(int numberOfSeatsLeft) { this.provoliSeatsLeft = numberOfSeatsLeft;}

    public int getProvoliId() { return provoliId;}
    public int getProvoliNumberOfReservations(){ return provoliNumberOfReservations;}
    public int getProvoliNumberOFSeatsLeft(){ return provoliSeatsLeft;}
    public String getProvoliStartDate(){ return provoliStartDate;}
    public String getProvoliEndDate(){ return provoliEndDate;}
    public String getProvoliFilm(){
        return provoliFilm;
    }
    public String getProvoliCinema(){
        return provoliCinema;
    }
    public boolean getProvoliIsAvailable(){return provoliIsAvailable;}

    //public List<Reservations> getReservations(){return reservations;}

    public void addReservation(Reservations reservation){
        reservations.add(reservation);
        if(getProvoliNumberOFSeatsLeft()<1){
            provoliIsAvailable = false;
        }
    }



}