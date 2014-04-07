<%-- 
    Document   : perfil
    Created on : 30-mar-2014, 19:43:24
    Author     : rogeliotorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil de usuario - tiendeshion</title>
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <link rel="stylesheet" type="text/css" href="css/perfil.css">
        <script src="js/jquery.js"></script>
        <script>
            $(document).ready( function(){
                $('#btn_edit').click(function(){
                        
                    if($(this).text()=='Editar datos')
                    {
                        $('#lines input').css('display','inline');
                        $('#lines input').prev().css('display','none');
                        $(this).text('Cancelar edición');
                        $(this).val('Cancelar edición');
                        $('#btn_save').css('display','inline');
                        $('#frm_cambiar_imagen').css('display','inline');
                    }
                    else
                    {
                        $('#lines input').css('display','none');
                        $('#lines input').prev().css('display','inline');
                        $(this).val('Editar datos');
                        $(this).text('Editar datos');
                        $('#btn_save').css('display','none');
                        $('#frm_cambiar_imagen').css('display','none');
                    }
                });
                
                $('#btn_save').click(function(){
                    $('#frm_cambiar_datos').submit();
                });
            })
        </script>
    </head>
    <body>
        <div id="main_container">
            <%@include file="../include/header.jsp" %>
            <div id="main_content">
                
                <div id="lines">
                    <img src="img/users/${userPhoto}" />
                    <form id="frm_cambiar_imagen" method="POST" action="cambiarImagenPerfil?id=${user.id}" enctype="multipart/form-data">
                        <label id="lbl_cambiar_imagen">Cambiar imagen</label><br /><input type="file" name="imagen" id="i_imagen">
                        <button>Cambiar imagen</button>
                    </form>
                        
                    <form action="cambiarDatosDePerfil?id=${user.id}" method="POST" id="frm_cambiar_datos">
                        <div class="line">
                            <div class="left_container">
                                <label>Nombre</label>
                            </div>
                            <div class="rigth_container">
                                <h2><c:out value="${user.name}" /></h2>
                                <input id="txt_name" value="${user.name}" name="name" />
                            </div>
                        </div>

                        <div class="line">
                            <div class="left_container">
                                <label>Appellidos</label>
                            </div>
                            <div class="rigth_container">
                                <label><c:out value="${user.lastName}" /></label>
                                <input id="txt_last_name" value="${user.lastName}" name="lastName" />
                            </div>
                        </div>

                        <div class="line">
                            <div class="left_container">
                                <label>Dirección</label>
                            </div>
                            <div class="rigth_container">
                                <label><c:out value="${user.address}" /></label>
                                <input id="txt_address" value="${user.address}" name="address" />
                            </div>
                        </div>

                        <div class="line">
                            <div class="left_container">
                                <label>CP</label>
                            </div>
                            <div class="rigth_container">
                                <label><c:out value="${user.postalCode}" /></label>
                                <input id="txt_cp" value="${user.postalCode}" name="postalCode" />
                            </div>
                        </div>

                        <div class="line">
                            <div class="left_container">
                                <label>Telefono</label>
                            </div>
                            <div class="rigth_container">
                                <label><c:out value="${user.phone}" /></label>
                                <input id="txt_phone" value="${user.phone}" name="phone"/>
                            </div>
                        </div>
                    </form>
                </div>
                
                <div id="options">
                    <button id="btn_edit">Editar datos</button>
                    <button id="btn_save">Guardar</button><br>
                    <button id="btn_delete">Eliminar cuenta</button>
                </div>
            </div>
        </div>
    </body>
</html>
