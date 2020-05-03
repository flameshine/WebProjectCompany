<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="view/styles/styles.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>Home</title>
</head>
<body>

<form class="box" action="${pageContext.request.contextPath}/user" method="post">
    <h1>Create a new order</h1>
    <input type="text" name="orderName" placeholder="Order Name">
    <input type="text" name="priceOffer" placeholder="Price Offer">
    <input type="submit" name="submit">
</form>

</body>
</html>