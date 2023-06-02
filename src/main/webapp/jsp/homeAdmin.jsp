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

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
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

    <form id="search-user" class="hidden" method="post" action="${pageContext.request.contextPath}/SearchUser" >
        <h2>Search User</h2>
        <label for="username-search">Username:</label>
        <input type="text" id="username-search" name="username-search" required>
        <button type="submit">Search</button>
    </form>



    <form id="add-user" class="hidden" method="post" action="${pageContext.request.contextPath}/AddUser">
        <h2>Add a New User</h2>
        <label for="name-add">Name:</label>
        <input type="text" id="name-add" name="name-add" required>
        <label for="username-add">Username:</label>
        <input type="text" id="username-add" name="username-add" required>
        <label for="email-add">Email:</label>
        <input type="text" id="email-add" name="email-add" required>
        <label for="password-add">Password: </label>
        <input type="password" id="password-add" name="password-add" required>
        <label for="role-add">Role:</label>
        <select id="role-add" name="role-add" required>
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

    <div id="result-container-search">
        <% Users foundUser = (Users)request.getAttribute("searched-user"); %>
        <% if(foundUser!=null) { %>
            <h2>User Information</h2>
            <p>Username: <%= foundUser.getUsername() %></p>
            <p>Name: <%=foundUser.getName()%></p>
            <p>Email: <%= foundUser.getEmail() %></p>
            <p>Role: <%= foundUser.getRole() %></p>
            <p>Registered at: <%=foundUser.getCreationDate()%></p>
            <p>ID: <%=foundUser.getId()%></p>
            <% request.removeAttribute("searched-user"); %>
        <% } else { %>
            <% String userNotFoundMessage = (String) request.getAttribute("user-not-found-message"); %>
            <% if (userNotFoundMessage != null) { %>
                <p><%= userNotFoundMessage %></p>
            <% } %>
            <% request.removeAttribute("user-not-found-message"); %>
        <% } %>
    </div>

    <% String resultdelete = request.getParameter("resultdelete"); %>
    <% String resultadd = request.getParameter("resultadd"); %>

    <% if (resultdelete != null) { %>
    <div id="result-container-delete">
        <h2>Deleted User</h2>
        ${param.resultdelete}
    </div>
    <% } %>

    <% if (resultadd != null) { %>
    <div id="result-container-add">
        <h2>Added User</h2>
        ${param.resultadd}
    </div>
    <% } %>



</div>

<script src="<%= request.getContextPath() %>/js/homeAdmin.js"></script>
</body>
</html>

