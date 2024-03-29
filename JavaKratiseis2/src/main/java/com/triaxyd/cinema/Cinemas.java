package com.triaxyd.cinema;

public final class Cinemas{
    private String cinemaName;
    private int cinemaSeats;
    private int cinemaId;
    private String cinemaIs3d;

    public Cinemas(){

    }

    public Cinemas(int cinemaId, String cinemaName, int cinemaSeats, String cinemaIs3d){
        this.cinemaName = cinemaName;
        this.cinemaSeats = cinemaSeats;
        this.cinemaId = cinemaId;
        this.cinemaIs3d = cinemaIs3d;
    }

    public void setCinemaId(int cinemaId){this.cinemaId=cinemaId;}
    public void setCinemaName(String cinemaName){this.cinemaName=cinemaName;}
    public void setCinemaSeats(int cinemaSeats){this.cinemaSeats=cinemaSeats;}
    public void setCinemaIs3d(String cinemaIs3d){this.cinemaIs3d=cinemaIs3d;}

    public String getCinemaName(){
        return cinemaName;
    }

    public int getCinemaSeats(){
        return cinemaSeats;
    }
    public int getCinemaId(){
        return cinemaId;
    }

    public String getCinemaIs3d(){
        return cinemaIs3d;
    }




}
