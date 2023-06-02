package com.triaxyd.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

@WebServlet(name = "DeleteUser", value = "/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destPage;
        try{
            HttpSession session = request.getSession(false);
            Users user = (Users) session.getAttribute("user");
            destPage = "/jsp/homeAdmin.jsp";
            if(user==null){
                destPage = "/index.jsp";
                response.sendRedirect(request.getContextPath()+destPage);
                return;
            }

            String username = request.getParameter("delete-username");
            String resultDelete = ((Admins)user).deleteUser(username);

            //request.setAttribute("resultdelete",resultDelete);
            //RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
            //requestDispatcher.forward(request,response);


            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Last-Modified", (new Date()).getTime());
            String encodedMessage = URLEncoder.encode(resultDelete, "UTF-8");
            String redirectURL = request.getContextPath()+destPage + "?resultdelete=" + encodedMessage;
            response.sendRedirect(redirectURL);
        }catch (NullPointerException e) {
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath() + destPage);
        }
    }
}
