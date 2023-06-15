package com.triaxyd.cinema;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MakeReservation", value = "/MakeReservation")
public class MakeReservationServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session==null) return;
        if(session.getAttribute("user")==null)return;

        String message;

        String movieId = request.getParameter("movieId");
        String cinemaId = request.getParameter("cinemaId");
        String customerId = request.getParameter("customerId");
        String numOfSeats = request.getParameter("numOfSeats");

        if(movieId.isEmpty() || cinemaId.isEmpty() || customerId.isEmpty() || numOfSeats.isEmpty()){
            message = "Something went wrong with the reservation, try again";
        }


    }
}
