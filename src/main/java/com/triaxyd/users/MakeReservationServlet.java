package com.triaxyd.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MakeReservation", value = "/MakeReservation")
public class MakeReservationServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session==null) return;

        String movieId = request.getParameter("movieId");
        String cinemaId = request.getParameter("cinemaId");

        Users customer  = (Users)session.getAttribute("user");
        if(!customer.getRole().equals("Customer")) return;

    }
}
