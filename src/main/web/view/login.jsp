<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Login</title>
</head>
<body>

<form class="box" action="${pageContext.request.contextPath}/login" method="post">
    <h1>Login</h1>
    <input type="text" name="username" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" name="submit">
    <a href="http://localhost:8080/WebProjectITCompany/register">
        <div class="login">
        <p>Don't have an account?</p>
        </div>
    </a>
</form>

</body>
</html>