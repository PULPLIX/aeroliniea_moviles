<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

        <t:template>
            <jsp:attribute name="title">
                Login
            </jsp:attribute>

            <jsp:attribute name="scripts">
                <%--<script src="/aerolinea/resources/js/socket.js">
                    </script> --%>
            </jsp:attribute>

            <jsp:body>
                <div class="container">
                    <div class="row my-5">
                        <div class="col-sm-12 col-md-7 d-flex justify-content-end">
                            <div class="d-flex justify-content-center align-items-center">
                                <img src="/aerolinea/resources/images/utiles/waiting.png" alt="" class="img-responsive"
                                    style="max-width: 100%;">
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-3 float-sm-none d-flex justify-content-center align-items-center ">
                            <div>
                                <div class="d-flex justify-content-center my-3 ">
                                    <img src="/aerolinea/resources/images/utiles/avatar2.png" alt="" class="img-fluid"
                                        style="max-width: 50%;">
                                </div>
                                <form action="/aerolinea/usuario/login" method="post">
                                    <div class="input-group mb-3">
                                        <input type="text" name="id" class="form-control"
                                            aria-label="Sizing example input"
                                            aria-describedby="inputGroup-sizing-default">
                                        <span class="input-group-text" id="inputGroup-sizing-default"><i
                                                class="fas fa-user texto-azul"></i></span>

                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="text" name="contrasena" class="form-control"
                                            aria-label="Sizing example input"
                                            aria-describedby="inputGroup-sizing-default">
                                        <span class="input-group-text" id="inputGroup-sizing-default"><i
                                                class="fas fa-key texto-azul"></i></span>
                                    </div>
                                    <div class="d-flex justify-content-center mb-3">
                                        <button type="submit"
                                            class="btn btn-outline-primary btn-lg w-100">Ingresar</button>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </jsp:body>



        </t:template>