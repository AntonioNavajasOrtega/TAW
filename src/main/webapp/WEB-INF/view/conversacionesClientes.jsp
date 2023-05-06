<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.dto.ConversacionDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

//Autor: Juan José Torres

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ConversacionDTO> conversaciones = (List<ConversacionDTO>) request.getAttribute("conversaciones");
%>

<html>
<head>
    <title></title>
</head>
<body>

<h2>Chats:</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Cliente</th>
        <th>Estado</th>
        <th>Asunto</th>
        <th>Numero de mensajes</th>
        <th>Fecha apertura</th>
        <th>Fecha cierre</th>
        <th>Ver conversacion</th>
    </tr>
    <%
        for(ConversacionDTO conversacion : conversaciones){
    %>
    <tr>
        <td><%=conversacion.getId()%></td>
        <td><%=conversacion.getCliente().getNombre()%></td>
        <%
            String estado = "Abierta";
            if(conversacion.getAbierta()!=1){estado="Cerrada";}
        %>
        <td><%=estado%></td>
        <td><%=conversacion.getAsunto() == null ? "" : conversacion.getAsunto()%></td>
        <td><%=conversacion.getNumeroMensajes()%></td>
        <td><%=conversacion.getFechaApertura()%></td>
        <td><%=conversacion.getFechaCierre() == null ? "---" : conversacion.getFechaCierre()%></td>
        <td><a href="/chat/listar?idCliente=<%=cliente.getId()%>&idChat=<%=conversacion.getId()%>&soyAsistente=<%=0%>">Ir a la conversación</a></td>
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

</body>
</html>
