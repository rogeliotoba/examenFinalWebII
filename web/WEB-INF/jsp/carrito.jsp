<%-- 
    Document   : carrito
    Created on : 06-abr-2014, 20:09:08
    Author     : rogeliotorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tiendeishon: Carrito de compra</title>
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <link rel="stylesheet" type="text/css" href="css/carrito.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../include/header.jsp" %>
            <div id="main_content">
                <h2>Carrito de compra</h2>
                <table>
                    <thead>
                        <tr>
                            <td>Imagen</td>
                            <td>Nombre</td>
                            <td>Descripción</td>
                            <td>Precio</td>
                            <td>Cantidad</td>
                            <td>Subtotal</td>
                            <td>Acciones</td>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <tr>
                            <td><img src="img/gopro3.jpg" alt="go pro here 3" /></td>
                            <td>Go pro 3</td>
                            <td>Es uan camara para deportes, aguanta todo</td>
                            <td>$2,500.00</td>
                            <td>1</td>
                            <td>$2,500.00</td>
                            <td><a href="eliminarCarrito?id=2">Eliminar</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>