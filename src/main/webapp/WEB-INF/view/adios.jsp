<%@ page import="es.taw.sampletaw.entity.ClienteEntity" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 11/5/22
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> lista = (List<ClienteEntity>) request.getAttribute("clientes");
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
        <th>NAME</th>
        <th>EMAIL</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (ClienteEntity cliente : lista){
    %>
    <tr>
        <td><%= cliente.getId() %></td>
        <td><%=cliente.getNombre() %> <br></td>
        <td><%=cliente.getEmail() %></td>
        <td><a href="/customer/editar?id=<%=cliente.getId() %>">Editar</a></td>
        <td><a href="/customer/borrar?id=<%=cliente.getId() %>">Borrar</a></td>
    </tr>


    <%
        }
    %>
</table>

</body>
</html>
