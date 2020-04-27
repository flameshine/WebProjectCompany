<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="view/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Login</title>
</head>
<body>
<form class="box" action="${pageContext.request.contextPath}/login" method="post">
    <h1>Login</h1>
    <input type="text" name="username" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" name="submit" placeholder="Login">
    <a href="http://localhost:8080/WebProjectITCompany/register">Don't have an account?</a>
</form>
</body>
</html>