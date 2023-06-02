<%@ page import="com.triaxyd.users.Users" %>
<%@ page import="com.triaxyd.cinema.Movies" %>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.triaxyd.cinema.CinemaDAO" %>
<%@ page import="com.triaxyd.cinema.Cinemas" %>
<%@ page import="com.triaxyd.cinema.Provoles" %>
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
    List<Provoles> provolesList = CinemaDAO.getProvoles();

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<input class="logout-button" type="button" value="LOGOUT" onclick="location.href='${pageContext.request.contextPath}/LogOut';">
<div class="user-info">
    <div class="welcome-id">ID: <%=userId%></div>
    <div class="welcome-username">USERNAME: <%=userName%></div>
</div>
<div class="action-message">${param.actionmade}</div>
<div class="container">
    <div class="button-container">
        <button id="insert" class="button">INSERT NEW MOVIE</button>
        <button id="assign" class="button">ASSIGN MOVIE TO CINEMA</button>
        <button id="delete-provoli" class="button">DELETE PROVOLI</button>
    </div>
    <hr>

    <section id="movies">
        <h2>MOVIES</h2>
        <div class="display-movies-container">
            <ul>
                <% for (Movies movie : moviesList) { %>
                <li class="movie-box">
                    <div class="movie-info">
                        <span class="movie-title"><%= movie.getMovieTitle() %></span><br>
                        <span class="movie-id">ID: </span><span><%= movie.getMovieID()%></span><br>
                        <span class="movie-content-admin">ADDED BY: </span><span><%= movie.getMovieContentAdminID() %></span>
                    </div>
                </li>
                <% } %>
            </ul>
        </div>
    </section>
    <hr>

    <section id="cinemas">
        <h2>CINEMAS</h2>
        <div class="display-cinemas-container">
            <ul>
                <% for (Cinemas cinema : cinemasList) { %>
                <li class="cinema-box">
                    <div class="cinema-info">
                        <span class="cinema-name"><%= cinema.getCinemaName() %></span><br>
                        <span class="cinema-id">ID:</span><span><%= cinema.getCinemaId()%></span><br>
                        <span class="cinema-seats">SEATS:</span><span><%= cinema.getCinemaSeats()%></span><br>
                        <span class="cinema-3d">3D:</span><span><%= cinema.getCinemaIs3d() %></span>
                    </div>
                </li>
                <% } %>
            </ul>
        </div>
    </section>
    <hr>

    <section id="provoles">
        <h2>PROVOLES</h2>
        <div class="display-provoles-container">
            <ul>
                <% for (Provoles provoli : provolesList ) { %>
                <li class="provoli-box">
                    <div class="provoli-info">
                        <span class="provoli-name"><%= provoli.getMoviesName() + " - " + provoli.getCinemaId()%></span><br>
                        <span class="provoli-id">ID: </span><span><%= provoli.getId()%></span><br>
                        <span class="provoli-movie-id">MOVIE ID: </span><span><%= provoli.getMoviesId()%></span><br>
                        <span class="provoli-content-admin">ADDED BY:  </span><span><%= provoli.getContentAdminId() %></span>
                    </div>
                </li>
                <% } %>
            </ul>
        </div>
    </section>
    <hr>

    <div class="show-form" id="show-form-insert">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="insertMovie" name="insertMovie" class="form" action="${pageContext.request.contextPath}/InsertMovie" method="post">
                <input type="text" name="title" id="title" placeholder="Movie Title" maxlength="45" >
                <input type="text" name="content" id="content" placeholder="Content" maxlength="45">
                <input type="text" name="length" id="length" placeholder="Length (minutes)" maxlength="3">
                <input type="text" name="type" id="type" placeholder="Type" maxlength="45">
                <input type="text" name="summary" id="summary" placeholder="Summary" maxlength="255">
                <input type="text" name="director" id="director" placeholder="Director" maxlength="45">
                <input type="hidden" name="content_admin_id" id="content_admin_id" value="<%=String.valueOf(user.getId())%>">
                <input type="submit" id="submitInsert" value="INSERT">
            </form>
        </div>
    </div>

    <div class="show-form" id="show-form-assign">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="assignMovie" name="assignMovie" class="form" action="${pageContext.request.contextPath}/AssignMovieToCinema" method="post">
                <input type="text" name="movie-id" id="movie-id" placeholder="Movie ID" maxlength="10">
                <input type="text" name="cinema-id" id="cinema-id" placeholder="Cinema ID" maxlength="2">
                <input type="submit" id="submitAssign" value="ASSIGN">
            </form>
        </div>
    </div>

    <div class="show-form" id="show-form-delete-provoli">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="deleteProvoli" name="deleteProvoli" class="form" action="${pageContext.request.contextPath}/DeleteProvoli" method="post">
                <input type="text" name="provoli-id-delete" id="provoli-id-delete" placeholder="Provoli ID" maxlength="10" >
                <input type="hidden" name="content_admin_id_delete" id="content_admin_id_delete" value="<%=String.valueOf(user.getId())%>">
                <input type="submit" id="submitDeleteProvoli" value="DELETE">
            </form>
        </div>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/js/homeContentAdmin.js"></script>

</body>
</html>



