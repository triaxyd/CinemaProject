package com.triaxyd.cinema;

import com.triaxyd.database.DatabaseConnector;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {


    public int generateID(String x, String y) {
        String combination = (x.toUpperCase().trim() + "" + y.toUpperCase()).trim();
        byte[] hashBytes = getSHA256Hash(combination);
        return bytesToInt(hashBytes);
    }
    public int generateID(String x, String y,String z) {
        String combination = (x.toUpperCase().trim() + "" + y.toUpperCase() +"" + z.toUpperCase()).trim();
        byte[] hashBytes = getSHA256Hash(combination);
        return bytesToInt(hashBytes);
    }
    public int generateID(String x, String y, LocalDate date, LocalTime startTime) {
        String combination = (x.toUpperCase().trim() + "" + y.toUpperCase() + "" + date.toString() + "" + startTime.toString()).trim();
        byte[] hashBytes = getSHA256Hash(combination);
        return bytesToInt(hashBytes);
    }
    private byte[] getSHA256Hash(String combination) {
        byte[] digested = new byte[0];
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(combination.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            //error
        }
        return digested;
    }
    private int bytesToInt(byte[] bytes) {
        int result = 0;
        for (byte b : bytes) {
            result = (result << 8) | (b & 0xFF);
        }
        return Math.abs((result));
    }




    public boolean checkMovieExists(int id){
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM movies WHERE ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //movie already exists
                return true;
            }
            return false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkCinemaExists(int cinemaId){
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM cinemas WHERE ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,cinemaId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //cinema exists
                return true;
            }
            return false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkProvoliExists(int movieId,int cinemaId,LocalDate date, LocalTime time){
        String movieName = null;
        CinemaDAO cinemaDAO = new CinemaDAO();
        movieName = cinemaDAO.getMovie(movieId).getMovieTitle();

        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM provoles WHERE MOVIES_ID = ? AND MOVIES_NAME=? AND CINEMAS_ID = ? AND PROVOLI_DATE = ? AND PROVOLI_START_TIME = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,movieId);
            ps.setString(2,movieName);
            ps.setInt(3,cinemaId);
            ps.setDate(4, java.sql.Date.valueOf(date));
            ps.setTime(5, java.sql.Time.valueOf(time));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }


    public Movies getMovie(int movieId){
        for (Movies m : getMovies()){
            if(m.getMovieID()==movieId){
                return m;
            }
        }
        return null;
    }

    public Cinemas getCinema(int cinemaId){
        for(Cinemas c : getCinemas()){
            if(c.getCinemaId()==cinemaId){
                return c;
            }
        }
        return null;
    }
    public Provoles getProvoli(int provoliId){
        for(Provoles p : getProvoles()){
            if(p.getId()==provoliId){
                return p;
            }
        }
        return null;
    }
    public Provoles getProvoli(int movieId,int cinemaId){
        for(Provoles p : getProvoles()){
            if(movieId==p.getMovieId()){
                if(cinemaId==p.getCinemaId()){
                    return p;
                }
            }
        }
        return null;
    }




    public static List<Movies> getMovies(){
        List<Movies> movies = new ArrayList<>();
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * from movies";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Movies movie = new Movies();
                movie.setMovieID(rs.getInt("ID"));
                movie.setMovieTitle(rs.getString("NAME"));
                movie.setMovieContent(rs.getString("CONTENT"));
                movie.setMovieLength(rs.getInt("LENGTH"));
                movie.setMovieType(rs.getString("TYPE"));
                movie.setMovieSummary(rs.getString("SUMMARY"));
                movie.setMovieDirector(rs.getString("DIRECTOR"));
                movie.setMovieContentAdminID(rs.getInt("CONTENT_ADMIN_ID"));
                movies.add(movie);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch(SQLException e){

        }
        return movies;
    }


    public static List<Cinemas> getCinemas(){
        List<Cinemas> cinemas= new ArrayList<Cinemas>();
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * from cinemas";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Cinemas cinema = new Cinemas();
                cinema.setCinemaId(rs.getInt("ID"));
                cinema.setCinemaName(rs.getString("NAME"));
                cinema.setCinemaSeats(rs.getString("SEATS"));
                cinema.setCinemaIs3d(rs.getString("3D"));

                cinemas.add(cinema);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch(SQLException e){

        }
        return cinemas;
    }



    public static List<Reservations> getReservations(){
        List<Reservations> reservations = new ArrayList<>();
        try {
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * from reservations";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Reservations reservation = new Reservations();
                reservation.setProvoles_movies_id(rs.getInt("PROVOLES_MOVIES_ID"));
                reservation.setProvoles_movies_name(rs.getString("PROVOLES_MOVIES_NAME"));
                reservation.setProvoles_cinemas_id(rs.getInt("PROVOLES_CINEMAS_ID"));
                reservation.setCustomers_id(rs.getInt("CUSTOMERS_ID"));
                reservation.setNum_of_seats(rs.getInt("NUMBER_OF_SEATS"));

                reservations.add(reservation);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch(SQLException e){

        }
        return reservations;
    }

    public int getReservationsForProvoli(int provoliId){
        CinemaDAO cinemaDAO = new CinemaDAO();
        int sumReservations = 0;
        for (Reservations r : getReservations()){
            Provoles pr = cinemaDAO.getProvoli(r.getProvoles_movies_id(),r.getProvoles_cinemas_id());
            if(provoliId==pr.getId()) {
                sumReservations += r.getNum_of_seats();
            }
        }
        return sumReservations;
    }


    public static List<Provoles> getProvoles(){
        CinemaDAO cinemaDAO = new CinemaDAO();
        List<Provoles> provoles= new ArrayList<>();
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * from provoles";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Provoles provoli = new Provoles();
                provoli.setMovieId(rs.getInt("MOVIES_ID"));
                provoli.setMovieName(rs.getString("MOVIES_NAME"));
                provoli.setCinemaId(rs.getInt("CINEMAS_ID"));
                provoli.setId(rs.getInt("ID"));
                provoli.setContentAdminId(rs.getInt("CONTENT_ADMIN_ID"));
                provoli.setNum_of_seats(rs.getInt("NUM_OF_SEATS"));
                provoli.setDate(rs.getDate("PROVOLI_DATE").toLocalDate());
                provoli.setStartTime(rs.getTime("PROVOLI_START_TIME").toLocalTime());
                provoli.setEndTime(rs.getTime("PROVOLI_END_TIME").toLocalTime());
                //provoli.setNum_of_seats(cinemaDAO.getReservationsForProvoli(provoli.getId()));

                provoles.add(provoli);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch(SQLException e){

        }
        return provoles;
    }






}
