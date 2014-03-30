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
    </head>
    <body>
        <div id="main_container">
        <%@ include file="../include/header.jsp" %>
        
        <div id="main_content">
            <div id="sections_menu">
                <ul >
                    <li><a href="?departamento=electronica">Electronica</a></li>
                    <li><a href="?departamento=linea_blanca">Linea blanca</a></li>
                    <li><a href="?departamento=lalalala">lalala</a></li>
                </ul>
            </div>
            
            <div id="comercial_content">
                <div class="section">
                    <div class="section_content">
                        lalalalala alaa sasas
                    </div>
                    <div class="section_content">
                        lalalala asdasdasda dasd asd
                    </div>
                </div>
                <div class="section">
                    <div class="section_content">
                        lalalalala alaa sasas
                    </div>
                    <div class="section_content">
                        lalalala asdasdasda dasd asd
                    </div>
                </div>
                <div class="section">
                    <div class="section_content">
                        lalalalala alaa sasas
                    </div>
                    <div class="section_content">
                        lalalala asdasdasda dasd asd
                    </div>
                </div>
                <div class="section">
                    <div class="section_content">
                        lalalalala alaa sasas
                    </div>
                    <div class="section_content">
                        lalalala asdasdasda dasd asd
                    </div>
                </div>
            </div>
        </div>
        </div>
    </body>
</html>