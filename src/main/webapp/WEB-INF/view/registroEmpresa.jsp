<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: juanj
  Date: 20/03/2023
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro Empresa</title>
</head>
<body>

<h1>Formulario de registro de una empresa</h1>

<form method="post" action="/Empresa">
    <h3>Datos de la empresa:</h3>
    Nombre: <input size="50" maxlength="50"  /><br/>
    Dirección: <input  size="100" maxlength="100" />
    Teléfono: <input type="telefono" maxlength="12" size="12" /><br/>
    Usuario:  <input type="text" maxlength="" size=""> <br/>
    Contraseña: <input type="password" size="50" maxlength="50" /><br/>

    <h3>Datos del cliente a autorizar:</h3>
    Nombre: <input size="50" maxlength="50"  /><br/>
    Apellido: <input size="50" maxlength="50" /><br/>
    Email: <input size="50"  maxlength="50"/> <br/>
    Dirección: <input  size="100" maxlength="100" />
    Teléfono: <input type="telefono" maxlength="12" size="12" /><br/>
    Contraseña: <input type="password" size="50" maxlength="50" /><br/>
    <button>Guardar</button>
</form>

</body>
</html>
