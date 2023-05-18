<%@ page import="com.triaxyd.users.Users" %>
<!DOCTYPE html>
<html>
<head>
    <title>CIN3MAS CONTENT ADMIN</title>
    <link rel="stylesheet" type="text/css" href="../css/homeContentAdmin.css">
</head>
<body>
<%
    if (session.getAttribute("user") == null ) {
        response.sendRedirect("/index.jsp");
    }
    Users user = ((Users)session.getAttribute("user"));
    //((ContentAdmins)user).insertFilm();
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) userName = cookie.getValue();
            if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>
<input class="logout-button" type="button" value="LOGOUT" onclick="location.href='${pageContext.request.contextPath}/LogOut';">
<div class="container">
    <div class="button-container">
        <button id="insert" class="button">INSERT NEW MOVIE</button>
        <button id="assign" class="button">ASSIGN MOVIE TO CINEMA</button>
        <button id="delete" class="button">DELETE MOVIE</button>
    </div>

    <div class="show-form" id="show-form-insert">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="insertMovie" class="form" action="${pageContext.request.contextPath}/InsertMovie" method="post">
                <input type="text" name="title" id="title" placeholder="Movie Title">
                <input type="text" name="content" id="content" placeholder="Content">
                <input type="time" name="length" id="length" placeholder="Length">
                <input type="text" name="type" id="type" placeholder="Type">
                <input type="text" name="summary" id="summary" placeholder="Summary">
                <input type="text" name="director" id="director" placeholder="Director">
                <input type="hidden" name="content_admin_id" id="content_admin_id" value="<%=user.getId()%>">
                <input type="submit" value="INSERT">
            </form>
        </div>
    </div>

    <div class="show-form" id="show-form-assign">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="assignMovie" class="form" action="#" method="post">
                <input type="text" placeholder="Movie ID">
                <input type="submit" value="ASSIGN">
            </form>
        </div>
    </div>

    <div class="show-form" id="show-form-delete">
        <div class="form-info"></div>
        <div class="form-container">
            <form id="deleteMovie" class="form" action="#" method="post">
                <input type="text" placeholder="Movie ID">
                <input type="text" placeholder="Cinema ID">
                <input type="text" placeholder="Movie Title">
                <input type="submit" value="DELETE">
            </form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="../js/homeContentAdmin.js"></script>
</body>
</html>



