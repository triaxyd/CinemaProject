package com.triaxyd.users;

import com.triaxyd.cinema.Movies;
import com.triaxyd.cinema.Provoles;
import com.triaxyd.cinema.Reservations;
import com.triaxyd.cinema.CinemaDAO;

import java.util.Date;
import java.util.List;



public class Admins extends Users {

    private String name;
    private int id;
    public Admins() {this.setRole("Admin");}


    public void setName(String name){this.name = name;}
    public void setId(int id){this.id=id;}

    public String getName(){return this.name;}
    public int getId(){return this.id;}


    public String addUser(String username,String name, String email, String password, Date create_time,String role) {
        UserDAO userDAO = new UserDAO();
        String result;
        Users userFound = userDAO.getUser(username);

        if(userFound==null){
            //user doesnt exist
            userDAO.createUser(username,email,password,(java.sql.Date) create_time,role);
            userDAO.createRole(username,name,role);

            result = "user with username " + username + " created";
        }else{
            //user exists
            result = "User " + username + " is already registered";
        }
        return result;
    }


    public String deleteUser(String username) {

        UserDAO userDAO = new UserDAO();
        Users user = userDAO.getUser(username);
        if (user==null) return "User " + " not found";

        CinemaDAO cinemaDAO = new CinemaDAO();
        if(user.getRole().equals("Customer")){
            List<Reservations> reservations = cinemaDAO.getReservationsForUser(user.getId());
            for(Reservations r: reservations){
                return "Customer " + username + " has reservations";
            }
        } else if (user.getRole().equals("ContentAdmin")) {
            List<Movies> movies = cinemaDAO.getMoviesForContentAdmin(user);
            for(Movies m: movies){
                return "Content Admin with ID:" +user.getId()+" has created Movies";
            }
            List<Provoles> provoles = cinemaDAO.getProvolesForContentAdmin(user);
            for(Provoles p : provoles){
                return "Content Admin with ID:"+user.getId()+ " has created Provoles";
            }
        }
        if(user.getRole().equals("Admin")) return "Cannot delete ADMIN";

        if(userDAO.deleteUser(user)){
            return username + " deleted";
        }else{
            return "User " + username + " could not be deleted";
        }
    }

    public Users searchUser(String username) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUser(username);
    }

    public void updateUser() {}


    public void viewAllUsers() {}


}