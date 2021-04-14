
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>




<t:admin>
    <jsp:attribute name="title">
        Gestión de aviones
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/aviones.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
            <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje" ></div>
            <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje" style="color: #046704e8; ">Participante agregado correctamente</div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
             tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Editar avión # <span id="id-modal"></span></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Tipo</span>
                            <input type="text" class="form-control" aria-describedby="basic-addon1" 
                                   name="tipo" id="tipo-modal">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Capacidad</span>
                            <input type="text" class="form-control" aria-describedby="basic-addon1" 
                                   name="capacidad" id="capacidad-modal">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Año</span>
                            <input type="text" class="form-control" aria-describedby="basic-addon1"
                                   name="anio" id="anio-modal">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Marca</span>
                            <input type="text" class="form-control" aria-describedby="basic-addon1"
                                   name="marca" id="marca-modal">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Asientos_x_Fila</span>
                            <input type="text" class="form-control" aria-describedby="basic-addon1"
                                   name="asientosFila" id="asientosFila-modal">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Cantidad de filas</span>
                            <input type="text" class="form-control" aria-describedby="basic-addon1"
                                   name="contidadFilas" id="cantidadFilas-modal">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="cerrar-modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" onclick="actualizarAvion()">Guardar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row my-3">
            <div class="col-sm-6">
                <h1 class="display-6 fw-normal text-negro"><i class="fas fa-plane text-primary"></i>&nbsp;Gestión de aviones</h1>
            </div>
        </div>
            <div class="row  border-bottom">
                <div class="col-6 ">
                    <div class="card">
                        <div class="card-header titulo"><i class="fas fa-plus"></i> &nbsp;Agregar avión</div>
                        <div class="card-body">
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon1">Tipo: </span>
                                <input type="text" class="form-control" placeholder="777" id="tipo" name="tipo"
                                       aria-describedby="basic-addon1">
                            </div>

                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">Capacidad:</span>
                                <input type="text" class="form-control" placeholder="150" id="capacidad" name="capacidad"
                                       aria-describedby="basic-addon2">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">Año:</span>
                                <input type="text" class="form-control" placeholder="2018" id="anio" name="anio"
                                       aria-describedby="basic-addon2">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">Marca:</span>
                                <input type="text" class="form-control" placeholder="Boeing" id="marca" name="marca"
                                       aria-describedby="basic-addon2">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">Asientos fila</span>
                                <input type="text" class="form-control" placeholder="5" id="asientosFila" name="asientosFila"
                                       aria-describedby="basic-addon2">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon2">Cantidad de filas</span>
                                <input type="text" class="form-control" placeholder="5" id="cantidadFilas" name="cantidadFilas" 
                                       aria-describedby="basic-addon2">
                            </div>
                        </div>
                        <div class="d-flex justify-content-center pb-4">
                            <button class="btn btn-primary" onclick="insertarAvion()"><i class="fas fa-plus"></i> &nbsp; Agregar</button>
                        </div>
                    </div>
                </div>
            </div>
        <div class="row mt-3">
            <div class="col-12">

                <div class="card">
                    <!-- /.card-header -->
                    <div class=" card-header titulo mb-2">
                        <span><i class="fas fa-bars"></i> &nbsp;Listado de aviones</span>
                    </div>
                    <!-- /.card-body -->
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">id</th>
                                    <th scope="col">tipo</th>
                                    <th scope="col">capacidad</th>
                                    <th scope="col">Año</th>
                                    <th scope="col">Marca</th>
                                    <th scope="col">Asientos Fila</th>
                                    <th scope="col">Asientos Columna</th>
                                    <th scope="col" style="width: 300px;">Acciones</th>
                                </tr>
                            </thead>
                            <tbody id="tabla-aviones">

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>
    </jsp:body>



</t:admin>