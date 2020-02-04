<%@ page import="java.text.SimpleDateFormat" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Order form</title>
  </head>
  <body>
    <%--объявления--%>
    <jsp:declaration>private int count = 2;</jsp:declaration>

    <%! private int value = 42; %>

    <%--выражение--%>
    <h1><jsp:expression>count</jsp:expression></h1>

    <h1><%=value%></h1>

    <%--скриптлеты--%>
    <% class Clazz {
      private int a = 10;

      public int returnInt() {
        return this.a;
      }
    }%>

    <%System.out.println("Hello2");%>

    <%new SimpleDateFormat("yyyy").parse("1999");%>

    <%--Использование скриптлетов--%>
    <h2><%=new Clazz().returnInt()%></h2>

    <%--Директивы--%>
    <%@include file="params.html"%>
    <jsp:include page="params.html"/>

    <form action="/order" method="post">
      <input type="hidden" name="id_customer" value="<%=request.getParameter("idCustomer") %>">
      Product name: <input name="product"><br>
      Product count:<input name="count" type="number"><br>
      <input type="submit" value="Make order">
    </form>
  </body>
</html>