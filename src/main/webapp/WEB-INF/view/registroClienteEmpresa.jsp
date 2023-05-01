<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.sampletaw.entity.Empresa" %>
<%@ page import="es.taw.sampletaw.dto.EmpresaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<form method="get" action="/empresa/guardarCliente">

    Nombre: <input name="nombre" size="50" maxlength="50"  /><br/>
    Apellido: <input name="apellido" size="50" maxlength="50" /><br/>
    Email: <input name="email" size="50"  maxlength="50"/> <br/>
    DNI: <input name="nif" size="10" maxlength="9" /><br/>
    Dirección: <input  name="direccion" size="100" maxlength="100" />
    Teléfono: <input name="telefono" type="telefono" maxlength="12" size="12" /><br/>
    Contraseña: <input name="contrasena" type="password" size="50" maxlength="50" /><br/>
    <input name="empresa_id" type="hidden" value="<%=empresa.getId() %>" />
    Rol : <select name="tipoCliente" >
        <option value="Socio">Socio</option>
        <option value="Autorizado">Autorizado</option>
    </select>
    <br/>
    <button>Guardar</button>
</form>

</body>
</html>
