<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.dto.ClienteDTO" %>
<%@ page import="es.taw.sampletaw.dto.ConversacionDTO" %>
<%@ page import="es.taw.sampletaw.dto.EmpleadoDTO" %>

//Autor: Juan José Torres

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    List<ClienteDTO> usuarios = (List<ClienteDTO>) request.getAttribute("usuarios");
    List<ConversacionDTO> conversacionList = (List<ConversacionDTO>) request.getAttribute("conversaciones");
%>

<html>
<head>
    <title>Asistente|Conversaciones</title>
</head>
<body>

<h3>Filtros:</h3>
<form:form action="/asistente/filtrar" method="post" modelAttribute="filtro">
    Ordenar por numero de mensajes: <form:select path="numeroMensajes" >
                                            <form:option value="" label=" "/>
                                            <form:option value="asc" label="ascendente"/>
                                            <form:option value="desc" label="descendente"/>
                                    </form:select>
    Ordenar por fecha de apertura:  <form:select path="fechaApertura" >
                                        <form:option value="" label=" "/>
                                        <form:option value="asc" label="ascendente"/>
                                        <form:option value="desc" label="descendente"/>
                                    </form:select>
    Buscar usuario: <form:input path="usuario"></form:input>
    <form:button>Filtrar</form:button>
</form:form>

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
        for(ConversacionDTO conversacion : conversacionList){
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
        <td><%=conversacion.getFechaCierre() == null ? "" : conversacion.getFechaCierre()%></td>
        <td><a href="/chat/listar?idChat=<%=conversacion.getId()%>&soyAsistente=<%=1%>">Ir a la conversación</a></td>
    </tr>
    <%
        }
    %>
</table>

<h1>Usuarios del sistema</h1>
<table border="1">
    <th>Nombre</th>
    <th>Apellidos</th>
    <th>Nif</th>
    <th>Ver mensajes</th>

    <%
        for(ClienteDTO usuario : usuarios){
    %>
    <tr>
        <td><%=usuario.getNombre()%></td>
        <td><%=usuario.getApellido()%></td>
        <td><%=usuario.getNif()%></td>
        <td><a href="/asistente/mensajesUsuario?idUsuario=<%=usuario.getId()%>">Ver mensajes</a></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
