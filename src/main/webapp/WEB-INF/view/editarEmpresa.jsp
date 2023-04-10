<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.sampletaw.entity.Empresa" %>
<%@ page import="es.taw.sampletaw.entity.Cliente" %>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro Empresa</title>
</head>
<body>

<h1>Formulario de registro de una empresa</h1>


<form method="get" action="/empresa/guardar2">
    <h3>Datos de la empresa:</h3>
    Nombre: <input name="nombre"size="50" maxlength="50"  /><br/>
    Dirección: <input  name="direccion"size="100" maxlength="100" />
    Teléfono: <input name="telefono"type="telefono" maxlength="20" size="20" /><br/>
        <input type="hidden" name="id"value="<%=cliente.getId() %>">
    <button>Guardar</button>
</form>
</body>
</html>
