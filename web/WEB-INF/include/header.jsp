<header>
    <div id="header_content">
        <h1>Tiendeishon</h1>
        <ul id="main_menu">
            <li><a href="main">Tienda</a></li>

            <c:if test="${logged_in==true}">
                <li><a href="perfil">Perfil</a></li>
                <li><a href="logout">Cerrar sesión</a></li>
            </c:if>

            <c:if test="${logged_in==false}">
                <li><a href="access">Login</a></li>
            </c:if>

            <c:if test="${rol==1}">
                <li><a href="admin">Administración</a></li>
            </c:if>
        </ul>
        <ul id="top_right_menu">
            <li><a href="carrito" class="carrito"><span class="carrito"></span></a></li>
        </ul>
    </div>
</header>