<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Confirmation</title>
</head>
<body>
<form action="j_security_check" method="post" class="form-group">
    <label for="usr">Login:</label>
    <input type="text" class="form-control" name="j_username">
    <label for="usr">Password:</label>
    <input type="password" class="form-control" name="j_password">
    <input type="submit" value="submit" class="btn-reg"/>
</form>
</body>
</html>
