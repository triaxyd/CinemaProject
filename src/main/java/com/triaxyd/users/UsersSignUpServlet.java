package com.triaxyd.users;
import com.triaxyd.users.Users;
import com.triaxyd.database.DatabaseConnector;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;


@WebServlet(name = "UsersSignUp", value = "/UsersSignUp")
public class UsersSignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String message = "";
        UserDAO userDAO = new UserDAO();
        if (userDAO.checkUserExists(username)) {
            //User already exists
            message = "User " + username +" already exists";
        } else {
            //user not found
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Date createTime = Date.valueOf(LocalDate.now());
            String role = "Customer";
            String salt = BCrypt.gensalt(12);
            String hashedPassword = BCrypt.hashpw(password, salt);

            userDAO.createUser(username, email, hashedPassword, createTime, role, salt);

            userDAO.createRole(username,name,role);

            message = "user with username " + username + " created";



        }
        request.setAttribute("message",message);
        request.setAttribute("hiddenusername",username);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try{
            dispatcher.forward(request,response);
        }catch(ServletException e){

        }
    }




}
