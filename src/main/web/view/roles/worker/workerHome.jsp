<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Home</title>
</head>
<body>

<form class="orders" action="${pageContext.request.contextPath}/worker" method="post">
    <h1>Confirmed Orders</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Name</th>
            <th>Price</th>
            <th>Status</th>
        </tr>
        <c:forEach var="order" items="${workerOrders}">
            <tr>
                <td>${order.getOrderID()}</td>
                <td>${order.getUsername()}</td>
                <td>${order.getOrderName()}</td>
                <td>${order.getOrderPrice()}</td>
                <td>${order.getOrderStatusMeaning()}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="checkbox" id="popupWindow">
    <div class="window">
        <div class="windowContent">
            <label for="popupWindow" class="popupCloser">&#215;</label>
            <div class="orderID">
                <label for="id">Order ID:</label>
                <input id="id" type="number" name="orderID" placeholder="ID">
            </div>
            <div class="orderOperation">
                <label for="option">Operation:</label>
                <select id="option" name="orderStatusID">
                    <option selected disabled>None</option>
                    <option value="3">Being developed</option>
                    <option value="4">Done</option>
                </select>
            </div>
            <input type="submit" name="submit">
        </div>
    </div>
    <label for="popupWindow" class="popupShower">Update order status</label>
</form>

</body>
</html>