<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Orders</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<table>

    <tr>
        <td>Order Sum</td>
        <td>${order.sum}</td>
    </tr>
    <tr>
        <td>Discount</td>
        <td>${order.discount}</td>
    </tr>
    <tr>
        <td>Goods</td>
        <td>${order.goods}</td>
    </tr>
</table>
<form action="/request/new?orderId=${order.id}" method="post">
    <input type="submit" value="Взять кредит"/>
</form>
</body>
</html>