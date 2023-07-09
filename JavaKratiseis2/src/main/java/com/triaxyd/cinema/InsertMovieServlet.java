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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@WebServlet(name = "InsertMovie", value = "/InsertMovie")
public class InsertMovieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destPage;
        try{
            destPage="/jsp/homeContentAdmin.jsp";
            HttpSession session = request.getSession(false);
            if(session.getAttribute("user")==null){
                destPage = "/index.jsp";
                response.sendRedirect(request.getContextPath()+destPage);
                return;
            }
            String title = request.getParameter("title").toUpperCase().trim();
            String content = request.getParameter("content").trim();
            String sLength = request.getParameter("length").trim();
            String type = request.getParameter("type").trim();
            String summary = request.getParameter("summary").trim();
            String director  = request.getParameter("director").trim();
            String content_admin_ID = request.getParameter("content_admin_id").trim();

            CinemaDAO cinemaDAO = new CinemaDAO();
            int id = cinemaDAO.generateID(title);

            int content_admin_id = Integer.parseInt(content_admin_ID);
            int length = Integer.parseInt(sLength);

            Users user = (ContentAdmins)session.getAttribute("user");
            Movies movie = ((ContentAdmins) user).insertMovie(id,title,content,length,type,summary,director,content_admin_id);

            String message;
            if(movie!=null){
                message = "MOVIE " +title + " ADDED";
                //request.setAttribute("actionmade","MOVIE "+ title + " ADDED");
            }else{
                message = "MOVIE " + title + " ALREADY EXISTS";
                //request.setAttribute("actionmade","MOVIE " +title + " ALREADY EXISTS");
            }
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
