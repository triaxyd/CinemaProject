package com.triaxyd.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users user = (Users)session.getAttribute("user");
        String destPage = "/jsp/homeAdmin.jsp";
        if(user==null){
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath()+destPage);
            return;
        }
        return;

    }
}