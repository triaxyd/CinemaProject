package com.triaxyd.cinema;

import com.triaxyd.users.ContentAdmins;
import com.triaxyd.users.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteProvoli", value = "/DeleteProvoli")
public class DeleteProvoliServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destPage = "/jsp/homeContentAdmin.jsp";
        HttpSession session = request.getSession(false);
        if(session.getAttribute("user")==null){
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath()+destPage);
            return;
        }

        String provoliId = request.getParameter("provoli-id-delete");
        int content_admin_id = Integer.parseInt(request.getParameter ("content_admin_id_delete"));

        Users user = (Users)session.getAttribute("user");

        String deleted = ((ContentAdmins)user).deleteProvoli(provoliId,content_admin_id);

        request.setAttribute("actionmade",deleted);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request,response);
    }
}