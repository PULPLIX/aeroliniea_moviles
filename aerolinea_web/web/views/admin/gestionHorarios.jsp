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
        Gestión de horarios
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="/aerolinea/resources/js/global.js"></script>

        <script src="/aerolinea/resources/js/horarios.js"></script>

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
                        <h5 class="modal-title" id="staticBackdropLabel">Editar horario # <span id="id-modal"></span></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="card-body d-flex justify-content-center">
                            <div class=" w-100">
                                <div class="input-group mb-3 w-75">
                                    <span class="input-group-text" id="basic-addon1">Dia: </span>
                                    <select class="form-select" id="diaSemana-modal" name="dia" class="form-control">
                                        <option value="Lunes">Lunes</option>
                                        <option value="Martes">Martes</option>
                                        <option value="Miércoles">Miércoles</option>
                                        <option value="Jueves">Jueves</option>
                                        <option value="Viernes">Viernes</option>
                                        <option value="Sábado">Sábado</option>
                                        <option value="Domingo">Domingo</option>
                                    </select>
                                </div>
                                <div class="w-100  d-flex ">
                                    <input type="number" min="0" step="1"   name="horaLlegada" id="horaLlegada-modal"   value='0' disabled/>
                                    <span class=" d-flex align-items-center mx-3 fw-bold"> h</span>
                                </div>       
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="cerrar-modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" onclick="actualizarHorario()">Guardar</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="row my-3">
            <div class="col-sm-6">
                <h1 class="display-6 fw-normal text-negro">Gestión de horarios</h1>
            </div>
        </div>
        <div class="row  border-bottom">
            <div class="col-6 ">
                <div class="card">
                    <div class="card-header titulo"><i class="fas fa-plus"></i> &nbsp;Agregar horario</div>
                    <div class="card-body d-flex justify-content-center">
                        <div class=" w-50">
                            <label for="diaSemana">Día de la semana:</label>

                            <div class="input-group mb-3 w-75">
                                <span class="input-group-text" id="basic-addon1">Día: </span>
                                <select class="form-select" id="diaSemana" name="dia" class="form-control">
                                    <option value="Lunes">Lunes</option>
                                    <option value="Martes">Martes</option>
                                    <option value="Miércoles">Miércoles</option>
                                    <option value="Jueves">Jueves</option>
                                    <option value="Viernes">Viernes</option>
                                    <option value="Sábado">Sábado</option>
                                    <option value="Domingo">Domingo</option>
                                </select>
                            </div>
                            <label for="horaLlegada">Hora de llegada:</label>
                            <div class="w-100  d-flex ">
                                <input type="number" min="0" step="0"  name="horaLlegada" id="horaLlegada"   value='0' />
                                <span class=" d-flex align-items-center mx-3 fw-bold"> h</span>
                            </div>       
                        </div>

                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center py-1">
                            <button class="btn btn-primary" onclick="insertarHorario()"><i class="fas fa-plus"></i> &nbsp; Agregar</button>
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
                        <span><i class="fas fa-bars"></i> &nbsp;Listado de horarios</span>
                    </div>
                    <!-- /.card-body -->
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">id</th>
                                    <th scope="col">Dia de semana</th>
                                    <th scope="col">Hora de llegada</th>
                                    <th scope="col" style="width: 300px;">Acciones</th>
                                </tr>
                            </thead>
                            <tbody id="tabla-horarios">

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>
    </jsp:body>



</t:admin>