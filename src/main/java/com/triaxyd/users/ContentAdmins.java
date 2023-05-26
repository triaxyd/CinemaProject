package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
import com.triaxyd.cinema.Movies;
import com.triaxyd.cinema.Provoles;
import com.triaxyd.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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





    public Provoles assignMovieToCinema(String movieId, String cinemaId) {
        Provoles provoli = new Provoles();
        CinemaDAO cinemaDAO = new CinemaDAO();
        try {
            int checkmovieIdInt = Integer.parseInt(movieId);
            int checkCinemaIdInt = Integer.parseInt(cinemaId);
        } catch (NumberFormatException e) {
            return null;
        }

        if (!cinemaDAO.checkMovieExists(Integer.parseInt(movieId))) return null;
        if (!cinemaDAO.checkCinemaExists(Integer.parseInt(cinemaId))) return null;
        if(cinemaDAO.checkProvoliExists(movieId,cinemaId,this.id)){
            //provoli exists
            return null;
        }else{
            int provoliId = cinemaDAO.generateID(movieId,cinemaId,String.valueOf(this.getId()));
            String movieName = cinemaDAO.getMovie(Integer.parseInt(movieId)).getMovieTitle();
            try{
                Connection connection = DatabaseConnector.connect();
                String sql = "INSERT INTO provoles VALUES(?,?,?,?,?)";
                PreparedStatement psAssign = connection.prepareStatement(sql);
                psAssign.setInt(1,Integer.parseInt(movieId));
                psAssign.setString(2,movieName);
                psAssign.setInt(3,Integer.parseInt(cinemaId));
                psAssign.setInt(4,provoliId);
                psAssign.setInt(5,this.getId());

                psAssign.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return provoli;
    }





    public String deleteProvoli(String provoliId, int content_admin_id){
        CinemaDAO cinemaDAO = new CinemaDAO();
        try{
            int intProvoliId = Integer.parseInt(provoliId);

            Provoles provoli = cinemaDAO.getProvoli(intProvoliId);
            if(provoli==null) return "PROVOLI WITH ID " + provoliId + " NOT FOUND";

            if(!(provoli.getContentAdminId()==content_admin_id)) return "UNAUTHORIZED TO DELETE MOVIE";


            try{
                Connection connection = DatabaseConnector.connect();
                String sqlProvoli = "DELETE FROM provoles WHERE ID = ?";
                PreparedStatement ps = connection.prepareStatement(sqlProvoli);
                ps.setInt(1,provoli.getId());

                ps.executeUpdate();

                return "PROVOLI FOR -"+ provoli.getMoviesName() + "- DELETED";
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch(NumberFormatException e){
            return "WRONG INPUT";
        }
        return "";
    }







}



