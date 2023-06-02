package com.triaxyd.cinema;

import com.triaxyd.users.ContentAdmins;
import com.triaxyd.users.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

@WebServlet(name = "DeleteProvoli", value = "/DeleteProvoli")
public class DeleteProvoliServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destPage;
        try{
            destPage = "/jsp/homeContentAdmin.jsp";
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

            //request.setAttribute("actionmade",deleted);
            //RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            //dispatcher.forward(request,response);

            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Last-Modified", (new Date()).getTime());
            String encodedMessage = URLEncoder.encode(deleted, "UTF-8");
            String redirectURL = request.getContextPath()+destPage + "?actionmade=" + encodedMessage;
            response.sendRedirect(redirectURL);
        }catch (NullPointerException e) {
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath() + destPage);
        }

    }
}