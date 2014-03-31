<%-- 
    Document   : index
    Created on : 30-mar-2014, 13:41:39
    Author     : rogeliotorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Tiendeshion</title>
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <link rel="stylesheet" type="text/css" href="css/access.css">
    </head>
    <body>

        <div id="main_container">
            <%@include file="../include/header.jsp" %>
            <div id="main_content">
                <h2>Accesso a tiendeishon</h2>
                <c:if test="${param.error==true}">
                    <p style="background: #d35512; border:1px solid #ccc; font-size: 1em; font-family: verdana; color:#eee; padding: 1em;">Datos de acceso erroneos</p>
                </c:if>
                <form id="form_container" action="doLogin" method="POST">
                    <ul>
                        <li>
                            <label for="txt_login">Login</label>
                            <input type="text" name="login" id="txt_login" required>
                        </li>
                        <li>
                            <label for="txt_password">Password</label>
                            <input type="password" name="password" id="txt_password" required>
                        </li>
                    </ul>
                    <button>Login</button>
                </form>
            </div>
        </div>
    </body>
</html>
