package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
import com.triaxyd.cinema.Movies;
import com.triaxyd.cinema.Provoles;
import com.triaxyd.cinema.Reservations;
import com.triaxyd.database.DatabaseConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ContentAdmins extends Users {
    private String name;
    private int id;
    public ContentAdmins(){
        this.setRole("ContentAdmin");
    }

    public void setName(String name){this.name=name;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public int getId(){return this.id;}
    public void login(){}
    public void logout(){}


    public Movies insertMovie(int id,String title,String content,int length,String type,String summary,String director,int content_admin_id){
        CinemaDAO cinemaDAO = new CinemaDAO();
        Movies movie = null;
        if(cinemaDAO.checkMovieExists(id)){
            //movie already exists
        }else{
            //movie is being created
            try{
                movie = new Movies(id,title,content,length,type,summary,director,content_admin_id);
                Connection connection = DatabaseConnector.connect();
                String sql = "INSERT INTO movies VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement psMovie = connection.prepareStatement(sql);
                psMovie.setInt(1,id);
                psMovie.setString(2,title);
                psMovie.setString(3,content);
                psMovie.setInt(4,length);
                psMovie.setString(5,type);
                psMovie.setString(6,summary);
                psMovie.setString(7,director);
                psMovie.setInt(8,content_admin_id);

                psMovie.executeUpdate();
            }catch(SQLException e){
                //error
            }

        }
        return movie;
    }





    public Provoles assignMovieToCinema(int movieId, int cinemaId, LocalDate date, LocalTime startTime) {
        Provoles provoli = null;
        CinemaDAO cinemaDAO = new CinemaDAO();

        if (!cinemaDAO.checkMovieExists(movieId)) return null;
        if (!cinemaDAO.checkCinemaExists(cinemaId)) return null;

        if(cinemaDAO.checkProvoliExists(movieId,cinemaId,date,startTime)){
            //provoli exists
            return null;
        }
        LocalTime endTime = startTime.plusMinutes(cinemaDAO.getMovie(movieId).getMovieLength());
        for(Provoles p : CinemaDAO.getProvoles()){
            if(p.getDate().equals(date) && p.getCinemaId()==cinemaId){
                LocalTime existingStartTime = p.getStartTime();
                LocalTime existingEndTime = p.getEndTime();
                if (startTime.isBefore(existingEndTime) && existingStartTime.isBefore(endTime)) {
                    return null;
                }
            }
        }
        provoli = new Provoles();
        int provoliId = cinemaDAO.generateID(String.valueOf(movieId), String.valueOf(cinemaId), date, startTime);
        String movieName = cinemaDAO.getMovie(movieId).getMovieTitle();
        try{
            provoli.setMovieId(movieId);
            provoli.setMovieName(movieName);
            provoli.setId(provoliId);
            provoli.setCinemaId(cinemaId);
            provoli.setContentAdminId(this.id);

            int num_of_seats= provoli.setTotalSeats();

            provoli.setDate(date);
            provoli.setStartTime(startTime);
            provoli.setEndTime(endTime);

            Connection connection = DatabaseConnector.connect();
            String sql = "INSERT INTO provoles VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement psAssign = connection.prepareStatement(sql);
            psAssign.setInt(1,movieId);
            psAssign.setString(2,movieName);
            psAssign.setInt(3,cinemaId);
            psAssign.setInt(4,provoliId);
            psAssign.setInt(5,this.getId());
            psAssign.setInt(6,num_of_seats);
            psAssign.setDate(7, java.sql.Date.valueOf(date));
            psAssign.setTime(8, java.sql.Time.valueOf(startTime));
            psAssign.setTime(9, java.sql.Time.valueOf(endTime));

            psAssign.executeUpdate();

            return provoli;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }





    public String deleteProvoli(int provoliId, int content_admin_id){
        CinemaDAO cinemaDAO = new CinemaDAO();
        try{
            Provoles provoli = cinemaDAO.getProvoli(provoliId);
            if(provoli==null) return "PROVOLI WITH ID " + provoliId + " NOT FOUND";

            List<Reservations> reservationsList = CinemaDAO.getReservations();
            for (Reservations r : reservationsList){
                if(provoli.getStartTime()==r.getTime() && provoli.getDate()==r.getDate() && provoli.getMovieId()==r.getProvoles_movies_id() && provoli.getCinemaId()==r.getProvoles_cinemas_id()){
                    return "PROVOLI IS RESERVED";
                }
            }

            if(!(provoli.getContentAdminId()==content_admin_id)) return "UNAUTHORIZED TO DELETE MOVIE";


            try{
                Connection connection = DatabaseConnector.connect();
                String sqlProvoli = "DELETE FROM provoles WHERE ID = ?";
                PreparedStatement ps = connection.prepareStatement(sqlProvoli);
                ps.setInt(1,provoli.getId());

                ps.executeUpdate();

                return "PROVOLI FOR -"+ provoli.getMovieName() + "- DELETED";
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch(NumberFormatException e){
            return "WRONG INPUT";
        }
        return "";
    }







}



