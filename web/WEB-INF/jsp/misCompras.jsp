<%-- 
    Document   : misCompras
    Created on : 11-abr-2014, 19:07:04
    Author     : rogeliotorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mis compras</title>
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <link rel="stylesheet" type="text/css" href="css/carrito.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../include/header.jsp" %>
            <div id="main_content">
                <table>
                    <thead>
                    <tr>
                        <td>Total</td>
                        <td>Ver compra</td>
                    </tr>
                    </thead>
                <c:forEach var="sale" items="${sales}">
                    <tr>
                        <td>Total: <c:out value="${sale.totalAmount}" /></td>
                        <td><a href="/examenFinalWebII/mostrarCompra?Sale=${sale.id}">Ver compra</a></td>
                    </tr>
                </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
