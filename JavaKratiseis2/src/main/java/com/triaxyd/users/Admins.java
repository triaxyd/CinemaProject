package com.triaxyd.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Admins extends Users {

    private String name;
    private int id;
    public Admins() {this.setRole("Admin");}

    /*
    public Admins(String username, String name) {
        this.setName(name);
        this.setUsername(username);
    }

     */


    public void setName(String name){this.name = name;}
    public void setId(int id){this.id=id;}
    public String getName(){return this.name;}
    public int getId(){return this.id;}

    public void login() {
        System.out.println("--- Welcome Admin, " + getUsername().toUpperCase() + " ---");
    }

    public void logout() {
        System.out.println("Admin " + getUsername().toUpperCase() + " logged out.");
    }

    public void showUserMenu() {
        System.out.println("--\n1.Create Customer");
        System.out.println("2.Update User");
        System.out.println("3.Delete User");
        System.out.println("4.Search User");
        System.out.println("5.View All Users");
        System.out.println("6.Register Admin");
        System.out.println("7.Logout\n--");
    }

    public Users createUser() {
        Users user = new Customers();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        getInput(reader , user);
        System.out.println("Creating User " + this.getName());
        return user;
    }

    public void updateUser(List<Users> users) {
        boolean userFound = false;
        String userInput = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Username of the User to update: ");
        try{
            userInput=reader.readLine();
        }catch(IOException e){
            System.out.println("Error in input.");
        }
        for (Users user : users){
            if (userInput.equals(user.getUsername())){
                userFound = true;
                System.out.print("Enter name: ");
                try {
                    this.setName(reader.readLine());
                } catch (IOException e) {
                    System.out.println("Error in Name");
                }
                System.out.print("Enter Username: ");
                try {
                    user.setUsername(reader.readLine());
                } catch (IOException e) {
                    System.out.println("Error in Username.");
                }
                System.out.print("Enter Password: ");
                try {
                    user.setPassword(reader.readLine());
                } catch (IOException e) {
                    System.out.println("Error in Password");
                }
            }
        }
        if (!userFound){
            System.out.println("User not found.");
        }
    }

    public void deleteUser(List<Users> users, Users userInControl) {
        boolean userFound = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input= null;
        System.out.print("Enter the Username of the User you want to Delete: ");
        try{
            input = reader.readLine();
        }catch (IOException e){
            System.out.println("Error in input.");
        }
        for (Users user : users){
            if (input.equals(user.getUsername())){
                userFound= true;
                if(user.getUsername().equals(userInControl.getUsername())){
                    System.out.println("You can't delete this user.");
                    break;
                }else if(!((Customers)user).getReservationsUser().isEmpty()){
                    System.out.println("Cannot delete User with active Reservations.");
                    break;
                }else{
                    System.out.println("Removing User: " + user.getUsername());
                    users.remove(user);
                    break;
                }
            }
        }
        if (!userFound){
            System.out.println("User not found.");
        }
    }

    public void searchUser(List<Users> users) {
        boolean userFound = false;
        String userInput = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the Username: ");
        try {
            userInput = reader.readLine();
        }catch (IOException e) {
            System.out.println("Error in user Input.");
        }
        for (Users user : users){
            if (userInput.equals(user.getUsername())){
                userFound = true;
                System.out.println("Name: " + this.getName() + " Username: " + user.getUsername());
                break;
            }
        }
        if (!userFound){
            System.out.println("User not found.");
        }
    }

    public void viewAllUsers(List<Users> users) {
        System.out.println("Viewing All Users..");
        for (Users user : users) {
            System.out.print("Name: " + this.getName() + " Username: " + user.getUsername());
            if (user instanceof Customers) {
                System.out.println(" Role: Customer\n");
            } else if (user instanceof ContentAdmins) {
                System.out.println(" Role: Content Admin\n");
            } else {
                System.out.println(" Role: Admin\n");
            }
        }
    }

    public Users registerAdmin() {
        Users user= null;
        int userInput;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter (1) for Content Admin, Enter (2) for Admin: ");
            try {
                userInput = Integer.parseInt(reader.readLine());
                break;
            } catch (IOException e) {
                System.out.println("Error in Input.");
            } catch (NumberFormatException e) {
                System.out.print("Please Enter (1) for Content Admin, Enter (2) for Admin: ");
            }
        }
        switch (userInput) {
            case 1:
                user = new ContentAdmins();
                getInput(reader, user);
                System.out.println("Created Content Admin " + this.getName());
                break;
            case 2:
                user = new Admins();
                getInput(reader, user);
                System.out.println("Created Admin " +this.getName());
                break;
            default:
                System.out.println("Could't create User.");break;
        }
        return user;
    }


    private Users getInput(BufferedReader reader,Users user){
        System.out.print("Enter Name: ");
        try{
            this.setName(reader.readLine());
        }catch (IOException e){
            System.out.println("Error in Name");
        }
        System.out.print("Enter Username: ");
        try {
            user.setUsername(reader.readLine());
        }catch (IOException e){
            System.out.println("Error in Username.");
        }
        System.out.print("Enter Password: ");
        try {
            user.setPassword(reader.readLine());
        }catch (IOException e){
            System.out.println("Error in Password");
        }
        return user;
    }
}