package com.triaxyd.cinema;

import com.triaxyd.users.ContentAdmins;
import com.triaxyd.users.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "InsertMovie", value = "/InsertMovie")
public class InsertMovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String destPage="/jsp/homeContentAdmin.jsp";
        HttpSession session = request.getSession(false);
        if(session.getAttribute("user")==null){
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath()+destPage);
            return;
        }
        String title = request.getParameter("title").toUpperCase();
        String content = request.getParameter("content");
        String sLength = request.getParameter("length");
        String type = request.getParameter("type");
        String summary = request.getParameter("summary");
        String director  = request.getParameter("director");
        String content_admin_ID = request.getParameter("content_admin_id");

        CinemaDAO cinemaDAO = new CinemaDAO();
        int id = cinemaDAO.generateID(title,content_admin_ID);

        int content_admin_id = Integer.parseInt(content_admin_ID);
        int length = Integer.parseInt(sLength);

        Users user = (ContentAdmins)session.getAttribute("user");
        Movies movie = ((ContentAdmins) user).insertMovie(id,title,content,length,type,summary,director,content_admin_id);

        if(movie!=null){
            request.setAttribute("actionmade","MOVIE "+ title + " ADDED");
        }else{
            request.setAttribute("actionmade","MOVIE " +title + " ALREADY EXISTS");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request,response);
    }


}
