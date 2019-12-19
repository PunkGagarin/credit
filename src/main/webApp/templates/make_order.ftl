<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
<head>
    <title>Make Order</title>
</head>
<body>
<@sf.form action="/make_order" method="post" modelAttribute="order">

    <div>
        <@sf.label path="id">ID</@sf.label>
        <@sf.input path="id"/>
        <@sf.errors path="id"/>
    </div>

    <div>
        <@sf.label path="sum">Summa</@sf.label>
        <@sf.input path="sum"/>
        <@sf.errors path="sum"/>
    </div>

    <div>
        <@sf.label path="discount">Discount</@sf.label>
        <@sf.input path="discount"/>
        <@sf.errors path="discount"/>
    </div>

    <div>
        <@sf.label path="goods">Goods</@sf.label>
        <@sf.input path="goods"/>
        <@sf.errors path="goods"/>
    </div>

    <input type="submit">

</@sf.form>
</body>
</html>
