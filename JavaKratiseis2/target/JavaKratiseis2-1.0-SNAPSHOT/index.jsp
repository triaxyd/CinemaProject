<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CIN3MA - TRIANTAFYLLOS XYDIS </title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/stylingIndex.css">
</head>

<body>
<div class="container">
    <div class="user-already-exists">${message}</div>
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
<script src="<%= request.getContextPath() %>/js/indexJs.js"></script>
</body>

</html>

























