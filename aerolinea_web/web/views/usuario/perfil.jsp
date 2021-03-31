<%-- 
    Document   : registro
    Created on : Mar 26, 2021, 5:42:16 PM
    Author     : david
--%>

<%@page import="logic.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%

    Usuario usuario = (Usuario) session.getAttribute("usuarioTem");
    request.setAttribute("usuario", usuario);

    
%>


<t:template>
    <jsp:attribute name="title">
        Perfil
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/socket.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row my-5 d-flex justify-content-center">
                <div class="col-md-8">
                    <div class="card ">
                        <div class="card-body d-flex justify-content-center align-items-center ">
                            <form class="form-signin" method="POST" action="/aerolinea/usuario/perfil">
                                <div class="input-group mb-4">
                                    <input type="id" name="id" id="inputEmail" value="${usuario.getId()}" class="form-control" disabled="true">
                                    <span class="input-group-text"><i
                                            class="fas fa-user text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="nombre" name="nombre" value="${usuario.getNombre()}" id="inputNombre" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="apellido" name="apellido" value="${usuario.getApellidos()}" id="inputApellidos" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="gmail" name="correo" value="${usuario.getCorreo()}" id="inputCorreo" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-envelope text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="fecha_nacimiento" name="fecha_nacimiento" value="${usuario.getFechaNacimiento()}" id="inputFecha_nacimiento" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-calendar-day text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="direccion" name="direccion" value="${usuario.getDireccion()}" id="inputDireccion" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-street-view text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="telefono" name="telefono" value="${usuario.getTelefonoTrabajo()}" id="inputTelefono" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-phone-alt text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="celular" name="celular" value="${usuario.getCelular()}" id="inputCelular" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-mobile-alt text-primary"></i></span>
                                </div>
                                <div class="input-group mb-3">
                                    <input type="Password" name="contrasena" value="1234" autocomplete="current-password" id="inputPassword" class="form-control">
                                    <span class="input-group-text"><i
                                            class="fas fa-key text-primary"></i></span>
                                </div>

                                <br>
                                <div class="d-flex justify-content-center align-items-center "><button
                                        class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Actualizar</button></div>
                            </form>
                            <div class="card-header d-flex justify-content-center align-items-center mb-4">
                                <img id="profile-img" class="profile-img-card"
                                     src="/aerolinea/resources/images/logo.png" alt="Logo" width="200" height="200"
                                     class="d-inline-block align-text-top rounded " />
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>