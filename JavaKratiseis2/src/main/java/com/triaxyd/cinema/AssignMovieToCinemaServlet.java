package com.triaxyd.cinema;

import com.triaxyd.users.ContentAdmins;
import com.triaxyd.users.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AssignMovieToCinema", value = "/AssignMovieToCinema")
public class AssignMovieToCinemaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String movieId = request.getParameter("movie-id");
        String cinemaId = request.getParameter("cinema-id");

        HttpSession session = request.getSession(false);
        Users user = (ContentAdmins)session.getAttribute("user");
        Provoles provoli = ((ContentAdmins) user).assignMovieToCinema(movieId,cinemaId);

        String destPage = "/jsp/homeContentAdmin.jsp";
        if (provoli!=null && session.getAttribute("user")!=null){
            //provoli added
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request,response);
        }else{
            //provoli couldn't be inserted
            response.sendRedirect(request.getContextPath()+destPage);
        }
    }
}
