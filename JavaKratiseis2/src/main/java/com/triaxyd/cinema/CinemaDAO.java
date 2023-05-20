package com.triaxyd.cinema;

import com.triaxyd.database.DatabaseConnector;
import jakarta.servlet.RequestDispatcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {

    public boolean checkMovie(int id, String title, int content_admin_id){
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM movies WHERE ID=? AND NAME=? AND CONTENT_ADMIN_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,title);
            ps.setInt(3,content_admin_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //movie already exists
                return true;
            }
            return false;
        }catch(SQLException e){

        }
        return true;
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
                movie.setFilmId(rs.getInt("ID"));
                movie.setFilmTitle(rs.getString("NAME"));
                movie.setFilmContent(rs.getString("CONTENT"));
                movie.setFilmLength(rs.getInt("LENGTH"));
                movie.setFilmType(rs.getString("TYPE"));
                movie.setFilmSummary(rs.getString("SUMMARY"));
                movie.setFilmDirector(rs.getString("DIRECTOR"));
                movie.setFilmContentAdminID(rs.getInt("CONTENT_ADMIN_ID"));
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
}
