<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.Conversacion" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="java.util.Collection" %>
<%@ page import="es.taw.sampletaw.entity.Mensaje" %><%--
  Created by IntelliJ IDEA.
  User: juanj
  Date: 27/03/2023
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Mensaje> mensajeList = (List<Mensaje>) request.getAttribute("mensajes");
%>

<table border="1">
    <th>Emisor</th>
    <th>Receptor</th>
    <th>Mensaje</th>
    <th>Fecha</th>
    <%
        for(Mensaje mensaje : mensajeList){
    %>
    <tr>
        <td><%=mensaje.getClienteByEmisorCliente() != null ? mensaje.getClienteByEmisorCliente().getNombre() : "Asistente"%></td>
        <td><%=mensaje.getClienteByReceptorCliente() != null ? mensaje.getClienteByReceptorCliente().getNombre() : "Asistente"%></td>
        <td><%=mensaje.getContenido()%></td>
        <td><%=mensaje.getFecha()%></td>
    </tr>
    <%
        }
    %>

</table>