package com.triaxyd.mainpackage;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "Filter")
public class ServFilter implements Filter {

    public void init(FilterConfig config){
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getSession().getAttribute("user")==null){

        }


    }
}
