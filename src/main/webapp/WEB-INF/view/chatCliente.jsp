<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="java.util.ConcurrentModificationException" %>
<%@ page import="es.taw.sampletaw.entity.Conversacion" %>
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
    Conversando con: <%=1/*chat.getAsunto()*/%> <br/>


    <input type="text"> <button>Enviar mensaje</button> <br/>
    <button onclick="location.href='/chat/cerrar?idConversacion=<%=chat.getId()%>'">Cerrar conversacion</button>




</body>
</html>
