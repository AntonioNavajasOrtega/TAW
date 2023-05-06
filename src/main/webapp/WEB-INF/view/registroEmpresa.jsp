<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.Empresa" %>

<%--Autor: Juan José Torres 60%
           Antonio Navajas Ortega 40%
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro Empresa</title>
</head>
<body>

<h1>Formulario de registro de una empresa</h1>

<form:form action="/empresa/guardar" modelAttribute="empresa" method="get">
    <h3>Datos de la empresa:</h3>
    <form:hidden path="id"/>
    Nombre: <form:input path="nombre" size="50" maxlength="50" /><br/>
    Dirección: <form:input path="direccion"  size="100" maxlength="100" /><br/>

    Teléfono: <form:input type="telefono" path="telefono" maxlength="12" size="12" /><br/>
    <form:button>Guardar</form:button>
</form:form>



</body>
</html>
