package com.triaxyd.cinema;

public class Movies {
    private int filmId,filmLength,filmContentAdminID;
    private String filmTitle,filmContent,filmType,filmSummary,filmDirector;

    public Movies(){

    }


    public Movies(int id, String title, String content,int length,String type,String summary,String director,int content_admin_id){
        this.setFilmId(id);
        this.setFilmTitle(title);
        this.setFilmContent(content);
        this.setFilmLength(length);
        this.setFilmType(type);
        this.setFilmSummary(summary);
        this.setFilmDirector(director);
        this.setFilmContentAdminID(content_admin_id);
    }


    public void setFilmId(int filmId) { this.filmId = filmId;}
    public void setFilmTitle(String filmTitle) { this.filmTitle = filmTitle;}
    public void setFilmContent(String filmContent){this.filmContent=filmContent;}
    public void setFilmLength(int filmLength){this.filmLength=filmLength;}
    public void setFilmType(String filmType){this.filmType=filmType;}
    public void setFilmSummary(String filmSummary){this.filmSummary=filmSummary;}
    public void setFilmDirector(String filmDirector){this.filmDirector= filmDirector;}
    public void setFilmContentAdminID(int filmContentAdminID){this.filmContentAdminID=filmContentAdminID;}


    public int getFilmId(){return filmId;}
    public String getFilmTitle(){return filmTitle;}
    public String getFilmContent(){return filmContent;}
    public int getFilmLength(){return filmLength;}
    public String getFilmType(){return filmType;}
    public String getFilmSummary(){return filmSummary;}
    public String getFilmDirector(){return filmDirector;}
    public int getFilmContentAdminID(){return filmContentAdminID;}



}
