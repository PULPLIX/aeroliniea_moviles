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
                    <div class="row my-5 d-flex justify-content-center">
                        <div class="col-md-6">
                            <div class="card ">
                                <div class="card-header d-flex justify-content-center align-items-center">
                                    <img id="profile-img" class="profile-img-card"
                                        src="/aerolinea/resources/images/logo.png" alt="Logo" width="200" height="200"
                                        class="d-inline-block align-text-top rounded " />
                                </div>
                                <div class="card-body d-flex justify-content-center align-items-center ">
                                    <form class="form-signin" method="POST" action="/aerolinea/usuario/login">
                                        <div class="input-group mb-3">
                                            <input type="id" name="id" id="inputEmail" value="12" class="form-control"
                                                placeholder="Usuario id" autofocus required>
                                            <span class="input-group-text"><i
                                                    class="fas fa-user text-primary"></i></span>
                                        </div>
                                        <div class="input-group mb-3">
                                            <input type="contrasena" name="contrasena" value="1234" autocomplete="current-password" id="inputPassword" class="form-control"
                                                placeholder="Contraseña" required>
                                            <span class="input-group-text"><i
                                                    class="fas fa-key text-primary"></i></span>
                                        </div>

                                        <br>
                                        <div class="d-flex justify-content-center align-items-center "><button
                                                class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign
                                                in</button></div>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </jsp:body>



        </t:template>