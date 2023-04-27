<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="es.taw.sampletaw.entity.*" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 11/5/22
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    Empresa empresa = (Empresa) request.getAttribute("empresa");
    List<Cliente> listaSocios = (List<Cliente>) request.getAttribute("clientesSocios");
    List<Transaccion> transacciones = (List<Transaccion>) request.getAttribute("transacciones");
    List<String> lista = (List<String>) request.getAttribute("lista");
    Tipoclienterelacionado tablaIntermedia = (Tipoclienterelacionado) request.getAttribute("tablaIntermedia");
    int tipo = tablaIntermedia.getTipoClienteByTipo().getId();

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
        <th>DIRECCION</th>
        <th>TELEFONO</th>
        <th>EMPRESA</th>
    </tr>
    <tr>
        <td><%= cliente.getNombre() %></td>
        <td><%=cliente.getApellido() %> <br></td>
        <td><%=cliente.getEmail() %></td>
        <td><%=cliente.getDireccion() %></td>
        <td><%=cliente.getTelefono() %></td>
        <td><%=cliente.getEmpresaByEmpresaId().getId() %></td>
    </tr>
</table>
<%
    if(tipo == 2)
    {
        %>
<a href="/empresa/editarEmpresa?id=<%=cliente.getId() %>">Editar Empresa</a> </br>
<a href="/empresa/editarCliente?id=<%=cliente.getId() %>">Editar Cliente</a> </br>
<a href="/empresa/anadirCliente?id=<%=cliente.getId() %>">Añadir Cliente</a> </br>
<%
    }
%>

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
        Cliente y = (Cliente) empresa.getClientesById().toArray()[0];
       Cuenta cuenta = (Cuenta) y.getCuentasById().toArray()[0];


    %>
    <tr>
        <td><%= cuenta.getId()%></td>
        <td><%=cuenta.getIban()%></td>
        <td><%= cuenta.getSaldo()%></td>
        <td><%= cuenta.getEstadoCuentaByEstado().getTipo()%></td>
        <td><%if(!cuenta.getEstadoCuentaByEstado().getTipo().equals("Activa") ){%><a href="/empresa/solicitar?id=<%=
        cuenta.getId()%>&idcliente=<%=cliente.getId()%>">Solicitar desbloqueo</a>
            <%}else{%>
            <a href="/empresa/transaccion?id=<%=cuenta.getId()%>&volver=<%=cliente.getId()%>">Realizar transacción</a>
            <a href="/empresa/cambio?id=<%=cuenta.getId()%>&volver=<%=cliente.getId()%>">Cambio de divisas</a>
            <%
            if(tipo == 2)
            {
            %>
            <a href="/empresa/bloquear/?idcuenta=<%=cuenta.getId()%>&volver=<%=cliente.getId()%>">Bloquear</a>
            <%
                }
            %>

            <%}%></td>
    </tr>

</table>


<br/>
<h2>Operaciones:</h2>
<form:form action="/empresa/filtrarop" method="post" modelAttribute="filtro">
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

<%
    if(tipo == 2)
    {
%>

<%@ include file = "conversacionesClientes.jsp" %>


Lista de clientes asociados a esta Empresa.
</br>
</br>


<form:form  action="/empresa/filtradoSocios" method="post" modelAttribute="filtroApellido">

    <form:hidden path="idEmpresa" value="${empresa.id}"></form:hidden>
    <input type="hidden" name="volver" value="${cliente.id}"/>
    Filtrar por Apellido: <form:select path="apellido">
        <form:option value="NONE" label=""></form:option>
        <form:options items="${lista}" ></form:options>

    </form:select>
    <form:button>Filtrar</form:button>
</form:form>


<table border="1">
    <tr>
        <th>NOMBRE</th>
        <th>APELLIDO</th>
        <th>EMAIL</th>
        <th>DIRECCION</th>
        <th>TELEFONO</th>
        <th>EMPRESA</th>
        <th>CUENTA</th>
    </tr>
    <%
        for(Cliente clienteSocio : listaSocios){
            String str = "";

            cuenta = tablaIntermedia.getCuentaByCuentaId();

            if(!clienteSocio.equals(cliente) && cuenta.getEstadoCuentaByEstado().getId() == 1)
            {
                str = "Bloquear";
            }
    %>

    <tr>
        <td><%= clienteSocio.getNombre() %></td>
        <td><%=clienteSocio.getApellido() %> <br></td>
        <td><%=clienteSocio.getEmail() %></td>
        <td><%=clienteSocio.getDireccion() %></td>
        <td><%=clienteSocio.getTelefono() %></td>
        <td><%=clienteSocio.getEmpresaByEmpresaId().getId() %></td>
        <td><a href="/empresa/bloquear/?idcuenta=<%=cuenta.getId()%>&volver=<%=cliente.getId()%>"><%=str%></a></td>
    </tr>
    <%
        }
    %>
</table>

<%
    }
%>


</body>
</html>
