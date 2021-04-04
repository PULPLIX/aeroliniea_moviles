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
                        <script src="/aerolinea/resources/js/tiquetes.js"></script>

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
                            <div class="card ">
                                <div class="card-body">
                                    <div class="container mt-1">
                                        <div class="row d-flex justify-content-start align-items-center px-2">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="flexRadioDefault"
                                                    id="flexRadioDefault1">
                                                <label class="form-check-label" for="flexRadioDefault1">
                                                    Solo ida
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="flexRadioDefault"
                                                    id="flexRadioDefault2" checked>
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
                                                <div class="btn btn-primary btn-lg"><i class="fas fa-search"></i></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="container" id="container-table">
                            <div class="row mt-5">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-body"
                                            style="font-size: 22px;margin-left: 1.5rem;font-weight: 500;">
                                            <i class="far fa-calendar-alt text-celeste"></i>
                                            &nbsp;Resultado de la búsqueda
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div class="row  tabla-vuelos">
                                <div class="col-9 ">
                                    <div class="card card-info">
                                        <div class="card-body">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <div class="col-2"><img
                                                            src="/aerolinea/resources/images/logoBanner.png" alt=""
                                                            class="w-100">
                                                    </div>
                                                    <div
                                                        class="col-8 d-flex justify-content-between align-items-center">
                                                        <div class="texto-azul d-flex ">
                                                            Ciudad México - México
                                                            <div class="separate-city">
                                                            </div>
                                                            EWR
                                                        </div>
                                                    </div>
                                                    <div class="col-2 d-flex justify-content-end text-celeste">
                                                        16 abr - Solo ida
                                                    </div>
                                                </div>
                                                <div class="row pt-3 d-flex justify-content-end">
                                                    <div class="col-6 d-flex justify-content-start align-items-center">
                                                        <span class="horario-tabla">Lunes <i
                                                                class="fas fa-long-arrow-alt-right"></i> 9:00 </span>
                                                        <span class="duracion-tabla"><i
                                                                class="fas fa-history "></i>&nbsp;5h
                                                            12min </span>
                                                    </div>
                                                    <div
                                                        class="col-4 d-flex justify-content-end align-items-center text-muted avion-info">
                                                        <span>
                                                            <i class="fas fa-plane text-celeste"></i>
                                                        </span>
                                                        <span>
                                                            <i class="fas fa-wifi"></i>
                                                        </span>
                                                        <span>
                                                            <i class="fas fa-plug"></i>
                                                        </span>
                                                        <span>
                                                            <i class="fas fa-mug-hot"></i>
                                                        </span>
                                                        <span>
                                                            <i class="fas fa-desktop"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3 p-0 ">
                                    <div class="card w-100  d-flex justify-content-center align-items-center">
                                        <div class="card-body d-flex align-items-center flex-column ">
                                            <div class="precio">$ 269</div>
                                            <div class="precio-info">Precio por tiquete c/u</div>
                                            <div> <button class="btn btn-outline-celeste fw-bold">Ver
                                                    asientos</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </jsp:body>
                </t:template>