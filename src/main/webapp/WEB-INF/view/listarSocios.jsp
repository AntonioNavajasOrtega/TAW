<%@ page import="es.taw.sampletaw.entity.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 15/2/23
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Customer> lista = (List<Customer>) request.getAttribute("clientes");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Listado de clientes</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>EMAIL</th>
        <th>NAME</th>
        <th>CREDIT LIMIT</th>
        <th>DISCOUNT CODE</th>
        <th>CITY</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (Customer cliente: lista) {
    %>
    <tr>
        <td><%= cliente.getCustomerId() %></td>
        <td><%= cliente.getEmail() %></td>
        <td><%= cliente.getName() %></td>
        <td><%= cliente.getCreditLimit() %></td>
        <td><%= cliente.getDiscountCodeByDiscountCode().getRate() %></td>
        <td><%= cliente.getCity() %></td>
        <td><a href="/customer/editar?id=<%= cliente.getCustomerId() %>"> Editar</a></td>
        <td><a href="/customer/borrar?id=<%= cliente.getCustomerId() %>"> Borrar</a></td>
    </tr>


    <%
        }
    %>
</table border="1">
</body>
</html>
