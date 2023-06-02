package com.triaxyd.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "SearchUser", value = "/SearchUser")
public class SearchUserServlet extends HttpServlet {
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
            String username = request.getParameter("username-search");
            Users foundUser = ((Admins)user).searchUser(username);
            if(foundUser!=null){
                request.setAttribute("searched-user",foundUser);
            }else{
                request.setAttribute("user-not-found-message", username +" not found");
            }
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);


            request.getRequestDispatcher(destPage).forward(request, response);
        }catch (NullPointerException e) {
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath() + destPage);
        }




    }
}
