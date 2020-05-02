<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Create an account</title>
</head>
<body>

<form class="box" action="${pageContext.request.contextPath}/register" method="post">
    <h1>Create an account</h1>
    <input type="text" name="username" placeholder="Username">
    <input type="password" name="firstPasswordAttempt" placeholder="Password">
    <input type="password" name="secondPasswordAttempt" placeholder="Confirm Password">
    <input type="submit" name="submit">
    <a href="http://localhost:8080/WebProjectITCompany/login">
        <p>Already have an account?</p>
    </a>
</form>

</body>
</html>