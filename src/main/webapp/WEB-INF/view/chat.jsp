<%@ page import="es.taw.sampletaw.dto.ConversacionDTO" %>
<%@ page import="es.taw.sampletaw.dto.MensajeDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--Autor: Juan José Torres--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    ConversacionDTO chat = (ConversacionDTO) request.getAttribute("chat");
    Byte soyAsistente = (Byte) request.getAttribute("soyAsistente");
    List<MensajeDTO> mensajes = (List<MensajeDTO>) request.getAttribute("mensajes");
%>

<html>
<head>
    <title>Chateando con <%=soyAsistente == 1 ? "Asistente" : "Cliente" %></title>
</head>
<body>


    <h1> <%=chat.getAbierta()==1 ? "Chateando con" : "Chat cerrado por"%>  <%=soyAsistente == 1 ? chat.getCliente().getNombre() : "Asistente" %></h1>
    <h3>Asunto: <%=chat.getAsunto()%></h3>
    <%
        for(MensajeDTO m : mensajes){
    %>
    <table>
        <tr>
            <td><%=m.getEmpleadoByEmisorEmpleado() != null ? "Asistente": m.getClienteByEmisorCliente().getNombre()%> <%=":\t " + m.getContenido()%></td>
        </tr>
    </table>
    <%
        }
    %>

    <%
        if (chat.getAbierta()!=0){
    %>
    <form method="post" action="/chat/enviarMensaje">
        <input type="hidden" name="soyAsistente" value="<%=soyAsistente%>">
        <input type="hidden" name="idChat" value="<%=chat.getId()%>">
        <input type="text" name="mensaje"> <button>Enviar mensaje</button> <br/>
    </form>
    <%
        }
    %>
        <%
            if(soyAsistente == 0){
        %>
        <button onclick="location.href='/chat/cerrar?idConversacion=<%=chat.getId()%>'">Cerrar conversacion</button>
        <%
            if(chat.getCliente().getEmpresa() == null) {
        %>
            <a href="/cliente/?id=<%=chat.getCliente().getId()%>">Volver al perfil</a>
        <%
            } else {
        %>
            <a href="/empresa/?id=<%=chat.getCliente().getId()%>">Volver al perfil</a>
        <%
            }
        %>
        <br/>
        <%
            }else{
        %>
            <a href="/asistente/">Volver al perfil</a>
        <%}%>

</body>
</html>
