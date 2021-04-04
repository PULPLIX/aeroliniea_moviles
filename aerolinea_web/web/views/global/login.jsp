<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/global.js"></script>
        <script src="/aerolinea/resources/js/usuarios.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row my-5">
                <div class="col-sm-12 col-md-7 d-flex justify-content-end">
                    <div class="d-flex justify-content-center align-items-center">
                        <img src="/aerolinea/resources/images/utiles/waiting.png" alt="" class="img-responsive"
                             style="max-width: 100%;">
                    </div>
                </div>
                <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
                    <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje" ></div>
                    <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje" style="color: #046704e8; ">Participante agregado correctamente</div>
                </div>
                <div class="col-sm-12 col-md-3 float-sm-none d-flex justify-content-center align-items-center ">
                    <div>
                        <div class="d-flex justify-content-center my-3 ">
                            <img src="/aerolinea/resources/images/utiles/avatar2.png" alt="" class="img-fluid"
                                 style="max-width: 50%;">
                        </div>
                        <div class="input-group mb-3">
                            <input type="text" name="id" id="idLogin" class="form-control"
                                   aria-label="Sizing example input"
                                   aria-describedby="inputGroup-sizing-default" value="12">
                            <span class="input-group-text" id="inputGroup-sizing-default"><i
                                    class="fas fa-user texto-azul"></i></span>

                        </div>
                        <div class="input-group mb-3">
                            <input type="password" name="contrasena" id="contrasena" class="form-control"
                                   aria-label="Sizing example input"
                                   aria-describedby="inputGroup-sizing-default" value="1234">
                            <span class="input-group-text" id="inputGroup-sizing-default"><i
                                    class="fas fa-key texto-azul"></i></span>
                        </div>
                        <div class="d-flex justify-content-center mb-3">
                            <button class="btn btn-outline-primary btn-lg w-100" onclick="login()" >Ingresar</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </jsp:body>



</t:template>