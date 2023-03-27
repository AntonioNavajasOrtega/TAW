<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Autenticación</title>
</head>
<body>
<h1>Iniciar Sesión:</h1>

<c:if test="${error != null}" >
    <p style="color:red;">
            ${error}
    </p>
</c:if>

<form action="/" method="post">
    <table>
        <tr>
            <td>Email:</td> <td><input type="text" maxlength="50" size="50" name="email"></td>
        </tr>
        <tr>
            <td>Contraseña:</td> <td><input type="password" maxlength="50" size="50" name="contrasena"> </td>
        </tr>
        <tr>
            <td colspan="2"> <button>Continuar</button></td>
        </tr>
    </table>
</form>
<button><a href="/registroCliente">Registrarse</a></button>

</body>
</html>
