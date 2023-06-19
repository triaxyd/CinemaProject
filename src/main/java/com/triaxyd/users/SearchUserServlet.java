package com.triaxyd.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "SearchUser", value = "/SearchUser")
public class SearchUserServlet extends HttpServlet {

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

            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if(foundUser==null){
                String encodedMessage = URLEncoder.encode("User not found", "UTF-8");
                String redirectURL = request.getContextPath()+destPage + "?not-found=" + encodedMessage;
                response.sendRedirect(redirectURL);
                return;
            }else{
                response.sendRedirect(request.getContextPath() + destPage + "?username=" + URLEncoder.encode(foundUser.getUsername(), "UTF-8")
                        + "&name=" + URLEncoder.encode(foundUser.getName(), "UTF-8")
                        + "&email=" + URLEncoder.encode(foundUser.getEmail(), "UTF-8")
                        + "&role=" + URLEncoder.encode(foundUser.getRole(), "UTF-8")
                        + "&creation=" + URLEncoder.encode(String.valueOf(foundUser.getCreationDate()), "UTF-8")
                        + "&id=" + URLEncoder.encode(String.valueOf(foundUser.getId()), "UTF-8"));
                return;
            }
        }catch (NullPointerException e) {
            destPage = "/index.jsp";
            response.sendRedirect(request.getContextPath() + destPage);
        }

    }
}
