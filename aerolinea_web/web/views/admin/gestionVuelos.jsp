<%@page import="logic.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="logic.Avion"%>
<%@page import="logic.Avion"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    Usuario usu = (Usuario) session.getAttribute("usuario");

%>


<t:admin>
    <jsp:attribute name="title">
        Gestión de vuelos  
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/global.js"></script>
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
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Editar vuelo # <span id="id-modal"></span></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="card-body d-flex justify-content-center">
                            <div class=" w-100">
                                <div class="input-group mb-3 w-100">
                                    <span class="input-group-text" id="basic-addon1">Origen: </span>
                                    <select class="form-select" id="ciudadOrigen-modal"  class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                    <span class="input-group-text" id="basic-addon1">Destino: </span>
                                    <select class="form-select" id="ciudadDestino-modal" class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                </div>
                                <div class="input-group mb-3 w-50">
                                    <span class="input-group-text" id="basic-addon1">Horario </span>
                                    <select class="form-select" id="horarioId-modal"  class="form-control">
                                        <option value="">Seleccione</option>

                                    </select>
                                </div>

                                <div class="d-flex">
                                    <div class="input-group w-50 mb-3 ">
                                        <span class="input-group-text">₡</span>
                                        <span class="input-group-text">0.00</span>
                                        <input type="text" id="precio-modal" class="form-control" >
                                    </div> 
                                    <div class="custom-control custom-checkbox mx-2" id="wrap-chec-video">
                                        <input type="checkbox" class="custom-control-input" id="descuento-check" onclick="mostrarDescuento(this)">
                                        <input type="hidden" id="check_descuento" name="descuento">
                                        <label class="custom-control-label" for="descuento-check">Descuento</label>
                                    </div>
                                    <div class="w-25  " id="descuento-field" style="display: none;">
                                        <input type="number" class="mb-3" min="0" step="5"  name="descuento" id="descuento-modal"   value='0' />
                                    </div>  
                                </div>    
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="cerrar-modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" onclick="actualizarRuta()">Guardar</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="row my-3">
            <div class="col-sm-6">
                <h1 class="display-6 fw-normal text-negro"><i class="fas fa-map-marked-alt text-primary"></i> &nbsp;Gestión de vuelos </h1>
            </div>
        </div>
        <div class="row  border-bottom">
            <div class="col-6 ">
                <div class="card">
                    <div class="card-header titulo"><i class="fas fa-plus"></i> &nbsp;Agregar vuelo</div>
                    <div class="card-body d-flex justify-content-center">
                        <div class="card-body d-flex justify-content-center">
                            <div class=" w-100">
                                <div class="input-group mb-3 w-100">
                                    <span class="input-group-text" id="basic-addon1">Origen: </span>
                                    <select class="form-select" id="ciudadOrigen"  class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                    <span class="input-group-text" id="basic-addon1">Destino: </span>
                                    <select class="form-select" id="ciudadDestino" class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                </div>
                                <div class="input-group mb-3 w-50">
                                    <span class="input-group-text" id="basic-addon1">Horario </span>
                                    <select class="form-select" id="horarioId"  class="form-control">
                                        <option value="">Seleccione</option>
                                    </select>
                                </div>

                                <div class="d-flex">
                                    <div class="input-group w-50 mb-3 ">
                                        <span class="input-group-text">₡</span>
                                        <span class="input-group-text">0.00</span>
                                        <input type="text" id="precio" class="form-control" >
                                    </div> 
                                    <div class="custom-control custom-checkbox mx-2" id="wrap-chec-video">
                                        <input type="checkbox" class="custom-control-input" id="descuento-check" onclick="mostrarDescuento(this)">
                                        <input type="hidden" id="check_descuento" name="descuento">
                                        <label class="custom-control-label" for="descuento-check">Descuento</label>
                                    </div>
                                    <div class="w-25  " id="descuento-field" style="display: none;">
                                        <input type="number" class="mb-3" min="0" step="5"  name="descuento" id="descuento"   value='0' />
                                    </div>  
                                </div>

                            </div>

                        </div>

                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center py-1">
                            <button class="btn btn-primary" onclick="insertarRuta()"><i class="fas fa-plus"></i> &nbsp; Agregar</button>
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
                                    <th scope="col">Ciudad origen <i class="fas fa-sort-down "></i></th>
                                    <th scope="col">Ciudad destino <i class="fas fa-sort-down "></i></th>
                                    <th scope="col">Horario <i class="far fa-calendar-minus text-primary"></i></th>
                                    <th scope="col">Precio <i class="fas fa-dollar-sign text-primary"></i></th>
                                    <th scope="col">Descuento <i class="fas fa-percent text-primary"></i></th>

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