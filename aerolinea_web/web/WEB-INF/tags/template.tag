<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="title" fragment="true" %>


<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
              crossorigin="anonymous">
        <link href="/aerolinea/resources/css/global.css" rel="stylesheet">
        <link id="favicon" rel="shortcut icon" href="/aerolinea/resources/images/logoSoloAzul.png"
              type="image/png" />
        <link rel="stylesheet" type="text/css"
              href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

        <title>
            <jsp:invoke fragment="title" />
        </title>

        <jsp:invoke fragment="css" />

    </head>

    <body style="background-color: #f6f7f8;">
        <!-- BARRA DE MENÚ -->
        <nav class="navbar navbar-expand-lg navbar-light bg-gardiente">
            <div class="container">
                <a class="navbar-brand d-flex align-items-center" href="#"
                   style="font-size: 16px; font-weight: 700;">
                    <img src="/aerolinea/resources/images/logoBanner.png" alt="Logo" width="180"
                         class="d-inline-block align-text-top rounded ">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <div class="col-10">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link " aria-current="page"
                                   href="/aerolinea/views/usuario/registro.jsp">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="/aerolinea/views/usuario/compraTiquetes.jsp">Comprar tiquetes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="/aerolinea/views/usuario/asientos.jsp">ASIENTOS*</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Acerca de nosotros</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-2">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/aerolinea/views/global/login.jsp"
                                   tabindex="-1"><i class="fas fa-sign-in-alt"></i> Iniciar sesión</a>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
        </nav>

        <div id="body">
            <jsp:doBody />
        </div>


        <div class="footer-basic d-flex flex-column mt-5">
            <!-- For demo purpose -->
            <div class="footer-title container-fluid flex-grow-1 flex-shrink-0">
                <div class="px-lg-5">
                    <div class="row py-5">
                        <div class="col-lg-12 mx-auto text-white text-center">
                            <h1 class="display-4">DAVID's AIRLINES</h1>
                            <p class="lead mb-0">La mejor compañía para viajes de interconexión en todo el
                                continente americano.</p>
                            <p class="lead">Snippet by <a href="https://bootstrapious.com/snippets"
                                                          class="text-white">
                                    <u>DAVID's AIRLINES</u></a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End -->


            <!-- Footer -->
            <footer class=" ">
                <div class="container py-5">
                    <div class="row py-4">
                        <div class="col-lg-4 col-md-6 mb-4 mb-lg-0"><img
                                src="/aerolinea/resources/images/logoSinLetras.png" alt="" width="180"
                                class="mb-3">
                            <p class="font-italic text-muted">DAVID's AIRLANS es unna de las aerolíneas con
                                mejores sistemas informáticos para agendar el vuelo que siempre has soñado.
                            </p>
                            <ul class="list-inline mt-4">
                                <li class="list-inline-item"><a href="#" target="_blank" title="twitter"><i
                                            class="footer-icon fa fa-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="#" target="_blank" title="facebook"><i
                                            class="footer-icon fa fa-facebook"></i></a></li>
                                <li class="list-inline-item"><a href="#" target="_blank"
                                                                title="instagram"><i class="footer-icon fa fa-instagram"></i></a>
                                </li>
                                <li class="list-inline-item"><a href="#" target="_blank"
                                                                title="pinterest"><i class="footer-icon fa fa-pinterest"></i></a>
                                </li>
                                <li class="list-inline-item"><a href="#" target="_blank" title="vimeo"><i
                                            class="footer-icon fa fa-vimeo"></i></a></li>
                            </ul>
                        </div>
                        <div class="col-lg-2 col-md-6 mb-4 mb-lg-0">
                            <h6 class="text-uppercase font-weight-bold mb-4">Servicios</h6>
                            <ul class="list-unstyled mb-0">
                                <li class="mb-2"><a href="#" class="text-muted">Tiquetes reservados</a></li>
                                <li class="mb-2"><a href="#" class="text-muted"> Reservar tiquetes </a></li>
                                <li class="mb-2"><a href="#" class="text-muted">Vuelos disponibles</a></li>
                            </ul>
                        </div>
                        <div class="col-lg-2 col-md-6 mb-4 mb-lg-0">
                            <h6 class="text-uppercase font-weight-bold mb-4">DAVID's Company</h6>
                            <ul class="list-unstyled mb-0">
                                <li class="mb-2"><a href="#" class="text-muted">Login</a></li>
                                <li class="mb-2"><a href="#" class="text-muted">Register</a></li>
                                <li class="mb-2"><a href="#" class="text-muted">About Us</a></li>
                            </ul>
                        </div>
                        <div class="col-lg-4 col-md-6 mb-lg-0">
                            <h6 class="text-uppercase font-weight-bold mb-4">Novedades</h6>
                            <p class="text-muted mb-4">Registra tu correo electrónico para mantenerte
                                actualizado de todas nuestras promociones y descuentos en vuelos por
                                américa.</p>
                            <div class="p-1 rounded border">
                                <div class="input-group">
                                    <input type="email" placeholder="Digita tu correo electrónico"
                                           aria-describedby="button-addon1"
                                           class="form-control border-0 shadow-0">
                                    <div class="input-group-append">
                                        <button id="button-addon1" type="submit" class="btn btn-link"><i
                                                class="footer-icon  fa fa-paper-plane"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Copyrights -->
                <div class="bg-light py-4">
                    <div class="container text-center">
                        <p class="text-muted mb-0 py-2">© 2021 DISEÑO E IMPLEMENTACIÓN DE PLATAFORMAS
                            MÓVILES.</p>
                    </div>
                </div>
            </footer>
        </div>

        <script src="https://kit.fontawesome.com/39f4ebbbea.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>

        <script type="text/javascript"
        src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <script src="/aerolinea/resources/js/global.js" crossorigin="anonymous"></script>

        <jsp:invoke fragment="scripts" />

    </body>

</html>