<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

        <t:admin>
            <jsp:attribute name="title">
                Gestión de aviones
            </jsp:attribute>

            <jsp:attribute name="scripts">
            </jsp:attribute>
            <jsp:attribute name="user_id">
                117380366
            </jsp:attribute>
            <jsp:body>


                <!-- Modal -->
                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
                    tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Editar avión [id_avion]</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">@</span>
                                    <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                                        aria-describedby="basic-addon1">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">@</span>
                                    <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                                        aria-describedby="basic-addon1">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">@</span>
                                    <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                                        aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="button" class="btn btn-primary">Guardar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row my-3">
                    <div class="col-sm-6">
                        <h1 class="display-6 fw-normal text-negro">Gestión de aviones</h1>
                    </div>
                </div>
                <div class="row  border-bottom">
                    <div class="col-6 ">
                        <div class="card">
                            <div class="card-header titulo"><i class="fas fa-plus"></i> &nbsp;Agregar avión</div>
                            <div class="card-body">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">@</span>
                                    <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                                        aria-describedby="basic-addon1">
                                </div>

                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Recipient's username"
                                        aria-label="Recipient's username" aria-describedby="basic-addon2">
                                    <span class="input-group-text" id="basic-addon2">@example.com</span>
                                </div>

                                <label for="basic-url" class="form-label">Your vanity URL</label>
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon3">https://example.com/users/</span>
                                    <input type="text" class="form-control" id="basic-url"
                                        aria-describedby="basic-addon3">
                                </div>
                            </div>
                            <div class="d-flex justify-content-center pb-4">
                                <button class="btn btn-primary"><i class="fas fa-plus"></i> &nbsp; Agregar</button>
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
                                            <th scope="col">#</th>
                                            <th scope="col">First</th>
                                            <th scope="col">Last</th>
                                            <th scope="col">Handle</th>
                                            <th scope="col" style="width: 300px;">Acciones</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>Mark</td>
                                            <td>Otto</td>
                                            <td>@mdo</td>
                                            <td>
                                                <!-- Button trigger modal  editar-->
                                                <button class="btn btn-warning btn-sm mr-2" data-bs-toggle="modal"
                                                    data-bs-target="#staticBackdrop"><i class="fas fa-pencil-alt"></i>
                                                    Editar</button>
                                                <!-- Boton de eliminar -->
                                                <button class="btn btn-danger btn-sm"> <i class="fas fa-times"></i>
                                                    Eliminar</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                            <td>
                                                <!-- Button trigger modal  editar-->
                                                <button class="btn btn-warning btn-sm mr-2" data-bs-toggle="modal"
                                                    data-bs-target="#staticBackdrop"><i class="fas fa-pencil-alt"></i>
                                                    Editar</button>
                                                <!-- Boton de eliminar -->
                                                <button class="btn btn-danger btn-sm"> <i class="fas fa-times"></i>
                                                    Eliminar</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">3</th>
                                            <td>Larry</td>
                                            <td>the Bird</td>
                                            <td>@twitter</td>
                                            <td>
                                                <!-- Button trigger modal  editar-->
                                                <button class="btn btn-warning btn-sm mr-2" data-bs-toggle="modal"
                                                    data-bs-target="#staticBackdrop"><i class="fas fa-pencil-alt"></i>
                                                    Editar</button>
                                                <!-- Boton de eliminar -->
                                                <button class="btn btn-danger btn-sm"> <i class="fas fa-times"></i>
                                                    Eliminar</button>
                                            </td>
                                        </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- /.card -->
                    </div>
                </div>
            </jsp:body>



        </t:admin>