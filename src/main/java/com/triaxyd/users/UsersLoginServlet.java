package com.triaxyd.users;
import com.triaxyd.database.DatabaseConnector;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.PrintWriter;
import java.sql.*;
import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;


@WebServlet(name = "UsersLogin", value = "/UsersLogin")
public class UsersLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usrname = request.getParameter("username");
        String usrpsw = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        Users user = userDAO.checkLogin(usrname,usrpsw);

        String destPage = "/index.jsp";
        if(user!=null){
            //user was found
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            session.setMaxInactiveInterval(30*60);

            //Cookie userName = new Cookie("username", user.getUsername());
            //Cookie userRole = new Cookie("userrole",user.getRole());
            //Cookie userId = new Cookie("userId",String.valueOf(user.getId()));
            //userName.setMaxAge(30*60);
            //userRole.setMaxAge(30*60);
            //response.addCookie(userName);
            //response.addCookie(userRole);
            //response.addCookie(userId);

            if(user.getRole().equals("Admin")){
                destPage="/jsp/homeAdmin.jsp";
            }else if(user.getRole().equals("ContentAdmin")){
                destPage="/jsp/homeContentAdmin.jsp";
            }else{
                destPage="/jsp/homeCustomer.jsp";
            }
            response.sendRedirect(request.getContextPath()+destPage);
        }else{
            //user was not found
            String message = "Wrong username/password";
            request.setAttribute("message",message);
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request,response);
        }
    }
}
