<%-- Document : registro Created on : Mar 26, 2021, 5:42:16 PM Author : david --%>

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
        <script src="/aerolinea/resources/js/global.js"></script>
        <script src="/aerolinea/resources/js/asientos.js"></script>
    </jsp:attribute>

    <jsp:body>



        <div class="container">
            <div class="card" style="margin: 2rem 7rem 3rem 7rem;">
                <div class="card-header titulo-azul d-flex justify-content-center align-items-center">
                    <i class="fas fa-home "></i>&nbsp;Ciudad de México&nbsp;&nbsp;<i
                        class="fas fa-arrow-right texto-verde "></i>&nbsp;&nbsp;<i
                        class="fas fa-suitcase-rolling "></i>&nbsp;San José CR&nbsp; <i
                        class="texto-verde">|</i> &nbsp;&nbsp;<i class="far fa-calendar"></i> &nbsp; abr 19,
                    2021 </span>
                </div>
                <div class="card-body ">
                    <div class="row py-5">
                        <div class="col-5 py-3" style="padding-left: 6rem;">
                            <div class="row ">
                                <div class="d-flex justify-content-center align-items-center ">
                                    <div class="avion-ref">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col d-flex flex-column align-items-start "
                                     style="border-right: 1px solid #c7c7c7;">
                                    <div class="d-flex mb-3 mt-4">
                                        <div class="mx-1">
                                            <a href="#" class="asiento ocupado  mx-1"><i
                                                    class="fas fa-times-circle"></i></a>
                                        </div>
                                        <span class="fw-bold"> Ocupado</span>
                                    </div>
                                    <div class="d-flex  align-items-center mb-3">
                                        <div class="mx-1">
                                            <a href="#" class="btn btn-azul-avion  mx-1"><i
                                                    class="fas fa-user  "></i></a>
                                        </div>

                                        <span class="fw-bold"> Disponible</span>
                                    </div>
                                    <div class="d-flex align-items-center mb-4">
                                        <div class="mx-2">
                                            <a href="#" class="btn bg-select"><i
                                                    class="fas fa-check-circle"></i></a>
                                        </div>
                                        <span class="fw-bold"> Seleccionado</span>
                                    </div>
                                </div>
                                <div class="col d-flex flex-column justify-content-center">
                                    <div class="d-flex mb-3 mt-2">
                                        <span class="fw-bold ">Columnas: </span>
                                        &nbsp;&nbsp;<span class="text-primary "> 6</span>

                                    </div>
                                    <div class="mb-3">
                                        <span class="fw-bold ">Filas:</span>
                                        &nbsp;<span class="text-primary "> 20</span>

                                    </div>
                                    <div class="mb-3">
                                        <span class="fw-bold ">Capacidad: </span>
                                        &nbsp;<span class="text-primary "> 120</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-7 d-flex justify-content-center">
                            <div id="asientos-container" class="asientos-container">
                            </div>
                        </div>

                    </div>
                </div>
                <div class="card-footer d-flex justify-content-center align-items-center">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#comprarModal">
                        Continuar compra
                    </button>
                </div>

            </div>
        </div>



        <!-- Modal -->
        <div class="modal fade" id="comprarModal" data-bs-backdrop="static" data-bs-keyboard="false"
             tabindex="-1" aria-labelledby="comprarModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered  modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="comprarModalLabel">Finalizar compra</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row card-compra ">
                                <div class="col-md-3 ">
                                    <div class="container-image-modal">
                                        <div class="image-modal-avion">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-9 ms-auto  border-start">
                                    <div class="row mx-2">
                                        <div class="col-md-6 d-flex flex-column justify-content-center">
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-home text-primary"></i>
                                                <span class="fw-bold mx-2">Origen:</span>
                                                <span id="origen-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="far fa-calendar text-primary"></i>
                                                <spa class="fw-bold mx-2">Fecha:</spa>
                                                <span id="fecha-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-plane-departure text-primary"></i>
                                                <span class="fw-bold mx-2">Modalidad</span>
                                                <span id="modalidad-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-user-friends text-primary"></i>
                                                <span class="fw-bold mx-2">Asientos: </span>
                                                <span id="asientos-modal"></span>
                                            </div>
                                        </div>
                                        <div class="col-md-6 d-flex flex-column ">
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-suitcase-rolling text-primary"></i>
                                                <span class="fw-bold mx-2">Destino: </span>
                                                <span id="destino-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-history text-primary"></i>
                                                <span class="fw-bold mx-2">Duracion: </span>
                                                <span id="duracion-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-plane text-primary"></i>
                                                <span class="fw-bold mx-2">Avión: </span>
                                                <span id="avion-modal"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row my-3 card-compra ">
                                <div class="col-md-3">
                                    <div class="container-image-modal">
                                        <div class="image-modal-user"></div>
                                    </div>
                                </div>
                                <div class="col-md-9 ms-out border-start">
                                    <div class="row mx-2">
                                        <div class="col-md-6 d-flex flex-column justify-content-center">
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-user-circle text-primary"></i>
                                                <span class="fw-bold mx-2">Nombre:</span>
                                                <span id="nombre-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-at text-primary"></i>
                                                <spa class="fw-bold mx-2">Correo:</spa>
                                                <span id="correo-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-phone-alt text-primary"></i>
                                                <span class="fw-bold mx-2">Celular:</span>
                                                <span id="celular-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-phone-alt text-primary"></i>
                                                <span class="fw-bold mx-2">Teléfono: </span>
                                                <span id="telefono-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-map-marker-alt text-primary"></i>
                                                <span class="fw-bold mx-2">Direccion: </span>
                                                <span id="direccion-modal"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row my-3">
                                <div class="col-8">
                                    <div class="row">
                                        <div class="col ms-out d-flex flex-column justify-content-center">
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-dollar-sign text-success"></i>
                                                <span class="fw-bold mx-2">Valor por tiquete:</span>
                                                <span id="precio-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-ticket-alt text-warning"></i>
                                                <span class="fw-bold mx-2">Cantidad de tiquetes:</span>
                                                <span id="cantidadTiqutes-modal"></span>
                                            </div>
                                        </div>
                                        <div class="col ms-out  justify-content-center">
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-tags text-danger"></i>
                                                <span class="fw-bold mx-2">Descuento:</span>
                                                <span id="descuento-modal"></span>
                                            </div>
                                            <div class="d-flex my-2 d-flex align-items-center">
                                                <i class="fas fa-plane-departure text-primary"></i>
                                                <span class="fw-bold mx-2">Ida y vuelta:</span>
                                                <span id="modalidad2-modal"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-4 border-start">
                                    <div class="d-flex my-2 d-flex align-items-center">
                                        <span class="fw-bold mx-2">Total:</span>
                                        <span id="total-modal"></span>
                                    </div>
                                    <div class="input-group mb-3">
                                        <label class="input-group-text" for="inputGroupSelect01">
                                            Tipo pago:</label>
                                        <select class="form-select" id="inputGroupSelect01">
                                            <option value="1">Efectivo </option>
                                            <option value="2">Tarjeta </option>
                                            <option value="3">Paypal </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Comprar</button>
                    </div>
                </div>
            </div>
        </div>






    </jsp:body>



</t:template>