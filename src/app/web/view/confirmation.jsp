<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Confirmation</title>
</head>
<body>

<form class="confirm_box" action="j_security_check" method="post">
    <h1>Confirmation</h1>
    <input type="text" name="j_username" placeholder="Username">
    <input type="password" name="j_password" placeholder="Password">
    <input type="submit" name="submit" placeholder="Login">
</form>

</body>
</html>