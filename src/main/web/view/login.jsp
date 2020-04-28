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
    <div class="create">
        <a href="http://localhost:8080/WebProjectITCompany/register">
            <div class="text">
                <p>Don't have an account?</p>
            </div>
        </a>
    </div>
</form>
</body>
</html>