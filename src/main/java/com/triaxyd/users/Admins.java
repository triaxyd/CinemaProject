package com.triaxyd.users;

import com.triaxyd.cinema.Reservations;
import com.triaxyd.cinema.CinemaDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.triaxyd.cinema.CinemaDAO.getReservations;


public class Admins extends Users {

    private String name;
    private int id;
    public Admins() {this.setRole("Admin");}


    public void setName(String name){this.name = name;}
    public void setId(int id){this.id=id;}

    public String getName(){return this.name;}
    public int getId(){return this.id;}


    public Users createUser() {
        return null;
    }

    public void updateUser(List<Users> users) {
    }

    public String deleteUser(String username) {
        UserDAO userDAO = new UserDAO();
        Users user = userDAO.getUser(username);
        if (user==null) return "username " + username + " not found";
        if(user.getRole().equals("Admin")) return "Cannot delete ADMIN";
        for(Reservations reservations : getReservations()){
            if(user.getId()==reservations.getCustomers_id())return"User has reservations";
        }
        if(userDAO.deleteUser(user)){
            return username + " deleted";
        }else{
            return "User " + username + " could not be deleted";
        }
    }

    public Users searchUser(String username) {
        UserDAO userDAO = new UserDAO();
        Users user = userDAO.getUser(username);
        return user;
    }

    public void viewAllUsers() {

    }


}