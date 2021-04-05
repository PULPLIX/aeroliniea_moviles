<%-- Document : registro Created on : Mar 26, 2021, 5:42:16 PM Author : david --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

            <t:template>
                <jsp:attribute name="title">
                    Registro
                </jsp:attribute>
                <jsp:attribute name="css">
                </jsp:attribute>

                <jsp:attribute name="scripts">
                    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
                        crossorigin="anonymous"></script>
                    <script src="/aerolinea/resources/js/global.js"></script>
                    <script src="/aerolinea/resources/js/usuarios.js"></script>
                    <script src="/aerolinea/resources/js/asientos.js"></script>
                </jsp:attribute>

                <jsp:body>
                    <a href="#" class="btn btn-success asiento" role="button" data-bs-toggle="button"
                        onclick="evtAsiento(this)">
                        <i class="fas fa-user"></i></a>

                    <a href="#" class="asiento ocupado">
                        <i class="fas fa-times-circle"></i></a>

                </jsp:body>



            </t:template>