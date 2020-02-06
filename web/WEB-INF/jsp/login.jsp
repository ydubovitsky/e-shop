<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 04.02.2020
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign-in</title>
</head>
<body>
<form id="data" action="/e_shop_war_exploded/sign-in" method="post" >>
    <p><input placeholder="Ваше имя" name="user" form="data"></p>
    <p><input type="submit" value="Отправить форму" form="data"></p>
</form>
</body>
</html>
