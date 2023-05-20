<%@ page import="com.triaxyd.users.Users" %>
<%@ page import="com.triaxyd.users.Customers" %>
<%@ page import="com.triaxyd.cinema.CinemaDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.xml.crypto.Data" %>
<%@ page import="com.triaxyd.database.DatabaseConnector" %>
<%@ page import="com.triaxyd.cinema.Movies" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.swing.plaf.basic.BasicInternalFrameTitlePane" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CIN3MAS CUSTOMER</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/homeCustomer.css">
</head>
<body>
<%
    if (session.getAttribute("user") == null) {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/jsp/accessDenied.jsp");
        return;
    }
    Users user = ((Users)session.getAttribute("user"));
    if(!user.getRole().equals("Customer")){
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/accessDenied.jsp");
        rd.forward(request,response);
        session.removeAttribute("user");
        session.invalidate();
        return;
    }
    String userName = user.getUsername();
    String userRole = null;
    String userId = null;
    String scookie = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) userName = cookie.getValue();
            if(cookie.getName().equals("userrole")) userRole = cookie.getValue();
            if(cookie.getName().equals("userId")) userId = cookie.getValue();
            if (cookie.getName().equals("JSESSIONID")) scookie = cookie.getValue();
        }
    }
    List<Movies> moviesList = CinemaDAO.getMovies();
%>
<header>
    <input type="hidden" name="sessionid" value="<%=session.getId()%>">
    <input type="hidden" name="username" value="<%=userName%>">
    <input type="hidden" name="userrole" value="<%=userRole%>">
    <input type="hidden" name="userId" value="<%=userId%>">
    <nav>
        <ul>
            <li><a href="#movies">Movies</a></li>
            <li><a href="#you">Events</a></li>
            <li><a href="#offers">Offers</a></li>
            <li><a href="#contact">Contact</a></li>
        </ul>
    </nav>
    <div>WELCOME TO CIN3MAS, <%=userName%></div>
    <nav class="logout">
        <ul>
            <li>
                <input class="logout" type="button" value="LOGOUT" onclick="location.href='${pageContext.request.contextPath}/LogOut';">
            </li>
        </ul>
    </nav>
</header>


<section id="movies">
    <h2>Movies</h2>
    <div class="display-movies-container">
        <ul>

        </ul>

    </div>
</section>

<section id="you">
    <h2>You</h2>
    <!-- Event content goes here -->
</section>

<section id="offers">
    <h2>Offers</h2>
    <!-- Offer content goes here -->
</section>

<section id="contact">
    <h2>Contact</h2>
    <!-- Contact content goes here -->
</section>

<script src="<%= request.getContextPath() %>/js/homeCustomer.js"></script>
</body>
</html>
