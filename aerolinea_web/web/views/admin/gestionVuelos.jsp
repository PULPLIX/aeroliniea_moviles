<%@page import="logic.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="logic.Avion"%>
<%@page import="logic.Avion"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>




<t:admin>
    <jsp:attribute name="title">
        Gestión de vuelos  
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/vuelos.js"></script>
    </jsp:attribute>
    <jsp:attribute name="user_id">
        ${usu.getId()}
    </jsp:attribute>
    <jsp:body>
        <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
            <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje" ></div>
            <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje" style="color: #046704e8; ">Participante agregado correctamente</div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
             tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Editar vuelo # <span id="id-modal"></span></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="card-body d-flex justify-content-center">
                            <div class="w-100">
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Modalidad: </span>
                                    <select class="form-select" id="modalidad-modal"  class="form-control">
                                        <option value="">Seleccione</option>
                                        <option value="1">Solo ida</option>
                                        <option value="2">Ida y retorno</option>
                                    </select>
                                    <span class="input-group-text" id="basic-addon1"><i class="far fa-clock text-primary"></i>&nbsp;Duracion: </span>
                                    <input type="text" id="duracion-modal" class="form-control" >
                                </div>
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Fecha: </span>
                                    <input type="date" class="form-control datetime-local" id="fecha-modal" name="fecha" value="">
                                    <span class="input-group-text" id="basic-addon1">Avión: </span>
                                    <select class="form-select" id="avionId-modal"  class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                </div>
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Ruta: </span>
                                    <select class="form-select" id="rutaId-modal"  class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                </div>
                            </div>    
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="cerrar-modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" onclick="actualizarVuelo()">Guardar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row my-3">
            <div class="col-sm-6">
                <h1 class="display-6 fw-normal text-negro"><i class="fas fa-plane-departure text-primary"></i>&nbsp;Gestión de vuelos </h1>
            </div>
        </div>
        <div class="row  border-bottom">
            <div class="col-6 ">
                <div class="card">
                    <div class="card-header titulo"><i class="fas fa-plus"></i> &nbsp;Agregar vuelo</div>
                    <div class="card-body d-flex justify-content-center">
                        <div class="card-body d-flex justify-content-center">
                            <div class=" w-100">
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Modalidad: </span>
                                    <select class="form-select" id="modalidad"  class="form-control">
                                        <option value="">Seleccione</option>
                                        <option value="1">Solo ida</option>
                                        <option value="2">Ida y retorno</option>
                                    </select>
                                    <span class="input-group-text" id="basic-addon1"><i class="far fa-clock text-primary"></i>&nbsp;Duracion: </span>
                                    <input type="text" id="duracion" class="form-control" >
                                </div>
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Fecha: </span>
                                    <input type="date" class="form-control datetime-local" id="fecha" name="fecha" value="" required>
                                    <span class="input-group-text" id="basic-addon1">Avión: </span>
                                    <select class="form-select" id="avionId"  class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                </div>
                                <div class="input-group mb-3 d-flex ">
                                    <span class="input-group-text" id="basic-addon1">Ruta: </span>
                                    <select class="form-select" id="rutaId"  class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                </div>
                            </div>

                        </div>

                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center py-1">
                            <button class="btn btn-primary" onclick="insertarVuelo()"><i class="fas fa-plus"></i> &nbsp; Agregar</button>
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
                        <span><i class="fas fa-bars"></i> &nbsp;Listado de vuelos</span>
                    </div>
                    <!-- /.card-body -->
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
                            <tbody id="tabla-vuelos">

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>
    </jsp:body>



</t:admin>