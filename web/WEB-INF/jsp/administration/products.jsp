<%-- 
    Document   : products
    Created on : Apr 6, 2014, 9:07:53 PM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion - Productos</title>
        <link rel="stylesheet" type="text/css" href="../css/theme.css">
        <link rel="stylesheet" type="text/css" href="../css/administration/departments.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../../include/header.jsp" %>
            <div id="main_content">
                <%@include file="include/menu_lateral.jsp" %>
                <div id="departmentContent">
                    <h2>Productos</h2>
                    <hr>
                    <div style="overflow: hidden; padding-bottom: 8px;">
                        <select style="float: left;" name="departments" onchange="if (this.value)
                                    window.location.href = 'main?section=Products&department=' + this.value">
                            <c:if test="${empty param.department}">
                                <option value="0">Departmanto</option>
                            </c:if>
                            <c:forEach var="object" items="${departments}">
                                <c:choose>
                                    <c:when test="${param.department == object.id}">
                                        <option value="${object.id}" selected=""><c:out value="${object.name}"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${object.id}"><c:out value="${object.name}"/></option>
                                    </c:otherwise>
                                </c:choose>
                                <c:out value="${object.name}"/>
                            </c:forEach>
                        </select>
                        <form style="float: left; padding-left: 10px" method="post" action="main?section=Products&add=true">
                            <input type="submit" value="Agregar Producto" />
                        </form>
                    </div>
                    <div>
                        <table border="1">
                            <tr>
                                <td><b>Id</b></td>
                                <td><b>Nombre</b></td>
                                <td><b>Descripcion</b></td>
                                <td><b>Precio</b></td>
                                <td><b>Cantidad</b></td>
                                <td><b>Estatus</b></td>
                                <td><b>Acciones</b></td>
                            </tr>
                            <c:forEach var="object" items="${products}">
                                <tr>
                                    <td><c:out value="${object.id}"/></td>
                                    <td><c:out value="${object.name}"/></td>
                                    <td><c:out value="${object.description}"/></td>
                                    <td><c:out value="$${object.price}"/></td>
                                    <td><c:out value="${object.quantity}"/></td>
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
                                    <td><a href="main?section=Products&edit=${object.id}">Editar</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>