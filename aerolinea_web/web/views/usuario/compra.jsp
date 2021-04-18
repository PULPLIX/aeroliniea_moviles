<%-- Document : registro Created on : Mar 26, 2021, 5:42:16 PM Author : david --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:template>
    <jsp:attribute name="title">
        Compra tiquetes
    </jsp:attribute>

    <jsp:attribute name="css">
        <link href="/aerolinea/resources/css/loader.css" rel="stylesheet">
        </jsp:attribute>

        <jsp:attribute name="scripts">
            <script src="/aerolinea/resources/js/ticketesSocket.js"></script>
            <script src="/aerolinea/resources/js/compra.js"></script>
        </jsp:attribute>

        <jsp:body>
            <div class="d-spinner-container" id="loader" style="display: none;">
                <div>
                    <svg width='150px' height='179px' version='1.1' xmlns='http://www.w3.org/2000/svg'>
                        <path class='d-spinner d-spinner__four' d='M144.421372,121.923755 C143.963266,123.384111 143.471366,124.821563 142.945674,126.236112 C138.856723,137.238783 133.098899,146.60351 125.672029,154.330576 C118.245158,162.057643 109.358082,167.978838 99.0105324,172.094341 C89.2149248,175.990321 78.4098994,178.04219 66.5951642,178.25 L0,178.25 L144.421372,121.923755 L144.421372,121.923755 Z'/>
                        <path class='d-spinner d-spinner__three' d='M149.033408,92.6053108 C148.756405,103.232477 147.219069,113.005232 144.421372,121.923755 L0,178.25 L139.531816,44.0158418 C140.776016,46.5834381 141.913968,49.2553065 142.945674,52.0314515 C146.681818,62.0847774 148.711047,73.2598899 149.033408,85.5570717 L149.033408,92.6053108 L149.033408,92.6053108 Z'/>
                        <path class='d-spinner d-spinner__two' d='M80.3248924,1.15770478 C86.9155266,2.16812827 93.1440524,3.83996395 99.0105324,6.17322306 C109.358082,10.2887257 118.245158,16.2099212 125.672029,23.9369874 C131.224984,29.7143944 135.844889,36.4073068 139.531816,44.0158418 L0,178.25 L80.3248924,1.15770478 L80.3248924,1.15770478 Z'/>
                        <path class='d-spinner d-spinner__one' d='M32.2942065,0 L64.5884131,0 C70.0451992,0 75.290683,0.385899921 80.3248924,1.15770478 L0,178.25 L0,0 L32.2942065,0 L32.2942065,0 Z'/>
                    </svg>
                </div>
                <span class="text-loader" id="text-loader">Procesando compra</span>
            </div>

            <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
                <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje"></div>
                <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje"
                     style="color: #046704e8; "></div>
            </div>
            <div class="container">
                <div class="card" style="margin: 2rem 7rem 3rem 7rem;">
                    <div class="card-header titulo-azul d-flex justify-content-center align-items-center">
                        <i class="fas fa-home "></i>&nbsp;
                        <span id="origen-title"></span>&nbsp;&nbsp;
                        <i class="fas fa-arrow-right texto-verde "></i>&nbsp;&nbsp;
                        <i  class="fas fa-suitcase-rolling "></i>&nbsp;
                        <span id="destino-title"></span>&nbsp; 
                        <i class="texto-verde">|</i> &nbsp;&nbsp;
                        <i class="far fa-calendar"></i> &nbsp; 
                        <span id="fecha-title"></span>
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
                                            <span class="fw-bold ">Columnas: </span>&nbsp;&nbsp;
                                            <span class="text-primary " id="columns-card"> </span>

                                        </div>
                                        <div class="mb-3">
                                            <span class="fw-bold ">Filas:</span>&nbsp;
                                            <span class="text-primary " id="rows-card"> </span>

                                        </div>
                                        <div class="mb-3">
                                            <span class="fw-bold ">Capacidad: </span>&nbsp;
                                            <span class="text-primary " id="capacity-card"> </span>
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
                        <button type="button" class="btn btn-primary" onclick="getcantAsientos()">
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
                                                    <span id="cantidadTiquetes-modal"></span>
                                                </div>
                                            </div>
                                            <div class="col ms-out  justify-content-center">
                                                <div class="d-flex my-2 d-flex align-items-center">
                                                    <i class="fas fa-tags text-danger"></i>
                                                    <span class="fw-bold mx-2">Descuento:</span>
                                                    <span id="descuento-modal"></span>
                                                </div>
                                                <div class="d-flex my-3 d-flex align-items-center">
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
                                                Tipo pago:
                                            </label>
                                            <select class="form-select" id="formaPago-modal">
                                                <option value="Efectivo">Efectivo </option>
                                                <option value="Tarjeta">Tarjeta </option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" onclick="comprar()">Comprar</button>
                        </div>
                    </div>
                </div>
            </div>






        </jsp:body>



    </t:template>