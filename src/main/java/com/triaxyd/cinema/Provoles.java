package com.triaxyd.cinema;

public class Provoles {
    //private static final long serialVersionUID = -756982741976214996L; //fix serialize/deserialize error
    //private List<Reservations> reservations;
    //provoliNumberOfReservations,provoliSeatsLeft , provoliFilm,provoliCinema,provoliStartDate,provoliEndDate ,provoliIsAvailable

    private int movieId,cinemaId,contentAdminId,id,totalSeats,remainingSeats;

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
    public void setTotalSeats(){
        CinemaDAO cinemaDAO = new CinemaDAO();
        Cinemas cinema = cinemaDAO.getCinema(cinemaId);
        totalSeats = Integer.parseInt(cinema.getCinemaSeats());
    }
    public void setRemainingSeats(int capturedSeats){
        this.remainingSeats = totalSeats -= capturedSeats;
    }

    public int getMovieId(){return this.movieId;}
    public int getCinemaId(){return this.cinemaId;}
    public int getContentAdminId(){return this.contentAdminId;}
    public int getId(){return this.id;}
    public String getMovieName(){return this.movieName;}
    public int getRemainingSeats(){return this.remainingSeats;}





}