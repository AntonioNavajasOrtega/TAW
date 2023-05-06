<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.Empresa" %>
<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="es.taw.sampletaw.dto.ClienteDTO" %>
<%--
    Autor: Antonio Navajas Ortega
--%>
<%
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro Empresa</title>
</head>
<body>

<h1>Formulario de registro de una empresa</h1>


<form:form action="/empresa/guardar2" modelAttribute="empresa" method="get">
    <h3>Datos de la empresa:</h3>
    <form:hidden path="id"/>
    Nombre: <form:input path="nombre" size="50" maxlength="50" /><br/>
    Dirección: <form:input path="direccion"  size="100" maxlength="100" /><br/>

    Teléfono: <form:input type="telefono" path="telefono" maxlength="12" size="12" /><br/>
    <input type="hidden" name="volver" value="${cliente.id}"/>
    <form:button>Guardar</form:button>
</form:form>
</body>
</html>
