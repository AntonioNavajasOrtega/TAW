<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.ClienteEntity" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 11/5/22
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Registro Cliente</title>
</head>
<body>
<h1>Formulario registro de un cliente</h1>
<form:form action="/guardar" modelAttribute="cliente" method="post">
    <form:hidden path="customerId"/>
    Nombre: <form:input path="name" size="50" maxlength="50"  /><br/>
    Apellido: <form:input path="apellido" size="50" maxlength="50" /><br/>
    Email: <form:input path="email" size="50"  maxlength="50"/> <br/>
    Dirección: <form:input path="addressline1"  size="100" maxlength="100" />
    Teléfono: <form:input type="telefono" path="phone" maxlength="12" size="12" /><br/>
    Contraseña: <form:input path="contraseña" size="50" maxlength="50" /><br/>
    <form:button>Guardar</form:button>

</form:form>

</body>
</html>
