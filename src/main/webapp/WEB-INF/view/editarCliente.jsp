<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.entity.Empresa" %>
<%@ page import="es.taw.sampletaw.dto.EmpresaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
    Autor: Javier Serrano Contreras 60%
--%>
<%
    EmpresaDTO empresa = (EmpresaDTO) request.getAttribute("empresaID");
%>

<html>
<head>
    <title>Registro Empresa <%=empresa.getId() %></title>
</head>
<body>
<h3>Datos del cliente a autorizar:</h3>
<%--
<form:form action="/empresa/guardarCliente?id=<%=empresa.getId() %>" modelAttribute="cliente" method="get">
    <form:hidden path="id"/>
    Nombre: <form:input path="nombre" size="50" maxlength="50" /><br/>
    Apellido: <form:input path="apellido" size="50" maxlength="50" /><br/>
    Email: <form:input path="email" size="50"  maxlength="50"/> <br/>
    Dirección: <form:input path="direccion"  size="100" maxlength="100" />
    Teléfono: <form:input type="telefono" path="telefono" maxlength="12" size="12" /><br/>
    Contraseña: <form:input path="contrasena" size="50" maxlength="50" /><br/>

    <form:button>Guardar</form:button>

</form:form>
--%>
<form:form method="get" action="/empresa/guardarClienteEditado" modelAttribute="cliente">
    Nombre: <form:input path="nombre" size="50"></form:input> <br/>
    DNI: <form:input path="nif" size="10" maxlength="9" ></form:input> <br/>
    Apellido: <form:input path="apellido" size="50"></form:input> <br/>
    Email:<form:input path="email" size="50"></form:input>  <br/>
    Dirección: <form:input path="direccion" size="100"></form:input> <br/>
    Teléfono: <form:input path="telefono" size="12"></form:input> <br/>
    Contraseña: <form:password path="contrasena" size="50"></form:password> <br/>
    <form:hidden path="id"></form:hidden>

    <input type="hidden" name="idempresa" value="<%=empresa.getId()%>">
    <form:button>Guardar</form:button>



</form:form>

</body>
</html>
