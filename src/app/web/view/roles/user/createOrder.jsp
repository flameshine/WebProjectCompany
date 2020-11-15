<%@ page contentType="text/html;charset=UTF-8"%>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Create new order</title>
</head>
<body>

<table class="menu">
    <tr>
        <th><a href="http://localhost:8080/WebProjectITCompany/home"><h3>Home</h3></a></th>
        <th><a href="http://localhost:8080/WebProjectITCompany/notifications"><h3>Notifications</h3></a></th>
        <th><a href="http://localhost:8080/WebProjectITCompany/create"><h3>New order</h3></a></th>
        <th><a href="http://localhost:8080/WebProjectITCompany/orders"><h3>My orders</h3></a></th>
        <th><a href="http://localhost:8080/WebProjectITCompany/about"><h3>About</h3></a></th>
        <th><a href="http://localhost:8080/WebProjectITCompany/logout"><h3>Log out</h3></a></th>
    </tr>
</table>

<form class="box" id="createOrder" action="${pageContext.request.contextPath}/create" method="post">
    <h1>Create a new order</h1>
    <input type="text" name="orderName" placeholder="Order Name">
    <input type="submit" name="submit">
</form>

</body>
</html>