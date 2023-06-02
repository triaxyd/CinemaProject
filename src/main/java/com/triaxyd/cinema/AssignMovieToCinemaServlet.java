package com.triaxyd.cinema;

import com.triaxyd.users.ContentAdmins;
import com.triaxyd.users.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

@WebServlet(name = "AssignMovieToCinema", value = "/AssignMovieToCinema")
public class AssignMovieToCinemaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String destPage;
        try{
            destPage = "/jsp/homeContentAdmin.jsp";
            HttpSession session = request.getSession(false);
            if(session.getAttribute("user")==null){
                destPage = "/index.jsp";
                response.sendRedirect(request.getContextPath()+destPage);
                return;
            }

            String movieId = request.getParameter("movie-id");
            String cinemaId = request.getParameter("cinema-id");

            Users user = (ContentAdmins)session.getAttribute("user");
            Provoles provoli = ((ContentAdmins) user).assignMovieToCinema(movieId,cinemaId);

            String message;
            if (provoli!=null){
                //provoli added
                message = "PROVOLI FOR " + provoli.getMoviesName() +" at CINEMA " + provoli.getCinemaId()  + " ADDED";
            }else{
                //provoli couldn't be inserted
                message = "COULDN'T ADD PROVOLI";
            }
            //request.setAttribute("actionmade",message);
            //RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            //dispatcher.forward(request,response);

            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Last-Modified", (new Date()).getTime());
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String redirectURL = request.getContextPath()+destPage + "?actionmade=" + encodedMessage;
            response.sendRedirect(redirectURL);
        }catch (NullPointerException e) {
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath() + destPage);
        }

    }
}
