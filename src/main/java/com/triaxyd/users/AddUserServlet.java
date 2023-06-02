package com.triaxyd.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destPage;
        try{
            HttpSession session = request.getSession(false);
            Users user = (Users)session.getAttribute("user");
            destPage = "/jsp/homeAdmin.jsp";
            if(user==null){
                destPage = "/index.jsp";
                response.sendRedirect(request.getContextPath()+destPage);
                return;
            }
            String name = request.getParameter("name-add");
            String username = request.getParameter("username-add");
            String email = request.getParameter("email-add");
            String password = request.getParameter("password-add");
            Date create_time = Date.valueOf(LocalDate.now());
            String role = request.getParameter("role-add");
            String salt;
            String hashedPassword;


            UserDAO userDAO = new UserDAO();
            String result;
            Users userFound = userDAO.getUser(username);
            if(userFound==null){
                //user doesnt exist
                salt = BCrypt.gensalt(12);
                hashedPassword = BCrypt.hashpw(password, salt);
                userDAO.createUser(username,email,hashedPassword,create_time,role,salt);
                userDAO.createRole(username,name,role);

                result = "user with username " + username + " created";
            }else{
                //user exists
                result = "User " + username + " is already registered";
            }

            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Last-Modified", (new java.util.Date()).getTime());
            String encodedMessage = URLEncoder.encode(result, "UTF-8");
            String redirectURL = request.getContextPath()+destPage + "?resultadd=" + encodedMessage;
            response.sendRedirect(redirectURL);
        }catch (NullPointerException e) {
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath() + destPage);
        }

    }
}