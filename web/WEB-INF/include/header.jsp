<%@page import="java.sql.ResultSet"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="app.Database"%>
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
        <c:if test="${sessionScope.logged_in==true}">
            <ul id="top_right_menu">
                <%
                    
                    Database db = new Database();
                    Integer user_id = Integer.parseInt(session.getAttribute("user_id").toString());
                    Object args[] = {
                        user_id
                    };
                    ResultSet rs = db.ExecQuery("Select count(ProductId) as cuenta from ShoppingCart where UserId = ?", args);
                    int cuenta = 0;
                    if(rs!=null)
                    {
                        rs.next();
                        cuenta = rs.getInt("cuenta");
                    }
                    pageContext.setAttribute("cuenta", cuenta);
                    db.CloseConnection();
                %>
                <li><c:out value="${cuenta}" /><a href="/examenFinalWebII/carrito" class="carrito"><span class="carrito"></span></a></li>
            </ul>
        </c:if>
    </div>
</header>