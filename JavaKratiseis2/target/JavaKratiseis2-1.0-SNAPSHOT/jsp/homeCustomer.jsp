<%@ page import="com.triaxyd.users.Users" %>
<%@ page import="com.triaxyd.users.Customers" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CIN3MAS CUSTOMER</title>
    <link rel="stylesheet" href="../css/homeCustomer.css">
</head>
<body>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("/index.jsp");
    }
    Users user = ((Users)session.getAttribute("user"));
    String userName = null;
    String userRole = null;
    String sessionID = null;
    String userId = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) userName = cookie.getValue();
            if(cookie.getName().equals("userrole")) userRole = cookie.getValue();
            if(cookie.getName().equals("userId")) userId = cookie.getValue();
            if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>
<header>
    <input type="hidden" name="sessionid" value="<%=session.getId()%>">
    <input type="hidden" name="username" value="<%=user.getUsername()%>">
    <input type="hidden" name="userrole" value="<%=user.getRole()%>">
    <input type="hidden" name="userId" value="<%=user.getId()%>">
    <nav>
        <ul>
            <li><a href="#movies">Movies</a></li>
            <li><a href="#you">Events</a></li>
            <li><a href="#offers">Offers</a></li>
            <li><a href="#contact">Contact</a></li>
        </ul>
    </nav>
    <div>WELCOME TO CIN3MAS, <%=user.getUsername()%></div>
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
    <!-- Movie content goes here -->
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

<script src="../js/homeCustomer.js"></script>
</body>
</html>
