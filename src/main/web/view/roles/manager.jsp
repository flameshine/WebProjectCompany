<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Home</title>
</head>
<body>

<form class="orders" action="${pageContext.request.contextPath}/manager" method="post">
    <h1>All Orders</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Name</th>
            <th>Offer</th>
            <th>Status</th>
        </tr>
        <c:forEach var="order" items="${managerOrders}">
            <tr>
                <td>${order.getOrderID()}</td>
                <td>${order.getUsername()}</td>
                <td>${order.getOrderName()}</td>
                <td>${order.getPriceOffer()}</td>
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
                <select id="option" name="orderStatusID" aria-required="true" onchange="selected(this)">
                    <option selected disabled>None</option>
                    <option value="2">Confirm</option>
                    <option value="5">Reject</option>
                </select>
            </div>
            <div id="orderPrice" style="display: none">
                <label for="price">Order price:</label>
                <input id="price" type="number" name="orderPrice" placeholder="Price">
            </div>
            <div id="rejectionReason" style="display: none">
                <label for="reason">Reason:</label>
                <input id="reason" type="text" name="rejectionReason" placeholder="Reason">
            </div>
            <input type="submit" name="submit">
        </div>
    </div>
    <label for="popupWindow" class="popupShower">Update order status</label>
</form>

<script>
    function selected(option) {

        const label = option.value;

        if (label == 2) {
            document.getElementById("orderPrice").style.display = 'block';
            document.getElementById("rejectionReason").style.display = 'none';
        } else if (label == 5) {
            document.getElementById("orderPrice").style.display = 'none';
            document.getElementById("rejectionReason").style.display = 'block';
        }
    }
</script>

</body>
</html>