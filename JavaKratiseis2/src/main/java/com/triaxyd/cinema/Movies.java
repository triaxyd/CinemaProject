package com.triaxyd.cinema;

public class Movies {
    private int movieID, movieLength, movieContentAdminID;
    private String movieTitle, movieContent, movieType, movieSummary, movieDirector;

    public Movies(){

    }


    public Movies(int id, String title, String content,int length,String type,String summary,String director,int content_admin_id){
        this.setMovieID(id);
        this.setMovieTitle(title);
        this.setMovieContent(content);
        this.setMovieLength(length);
        this.setMovieType(type);
        this.setMovieSummary(summary);
        this.setMovieDirector(director);
        this.setMovieContentAdminID(content_admin_id);
    }


    public void setMovieID(int movieID) { this.movieID = movieID;}
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle;}
    public void setMovieContent(String movieContent){this.movieContent = movieContent;}
    public void setMovieLength(int movieLength){this.movieLength = movieLength;}
    public void setMovieType(String movieType){this.movieType = movieType;}
    public void setMovieSummary(String movieSummary){this.movieSummary = movieSummary;}
    public void setMovieDirector(String movieDirector){this.movieDirector = movieDirector;}
    public void setMovieContentAdminID(int movieContentAdminID){this.movieContentAdminID = movieContentAdminID;}


    public int getMovieID(){return movieID;}
    public String getMovieTitle(){return movieTitle;}
    public String getMovieContent(){return movieContent;}
    public int getMovieLength(){return movieLength;}
    public String getMovieType(){return movieType;}
    public String getMovieSummary(){return movieSummary;}
    public String getMovieDirector(){return movieDirector;}
    public int getMovieContentAdminID(){return movieContentAdminID;}



}
