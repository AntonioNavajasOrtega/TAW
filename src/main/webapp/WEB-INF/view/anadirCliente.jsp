<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.entity.Empresa" %>
<%@ page import="es.taw.sampletaw.dto.EmpresaDTO" %>
<%@ page import="es.taw.sampletaw.dto.ClienteDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    Autor: Antonio Navajas Ortega
--%>

<%
    EmpresaDTO empresa = (EmpresaDTO) request.getAttribute("empresa");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("volver");
%>

<html>
<head>
    <title>Registro Empresa <%=empresa.getId() %></title>
</head>
<body>
<h3>Datos del cliente a autorizar:</h3>

<form:form modelAttribute="cliente" method="get" action="/empresa/guardarCliente2">
    <form:hidden path="id"/>
    Nombre: <form:input path="nombre" size="50" maxlength="50" /><br/>
    Apellido: <form:input path="apellido" size="50" maxlength="50" /><br/>
    Email: <form:input path="email" size="50"  maxlength="50"/> <br/>
    DNI: <form:input path="nif" size="10" maxlength="9" /><br/>
    Dirección: <form:input path="direccion"  size="100" maxlength="100" />
    Teléfono: <form:input type="telefono" path="telefono" maxlength="12" size="12" /><br/>
    Contraseña: <form:password path="contrasena" size="50" maxlength="50" /><br/>
    <input name="empresa_id" type="hidden" value="${empresa.id}" />
    <input type="hidden" name="volver" value="${volver.id}"/>
    Rol : <select name="tipoCliente" >
    <option value="Socio">Socio</option>
    <option value="Autorizado">Autorizado</option>
</select>
    <br/>
    <form:button>Guardar</form:button>

</form:form>


</body>
</html>
