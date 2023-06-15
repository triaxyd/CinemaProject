<%@ page import="com.triaxyd.users.Users" %>
<%@ page import="com.triaxyd.cinema.Movies" %>
<%@ page import="com.triaxyd.cinema.Provoles" %>
<%@ page import="com.triaxyd.cinema.CinemaDAO" %>
<%@ page import="com.triaxyd.cinema.Cinemas" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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


    response.addHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Cache-Control", "pre-check=0, post-check=0");
    response.setDateHeader("Expires", 0);

    CinemaDAO cinemaDAO = new CinemaDAO();

    List<Movies> moviesList = CinemaDAO.getMovies();
    List<Provoles> provolesList = CinemaDAO.getProvoles();
    List<Cinemas> cinemasList = CinemaDAO.getCinemas();

    String movieId = request.getParameter("movieId");
    String movieName = cinemaDAO.getMovie(Integer.parseInt(movieId)).getMovieTitle();
    String cinemaId = request.getParameter("cinemaId");

    Provoles provoli = cinemaDAO.getProvoli(movieId,cinemaId);

%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RESERVATION</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/makeReservation.css">
</head>
<header>
    <div id="timer" class="timer"></div>
</header>
<body>

<div class="provoli-info">
    <div class="provoli-id">PROVOLI ID - <%=provoli.getId()%></div>
    <div class="selected-movie">MOVIE - <%=movieName%></div>
    <div class="selected-cinema">CINEMA - <%=cinemaId%></div>
</div>
<hr>
<div class="provoli-remaining-seats">
    <div class="remaining-seats">AVAILABLE SEATS: <%=provoli.getRemainingSeats()%></div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/js/makeReservation.js"></script>
</body>
</html>
