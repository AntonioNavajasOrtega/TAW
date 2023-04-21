<%@ page import="es.taw.sampletaw.entity.Conversacion" %>
<%@ page import="es.taw.sampletaw.entity.Mensaje" %><%--
  Created by IntelliJ IDEA.
  User: juanj
  Date: 27/03/2023
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Conversacion conversacion = (Conversacion) request.getAttribute("conversacion");
%>
<html>
<head>
    <title>Chateando con cliente</title>

</head>
<body>

<h1>Esto es el chat de asistencia desde asistente</h1>
<h3>Asunto: <%=conversacion.getAsunto()%></h3>
Conversando con el cliente <%=conversacion.getClienteByCliente().getNombre()%> <%=conversacion.getClienteByCliente().getApellido()%>  <br/>
<%
    for(Mensaje m : conversacion.getMensajesById()){
%>
<table>
    <tr>
        <td><%=m.getEmpleadoByEmisorEmpleado() != null ? "Asistente: " + m.getContenido() : m.getClienteByEmisorCliente().getNombre() + ":" + m.getContenido()%></td>
    </tr>
</table>
<%
    }
%>

<form method="post" action="/chat/asistenteEnviaMensaje">
    <input type="hidden" name="idCliente" value="<%=conversacion.getClienteByCliente().getId()%>">
    <input type="hidden" name="idChat" value="<%=conversacion.getId()%>">
    <input type="text" name="mensaje"> <button>Enviar mensaje</button> <br/>
</form>


</body>
</html>
