
package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
import com.triaxyd.cinema.Cinemas;
import com.triaxyd.cinema.Movies;
import com.triaxyd.cinema.Provoles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public void setId(int Id){this.id=id;}
    public String getName(){return name;}
    public int getId(){return this.id;}
    public void login(){}
    public void logout(){}

    public void showUserMenu(){

    }

    public Movies insertMovie(String title,String content,String length,String type,String summary,String director,String content_admin_ID){
        CinemaDAO cinemaDAO = new CinemaDAO();
        Movies movie = cinemaDAO.checkMovie(title,content,length,type,summary,director,content_admin_ID);
        return movie;
    }







    public void deleteFilm(){

    }








    public void assignFilmToCinema() {

    }
}



