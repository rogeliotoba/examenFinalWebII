<%-- 
    Document   : departments
    Created on : Apr 2, 2014, 9:58:18 PM
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
        <link rel="stylesheet" type="text/css" href="../css/administration/departments.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../../include/header.jsp" %>
            <div id="main_content">
                <%@include file="include/menu_lateral.jsp" %>
                <div id="departmentContent">
                    <h2>Departamentos</h2>
                    <hr>
                    <table>
                        <tr>
                            <td>
                                Estatus: 
                            </td>
                        <form method="post" action="?section=Departments">
                            <td>
                                <select name="departmentStatus">
                                    <c:choose>
                                        <c:when test="${departmentStatus == 1 || empty departmentStatus}">
                                            <option value="1">Activos</option>
                                            <option value="0">Inactivos</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="0">Inactivos</option>
                                            <option value="1">Activos</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>
                            <td>
                                <input type="submit" value="Cambiar" />
                            </td>
                        </form>
                        </tr>
                    </table>
                    <table border="1">
                        <tr>
                            <td><b>ID</b></td>
                            <td style="width: 68%;"><b>Nombre</b></td>
                            <td><b>Productos</b></td>
                            <td><b>Estatus</b></td>
                            <td><b>Acciones</b></td>
                        </tr>
                        <c:forEach var="row" items="${departments}">
                            <tr>
                                <td><c:out value="${row[0]}"/></td>
                                <td><c:out value="${row[1]}"/></td>
                                <td><c:out value="${row[2]}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${row[3] == 1}">
                                            Activo
                                        </c:when>    
                                        <c:when test="${row[3] == 0}">
                                            Inactivo
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="?section=editDepartment&derpartmentId=${row[0]}">Editar</a>
                                    <c:choose>
                                        <c:when test="${row[2] == 0 && row[3] == 1}">
                                            <a href="?section=deleteDepartment&derpartmentId=${row[0]}">Eliminar</a>
                                        </c:when>    
                                        <c:when test="${row[3] == 0}">
                                            <a href="?section=activeDepartment&derpartmentId=${row[0]}">Activar</a>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${departmentStatus == 1 || empty departmentStatus}">
                            <tr>
                            <form method="post" action="?section=AddDepartment">
                                <td>
                                    #
                                </td>
                                <td>
                                    <input style="widows: 250px" type="text" name="departmentName" maxlength="50" required="" placeholder="Nombre del Departamento" value="" />
                                </td>
                                <td>
                                    0
                                </td>
                                <td>
                                    --
                                </td>
                                <td>
                                    <input type="submit" value="Agregar" />
                                </td>
                            </form>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
