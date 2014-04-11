<%-- 
    Document   : product_info
    Created on : Apr 10, 2014, 11:20:06 AM
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
        <link rel="stylesheet" type="text/css" href="../css/administration/profile.css">
        <style type="text/css">
            input[type="text"]{
                width: 220px;
            }
        </style>
    </head>
    <body>
        <div id="main_container">
            <%@include file="../../include/header.jsp" %>
            <div id="main_content">
                <%@include file="include/menu_lateral.jsp" %>
                <div id="profileContent">
                    <c:choose>
                        <c:when test="${empty product.name}">
                            <h2>Nuevo Producto</h2>
                        </c:when>
                        <c:otherwise>
                            <h2><c:out value="${product.name}"/></h2>
                        </c:otherwise>
                    </c:choose>
                    <hr>
                    <div id="photoPanel">
                        <c:if test="${not empty product.name}">
                            <img alt="no-profile.jpg" src="../img/products/${Photo}" width="200" height="200"/>
                            <br>
                            <form method="POST" action="main?section=ChangeProductPhoto&id=${product.id}&section=ProductInfo" enctype="multipart/form-data">
                                Cambiar Imagen
                                <br>
                                <input type="file" name="newPhoto" />
                                <br>
                                <input type="submit" value="Cambiar Foto" />
                            </form>
                        </c:if>
                    </div>
                    <div id="dataPanel">
                        <c:choose>
                            <c:when test="${empty product.name}">
                                <form method="post" action="main?section=AddProduct">
                                </c:when>
                                <c:otherwise>
                                    <form method="post" action="main?section=EditProduct">
                                    </c:otherwise>
                                </c:choose>
                                <table style="text-align: left;">
                                    <c:if test="${not empty product.name}">
                                        <tr>
                                            <td>Id:</td>
                                            <td>
                                                <input type="text" name="productId" value="${product.id}" readonly hidden/>
                                                <c:out value="${product.id}"/>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <tr>
                                        <td>Nombre:</td>
                                        <td>
                                            <input type="text" name="productName" value="${product.name}" required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Departamento:</td>
                                        <td>
                                            <input type="text" name="productLastDepartment" value="${product.departmentId}" readonly hidden/>
                                            <select style="float: left;" name="productDepartment" >
                                                <c:forEach var="object" items="${departments}">
                                                    <c:choose>
                                                        <c:when test="${product.departmentId == object.id}">
                                                            <option value="${object.id}" selected=""><c:out value="${object.name}"/></option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${object.id}"><c:out value="${object.name}"/></option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:out value="${object.name}"/>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Descripcion: </td>
                                        <td>
                                            <textarea name="productDescription" cols="25" rows="5"><c:out value="${product.description}"/></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Precio:</td>
                                        <td><input type="number" name="productPrice" value="${product.price}" required/></td>
                                    </tr>
                                    <tr>
                                        <td>Cantidad:</td>
                                        <td><input type="number" name="productQuantity" value="${product.quantity}" required/></td>
                                    </tr>
                                    <tr>
                                        <td>Estatus:</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${false == product.active}">
                                                    <select name="productStatus">
                                                        <option value="1">Activo</option>
                                                        <option value="0" selected>Inactivo</option>
                                                    </select>
                                                </c:when>
                                                <c:otherwise>
                                                    <select name="productStatus">
                                                        <option value="1" selected>Activo</option>
                                                        <option value="0">Inactivo</option>
                                                    </select>
                                                </c:otherwise>
                                            </c:choose>   
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${empty product.name}">
                                                    <input type="submit" value="Agregar Producto"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="submit" value="Guardar Cambios"/>
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