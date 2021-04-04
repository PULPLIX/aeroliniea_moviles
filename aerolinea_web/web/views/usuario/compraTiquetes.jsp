<%-- Document : registro Created on : Mar 26, 2021, 5:42:16 PM Author : david --%>

<%@page import="logic.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:template>
    <jsp:attribute name="title">
        Compra tiquetes
    </jsp:attribute>

    <jsp:attribute name="css">
        <link rel="stylesheet" type="text/css"
              href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/usuarios.js"></script>

        <script type="text/javascript"
        src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
        <script type="text/javascript"
        src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <script src="/aerolinea/resources/js/vuelos.js"></script>
        <script src="/aerolinea/resources/js/global.js"></script>
    </jsp:attribute>
    <jsp:body>
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
                                <input class="form-check-input" type="radio" name="modalidad"
                                       value="1">
                                <label class="form-check-label" for="flexRadioDefault1">
                                    Solo ida
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="modalidad"
                                       value="2" checked >
                                <label class="form-check-label" for="flexRadioDefault2">
                                    Ida y vuelta
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
                                <div class="btn btn-primary btn-lg" onclick="buscarVuelos()"><i class="fas fa-search"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- /.card-body -->
        <div class="card">
            <div class="card-body">
                <div class="container mt-1">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">id <i class="fas fa-sort-down "></i></th>
                                    <th scope="col">Fecha <i class="fas fa-sort-down "></i></th>
                                    <th scope="col">Modalidad <i class="fas fa-sort-down "></i></th>
                                    <th scope="col">Duracion <i class="far fa-clock text-primary"></i></th>
                                    <th scope="col">Ruta <i class="fas fa-map-marked-alt text-primary"></i></th>
                                    <th scope="col">Horario <i class="far fa-calendar-minus text-primary"></i></th>
                                    <th scope="col">Avion <i class="fas fa-plane text-primary"></i></th>
                                    <th scope="col" style="width: 300px;">Acciones</th>
                                </tr>
                            </thead>
                            <tbody id="tabla-vuelos-buscados">

                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>

    </jsp:body>
</t:template>