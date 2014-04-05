<%-- 
    Document   : main_view
    Created on : 29-mar-2014, 13:07:42
    Author     : rogeliotorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <link rel="stylesheet" type="text/css" href="css/main_view.css">
    </head>
    <body>
        <div id="main_container">
            <%@include file="../include/header.jsp" %>

            <div id="main_content">
                <div id="sections_menu">
                    <ul >
                        <c:forEach var="department" items="${departments}" >
                            <li><a href="?department=${department.id}"><c:out value="${department.name}" /></a></li>
                        </c:forEach>
                    </ul>
                </div>

                <div id="comercial_content">
                    <div class="product">
                        <div class="product_image_container">
                            <img src="img/gopro3.jpg" alt="product" />
                        </div>
                        <h3 class="product_name">Go pro hero 3</h3>
                        <p class="product_description">Camara para deportes go pro 3 hero, soporta condiciones extremas. grabación de video en 1080P</p>
                        <p class="price">$2,500.00</p><button class="addCar">Agregar al carrito</button>
                    </div>

                    <div class="product">
                        <div class="product_image_container">
                            <img src="img/bicicleta-bmw.jpg" alt="product" />
                        </div>
                        <h3 class="product_name">Bicileta bmw</h3>
                        <p class="product_description">Bicicleta super pro BMW idela para impresionar chicas</p>
                        <p class="price">$7,500.00</p><button class="addCar">Agregar al carrito</button>
                    </div>

                    <div class="product">
                        <div class="product_image_container">
                            <img src="img/gopro3.jpg" alt="product" />
                        </div>
                        <h3 class="product_name">Go pro hero 3</h3>
                        <p class="product_description">Camara para deportes go pro 3 hero, soporta condiciones extremas. grabación de video en 1080P</p>
                        <p class="price">$2,500.00</p><button class="addCar">Agregar al carrito</button>
                    </div>
                   
                    <div class="product">
                        <div class="product_image_container">
                            <img src="img/gopro3.jpg" alt="product" />
                        </div>
                        <h3 class="product_name">Go pro hero 3</h3>
                        <p class="product_description">Camara para deportes go pro 3 hero, soporta condiciones extremas. grabación de video en 1080P</p>
                        <p class="price">$2,500.00</p><button class="addCar">Agregar al carrito</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>