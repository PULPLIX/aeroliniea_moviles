<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

        <t:template>
            <jsp:attribute name="title">
                Pagina principal
            </jsp:attribute>

            <jsp:attribute name="scripts">
                <script src="/aerolinea/resources/js/socket.js"></script>
            </jsp:attribute>

            <jsp:body>
                <div class="card my-5" style="margin-bottom: 1000px !important;">
                    <div class="card-body ">
                        <h4>Mensaje: </h4>
                        <input type="text" name="" id="messageinput">
                    </div>

                    <div>
                        <p></p>
                        <p></p>
                        <button class="btn btn-info" type="button" onclick="openSocket();">Open</button>
                        <button class="btn btn-warning" type="button" onclick="send();">Send</button>
                        <button class="btn btn-danger" type="button" onclick="closeSocket();">Close</button>
                    </div>

                    <p></p>
                    <p></p>
                    <p></p>
                    <div id="messages"></div>
                    <div class="container">
                        <div class="row d-flex justify-content-center align-items-center">
                            <col-2>
                                <button class="btn btn-primary" type="button" onclick="insert();"><i
                                        class="fas fa-upload"></i> &nbsp;Insertar</button>
                                <button class="btn btn-warning" type="button" onclick="update();"><i
                                        class="fas fa-pencil-alt"></i> &nbsp;Actualizar</button>
                                <button class="btn btn-danger" type="button" onclick="deleteUser();"><i
                                        class="fas fa-trash-alt"></i> &nbsp;Borrar</button>
                            </col-2>
                        </div>
                    </div>


                </div>

            </jsp:body>



        </t:template>