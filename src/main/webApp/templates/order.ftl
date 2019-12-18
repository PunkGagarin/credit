<html>
<head>
    <title>Orders</title>
</head>
<body>
<li>${order.sum}</li>
<li>${order.discount}</li>
</body>
<form action="/request/new?orderId=${order.id}" method="post"}">
    <input type="submit" value="Взять кредит"/>
</form>
</html>