<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CIN3MA - TRIANTAFYLLOS XYDIS </title>
    <link rel="stylesheet" href="../css/stylingSignUp.css">
</head>

<body>
<div class="container">
    <h2>Sign Up</h2>
    <form id="signup-form" class="login-form" action="${pageContext.request.contextPath}/UsersSignUp" method="POST">
        <input type="text" placeholder="Name" name="name" id="name" maxlength="45">
        <input type="text" placeholder="Username" name="username" id="username" maxlength="32">
        <input type="email" placeholder="Email" name="email" id="email" maxlength="32">
        <input type="password" placeholder="Password" name="password" id="password" maxlength="25">
        <div id="error-message"></div>
        <button type="submit">Sign Up</button>
    </form>
    <div class="register-link">
        Already have an account? <a href="../index.jsp">Login</a>
    </div>
</div>
<script src="../js/signUp.js"></script>
</body>

</html>

