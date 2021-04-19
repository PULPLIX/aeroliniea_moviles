
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>




<t:admin>
    <jsp:attribute name="title">
        Dashboard
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="https://cdn.jsdelivr.net/npm/chart.js@3.1.1/dist/chart.min.js"></script>
        <script src="/aerolinea/resources/js/reportes.js"></script>
    </jsp:attribute>

    <jsp:body>

        <div class="mensaje-container" id="mensaje-info" style="display:none;  ">
            <div class="col-3 icono-mensaje d-flex align-items-center" id="icono-mensaje" ></div>
            <div class="col-9 texto-mensaje d-flex align-items-center text-center mx-2" id="texto-mensaje" style="color: #046704e8; ">Participante agregado correctamente</div>
        </div>
        <div class="row my-3">
            <div class="col-sm-6">
                <h1 class="display-6 fw-normal text-negro"><i class="fas fa-tachometer-alt text-primary"></i>&nbsp;Dashboord </h1>
            </div>
        </div>
        <div class="row  border-bottom">
            <div class="col-12 ">
                <div class="card">
                    <div class="card-header titulo"><i class="fas fa-money-check-alt text-info"></i> Ingresos del año</div>
                    <div class="card-body">
                        <div style="width: 100% ">
                            <canvas id="facturadoXAnio" width="600" height="200" ></canvas>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="row  my-4">
            <div class="col-5 ">
                <div class="card">
                    <div class="card-header titulo"><i class="fas fa-hand-holding-usd text-info"></i> Facturación de los últimos 12 meses</div>
                    <div class="card-body">
                        <div style="width: 100% ">
                            <canvas id="ultimos12Meses" width="600" height="400" ></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-7 ">
                <div class="card">
                    <div class="card-header titulo"> <i class="fas fa-trophy text-info"></i> Top 5 de rutas más vendidas</div>
                    <div class="card-body">
                        <div style="width: 100% ">
                            <canvas id="top5Chart" width="600" height="400" ></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>



    </jsp:body>



</t:admin>