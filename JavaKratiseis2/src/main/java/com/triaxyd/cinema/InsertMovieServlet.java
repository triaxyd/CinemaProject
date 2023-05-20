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
        HttpSession session = request.getSession(false);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String sLength = request.getParameter("length");
        String type = request.getParameter("type");
        String summary = request.getParameter("summary");
        String director  = request.getParameter("director");
        String content_admin_ID = request.getParameter("content_admin_id");

        int id = generateMovieID(title,content_admin_ID);

        int content_admin_id = Integer.parseInt(content_admin_ID);
        int length = Integer.parseInt(sLength);

        Users user = ((ContentAdmins)session.getAttribute("user"));
        Movies movie = ((ContentAdmins) user).insertMovie(id,title,content,length,type,summary,director,content_admin_id);
        String destPage="/jsp/homeContentAdmin.jsp";
        if(movie!=null){
            request.setAttribute("messageinsert","Movie "+ title + " added");
        }else{
            request.setAttribute("messageinsert","Movie " +title + " already exists");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request,response);
    }

    public static int generateMovieID(String title, String content_admin_ID) {
        String combination = (title.toUpperCase().trim() + "" + content_admin_ID).trim();
        byte[] hashBytes = getSHA256Hash(combination);
        return bytesToInt(hashBytes);
    }

    private static byte[] getSHA256Hash(String combination) {
        byte[] digested = new byte[0];
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(combination.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            //error
        }
        return digested;
    }

    private static int bytesToInt(byte[] bytes) {
        int result = 0;
        for (byte b : bytes) {
            result = (result << 8) | (b & 0xFF);
        }
        return Math.abs(result);
    }

}
