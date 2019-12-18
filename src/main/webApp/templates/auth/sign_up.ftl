<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<@sf.form action="/sign_up" method="post" modelAttribute="user">

    <div>
        <@sf.label path="fio">FIO</@sf.label>
        <@sf.input path="fio"/>
        <@sf.errors path="fio"/>
    </div>

    <div>
        <@sf.label path="login">Login</@sf.label>
        <@sf.input path="login"/>
        <@sf.errors path="login"/>
    </div>

    <div>
        <@sf.label path="password">Password</@sf.label>
        <@sf.input path="password" type="password"/>
        <@sf.errors path="password"/>
    </div>

    <input type="submit">

</@sf.form>
</body>
</html>
