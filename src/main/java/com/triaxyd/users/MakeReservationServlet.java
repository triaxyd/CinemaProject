package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
import com.triaxyd.cinema.Provoles;
import com.triaxyd.users.Customers;
import com.triaxyd.users.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

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

            int movieId = 0,cinemaId=0,customerId=0,numOfSeats=0;
            LocalDate date = null;
            LocalTime time = null;
            try{
                movieId = Integer.parseInt(request.getParameter("movieId"));
                cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
                customerId= Integer.parseInt(request.getParameter("customerId"));
                date = LocalDate.parse(request.getParameter("date"));
                time = LocalTime.parse(request.getParameter("time"));
                numOfSeats = Integer.parseInt(request.getParameter("numOfSeats"));
            }catch(NumberFormatException e){
                message = "Something went wrong, try again";
                destPage ="/index.jsp";
                response.sendRedirect(request.getContextPath()+destPage+"?message"+message);
            }

            if(movieId!=0 && cinemaId!=0 && date!=null && time!=null && customerId==user.getId()){
                Provoles provoli = cinemaDAO.getProvoli(movieId,cinemaId,date,time);
                message = ((Customers)user).makeReservation(provoli.getId(),numOfSeats);
            }else{
                message = "Something went wrong";
            }
            response.sendRedirect(request.getContextPath()+destPage+"?message=" +message);
        }catch(NullPointerException e){
            message = "Something went wrong";
            destPage ="/index.jsp";
            response.sendRedirect(request.getContextPath()+destPage+"?message"+message);
        }




    }
}
