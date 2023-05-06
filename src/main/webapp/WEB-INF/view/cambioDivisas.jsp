<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fom" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.mysql.cj.xdevapi.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ConcurrentModificationException" %>
<%@ page import="java.util.Collection" %>
<%@ page import="es.taw.sampletaw.entity.*" %>
<%@ page import="es.taw.sampletaw.dto.CuentaDTO" %>
<%@ page import="es.taw.sampletaw.dto.TransaccionDTO" %><%--

<%--
    Autor: Javier Serrano Contreras 60%
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<CuentaDTO> cuentas = (List<CuentaDTO>) request.getAttribute("cuentas");
    TransaccionDTO trans = (TransaccionDTO) request.getAttribute("trans");
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
    <form:form action="/cliente/cambiarmoneda" method="post" modelAttribute="trans">
        <form:hidden path="id"></form:hidden>
        <input type="hidden" name="cuentaByCuentaOrigenId1" value="<%=trans.getCuentaByCuentaOrigenId().getId()%>">
        <input type="hidden" name="cuentaByCuentaDestinoId1" value="<%=trans.getCuentaByCuentaOrigenId().getId()%>">
        <form:hidden path="cantidad"></form:hidden>
        Monedas: <form:select path="moneda">
        <form:option value="" label=""></form:option>
        <form:option value="eur" label="EUR"></form:option>
        <form:option value="usd" label="USD"></form:option>
        </form:select><br/>
        <input type="hidden" name="volver" value="${volver}">
        <form:button>Realizar</form:button>
    </form:form>
</body>
</html>
