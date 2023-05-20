package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
import com.triaxyd.cinema.Cinemas;
import com.triaxyd.cinema.Movies;
import com.triaxyd.cinema.Provoles;
import com.triaxyd.database.DatabaseConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.time.LocalDate;

public class ContentAdmins extends Users {
    private String name;
    private int id;
    public ContentAdmins(){
        this.setRole("ContentAdmin");
    }

    /*
    public ContentAdmins(String name,String username, String password){
        this.setRole("ContentAdmin");
        this.setUsername(username);
        this.setPassword(password);
    }
     */

    public void setName(String name){this.name=name;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public int getId(){return this.id;}
    public void login(){}
    public void logout(){}

    public void showUserMenu(){

    }

    public Movies insertMovie(int id,String title,String content,int length,String type,String summary,String director,int content_admin_id){
        CinemaDAO cinemaDAO = new CinemaDAO();
        Movies movie = null;
        if(cinemaDAO.checkMovie(id,title,content_admin_id)){
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







    public void deleteFilm(){

    }








    public void assignFilmToCinema() {

    }
}



