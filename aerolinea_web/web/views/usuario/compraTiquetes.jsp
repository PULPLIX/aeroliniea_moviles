<%@page import="logic.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:template>
    <jsp:attribute name="title">
        Compra tiquetes
    </jsp:attribute>

    <jsp:attribute name="css">

    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/usuarios.js"></script>
        <script src="/aerolinea/resources/js/vuelos.js"></script>
        <script src="/aerolinea/resources/js/global.js"></script>

    </jsp:attribute>
    <jsp:body>
        <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
            <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje"></div>
            <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje"
                 style="color: #046704e8; "></div>
        </div>
        <div class="banner-compra">
            <div class="titulo-compra">
                <div class="display-6">
                    David's Airlines
                </div>
            </div>
            <div class="icon-banner">
                <div class="d-flex justify-content-center">
                    <i class="fas fa-plane mb-1 "></i>
                </div>
                <span class=" my-2">Vuelos</span>
            </div>
            <div class="card">

                <div class="card-body">
                    <div class="container mt-1">
                        <div class="row d-flex justify-content-start align-items-center px-2">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="modalidad" value="1">
                                <label class="form-check-label" for="flexRadioDefault1">
                                    Solo ida
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="modalidad" value="2"
                                       checked>
                                <label class="form-check-label" for="flexRadioDefault2">
                                    Ida y vuelta
                                </label>
                            </div>
                            <div class="form-check" style=" width: 20% !important;">
                                <input class="form-check-input" type="checkbox" name="descuento" id="descuento" value="TRUE">
                                <label class="form-check-label" for="flexRadioDefault3">
                                    Con descuento &nbsp;<i class="fas fa-tags text-warning"></i>
                                </label>
                            </div>
                        </div>

                        <div class="row d-flex d-flex justify-content-start align-items-center mt-4">
                            <div class="input-search">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="add-icon-origen">
                                        <i class="fas fa-home text-primary"></i></span>
                                    <select class="form-select" id="origen" aria-label="origen"
                                            aria-describedby="add-icon-origen">
                                        <option value="">Origen</option>
                                    </select>
                                </div>
                            </div>
                            <div class="input-search">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="add-icon-destino">
                                        <i class="fas fa-suitcase-rolling text-primary"></i></span>
                                    <select class="form-select" id="destino" aria-label="destino"
                                            aria-describedby="add-icon-Destino">
                                        <option value="">Destino</option>
                                    </select>
                                </div>
                            </div>
                            <div class="input-search">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="add-icon-fechas">
                                        <i class="far fa-calendar text-primary"></i></span>
                                    <input type="text" class="form-control" name="daterange" id="fechas"
                                           placeholder="Fechas" aria-label="Fechas"
                                           aria-describedby="add-icon-fechas">
                                </div>
                            </div>
                            <div class="btn-search mb-3">
                                <div class="btn btn-primary btn-lg" onclick="buscarVuelos()"><i
                                        class="fas fa-search"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container" id="container-table">
            <div class="row mt-5">

                <div class="col-12 " style="padding: 0px 0px 0px 1.5rem;" >
                    <div class="card">
                        <div class="card-body d-flex justify-content-between"
                             style="font-size: 22px;margin-left: 1.5rem;font-weight: 500;">
                            <div class="">
                                <i class="far fa-calendar-alt text-celeste"></i>
                                &nbsp;Resultado de la búsqueda
                            </div>


                        </div>
                    </div>
                </div>


            </div>
            <div id="resultado-busqueda">

            </div>

        </div>


    </jsp:body>
</t:template>