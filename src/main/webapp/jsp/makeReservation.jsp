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
    <input type="button" id="cancel-reservation" value="Cancel" name="cancel-reservation">
</header>
<body>

<div class="provoli-info">
    <div class="provoli-id">PROVOLI ID - <%=provoli.getId()%></div>
    <div class="selected-movie">MOVIE - <%=movieName%></div>
    <div class="selected-cinema">CINEMA - <%=cinemaId%></div>
</div>
<div class="provoli-kratisi">
    <div class="remaining-seats">AVAILABLE SEATS: <div id="seats"> <%=provoli.getNum_of_seats()%> </div> </div>
    <form id="make-reservation" name="make-reservation" method="post" action="${pageContext.request.contextPath}/MakeReservation">
        <input type="hidden" id="movieId" name="movieId" value=<%=movieId%>>
        <input type="hidden" id="cinemaId" name="cinemaId" value=<%=cinemaId%>>
        <input type="hidden" id="customerId" name="customerId" value=<%=user.getId()%>>
        <input type="hidden" id="numOfSeats" name="numOfSeats" value="1">
        <div class="select-seats">
            <select id="select-seats-options" name="select-seats-options">
                <%for(int i=0; i<10; i++) { %>
                <option value=<%=i+1%>><%=i+1%></option>
                <% } %>
            </select>
        </div>
        <div id="no-seats-message" style="padding: 0.5rem; color: #a55"  hidden></div>
        <input type="submit" id="submit-reservation" name="submit-reservation" value="Reserve Seats">
    </form>
</div>



<script>
    var cancelButton = document.getElementById('cancel-reservation');
    cancelButton.onclick = function() {
        var contextPath = '<%= request.getContextPath() %>';
        var url = contextPath + "/jsp/homeCustomer.jsp";
        window.location.replace(url);
    };
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/js/makeReservation.js"></script>
</body>
</html>
