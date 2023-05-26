<%@ page import="com.triaxyd.users.Users" %>
<%@ page import="com.triaxyd.users.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cinemas Admin Page</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/homeAdmin.css">
</head>
<body>

<%
    if (session.getAttribute("user") == null ) {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/jsp/accessDenied.jsp");
        return;
    }
    Users user = ((Users)session.getAttribute("user"));
    if(!user.getRole().equals("Admin")){
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
%>

<div class="sidebar">
    <ul>
        <li><a href="#" onclick="openForm('search-user')">Search User</a></li>
        <li><a href="#" onclick="openForm('add-user')">Add User</a></li>
        <li><a href="#" onclick="openForm('delete-user')">Delete User</a></li>
        <li><a href="<%= request.getContextPath()%>/LogOut">Logout</a></li>
    </ul>
</div>

<div class="content">
    <h1>Admin Page</h1>

    <form id="search-user" method="post" action="${pageContext.request.contextPath}/SearchUser" class="hidden">
        <h2>Search User</h2>
        <label for="username-search">Username:</label>
        <input type="text" id="username-search" name="username-search" required>
        <button type="submit">Search</button>
    </form>



    <form id="add-user" class="hidden" method="post" action="#">
        <h2>Add a New User</h2>
        <label for="username-add">Name:</label>
        <input type="text" id="username-add" name="username-add" required>
        <label for="email-add">Email:</label>
        <input type="email-add" id="email-add" name="email-add" required>
        <label for="user-add-role">Role:</label>
        <select id="user-add-role" name="user-add-role" required>
            <option value="Customer" selected>Customer</option>
            <option value="ContentAdmin">ContentAdmin</option>
            <option value="Admin">Admin</option>
        </select>
        <button type="submit">Add User</button>
    </form>

    <form id="delete-user" class="hidden" method="post" action="${pageContext.request.contextPath}/DeleteUser">
        <h2>Delete User</h2>
        <label for="delete-username">Username:</label>
        <input type="text" id="delete-username" name="delete-username" required>
        <button type="submit">Delete User</button>
    </form>

    <div id="result-container">
        <% Users foundUser = (Users)request.getAttribute("searched-user"); %>
        <% if(foundUser!=null) { %>
            <h2>User Information</h2>
            <p>Username: <%= foundUser.getUsername() %></p>
            <p>Email: <%= foundUser.getEmail() %></p>
            <p>Role: <%= foundUser.getRole() %></p>
            <p>Registered at: <%=foundUser.getCreationDate()%></p>
        <% } %>
        <% request.removeAttribute("searched-user"); %>
    </div>
</div>

<script src="<%= request.getContextPath() %>/js/homeAdmin.js"></script>
</body>
</html>

