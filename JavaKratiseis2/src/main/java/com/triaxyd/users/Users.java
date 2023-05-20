package com.triaxyd.users;


import java.util.Date;


public abstract class Users {

    private String username,password,email,role,salt;

    private Date create_time;
    private boolean loggedIn = false;

    public Users(){}

    public void setUsername(String username) { this.username = username;}
    public void setPassword(String password){this.password = password;}
    public void setEmail(String email){this.email=email;}
    public void setRole(String role){this.role=role;}
    public void setCreationDate(Date creationDate){this.create_time = creationDate;}
    public void setSalt(String salt){this.salt = salt;}

    public void setLoggedIn(boolean loggedIn) { this.loggedIn = true;}


    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){
        return this.role;
    }
    public String getEmail(){
        return this.email;
    }
    public Date getCreationDate(){return this.create_time;}
    public int getId(){return this.getId();}
    public String getName(){return this.getName();}

    public boolean isLoggedIn() { return this.loggedIn;}

    public abstract void showUserMenu();

    public abstract void login();

    public abstract void logout();

}



