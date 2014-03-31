<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div id="header_content">
        <h1>Tiendeishon</h1>
        <ul id="main_menu">
            
            <li><a href="/examenFinalWebII/main">Tienda</a></li>

            <c:if test="${sessionScope.logged_in==true&&sessionScope.rol>1}">
                <li><a href="/examenFinalWebII/perfil">Perfil</a></li>
            </c:if>

            <c:if test="${sessionScope.rol==1}">
                <li><a href="/examenFinalWebII/administration/main">Administración</a></li>
            </c:if>
                
            <c:if test="${sessionScope.logged_in==true}">
                <li><a href="/examenFinalWebII/logout">Cerrar sesión</a></li>
            </c:if>

            <c:if test="${empty sessionScope.logged_in||logged_in==false}">
                <li><a href="/examenFinalWebII/access">Login</a></li>
            </c:if>


        </ul>
        <ul id="top_right_menu">
            <li><a href="carrito" class="carrito"><span class="carrito"></span></a></li>
        </ul>
    </div>
</header>