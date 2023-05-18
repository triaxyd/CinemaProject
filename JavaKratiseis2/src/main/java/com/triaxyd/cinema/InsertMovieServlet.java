package com.triaxyd.cinema;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.x.Notice;
import com.triaxyd.users.ContentAdmins;
import com.triaxyd.users.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "InsertMovie", value = "/InsertMovie")
public class InsertMovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String length = request.getParameter("length");
        String type = request.getParameter("type");
        String summary = request.getParameter("summary");
        String director  = request.getParameter("director");
        String content_admin_ID = request.getParameter("content_admin_id");

        Users user = ((ContentAdmins)session.getAttribute("user"));
        Movies movie = ((ContentAdmins) user).insertMovie(title,content,length,type,summary,director,content_admin_ID);
        if(movie!=null){
            request.setAttribute("message","Movie "+ title + " added");
            RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath()+"/homeContentAdmin.jsp");
        }else{

        }

    }
}
