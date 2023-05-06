<%@ page import="es.taw.sampletaw.dto.MensajeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.dto.EmpleadoDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

//Autor: Juan José Torres

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<MensajeDTO> mensajeList = (List<MensajeDTO>) request.getAttribute("mensajes");
%>

<table border="1">
    <th>Emisor</th>
    <th>Receptor</th>
    <th>Mensaje</th>
    <th>Fecha</th>
    <%
        for(MensajeDTO mensaje : mensajeList){
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