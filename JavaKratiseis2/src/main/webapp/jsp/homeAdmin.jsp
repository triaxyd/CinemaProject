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
        <li><a href="#" onclick="openForm('change-user-info')">Change User Info</a></li>
        <li><a href="#" onclick="openForm('add-contentadmin')">Add ContentAdmin</a></li>
        <li><a href="#" onclick="openForm('add-user')">Add User</a></li>
        <li><a href="#" onclick="openForm('delete-user')">Delete User</a></li>
    </ul>
</div>

<div class="content">
    <h1>Admin Page</h1>

    <form id="change-user-info" class="hidden">
        <h2>Change User Info</h2>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <label for="new-email">New Email:</label>
        <input type="email" id="new-email" name="new-email" required>
        <button type="submit">Change</button>
    </form>

    <form id="add-contentadmin" class="hidden">
        <h2>Add a New ContentAdmin</h2>
        <label for="contentadmin-name">Name:</label>
        <input type="text" id="contentadmin-name" name="contentadmin-name" required>
        <label for="contentadmin-email">Email:</label>
        <input type="email" id="contentadmin-email" name="contentadmin-email" required>
        <button type="submit">Add ContentAdmin</button>
    </form>

    <form id="add-user" class="hidden">
        <h2>Add a New User</h2>
        <label for="user-name">Name:</label>
        <input type="text" id="user-name" name="user-name" required>
        <label for="user-email">Email:</label>
        <input type="email" id="user-email" name="user-email" required>
        <label for="user-role">Role:</label>
        <select id="user-role" name="user-role" required>
            <option value="Customer">Customer</option>
            <option value="ContentAdmin">ContentAdmin</option>
            <option value="Admin">Admin</option>
        </select>
        <button type="submit">Add User</button>
    </form>

    <form id="delete-user" class="hidden">
        <h2>Delete User</h2>
        <label for="delete-username">Username:</label>
        <input type="text" id="delete-username" name="delete-username" required>
        <button type="submit">Delete User</button>
    </form>
</div>

<script src="<%= request.getContextPath() %>/js/homeAdmin.js"></script>
</body>
</html>

