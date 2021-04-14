<%-- 
    Document   : registro
    Created on : Mar 26, 2021, 5:42:16 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 


<t:template>
    <jsp:attribute name="title">
        Perfil
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/usuarios.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row my-5 d-flex justify-content-center">
                <div class="col-md-8">
                    <div class="card ">
                        <div class="card-body d-flex justify-content-center align-items-center ">
                            <form class="form-signin" method="POST" action="/aerolinea/views/usuario/perfil">
                                <div class="input-group mb-4">
                                    <input type="id" name="id" id="idPerfil" class="form-control" disabled="true">
                                    <span class="input-group-text"><i
                                            class="fas fa-user text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="nombre" name="nombre" id="nombrePerfil" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="apellido" name="apellido" id="apellidosPerfil" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="gmail" name="correo" id="correoPerfil" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-envelope text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="fecha_nacimiento" name="fecha_nacimiento" id="fechaNacimientoPerfil" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-calendar-day text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="direccion" name="direccion" id="direccionPerfil" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-street-view text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="telefono" name="telefono" id="telefonoTrabajoPerfil" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-phone-alt text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="celular" name="celular" id="celularPerfil" class="form-control">
                                    <span class="input-group-text"><i class="fas fa-mobile-alt text-primary"></i></span>
                                </div>
                                <div class="input-group mb-3">
                                    <input type="Password" name="contrasena" autocomplete="current-password" id="contrasenaPerfil" class="form-control">
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