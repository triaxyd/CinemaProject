package com.triaxyd.cinema;

import com.triaxyd.users.Customers;
import com.triaxyd.users.Users;
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

        Users user = (Customers)session.getAttribute("user");
        if(user==null)return;

        CinemaDAO cinemaDAO = new CinemaDAO();
        String message;

        String movieId = request.getParameter("movieId");
        String cinemaId = request.getParameter("cinemaId");
        String customerId = request.getParameter("customerId");
        String numOfSeats = request.getParameter("numOfSeats");

        if(movieId.isEmpty() || cinemaId.isEmpty() || customerId.isEmpty() || numOfSeats.isEmpty()){
            message = "Something went wrong with the reservation, try again";
        }
        Provoles provoli = cinemaDAO.getProvoli(movieId,cinemaId);
        message = ((Customers)user).makeReservation(provoli.getId(),Integer.parseInt(numOfSeats));




    }
}
