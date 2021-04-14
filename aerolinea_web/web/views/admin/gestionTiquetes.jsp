
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:admin>
    <jsp:attribute name="title">
        Gestión de tiquetes 
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/twbsPagination.js"></script>
        <script src="/aerolinea/resources/js/tiquetes.js"></script>

    </jsp:attribute>
    <jsp:attribute name="user_id">
        ${usu.getId()}
    </jsp:attribute>
    <jsp:body>
        <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
            <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje" ></div>
            <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje" style="color: #046704e8; ">Participante agregado correctamente</div>
        </div>

        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
             tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="card-body d-flex justify-content-center">
                            <div class=" w-100">
                                <div class="input-group mb-4">
                                    <input type="id" name="id" id="id-modal" class="form-control" disabled="true">
                                    <span class="input-group-text"><i
                                            class="fas fa-user text-primary"></i></span>
                                </div>

                                <div class="input-group mb-4">
                                    <input type="nombre" name="nombre" id="nombre-modal" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="apellido" name="apellido" id="apellidos-modal" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-id-card text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="gmail" name="correo" id="correo-modal" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-envelope text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="fecha_nacimiento" name="fecha_nacimiento" id="fechaNacimiento-modal" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-calendar-day text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="direccion" name="direccion" id="direccion-modal" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-street-view text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="telefono" name="telefono" id="telefonoTrabajo-modal" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-phone-alt text-primary"></i></span>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="celular" name="celular" id="celular-modal" class="form-control" disabled="true">
                                    <span class="input-group-text"><i class="fas fa-mobile-alt text-primary"></i></span>
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
                <h1 class="display-6 fw-normal text-negro"><i class="fas fa-ticket-alt text-primary"></i> &nbsp;Gestión de tiquetes </h1>
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
                        <table class="table table-hover order-table" id='paginacion'>
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
                            <tbody id="tabla-tiquetes">

                            </tbody>
                        </table>
                        <div id="pager">
                            <ul id="pagination" class="pagination-sm"></ul>
                        </div>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>

    </jsp:body>

</t:admin>





