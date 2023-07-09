<%@ page import="com.triaxyd.users.Users" %>
<%@ page import="com.triaxyd.cinema.Movies" %>
<%@ page import="com.triaxyd.cinema.Provoles" %>
<%@ page import="com.triaxyd.cinema.CinemaDAO" %>
<%@ page import="com.triaxyd.cinema.Cinemas" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("user") == null) {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/jsp/accessDenied.jsp");
        return;
    }
    Users user = ((Users)session.getAttribute("user"));
    if(!user.getRole().equals("Customer")){
        session.removeAttribute("user");
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/accessDenied.jsp");
        rd.forward(request,response);
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

    String provoliId = request.getParameter("provoliId");
    String customerId = request.getParameter("customerId");

    if(!customerId.equals(String.valueOf(user.getId()))){
        return;
    }

    Provoles provoli = cinemaDAO.getProvoli(Integer.parseInt(provoliId));
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
    <%DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); %>
    <div class="provoli-id">PROVOLI ID - <%=provoli.getId()%></div>
    <div class="selected-movie">MOVIE - <%=provoli.getMovieName()%></div>
    <div class="selected-date">DATE - <%=provoli.getDate().format(dateFormatter)%></div>
    <div class="selected-time">TIME - <%=provoli.getStartTime()%></div>
    <div class="selected-cinema">CINEMA - <%=provoli.getCinemaId()%></div>
</div>
<div class="provoli-kratisi">
    <div class="remaining-seats">AVAILABLE SEATS: <div id="seats"> <%=provoli.getNum_of_seats()%> </div> </div>
    <form id="make-reservation" name="make-reservation" method="post" action="${pageContext.request.contextPath}/MakeReservation">
        <input type="hidden" id="movieId" name="movieId" value=<%=provoli.getMovieId()%>>
        <input type="hidden" id="cinemaId" name="cinemaId" value=<%=provoli.getCinemaId()%>>
        <input type="hidden" id="date" name="date" value=<%=provoli.getDate()%>>
        <input type="hidden" id="time" name="time" value=<%=provoli.getStartTime()%>>
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
