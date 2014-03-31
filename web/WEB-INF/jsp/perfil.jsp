<%-- 
    Document   : perfil
    Created on : 30-mar-2014, 19:43:24
    Author     : rogeliotorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil de usuario - tiendeshion</title>
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <link rel="stylesheet" type="text/css" href="css/perfil.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../include/header.jsp" %>
            <div id="main_content">
                
                <div class="line">
                    <div class="left_container">
                        <label>Nombre</label>
                    </div>
                    <div class="rigth_container">
                        <h2><c:out value="${user.name}" /></h2>
                    </div>
                </div>
        
                <div class="line">
                    <div class="left_container">
                        <label>Appellidos</label>
                    </div>
                    <div class="rigth_container">
                        <label><c:out value="${user.lastName}" /></label>
                    </div>
                </div>
                    
                <div class="line">
                    <div class="left_container">
                        <label>Dirección</label>
                    </div>
                    <div class="rigth_container">
                        <label><c:out value="${user.address}" /></label>
                    </div>
                </div>

                <div class="line">
                    <div class="left_container">
                        <label>CP</label>
                    </div>
                    <div class="rigth_container">
                        <label><c:out value="${user.postalCode}" /></label>
                    </div>
                </div>

                <div class="line">
                    <div class="left_container">
                        <label>Telefono</label>
                    </div>
                    <div class="rigth_container">
                        <label><c:out value="${user.phone}" /></label>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
