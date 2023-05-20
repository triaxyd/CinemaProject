package com.triaxyd.cinema;

import com.triaxyd.users.Users;

import java.io.Serializable;

public class Reservations  implements Serializable {
    private final Users user;
    private final Provoles provoli;
    private final int seats;
    public Reservations(Users user,Provoles provoli , int seats){
        this.user = user;
        this.provoli = provoli;
        this.seats = seats;
        provoli.setProvoliNumberOfSeatsLeft(provoli.getProvoliNumberOFSeatsLeft()-seats);
        provoli.setProvoliNumberOfReservations(provoli.getProvoliNumberOfReservations() + seats);
    }

    public Users getUser(){
        return user;
    }

    public Provoles getProvoli(){
        return provoli;
    }
    public int getSeats(){ return seats;}
}