<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

        <t:template>
            <jsp:attribute name="title">
                Pagina principal
            </jsp:attribute>

            <jsp:attribute name="scripts">
            <script src="/aerolinea/resources/js/global.js" ></script>
            </jsp:attribute>

            <jsp:body>
                <div id="carouselExampleControls" class="carousel slide carousel-fade" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="0"
                            class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="1"
                            aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="2"
                            aria-label="Slide 3"></button>
                        <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="3"
                            aria-label="Slide 4"></button>
                        <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="4"
                            aria-label="Slide 5"></button>

                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active" id="slide0">
                            <img src="/aerolinea/resources/images/carousel/clouds.jpg" class="d-block h-80" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>DAVID's Airlines</h5>
                                <p>Te ofrecemos los mejores precios de vuelos americanos.</p>
                            </div>
                        </div>

                        <div class="carousel-item" id="slide1">
                            <img src="/aerolinea/resources/images/carousel/airport.jpg" class="d-block h-80" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Vuela seguro con nosotros</h5>
                                <p>Cientos de vuelos todos los d??as a todos los pa??ses americanos con y sin escala.</p>
                            </div>

                        </div>
                        <div class="carousel-item" id="slide2">
                            <img src="/aerolinea/resources/images/carousel/frontAirPlain.jpg" class="d-block h-80"
                                alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Date las vacaciones que te mereces</h5>
                                <p>Viaja sin remordimiento por medio de nuestros servicios. Reserva ahora por medio de
                                    nuestra plataforma en l??nea.</p>
                            </div>
                        </div>
                        <div class="carousel-item" id="slide3">
                            <img src="/aerolinea/resources/images/carousel/insideAirPlane.jpg" class="d-block h-80"
                                alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Aviones c??modos</h5>
                                <p>Contamos con aviones 100% tecnol??gicos con las mejores comodidades que exiten hasta
                                    el momento</p>
                            </div>
                        </div>
                        <div class="carousel-item " id="slide4">
                            <img src="/aerolinea/resources/images/carousel/vacations.jpg" class="d-block h-80"
                                alt="...">
                            <div class="carousel-caption d-none d-md-block text-dark">
                                <h5>No atrases m??s esas vacaciones</h5>
                                <p>!Atr??vete a tomar nuevas aventuras en tu vida! Reserva ahora con nosotros </p>
                            </div>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                        data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                        data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>

                <div class="container my-5">
                    <div class="row  d-flex justify-content-center align-items-center text-center">
                        <h2 class="text-bold">??C??mo buscar vuelos baratos?</h2>
                        <p class="text-muted"> Volar de un lugar a otro es el modo m??s r??pido y c??modo de viajar. Si
                            quieres saber c??mo
                            reservar los pasajes a??reos m??s baratos, dale un vistazo a algunas simples reglas. Esto te
                            ayudar?? a ahorrar dinero en tus vacaciones.</p>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <span class="mb-4 d-flex justify-content-center">
                                <i class="far fa-clock texto-azul fa-3x"></i>
                            </span>
                            <p class="text-muted">El tiempo es el factor m??s importante aqu??. Podemos encontrar vuelos
                                de bajo costo solo
                                cuando los buscamos con mucha, mucha anticipaci??n. No hagas cuentas con buenas ofertas
                                de ??ltimo minuto como con las agencias de viaje. Las aerol??neas son un animal
                                completamente diferente. Las compa????as a??reas prefieren quedarse con algunos asientos
                                vac??os en el avi??n. En dicho caso, ellas pueden vend??rselos a personas que tengan que
                                viajar en determinados horarios. Los pasajes a??reos m??s baratos pueden encontrarse
                                varias semanas antes del viaje programado. Lo mejor es comprarlos apenas est??n
                                disponibles online. Este es el momento en que las compa????as a??reas entran en acci??n con
                                la intenci??n de atraer clientes a sus servicios</p>
                        </div>
                        <div class="col-4">
                            <span class="mb-4 d-flex justify-content-center">
                                <i class="far fa-paper-plane texto-azul fa-3x"></i>
                            </span>
                            <p class="text-muted">Las compa????as a??reas conocen perfectamente nuestros h??bitos de viaje y
                                ellas saben c??mo
                                transformar esa informaci??n en ganancia. Los agentes de aerol??neas han observado que los
                                vuelos se compran generalmente durante los fines de semana. Este es el momento en que
                                nos reunimos con familiares y amigos. Este tambi??n es el momento en el cual planificamos
                                nuestros viajes y vacaciones. Es por esta raz??n que no hay forma de encontrar ofertas
                                baratas durante los fines de semana. Trata de comprar tus pasajes los lunes por la
                                ma??ana. Existen muchas m??s probabilidades de que los precios a mediados de semana est??n
                                descontados especialmente cuando la compra se lleva a cabo por la ma??ana o antes del
                                mediod??a. Puede ocurrir que el vuelo m??s barato se encuentre algunos d??as antes o
                                despu??s de nuestras fechas deseadas. S?? flexible y toma en consideraci??n otros d??as.
                                Puede ser que la diferencia de precio sea muy atractiva. Si cambiamos nuestra fecha de
                                salida, podr??amos ahorrar algo de dinero para otros gastos o cosas divertidas que
                                podamos hacer. </p>
                        </div>
                        <div class="col-4">
                            <span class="mb-4 d-flex justify-content-center">
                                <i class="fas fa-dollar-sign  texto-azul fa-3x"></i>
                            </span>
                            <p class="text-muted">Te estar??s preguntando qu?? hay que hacer para no perderse la
                                oportunidad de encontrar
                                buenas ofertas de vuelos baratos. La mejor t??ctica consiste en seguir de cerca a las
                                aerol??neas que ofrecen precios bajos a sus clientes. Las aerol??neas de bajo costo, como
                                las solemos llamar, no garantizan altos est??ndares a bordo de sus aviones. Sin embargo,
                                estas son las aerol??neas que proveer??n las tarifas m??s bajas del mercado entre varias
                                ciudades. En Colombia las compa????as a??reas de bajo costo m??s populares son las
                                aerol??neas Viva Air Colombia, Easyfly y Wingo. Si est??s pensando visitar esta tierra
                                cafetera seguramente querr??s controlar los vuelos de aerol??neas como Avianca, LATAM y
                                Copa Airlines que tambi??n cubren rutas en dicha zona. Si ya sabes c??mo y cu??ndo buscar
                                vuelos baratos, vamos a la siguiente pregunta: ??d??nde encontrarlos? </p>
                        </div>
                    </div>
                </div>
                <%-- <div class="card my-5" style="margin-bottom: 1000px !important;">
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
                    --%>
            </jsp:body>



        </t:template>