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
        
        
        <div class="background-diagonal-container">
            <div class="background-diagonal"></div>
        </div>

        <div class="container mt-5 p-0" >
            <div class="row mb-2">
                <span class="display-4 text-white">Registro</span>
            </div>
            <div class="row mb-5" style="background-color: #fff; padding: 4rem 2rem 6rem 2rem; border-radius: 10px;  box-shadow: 0px 0px 7px 0.2px #626262;">
                <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
                    <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje" ></div>
                    <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje" style="color: #046704e8; ">Participante agregado correctamente</div>
                </div>

                <div class="col-sm-12 col-md-6 float-sm-none d-flex justify-content-center align-items-center ">
                    <div class="container-fluid p-0">
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-4">
                                    <input type="id" name="id" id="id" value="" class="form-control"
                                           placeholder="Usuario id" autofocus required>
                                    <span class="input-group-text"><i
                                            class="fas fa-user text-primary"></i></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="input-group mb-4">
                                    <input type="nombre" name="nombre" value="" id="nombre" class="form-control"
                                           placeholder="Nombre" required>
                                    <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-4">
                                    <input type="apellido" name="apellido" value="" id="apellido" class="form-control"
                                           placeholder="Apellidos" required>
                                    <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="input-group mb-4">
                                    <input type="gmail" name="correo" value="" id="correo" class="form-control"
                                           placeholder="Correo" required>
                                    <span class="input-group-text"><i class="fas fa-envelope text-primary"></i></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-4">
                                    <input type="telefono" name="telefono" value="" id="telefonoTrabajo" class="form-control"
                                           placeholder="Telefono" required>
                                    <span class="input-group-text"><i class="fas fa-phone-alt text-primary"></i></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="input-group mb-4">
                                    <input type="celular" name="celular" value="" id="celular" class="form-control"
                                           placeholder="Celular" required>
                                    <span class="input-group-text"><i class="fas fa-mobile-alt text-primary"></i></span>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-4">
                                    <input type="Date" name="fecha_nacimiento" value="" id="fecha_nacimiento" class="form-control"
                                           placeholder="Fecha_nacimiento" required>
                                    <span class="input-group-text"><i class="fas fa-calendar-day text-primary"></i></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="input-group mb-3">
                                    <input type="password" name="contrasena" value="1234" id="contrasena" class="form-control"
                                           placeholder="Contraseña" required>
                                    <span class="input-group-text"><i
                                            class="fas fa-key text-primary"></i></span>
                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-4">
                                    <textarea type="direccion" name="direccion" value="" id="direccion" class="form-control"
                                              placeholder="Direccion" rows="4" > Dirección</textarea>
                                    <span class="input-group-text"><i class="fas fa-street-view text-primary"></i></span>
                                </div>
                            </div>

                        </div>

                        <div class="d-flex justify-content-center mb-3">
                            <button class="btn btn-outline-primary btn-lg w-100"  onclick="insertarUsuario()">Registrarse</button>
                        </div>
                    </div>

                </div>
                <div class="col-sm-12 col-md-6 d-flex justify-content-end">
                    <div class="d-flex justify-content-center align-items-center">
                        <img src="/aerolinea/resources/images/utiles/personalnfo.png" alt="" class="img-responsive"
                             style="max-width: 100%;">
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>



</t:template>