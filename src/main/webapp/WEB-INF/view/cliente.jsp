<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.entity.Cuenta" %>
<%@ page import="java.util.ConcurrentModificationException" %>
<%@ page import="es.taw.sampletaw.entity.Conversacion" %>
<%@ page import="java.util.Collection" %>
<%@ page import="es.taw.sampletaw.entity.Transaccion" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 11/5/22
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<Transaccion> transacciones = (List<Transaccion>) request.getAttribute("transacciones");
%>


<html>
<head>
    <title>Perfil</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Bienvenido <%= cliente.getNombre() %></h1>
<table border="1">
    <tr>
        <th>NOMBRE</th>
        <th>APELLIDO</th>
        <th>EMAIL</th>
        <th>DNI</th>
        <th>TELEFONO</th>
    </tr>
    <tr>
        <td><%= cliente.getNombre() %></td>
        <td><%=cliente.getApellido() %> <br></td>
        <td><%=cliente.getEmail() %></td>
        <td><%=cliente.getNif() %></td>
        <td><%=cliente.getTelefono() %></td>
    </tr>
</table>
<a href="/cliente/editar?id=<%=cliente.getId() %>">Editar</a>
<br/>

<h2>Cuentas:</h2>
<table border="1">
    <tr>
        <td>NºCuenta</td>
        <td>Iban</td>
        <td>Saldo</td>
        <td>Estado</td>
        <td></td>
    </tr>
    <%
        for (Cuenta cuenta: cliente.getCuentasById()) {
    %>
    <tr>
        <td><%= cuenta.getId()%></td>
        <td><%=cuenta.getIban()%></td>
        <td><%= cuenta.getSaldo()%></td>
        <td><%= cuenta.getEstadoCuentaByEstado().getTipo()%></td>
        <td><%if(!cuenta.getEstadoCuentaByEstado().getTipo().equals("Activa")){%><a href="/cliente/solicitar?id=<%=
        cuenta.getId()%>">Solicitar desbloqueo</a><%}else{%>
            <a href="/cliente/transaccion?id=<%=cuenta.getId()%>">Realizar transacción</a>
            <a href="/cliente/cambio?id=<%=cuenta.getId()%>">Cambio de divisas</a><%}%></td>
    </tr>
    <%
    }
    %>
</table>

<br/>
<h2>Operaciones:</h2>
<form:form action="/cliente/filtrarop" method="post" modelAttribute="filtro">
    <form:hidden path="clienteid" value="${cliente.id}"></form:hidden>
    Filtrar por: <form:select path="cuentadestino">
        <form:option value="" label=""></form:option>
        <form:option value="YES" label="Origen operación"></form:option>
    </form:select>
    Ordenar por: <form:select path="date">
    <form:option value="" label=""></form:option>
    <form:option value="YES" label="Fecha"></form:option>
</form:select>
    <form:button>Filtrar</form:button>
</form:form>
<br/>
<table border="1">
    <tr>
        <td>Tipo</td>
        <td>Fecha</td>
        <td>Cantidad</td>
        <td>Destino</td>
    </tr>
    <%
        for(Transaccion transaccion : transacciones){
    %>
    <tr>
        <td><%=transaccion.getTipoTransaccionByTipo().getTipo()%></td>
        <td><%= transaccion.getFecha()%></td>
        <td><%= transaccion.getCantidad()%></td>
        <td><%= transaccion.getCuentaByCuentaDestinoId().getIban()%></td>
    </tr>
    <%

        }
    %>
</table>
<br/>

<%@ include file = "conversacionesClientes.jsp" %>

</body>
</html>
