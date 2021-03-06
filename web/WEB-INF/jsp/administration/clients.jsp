<%-- 
    Document   : clients
    Created on : Apr 6, 2014, 9:08:07 PM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion - Clientes</title>
        <link rel="stylesheet" type="text/css" href="../css/theme.css">
        <link rel="stylesheet" type="text/css" href="../css/administration/departments.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../../include/header.jsp" %>
            <div id="main_content">
                <%@include file="include/menu_lateral.jsp" %>
                <div id="departmentContent">
                    <h2>Clientes</h2>
                    <hr>
                    <table border="1">
                        <tr>
                            <td><b>Id</b></td>
                            <td><b>Nombre</b></td>
                            <td><b>Domicilio</b></td>
                            <td><b>E-Mail</b></td>
                            <td><b>Usuario</b></td>
                            <td><b>Estatus</b></td>
                            <td><b>Acciones</b></td>
                        </tr> 
                        <c:forEach var="object" items="${clients}">
                            <tr>
                                <td><c:out value="${object.id}"/></td>
                                <td><c:out value="${object.name} ${object.lastName}"/></td>
                                <td><c:out value="${object.address}"/></td>
                                <td><c:out value="${object.email}"/></td>
                                <td><c:out value="${object.username}"/></td>
                                <c:choose>
                                    <c:when test="${true == object.active}">
                                        <td style="color: green;">Activo</td>
                                        <td><a href="main?section=Clients&Inactive=${object.id}">Baja</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="color: red;">Inactivo</td>
                                        <td><a href="main?section=Clients&Active=${object.id}">Alta</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

