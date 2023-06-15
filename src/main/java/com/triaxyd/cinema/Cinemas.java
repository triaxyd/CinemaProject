package com.triaxyd.cinema;

public final class Cinemas{
    private String cinemaName;
    private String cinemaSeats;
    private int cinemaId;
    private String cinemaIs3d;

    public Cinemas(){

    }

    public Cinemas(int cinemaId, String cinemaName, String cinemaSeats, String cinemaIs3d){
        this.cinemaName = cinemaName;
        this.cinemaSeats = cinemaSeats;
        this.cinemaId = cinemaId;
        this.cinemaIs3d = cinemaIs3d;
    }

    public void setCinemaId(int cinemaId){this.cinemaId=cinemaId;}
    public void setCinemaName(String cinemaName){this.cinemaName=cinemaName;}
    public void setCinemaSeats(String cinemaSeats){this.cinemaSeats=cinemaSeats;}
    public void setCinemaIs3d(String cinemaIs3d){this.cinemaIs3d=cinemaIs3d;}

    public String getCinemaName(){
        return cinemaName;
    }

    public String getCinemaSeats(){
        return cinemaSeats;
    }
    public int getCinemaId(){
        return cinemaId;
    }

    public String getCinemaIs3d(){
        return cinemaIs3d;
    }




}
