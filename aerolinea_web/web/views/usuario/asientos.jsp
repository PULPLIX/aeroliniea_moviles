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
                    <i class="fas fa-home "></i>&nbsp;Ciudad de México&nbsp;&nbsp;<i class="fas fa-arrow-right texto-verde "></i>&nbsp;&nbsp;<i class="fas fa-suitcase-rolling "></i>&nbsp;San José CR&nbsp; <i class="texto-verde">|</i> &nbsp;&nbsp;<i class="far fa-calendar"></i> &nbsp; abr 19, 2021 </span>
                </div>
                <div class="card-body ">
                    <div class="row py-5">
                        <div class="col-5 py-3" style="padding-left: 6rem;" >
                            <div class="row ">
                                <div class="d-flex justify-content-center align-items-center ">
                                    <div class="avion-ref">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col d-flex flex-column align-items-start " style="border-right: 1px solid #c7c7c7;">
                                    <div class="d-flex mb-3 mt-4">
                                        <div class="mx-1">
                                            <a href="#" class="asiento ocupado  mx-1" ><i class="fas fa-times-circle"></i></a>
                                        </div>
                                        <span class="fw-bold"> Ocupado</span>

                                    </div>
                                    <div class="d-flex  align-items-center mb-3">
                                        <div class="mx-1">
                                            <a href="#" class="btn btn-azul-avion  mx-1"><i class="fas fa-user  "></i></a>
                                        </div>

                                        <span class="fw-bold"> Disponible</span>
                                    </div>
                                    <div class="d-flex align-items-center mb-4">
                                        <div class="mx-2">
                                            <a href="#" class="btn bg-select"><i class="fas fa-check-circle"></i></a>
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
                                        &nbsp;<span class="text-primary ">  20</span>

                                    </div>
                                    <div class="mb-3">
                                        <span class="fw-bold ">Capacidad: </span>
                                        &nbsp;<span class="text-primary ">  120</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-7 d-flex justify-content-center">
                            <div id="asientos-container" class="asientos-container" >
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>


        <!-- Modal -->






    </jsp:body>



</t:template>

