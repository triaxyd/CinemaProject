<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession(false);
    session.invalidate();

%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CIN3MA - TRIANTAFYLLOS XYDIS </title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/stylingIndex.css">
</head>

<body>
<div class="background-wrapper">
    <div class="background-image"></div>
</div>
<div class="container">
    <%String message = request.getParameter("message"); %>
    <%if(message!=null){ %>
        <div class="message"><%=message%></div>
    <%}%>
    <h2>Login</h2>
    <form class="login-form" action="${pageContext.request.contextPath}/UsersLogin" method="POST" id="login-form">
        <input type="text" placeholder="Username" name="username" id="username" maxlength="25">
        <input type="password" placeholder="Password" name="password" id="password" maxlength="25">
        <div id="error-message"></div>
        <button type="submit">Login</button>
    </form>
    <div class="register-link">
        Don't have an account? <a href="jsp/signUp.jsp">Register</a>
    </div>
</div>
<script>window.history.forward();</script>
<script src="<%= request.getContextPath() %>/js/indexJs.js"></script>
</body>

</html>

























