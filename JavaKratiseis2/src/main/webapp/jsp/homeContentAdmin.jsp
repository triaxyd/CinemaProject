<%@ page import="com.triaxyd.users.Users" %>
<%@ page import="com.triaxyd.cinema.Movies" %>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.triaxyd.cinema.CinemaDAO" %>
<%@ page import="com.triaxyd.cinema.Cinemas" %>
<!DOCTYPE html>
<html>
<head>
    <title>CIN3MAS CONTENT ADMIN</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/homeContentAdmin.css">

</head>
<body>
<%
    if (session.getAttribute("user") == null ) {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/jsp/accessDenied.jsp");
        return;
    }
    Users user = ((Users)session.getAttribute("user"));
    if(!user.getRole().equals("ContentAdmin")){
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/accessDenied.jsp");
        rd.forward(request,response);
        session.removeAttribute("user");
        session.invalidate();
        return;
    }
    String userName = user.getUsername();
    int userId = user.getId();
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
    List<Movies> moviesList = CinemaDAO.getMovies();
    List<Cinemas> cinemasList = CinemaDAO.getCinemas();
%>
<input class="logout-button" type="button" value="LOGOUT" onclick="location.href='${pageContext.request.contextPath}/LogOut';">
<div class="user-info">
    <div class="welcome">WELCOME <%=userName%></div>
    <div class="user-id">YOUR ID: <%=userId%></div>
</div>
<div class="container">
    <div class="button-container">
        <button id="insert" class="button">INSERT NEW MOVIE</button>
        <button id="assign" class="button">ASSIGN MOVIE TO CINEMA</button>
        <button id="delete" class="button">DELETE MOVIE</button>
    </div>

    <section id="movies">
        <h2>Movies</h2>
        <div class="display-movies-container">
            <ul>
                <% for (Movies movie : moviesList) { %>
                <li><%= movie.getFilmId()+"."+movie.getFilmTitle() %></li>
                <% } %>
            </ul>
        </div>
    </section>

    <section id="cinemas">
        <h2>Cinemas</h2>
        <div class="display-cinemas-container">
            <ul>
                <% for (Cinemas cinema : cinemasList) { %>
                <li><%= cinema.getCinemaId()+"."+cinema.getCinemaName() +" - " + cinema.getCinemaSeats() + " - " + cinema.getCinemaIs3d() %></li>
                <% } %>
            </ul>
        </div>
    </section>

    <section id="provoles">

    </section>

    <div class="show-form" id="show-form-insert">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="insertMovie" class="form" action="${pageContext.request.contextPath}/InsertMovie" method="post">
                <div class="message-insert">${messageinsert}</div>
                <input type="text" name="title" id="title" placeholder="Movie Title">
                <input type="text" name="content" id="content" placeholder="Content">
                <input type="text" name="length" id="length" placeholder="Length (minutes)">
                <input type="text" name="type" id="type" placeholder="Type">
                <input type="text" name="summary" id="summary" placeholder="Summary">
                <input type="text" name="director" id="director" placeholder="Director">
                <input type="hidden" name="content_admin_id" id="content_admin_id" value="<%=String.valueOf(user.getId())%>">
                <input type="submit" id="submitInsert" value="INSERT">
            </form>
        </div>
    </div>

    <div class="show-form" id="show-form-assign">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="assignMovie" class="form" action="#" method="post">
                <div class="message-assign">${messageassign}</div>
                <input type="text" placeholder="Movie ID">
                <input type="submit" id="submitAssign" value="ASSIGN">
            </form>
        </div>
    </div>

    <div class="show-form" id="show-form-delete">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="deleteMovie" class="form" action="#" method="post">
                <div class="message-delete">${messagedelete}</div>
                <input type="text" placeholder="Movie ID">
                <input type="text" placeholder="Cinema ID">
                <input type="text" placeholder="Movie Title">
                <input type="submit" id="submitDelete" value="DELETE">
            </form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/js/homeContentAdmin.js"></script>
</body>
</html>



