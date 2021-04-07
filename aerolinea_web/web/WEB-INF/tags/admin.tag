<%@tag description="Template de admin" pageEncoding="UTF-8" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="user_id" fragment="true" %>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
              crossorigin="anonymous">
        <link href="/aerolinea/resources/css/global.css" rel="stylesheet">
        <link href="/aerolinea/resources/css/menuAdmin.css" rel="stylesheet">

        <link id="favicon" rel="shortcut icon" href="/aerolinea/resources/images/logoSoloAzul.png"
              type="image/png" />
        <title>
            <jsp:invoke fragment="title" />
        </title>

        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
        <jsp:invoke fragment="css" />

    </head>

    <body style="background-color: #f6f7f8;">
        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-oscuro border-right" id="sidebar-wrapper" style="max-width: 254px;">
                <div class="sidebar-heading mb-3 " style=" border-bottom: 1px solid #4b545c;"><img
                        src="/aerolinea/resources/images/logoBannerBlanco.png" alt=""
                        class="img-responsive" style="max-width: 100%;"> </div>
                <div class="list-group list-group-flush">
                    <a href="/aerolinea/dashboard/show"
                       class="list-group-item list-group-item-action active" id="item-dashboard"> <i
                            class="fas fa-tachometer-alt" id="item-dashboard"></i>&nbsp; Dashboard</a>
                    <a href="/aerolinea/views/admin/gestionAviones.jsp" class="list-group-item list-group-item-action" id="item-aviones"><i
                            class="fas fa-plane"></i>
                        &nbsp;Aviones</a>
                    <a href="/aerolinea/views/admin/gestionHorarios.jsp" class="list-group-item list-group-item-action "
                       id="item-horarios"><i class="far fa-calendar"></i> &nbsp;Horarios</a>
                    <a href="/aerolinea/views/admin/gestionRutas.jsp" class="list-group-item list-group-item-action "
                       id="item-rutas"><i class="fas fa-map-marked-alt"></i> &nbsp;Rutas</a>
                    <a href="/aerolinea/views/admin/gestionVuelos.jsp" class="list-group-item list-group-item-action "
                       id="item-vuelos"><i class="fas fa-plane-departure"></i> &nbsp;Vuelos</a>
                    <a href="/aerolinea/views/admin/gestionTiquetes.jsp" class="list-group-item list-group-item-action "
                       id="item-tiquetes"><i class="fas fa-ticket-alt"></i> &nbsp;Tiquetes</a>
                </div>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">

                <nav class="navbar navbar-expand-lg bg-white  border-bottom" id="navbar-admin">
                    <button class="btn btn-primary m-2" id="menu-toggle"><i
                            class="fas fa-th-large"></i></button>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <i class="fas fa-bars text-primary"></i>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarNav">
                        <div class="col-11" >

                            <ul class="navbar-nav ml-auto mt-2 mt-lg-0 ">
                                <li class="nav-item active">
                                    <a class="nav-link text-muted" href="#">Inicio </a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-1">
                            <ul class="navbar-nav ml-auto mt-2 mt-lg-0 ">
                                <li class="nav-item dropdown ">
                                    <div class="dropdown">
                                        <span class=" nav-link dropdown-toggle" type="button"
                                              id="idUsuarioSession" data-bs-toggle="dropdown"
                                              aria-expanded="false">
                                        </span>
                                        <ul class="dropdown-menu dropdown-menu-end"
                                            aria-labelledby="dropdownMenuButton1">
                                            <a class="dropdown-item text-primary" href="/aerolinea/views/usuario/perfil.jsp"><i
                                                    class="fas fa-user"></i> &nbsp;Mi perfil</a>
                                            <div class="dropdown-divider"></div>
                                            <button class="dropdown-item text-primary" onclick="logout()" > <i
                                                    class="fas fa-sign-out-alt"></i> &nbsp;Log
                                                out</button>
                                        </ul>
                                    </div>
                                </li>
                            </ul>
                        </div>

                    </div>
                </nav>

                <div class="container-fluid">
                    <jsp:doBody />

                </div>
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <script src="https://kit.fontawesome.com/39f4ebbbea.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-input-spinner@1.17.2/src/bootstrap-input-spinner.min.js"></script>
        <script>
            $("input[type='number']").inputSpinner();
        </script>
        <script type="text/javascript"  src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

        <script src="/aerolinea/resources/js/menu.js" crossorigin="anonymous"></script>
        <script src="/aerolinea/resources/js/global.js" crossorigin="anonymous"></script>
        <jsp:invoke fragment="scripts" />

    </body>


</html>