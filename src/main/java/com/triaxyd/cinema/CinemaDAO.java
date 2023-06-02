package com.triaxyd.cinema;

import com.triaxyd.database.DatabaseConnector;

import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public boolean checkProvoliExists(String movieId,String cinemaId){
        String movieName = null;
        CinemaDAO cinemaDAO = new CinemaDAO();
        movieName = cinemaDAO.getMovie(Integer.parseInt(movieId)).getMovieTitle();

        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM provoles WHERE MOVIES_ID = ? AND MOVIES_NAME=? AND CINEMAS_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(movieId));
            ps.setString(2,movieName);
            ps.setInt(3,Integer.parseInt(cinemaId));
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

    public Provoles getProvoli(int provoliId){
        for(Provoles p : getProvoles()){
            if(p.getId()==provoliId){
                return p;
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


    public static List<Provoles> getProvoles(){
        List<Provoles> provoles= new ArrayList<Provoles>();
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * from provoles";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Provoles provoli = new Provoles();
                provoli.setMoviesId(rs.getInt("MOVIES_ID"));
                provoli.setMoviesName(rs.getString("MOVIES_NAME"));
                provoli.setCinemaId(rs.getInt("CINEMAS_ID"));
                provoli.setId(rs.getInt("ID"));
                provoli.setContentAdminId(rs.getInt("CONTENT_ADMIN_ID"));

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
