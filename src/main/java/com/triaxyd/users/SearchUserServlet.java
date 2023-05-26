package com.triaxyd.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SearchUser", value = "/SearchUser")
public class SearchUserServlet extends HttpServlet {
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
        String username = request.getParameter("username-search");
        Users foundUser = ((Admins)user).searchUser(username);
        request.setAttribute("searched-user",foundUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
        requestDispatcher.forward(request,response);
    }
}
