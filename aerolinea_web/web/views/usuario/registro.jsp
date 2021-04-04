<%-- 
    Document   : registro
    Created on : Mar 26, 2021, 5:42:16 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
        Registro
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/global.js"></script>
        <script src="/aerolinea/resources/js/usuarios.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
            <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje" ></div>
            <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje" style="color: #046704e8; ">Participante agregado correctamente</div>
        </div>
        <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
            <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje" ></div>
            <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje" style="color: #046704e8; ">Participante agregado correctamente</div>
        </div>
        <div class="row  border-bottom d-flex justify-content-center">
            <div class="col-6 ">
                <div class="card">
                    <div class="card-header titulo d-flex justify-content-center">&nbsp;Registrase</div>
                    <div class="card-body d-flex justify-content-center">
                        <div class=" w-50">
                            <div class="input-group mb-4">
                                <input type="id" name="id" id="id" value="" class="form-control"
                                       placeholder="Usuario id" autofocus required>
                                <span class="input-group-text"><i
                                        class="fas fa-user text-primary"></i></span>
                            </div>
                            <div class="input-group mb-4">
                                <input type="nombre" name="nombre" value="" id="nombre" class="form-control"
                                       placeholder="Nombre" required>
                                <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                            </div>
                            <div class="input-group mb-4">
                                <input type="apellido" name="apellido" value="" id="apellido" class="form-control"
                                       placeholder="Apellidos" required>
                                <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                            </div>
                            <div class="input-group mb-4">
                                <input type="gmail" name="correo" value="" id="correo" class="form-control"
                                       placeholder="Correo" required>
                                <span class="input-group-text"><i class="fas fa-envelope text-primary"></i></span>
                            </div>
                            <div class="input-group mb-4">
                                <input type="Date" name="fecha_nacimiento" value="" id="fecha_nacimiento" class="form-control"
                                       placeholder="Fecha_nacimiento" required>
                                <span class="input-group-text"><i class="fas fa-calendar-day text-primary"></i></span>
                            </div>
                            <div class="input-group mb-4">
                                <input type="direccion" name="direccion" value="" id="direccion" class="form-control"
                                       placeholder="Direccion" required>
                                <span class="input-group-text"><i class="fas fa-street-view text-primary"></i></span>
                            </div>
                            <div class="input-group mb-4">
                                <input type="telefono" name="telefono" value="" id="telefonoTrabajo" class="form-control"
                                       placeholder="Telefono" required>
                                <span class="input-group-text"><i class="fas fa-phone-alt text-primary"></i></span>
                            </div>
                            <div class="input-group mb-4">
                                <input type="celular" name="celular" value="" id="celular" class="form-control"
                                       placeholder="Celular" required>
                                <span class="input-group-text"><i class="fas fa-mobile-alt text-primary"></i></span>
                            </div>
                            <div class="input-group mb-3">
                                <input type="password" name="contrasena" value="1234" id="contrasena" class="form-control"
                                       placeholder="Contraseña" required>
                                <span class="input-group-text"><i
                                        class="fas fa-key text-primary"></i></span>
                            </div>
                            <br>
                            <div class="d-flex justify-content-center align-items-center ">
                                <button class="btn btn-primary" onclick="insertarUsuario()">Registrarse</button></div>
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
    </div>
</div>
</div>
</div>
</div>
</jsp:body>



</t:template>