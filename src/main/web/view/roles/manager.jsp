<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Home</title>
</head>
<body>

<div class="orders">
<h1>Orders:</h1>
<table>
    <tr>
        <th>ID</th>
        <th>User</th>
        <th>Name</th>
        <th>Offer</th>
        <th>Status</th>
    </tr>

    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.getOrderID()}</td>
            <td>${order.getUsername()}</td>
            <td>${order.getOrderName()}</td>
            <td>${order.getPriceOffer()}</td>
            <td>${order.getOrderStatusMeaning()}</td>
        </tr>
    </c:forEach>

</table>
</div>

</body>
</html>