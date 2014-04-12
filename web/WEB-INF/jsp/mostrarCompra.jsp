<%-- 
    Document   : mostrarCompra
    Created on : 11-abr-2014, 18:31:43
    Author     : rogeliotorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle de compra</title>
       <link rel="stylesheet" type="text/css" href="css/theme.css">
        <link rel="stylesheet" type="text/css" href="css/mostrarCompra.css">
    </head>
    <body>
       <div id="main_container">
            <%@include file="../include/header.jsp" %>
            <div id="main_content">
                <iframe style="width:100%;height: 100%;" src="/examenFinalWebII/GenerarPDF?Sale=${param.Sale}"></iframe>
            </div>
       </div>
    </body>
</html>
