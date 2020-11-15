<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Notifications</title>
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

<div class="notifications">

    <h1>Notifications</h1>

    <table>
        <tr>
            <th>Order</th>
            <th>Message</th>
            <th>Status</th>
        </tr>

        <c:forEach var="notification" items="${userNotifications}">
            <tr>
                <td>${notification.getOrderName()}</td>
                <td>${notification.getNotificationText()}</td>
                <td>${notification.getOrderStatusMeaning()}</td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>