package com.triaxyd.listener;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebListener
public class SessionListener implements HttpSessionListener{

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getSession().getAttribute("javax.servlet.http.HttpServletRequest");
        HttpServletResponse response = (HttpServletResponse) event.getSession().getAttribute("javax.servlet.http.HttpServletResponse");

        event.getSession().invalidate();
        String destPage = "/index.jsp";
        try {
            response.sendRedirect(request.getContextPath() + destPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
