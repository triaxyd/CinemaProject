package com.triaxyd.cinema;


import java.time.LocalDate;
import java.time.LocalTime;

public class Provoles {
    //private static final long serialVersionUID = -756982741976214996L; //fix serialize/deserialize error
    //private List<Reservations> reservations;
    //provoliNumberOfReservations,provoliSeatsLeft , provoliFilm,provoliCinema,provoliStartDate,provoliEndDate ,provoliIsAvailable

    private int movieId,cinemaId,contentAdminId,id,num_of_seats;
    private LocalDate date;
    private LocalTime startTime,endTime;

    private String movieName;
    public Provoles(){}


    /*
    public Provoles(Cinemas cinema, Movies film){

        reservations = new ArrayList<>();
        provoliIsAvailable = true;
        provoliFilm= film.getMovieTitle();
        provoliCinema= cinema.getCinemaName();
        //provoliSeatsLeft = cinema.getCinemaSeats();
    }

     */



    public void setMovieId(int moviesId){this.movieId =moviesId;}
    public void setCinemaId(int cinemaId){this.cinemaId=cinemaId;}
    public void setContentAdminId(int contentAdminId){this.contentAdminId = contentAdminId;}

    public void setId(int id){this.id=id;}
    public void setMovieName(String movieName){this.movieName = movieName;}
    public void setNum_of_seats(int num_of_seats){this.num_of_seats = num_of_seats;}

    public void setDate(LocalDate date){this.date= date;}
    public void setStartTime(LocalTime startTime){this.startTime = startTime;}
    public void setEndTime(LocalTime endTime){this.endTime = endTime;}

    public int setTotalSeats(){
        CinemaDAO cinemaDAO = new CinemaDAO();
        Cinemas cinema = cinemaDAO.getCinema(cinemaId);
        num_of_seats = cinema.getCinemaSeats();
        return num_of_seats;
    }



    public void setRemainingSeats(int capturedSeats){
        this.num_of_seats = this.num_of_seats - capturedSeats;
    }

    public int getMovieId(){return this.movieId;}
    public int getCinemaId(){return this.cinemaId;}
    public int getContentAdminId(){return this.contentAdminId;}
    public int getId(){return this.id;}
    public String getMovieName(){return this.movieName;}
    public int getNum_of_seats(){return this.num_of_seats;}
    public LocalDate getDate(){return this.date;}
    public LocalTime getStartTime(){return this.startTime;}
    public LocalTime getEndTime(){return this.endTime;}





}