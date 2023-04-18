<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.entity.Cuenta" %>
<%@ page import="java.util.ConcurrentModificationException" %>
<%@ page import="es.taw.sampletaw.entity.Conversacion" %>
<%@ page import="java.util.Collection" %>
<%@ page import="es.taw.sampletaw.entity.Empresa" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 11/5/22
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<Conversacion> conversaciones = (List<Conversacion>) request.getAttribute("conversaciones");
    List<Cliente> listaSocios = (List<Cliente>) request.getAttribute("clientesSocios");
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
<a href="/empresa/editarEmpresa?id=<%=cliente.getId() %>">Editar Empresa</a> </br>
<a href="/empresa/editarCliente?id=<%=cliente.getId() %>">Editar Cliente</a> </br>
<a href="/empresa/anadirCliente?id=<%=cliente.getId() %>">Añadir Cliente</a> </br>
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
<table border="1">
    <tr>
        <th>ID</th>
        <th>Cliente</th>
        <th>Estado</th>
        <th>Numero de mensajes</th>
        <th>Fecha apertura</th>
        <th>Fecha cierre</th>
        <th>Ver conversacion</th>
    </tr>
    <%
        for(Conversacion conversacion : conversaciones){
    %>
    <tr>
        <td><%=conversacion.getId()%></td>
        <td><%=conversacion.getClienteByCliente().getNombre()%></td>
        <%
            String estado = "Abierta";
            if(conversacion.getAbierta()!=1){estado="Cerrada";}
        %>
        <td><%=estado%></td>
        <td><%=conversacion.getMensajesById().size()%></td>
        <td><%="no sé"%></td>
        <td><%="no sé"%></td>
        <td><a href="">Ir a la conversación</a></td>
    </tr>
    <%
        }
    %>
</table>
<br/>
<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">Crear nuevo chat</button>


<form action="/chat/nuevo" method="get">
    <input type="hidden" name="idCliente" value="<%=cliente.getId()%>">
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Introduce el asunto de la conversación</h4>
                </div>
                <div class="modal-body">
                    <input type="text" name="asunto">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Crear</button>
                </div>
            </div>
        </div>
    </div>
</form>

<table border="1">
    <tr>
        <th>NOMBRE</th>
        <th>APELLIDO</th>
        <th>EMAIL</th>
        <th>DIRECCION</th>
        <th>TELEFONO</th>
        <th>EMPRESA</th>
    </tr>
    <%
        for(Cliente clienteSocio : listaSocios){
    %>

    <tr>
        <td><%= clienteSocio.getNombre() %></td>
        <td><%=clienteSocio.getApellido() %> <br></td>
        <td><%=clienteSocio.getEmail() %></td>
        <td><%=clienteSocio.getDireccion() %></td>
        <td><%=clienteSocio.getTelefono() %></td>
        <td><%=clienteSocio.getEmpresaByEmpresaId().getId() %></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
