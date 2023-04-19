<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fom" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ConcurrentModificationException" %>
<%@ page import="java.util.Collection" %>
<%@ page import="es.taw.sampletaw.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: guzman
  Date: 11/5/22
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
    List<TipoTransaccion> tipos = (List<TipoTransaccion>) request.getAttribute("tipos");
%>


<html>
<head>
    <title>Transacción</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</head>
<body>

    <h1>Información de operación</h1>

    <form:form action="/cliente/realizar" method="post" modelAttribute="trans">
        <form:hidden path="id"></form:hidden>
        <form:hidden path="cuentaByCuentaOrigenId"></form:hidden>
        Tipos de opración:<form:select path="tipoTransaccionByTipo">
            <form:options items="${tipos}" itemLabel="tipo"></form:options>
        </form:select><br/>
        Cuenta Destino: <form:select path="cuentaByCuentaDestinoId">
            <form:options items="${cuentas}" itemLabel="iban"></form:options>
        </form:select><br/>
        Cantidad:<form:input path="cantidad"></form:input><br/>
        <form:button>Realizar</form:button>
    </form:form>
</body>
</html>
