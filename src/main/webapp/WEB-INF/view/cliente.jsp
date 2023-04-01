<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.entity.Cuenta" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 11/5/22
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
%>


<html>
<head>
    <title>Perfil</title>
</head>
<body>
<h1>Bienvenido <%= cliente.getNombre() %></h1>
<table border="1">
    <tr>
        <th>NOMBRE</th>
        <th>APELLIDO</th>
        <th>EMAIL</th>
        <th>DIRECCION</th>
        <th>TELEFONO</th>
    </tr>
    <tr>
        <td><%= cliente.getNombre() %></td>
        <td><%=cliente.getApellido() %> <br></td>
        <td><%=cliente.getEmail() %></td>
        <td><%=cliente.getDireccion() %></td>
        <td><%=cliente.getTelefono() %></td>
    </tr>
</table>
<a href="/cliente/editar?id=<%=cliente.getId() %>">Editar</a>
<br/>

<h2>Cuentas:</h2>
<table border="1">
    <tr>
        <td>NºCuenta</td>
        <td>Saldo</td>
        <td>Estado</td>
    </tr>
    <%
        for (Cuenta cuenta: cliente.getCuentasById()) {
    %>
    <tr>
        <td><%= cuenta.getId()%></td>
        <td><%= cuenta.getSaldo()%></td>
        <td><%= cuenta.getEstadoCuentaByEstado().getTipo()%></td>
    </tr>
    <%
    }
    %>
</table>

<h2>Chats:</h2>

</body>
</html>
