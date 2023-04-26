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
    Byte soyAsistente = (Byte) request.getAttribute("soyAsistente");
%>

<html>
<head>
    <title>Chateando con <%=soyAsistente == 1 ? "Asistente" : "Cliente" %></title>
</head>
<body>

    <h1>Chateando con <%=soyAsistente == 1 ? chat.getClienteByCliente().getNombre() : "Asistente" %></h1>
    <h3>Asunto: <%=chat.getAsunto()%></h3>
    <%
        for(Mensaje m : chat.getMensajesById()){
    %>
    <table>
        <tr>
            <td><%=m.getEmpleadoByEmisorEmpleado() != null ? "Asistente": m.getClienteByEmisorCliente().getNombre()%> <%=":\t " + m.getContenido()%></td>
        </tr>
    </table>
    <%
        }
    %>

    <form method="post" action="/chat/enviarMensaje">
        <input type="hidden" name="soyAsistente" value="<%=soyAsistente%>">
        <input type="hidden" name="idChat" value="<%=chat.getId()%>">
        <input type="text" name="mensaje"> <button>Enviar mensaje</button> <br/>
    </form>
        <%
            if(soyAsistente == 0){
        %>
        <button onclick="location.href='/chat/cerrar?idConversacion=<%=chat.getId()%>'">Cerrar conversacion</button>
        <a href="/cliente/?id=<%=chat.getClienteByCliente().getId()%>">Volver al perfil</a>
        <br/>
    <%
            }else{
        %>
    <a href="/asistente/">Volver al perfil</a>

    <%
            }
        %>

</body>
</html>
