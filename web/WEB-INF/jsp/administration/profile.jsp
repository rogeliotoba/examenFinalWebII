<%-- 
    Document   : main
    Created on : Mar 30, 2014, 5:20:21 PM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion - Mi Perfil</title>
        <link rel="stylesheet" type="text/css" href="../css/theme.css">
        <link rel="stylesheet" type="text/css" href="../css/administration/profile.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../../include/header.jsp" %>
            <div id="main_content">
                <%@include file="include/menu_lateral.jsp" %>
                <div id="profileContent">
                    <c:if test="${invalid_new_password == true}">
                        <h4 style="color: red;"><b>La nueva contrase&ntilde;a NO coincide!<br> Porfavor vuelva a ingresarla.</b></h4>

                    </c:if>

                    <c:if test="${invalid_new_password == false}">
                        <h4 style="color: green;"><b>Datos Actulizados Correctamente!</b></h4>

                    </c:if>
                    <div id="photoPanel">
                        <img alt="profile.jpg" src="aaaa.jpg" width="200" height="200"/>
                        <br>
                        <a href="#">Actulizar Foto</a>
                    </div>
                    <div id="dataPanel">
                        <form name="frmProfile" method="post" action="main?section=Profile">
                            <table>
                                <tr>
                                    <td>Nombre:</td>
                                    <td><input type="text" name="user_name" placeholder="Nombre" required="" maxlength="50" value="${user_profile.name}"/></td>
                                </tr>
                                <tr>
                                    <td>Apellidos:</td>
                                    <td><input type="text" name="user_lastname" placeholder="Apellidos" required="" maxlength="50" value="${user_profile.lastName}"/></td>
                                </tr>
                                <tr>
                                    <td>Domicilio:</td>
                                    <td><input type="text" name="user_address" placeholder="Nuevo Domicilio" required="" maxlength="100" value="${user_profile.address}"/></td>
                                </tr>
                                <tr>
                                    <td>Codigo Postal:</td>
                                    <td><input type="number" name="user_postalCode" placeholder="Nuevo Codigo Postal" required="" max="99999" value="${user_profile.postalCode}"/></td>
                                </tr>
                                <tr>
                                    <td>Telefono:</td>
                                    <td><input type="number" name="user_phone" placeholder="Nuevo Telefono" required="" max="999999999999999" value="${user_profile.phone}"/></td>
                                </tr>
                                <tr>
                                    <td>E-mail:</td>
                                    <td><input type="email" name="user_email" placeholder="Nuevo Email" required="" maxlength="100" value="${user_profile.email}"/></td>
                                </tr>
                                <tr>
                                    <td>Usuario:</td>
                                    <td style="text-align: left;"><c:out value="${user_profile.username}"/></td>
                                </tr>
                                <tr>
                                    <td>Constrase&ntilde;a:</td>
                                    <td style="text-align: left;">*****************</td>
                                </tr>
                                <tr>
                                    <td>Nueva Contrase&ntilde;a:</td>
                                    <td><input type="password" name="new_password" placeholder="Nueva contrase&ntilde;a"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="password" name="new_password2" placeholder="Repita la contrase&ntilde;a"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="submit" value="Actulizar Datos" />
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>