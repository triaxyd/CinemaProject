package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
import com.triaxyd.cinema.Provoles;
import com.triaxyd.users.Customers;
import com.triaxyd.users.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MakeReservation", value = "/MakeReservation")
public class MakeReservationServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String destPage = "/jsp/homeCustomer.jsp";
        String message;
        try{
            HttpSession session = request.getSession(false);
            if(session==null) return;

            Users user = (Customers)session.getAttribute("user");
            if(user==null)return;

            CinemaDAO cinemaDAO = new CinemaDAO();


            String movieIdStr = request.getParameter("movieId");
            int movieId = Integer.parseInt(movieIdStr);
            String cinemaIdStr = request.getParameter("cinemaId");
            int cinemaId  = Integer.parseInt(cinemaIdStr);
            String customerIdStr = request.getParameter("customerId");
            int customerId = Integer.parseInt(customerIdStr);
            String numOfSeatsStr = request.getParameter("numOfSeats");
            int numOfSeats = Integer.parseInt(numOfSeatsStr);

            if(movieIdStr.isEmpty() || cinemaIdStr.isEmpty() || customerIdStr.isEmpty() || numOfSeatsStr.isEmpty()){
                message = "Something went wrong, try again";
            }
            Provoles provoli = cinemaDAO.getProvoli(movieId,cinemaId);
            message = ((Customers)user).makeReservation(provoli.getId(),numOfSeats);

            response.sendRedirect(request.getContextPath()+destPage+"?message=" +message);
        }catch(NullPointerException e){
            message = "Something went wrong";
            destPage ="/index.jsp";
            response.sendRedirect(request.getContextPath()+destPage+"?message"+message);
        }




    }
}
