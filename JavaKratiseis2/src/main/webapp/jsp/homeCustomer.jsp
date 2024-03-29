<%@ page import="com.triaxyd.users.Users" %>
<%@ page import="com.triaxyd.users.Customers" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.xml.crypto.Data" %>
<%@ page import="com.triaxyd.database.DatabaseConnector" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.swing.plaf.basic.BasicInternalFrameTitlePane" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.triaxyd.cinema.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalTime" %>
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
    List<Provoles> provolesList = CinemaDAO.getProvoles();
    List<Cinemas> cinemasList = CinemaDAO.getCinemas();
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    List<Reservations> reservationsList = CinemaDAO.getReservations();
%>



<header>
    <input type="hidden" name="sessionid" value="<%=session.getId()%>">
    <input type="hidden" name="username" value="<%=userName%>">
    <input type="hidden" name="userrole" value="<%=userRole%>">
    <input type="hidden" name="userId" value="<%=userId%>">
    <nav>
        <ul>
            <li><a href="#movies">Movies</a></li>
            <li><a href="#provoles">Provoles</a></li>
            <li><a href="#you">You</a></li>
        </ul>
    </nav>
    <div class="welcome">WELCOME TO CIN3MAS, <%=user.getUsername()%></div>
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
    <%
        String message = request.getParameter("message");
        if(message!=null){
    %>
        <div class="container">
            <div class="message"><%=message.toUpperCase()%></div>
        </div>
    <%}%>

    <div class="display-movies-container">
        <div class="movie-box">
        <% int m =0; %>
        <% for (Movies movie : moviesList) { %>

            <div class="movie-info" id="movie-info-<%=++m%>">
                <span class="movie-name"><%= movie.getMovieTitle()%></span>
                <ul>
                    <li>CONTENT: <%=movie.getMovieContent()%></li>
                    <li>LENGTH: <%=movie.getMovieLength()%> minutes</li>
                    <li>TYPE: <%=movie.getMovieType() %></li>
                    <li>SUMMARY: <%=movie.getMovieSummary() %></li>
                    <li>DIRECTOR: <%=movie.getMovieDirector() %></li>
                </ul>
            </div>
            <% } %>
        </div>
        <div class="movie-navigation">
            <%m =0;%>
            <% for (Movies movie : moviesList) { %>
            <a href="#movie-info-<%=++m%>"></a>
            <% } %>
        </div>
    </div>
</section>

<section id="provoles">
    <h2>Provoles</h2>
    <form class="submit-provoli" id="submit-provoli" action="#" method="post">
        <input type="hidden" id="provoliId" name="provoliId" value="">
        <input type="hidden" id="customerId" name="customerId" value="<%=user.getId()%>">

        <div class="display-provoles-container">
            <div class="provoli-box">
                <div class="provoli-scroll-box">
                    <%
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    for (Provoles p : provolesList) {
                        LocalDate currentDate = LocalDate.now();
                        LocalTime currentTime = LocalTime.now();

                        if (p.getDate().isAfter(currentDate) || (p.getDate().isEqual(currentDate) && p.getStartTime().isAfter(currentTime))) {
                    %>
                            <div class="provoli-item">
                                <div class="provoli-movie"><%= p.getMovieName() %></div>
                                <div class="provoli-date"><%= p.getDate().format(dateFormatter) %></div>
                                <div class="provoli-time"><%= p.getStartTime() %></div>
                                <div class="provoli-cinema">at Cinema <%= p.getCinemaId()%></div>
                                <button class="select-provoli-button"
                                    data-provoli-id="<%= p.getId() %>">Select
                                </button>
                            </div>
                    <% } %>
                <% } %>
                </div>
            </div>
        </div>
    </form>
</section>


<section id="you">
    <h2>You</h2>
    <div class="reservations-for-user-container">
        <table id="reservation-table">
            <thead>
            <tr>
                <th>Movie</th>
                <th>Date</th>
                <th>Time</th>
                <th>Cinema</th>
                <th>Seats</th>
            </tr>
            </thead>
            <tbody>
            <%CinemaDAO cinemaDAO = new CinemaDAO(); %>
            <% for (Reservations r : cinemaDAO.getReservationsForUser(user.getId())) { %>
                <tr>
                    <td><%= r.getProvoles_movies_name() %></td>
                    <td><%= r.getDate().format(dateFormatter)%></td>
                    <td><%= r.getTime() %></td>
                    <td><%= r.getProvoles_cinemas_id() %></td>
                    <td><%= r.getNum_of_seats() %></td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</section>



<div class="contact-container">
    <div class="contact-name">@Triantafyllos Xydis   triantafyllosxyd@gmail.com</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/js/homeCustomer.js"></script>
</body>
</html>
