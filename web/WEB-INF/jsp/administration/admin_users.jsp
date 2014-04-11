<%-- 
    Document   : admin_users
    Created on : Apr 6, 2014, 9:08:17 PM
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
        <link rel="stylesheet" type="text/css" href="../css/administration/departments.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../../include/header.jsp" %>
            <div id="main_content">
                <%@include file="include/menu_lateral.jsp" %>
                <div id="departmentContent">
                    <h2>Cuentas Administrador</h2>
                    <hr>
                    <form style="padding-bottom: 8px;" method="post" action="main?section=AdminUsers&add=true">
                        <input type="submit" value="Agregar Usuario"/>
                    </form>
                    <table border="1">
                        <tr>
                            <td><b>Id</b></td>
                            <td><b>Nombre</b></td>
                            <td><b>E-Mail</b></td>
                            <td><b>Telefono</b></td>
                            <td><b>Usuario</b></td>
                            <td><b>Estatus</b></td>
                            <td><b>Acciones</b></td>
                        </tr>
                        <c:forEach var="object" items="${users}">
                            <tr>
                                <td><c:out value="${object.id}"/></td>
                                <td><c:out value="${object.name} ${object.lastName}"/></td>
                                <td><c:out value="${object.email}"/></td>
                                <td><c:out value="${object.phone}"/></td>
                                <td><c:out value="${object.username}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${true == object.active}">
                                            Activo
                                        </c:when>
                                        <c:otherwise>
                                            Inactivo
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><a href="main?section=AdminUsers&edit=${object.id}">Editar</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
