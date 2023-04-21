<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="java.util.ConcurrentModificationException" %>
<%@ page import="es.taw.sampletaw.entity.Conversacion" %>
<%@ page import="es.taw.sampletaw.entity.Mensaje" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: juanj
  Date: 27/03/2023
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Conversacion chat = (Conversacion) request.getAttribute("chat");
%>

<html>
<head>
    <title>Chateando con soporte</title>
</head>
<body>

    <h1>Esto es el chat de asistencia desde cliente</h1>
    <h3>Asunto: <%=chat.getAsunto()%></h3>
    Conversando con Asistente: <br/>

    <%
        for(Mensaje m : chat.getMensajesById()){
    %>
    <table>
        <tr>
            <td><%=m.getEmpleadoByEmisorEmpleado() != null ? "Asistente: ": m.getClienteByEmisorCliente().getNombre() + ":" + m.getContenido()%></td>
        </tr>
    </table>
    <%
        }
    %>

    <form method="post" action="/chat/clienteEnviaMensaje">
        <input type="hidden" name="idCliente" value="<%=chat.getClienteByCliente().getId()%>">
        <input type="hidden" name="idChat" value="<%=chat.getId()%>">
        <input type="text" name="mensaje"> <button>Enviar mensaje</button> <br/>
    </form>
        <button onclick="location.href='/chat/cerrar?idConversacion=<%=chat.getId()%>'">Cerrar conversacion</button>

</body>
</html>
