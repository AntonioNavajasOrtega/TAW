<%@ page import="es.taw.sampletaw.entity.Conversacion" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: juanj
  Date: 27/03/2023
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Conversacion> conversacionList = (List<Conversacion>) request.getAttribute("conversaciones");
%>

<html>
<head>
    <title>Asistente|Conversaciones</title>
</head>
<body>

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
        for(Conversacion conversacion : conversacionList){
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
        <td><a href="/chat/asistente?idConversacion=<%=conversacion.getId()%>">Ir a la conversación</a></td>
    </tr>
    <%
        }
    %>
</table>



</body>
</html>
