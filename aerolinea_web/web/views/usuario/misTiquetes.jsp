<%@page import="logic.Usuario"%>
<%@page import="java.util.List"%>



<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>

    <jsp:attribute name="title">
        Mis tiquetes 
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/global.js"></script>
        <script src="/aerolinea/resources/js/usuarios.js"></script>
    </jsp:attribute>
    <jsp:attribute name="user_id">
        ${usu.getId()}
    </jsp:attribute>
    <jsp:body>

                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
             tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Vuelo # <span id="id-modal"></span></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="card-body d-flex justify-content-center">
                            <div class="w-100">
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Modalidad: </span>
                                    <select class="form-select" id="modalidad-modal"  class="form-control" disabled="true">
                                        <option value="">Seleccione</option>
                                        <option value="1">Solo ida</option>
                                        <option value="2">Ida y retorno</option>
                                    </select>
                                    <span class="input-group-text" id="basic-addon1"><i class="far fa-clock text-primary"></i>&nbsp;Duracion: </span>
                                    <input type="text" id="duracion-modal" class="form-control" disabled="true">
                                </div>
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Fecha: </span>
                                    <input type="date" class="form-control datetime-local" id="fecha-modal" name="fecha" value="" disabled="true">
                                    <span class="input-group-text" id="basic-addon1">Avión: </span>
                                    <select class="form-select" id="avionId-modal"  class="form-control" disabled="true">
                                    </select>
                                </div>
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Ruta: </span>
                                    <select class="form-select" id="rutaId-modal"  class="form-control" disabled="true">
                                    </select>
                                </div>
                            </div>    
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="cerrar-modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row my-3">
            <div class="col-sm-6">
                <h1 class="display-6 fw-normal text-negro"><i class="fas fa-ticket-alt text-primary"></i> &nbsp;Mis tiquetes </h1>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="container mt-1">

                    <div class="col-6">
                        <div class="input-group mb-2">
                            <input id="texto" class="form-control col-md-3 light-table-filter" data-table="order-table" type="text" placeholder="Search.." aria-label="Input group example">
                            <div class="input-group-append">
                                <span class="input-group-text">Buscar</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-12">

                <div class="card">
                    <!-- /.card-header -->
                    <div class=" card-header titulo mb-2">
                        <span><i class="fas fa-bars"></i> &nbsp;Listado de tiquetes</span>
                    </div>
                    <!-- /.card-body -->
                    <div class="table-responsive">
                        <table class="table table-hover order-table">
                            <thead>
                                <tr>
                                    <th scope="col">Id vuelo<i class="fas fa-sort-down "></i></th>
                                    <th scope="col">Id tiquete<i class="fas fa-sort-down "></i></th>
                                    <th scope="col">Id usuario<i class="fas fa-sort-down "></i></th>
                                    <th scope="col">Precio<i class="far fa-dollar-sign  text-primary"></i></th>
                                    <th scope="col">Fila</th>
                                    <th scope="col">Columna</th>
                                    <th scope="col">Forma de Pago</th>

                                    <th scope="col" style="width: 300px;">Acciones</th>
                                </tr>
                            </thead>
                            <tbody id="tabla-historial">

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>
    </jsp:body>



</t:template>