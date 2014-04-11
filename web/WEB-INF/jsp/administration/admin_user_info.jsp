<%-- 
    Document   : admin_user_info
    Created on : Apr 10, 2014, 5:54:52 PM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion - Cuentas Administracion</title>
        <link rel="stylesheet" type="text/css" href="../css/theme.css">
        <link rel="stylesheet" type="text/css" href="../css/administration/profile.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../../include/header.jsp" %>
            <div id="main_content">
                <%@include file="include/menu_lateral.jsp" %>
                <div id="profileContent">
                    <c:choose>
                        <c:when test="${empty user.name}">
                            <h2>Nuevo Usuario Administrador</h2>
                        </c:when>
                        <c:otherwise>
                            <h2>Perfil <c:out value="${user.name} ${user.lastName}"/></h2>
                        </c:otherwise>
                    </c:choose>
                    <hr>
                    <c:if test="${invalid_new_password == true}">
                        <h4 style="color: red;"><b>La nueva contrase&ntilde;a NO coincide!<br> Porfavor vuelva a ingresarla.</b></h4>

                    </c:if>

                    <c:if test="${invalid_new_password == false}">
                        <h4 style="color: green;"><b>Datos Actulizados Correctamente!</b></h4>

                    </c:if>
                    <div id="photoPanel">
                        <c:if test="${not empty user.name}">
                            <img alt="profile.jpg" src="aaaa.jpg" width="200" height="200"/>
                            <br>
                            <a href="#">Actulizar Foto</a>
                        </c:if>
                    </div>
                    <div id="dataPanel">
                        <c:choose>
                            <c:when test="${empty user.name}">
                                <form method="post" action="main?section=AddAdminUser">
                            </c:when>
                            <c:otherwise>
                                <form metho="post" action="main?section=EditAdminUser">
                            </c:otherwise>
                        </c:choose>
                            <table>
                                <tr>
                                    <td>Nombre:</td>
                                    <td><input type="text" name="user_name" placeholder="Nombre" required="" maxlength="50" value="${user.name}"/></td>
                                </tr>
                                <tr>
                                    <td>Apellidos:</td>
                                    <td><input type="text" name="user_lastname" placeholder="Apellidos" required="" maxlength="50" value="${user.lastName}"/></td>
                                </tr>
                                <tr>
                                    <td>Domicilio:</td>
                                    <td><input type="text" name="user_address" placeholder="Nuevo Domicilio" required="" maxlength="100" value="${user.address}"/></td>
                                </tr>
                                <tr>
                                    <td>Codigo Postal:</td>
                                    <td><input type="number" name="user_postalCode" placeholder="Nuevo Codigo Postal" required="" max="99999" value="${user.postalCode}"/></td>
                                </tr>
                                <tr>
                                    <td>Telefono:</td>
                                    <td><input type="number" name="user_phone" placeholder="Nuevo Telefono" required="" max="999999999999999" value="${user.phone}"/></td>
                                </tr>
                                <tr>
                                    <td>E-mail:</td>
                                    <td><input type="email" name="user_email" placeholder="Nuevo Email" required="" maxlength="100" value="${user.email}"/></td>
                                </tr>
                                <tr>
                                    <td>Estatus:</td>
                                    <td>
                                        <select name="user_status">
                                            <c:choose>
                                                <c:when test="${false == user.active}">
                                                    <option value="1">Activo</option>
                                                    <option value="0" selected>Inactivo</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="1" selected>Activo</option>
                                                    <option value="0">Inactivo</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Usuario:</td>
                                    <td style="text-align: left;">
                                        <c:choose>
                                            <c:when test="${empty user.name}">
                                                <input type="text" name="user_username" value="" maxlength="25" required=""/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${user.username}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <c:choose>
                                    <c:when test="${empty user.name}">
                                        <tr>
                                            <td>Contrase&ntilde;a:</td>
                                            <td><input type="password" name="new_password" placeholder="Contrase&ntilde;a" maxlength="25" required=""/></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><input type="password" name="new_password2" placeholder="Repita la contrase&ntilde;a" maxlength="25" required=""/></td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
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
                                    </c:otherwise>
                                </c:choose>
                                <tr>
                                    <td></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty user.name}">
                                                <input type="submit" value="Agregar Usuario" />
                                            </c:when>
                                            <c:otherwise>
                                                <input type="submit" value="Actulizar Datos" />
                                            </c:otherwise>
                                        </c:choose>
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