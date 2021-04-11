
var meses = new Map();
meses.set("ene", "jan");
meses.set("feb", "feb");
meses.set("mar", "march");
meses.set("may", "may");
meses.set("abr", "apr");
meses.set("jun", "jun");
meses.set("jul", "jul");
meses.set("ago", "aug");
meses.set("sep", "sep");
meses.set("oct", "oct");
meses.set("nov", "nov");
meses.set("dic", "dec");


var elements = document.getElementsByClassName('list-group-item-action active');
while (elements.length > 0) {
    elements[0].classList.remove('active');
}
$("#item-vuelos").addClass("active");



function listarRutas() {
    $.ajax({
        url: "/aerolinea/api/rutas/listar",
        type: "get",
        success: function (listadoRutas) {
            llenarRutas(listadoRutas);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}
function llenarRutas(listadoRutas) {
    listadoRutas.forEach(ruta => {
        var option = "(" + ruta.ciudadOrigen.nombre + ") - (" + ruta.ciudadDestino.nombre + ") -> " + ruta.horarioId.diaSemana + ":" + ruta.horarioId.horaLlegada + "h ";
        $("#rutaId").append("<option value='" + ruta.id + "'>" + option + "</option>");
        $("#rutaId-modal").append("<option value='" + ruta.id + "'>" + option + "</option>");
    });
}

function listarAviones() {
    $.ajax({
        url: "/aerolinea/api/aviones/listar",
        type: "get",
        success: function (listadoAviones) {
            llenarAviones(listadoAviones);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function llenarAviones(listadoAviones) {
    listadoAviones.forEach(avion => {
        var option = avion.marca + "-" + avion.tipo + " (" + avion.anio + ")";
        $("#avionId").append("<option value='" + avion.id + "'>" + option + "</option>");
        $("#avionId-modal").append("<option value='" + avion.id + "'>" + option + "</option>");
    });
}



function getVuelo(id) {
    $.ajax({
        url: "/aerolinea/api/vuelos/get/" + id,
        type: "GET",
        success: function (vuelo) {
            var aux = meses.get(vuelo.fecha.substr("0", "3")) + vuelo.fecha.substr("3", vuelo.fecha.length);
            var fecha = new Date(aux);
            var vueloModal = {
                id: vuelo.id,
                modalidad: vuelo.modalidad,
                duracion: vuelo.duracion,
                fecha: fecha,
                avionId: vuelo.avionId.id,
                rutaId: vuelo.rutaId.id,
            }
            llenarModal(vueloModal);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function getMes(fecha) {
    return fecha.substr("0", "2");
}
function listarVuelos() {
    $.ajax({
        url: "/aerolinea/api/vuelos/listar",
        type: "get",
        success: function (listadoVuelos) {
            recargarTabla(listadoVuelos);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}
function insertarVuelo() {
    if (verificaCampoVacio($("#fecha").val()) && verificaCampoVacio($("#rutaId").val()) && verificaCampoVacio($("#modalidad").val()) && verificaCampoVacio($("#avionId").val()) && verificaCampoNum($("#duracion").val())) {
        var ruta = crearVuelo();
        $.ajax({
            url: "/aerolinea/api/vuelos/insertar",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(ruta),
            success: function (listadoVuelos) {
                recargarTabla(listadoVuelos);
                mostrarMensaje("success", "Vuelo agregado correctamente");
            },
            statusCode: {
                404: function () {
                    alert("Hubo un error");
                }
            }
        });
    } else {
        mostrarMensaje("error", "Datos incorrectos");
    }
}

function actualizarVuelo() {
    var ruta = crearVueloModal();
    $.ajax({
        url: "/aerolinea/api/vuelos/actualizar",
        type: "put",
        contentType: "application/json",
        data: JSON.stringify(ruta),
        success: function (listadoVuelos) {
            recargarTabla(listadoVuelos);
            mostrarMensaje("success", "Vuelo actualizado correctamente");
            $("#cerrar-modal").trigger("click");
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "Ocurrió un error al agregar");
            }
        }
    });
}

function eliminarVuelo(id) {
    $.ajax({
        url: "/aerolinea/api/vuelos/eliminar",
        type: "delete",
        contentType: "application/json",
        data: JSON.stringify(id),
        success: function (listadoVuelos) {
            mostrarMensaje("success", "Eliminado correctamente");
            recargarTabla(listadoVuelos);
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "No se ha podido eliminar");
            }
        }
    });
}
function recargarTabla(listadoVuelos) {
    $("#tabla-vuelos").html("");
    var tabla = $("#tabla-vuelos");
    listadoVuelos.forEach(vuelo => {
        var modAux = vuelo.modalidad;
        if (vuelo.modalidad === 1) {
            modAux = "Solo ida";
        } else {
            modAux = "Ida y retorno";
        }
        var row = $('<tr></tr>');
        $('<td></td').html(vuelo.id).appendTo(row);
        $('<td></td>').html(vuelo.fecha).appendTo(row);
        $('<td></td>').html(modAux).appendTo(row);
        $('<td></td>').html(vuelo.duracion).appendTo(row);
        $('<td></td>').html(vuelo.rutaId.ciudadOrigen.nombre + " - " + vuelo.rutaId.ciudadDestino.nombre).appendTo(row);
        $('<td></td>').html(vuelo.rutaId.horarioId.diaSemana + " - " + vuelo.rutaId.horarioId.horaLlegada).appendTo(row);
        $('<td></td>').html(vuelo.avionId.tipo + " - " + vuelo.avionId.marca + "(" + vuelo.avionId.anio + ")").appendTo(row);
        var btn1 = "<button class='btn btn-warning btn-sm mx-2' data-bs-toggle='modal' data-bs-target='#staticBackdrop'" +
                "onclick='getVuelo(" + vuelo.id + ")'" + "><i class='fas fa-pencil-alt'></i> Editar</button>";
        var btn2 = "<button class='btn btn-danger btn-sm' onclick='eliminarVuelo(" + vuelo.id + ")'>" +
                " <i class='fas fa-times'></i>&nbsp;Eliminar</button>";
        var btn = btn1 + btn2;
        $('<td></td>').html(btn).appendTo(row);
        row.appendTo(tabla);
    });
}

function crearVuelo() {
    var rutaObj = {
        id: $("#rutaId").val()
    };
    var avionObj = {
        id: $("#avionId").val()
    };

    var ruta = {
        modalidad: $("#modalidad").val(),
        duracion: $("#duracion").val(),
        fecha: $("#fecha").val(),
        rutaId: rutaObj,
        avionId: avionObj,
    };
    return ruta;
}
function crearVueloModal() {
    var rutaObj = {
        id: $("#rutaId-modal").val()
    };
    var avionObj = {
        id: $("#avionId-modal").val()
    };

    var vuelo = {
        id: $("#id-modal").val(),
        modalidad: $("#modalidad-modal").val(),
        duracion: $("#duracion-modal").val(),
        fecha: $("#fecha-modal").val(),
        rutaId: rutaObj,
        avionId: avionObj,
    };
    return vuelo;
}

function buscarVuelos() {
    var modalidad = $('input[name="modalidad"]:checked').val();
    var idOrigen = $("#origen").val();
    var idDestino = $("#destino").val();
    var fechas = $("#fechas").val();
    var fechaI = fechas.substr(0, 10);
    var fechaF = fechas.substr(-10);
    var descuento = $('input#descuento').prop('checked');
    $.ajax({
        url: "/aerolinea/api/vuelos/buscar?Modalidad=" + modalidad + "&idOrigen=" + idOrigen + "&idDestino=" + idDestino + "&fechaI=" + fechaI + "&fechaF=" + fechaF+ "&descuento=" + descuento,
        type: "get",
        success: function (listadoVuelos) {
            mostrarMensaje("success", "Busqueda satisfactoria");
            recargarTablaVuelosBuscados(listadoVuelos);
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "No se ha podido encontrar ningun vuelo");
            }
        }
    });
}

function listarCiudades() {
    $.ajax({
        url: "/aerolinea/api/ciudades/listar",
        type: "get",
        success: function (listadoCiudades) {
            llenarCiudadesBusqueda(listadoCiudades);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

$('input[name="dates"]').daterangepicker();


$(function () {
    $('input[name="daterange"]').daterangepicker({
        opens: 'left'
    }, function (start, end, label) {
    });
});

function llenarCiudadesBusqueda(listadoCiudades) {
    listadoCiudades.forEach(ciudad => {
        $("#origen").append("<option value='" + ciudad.id + "'>" + ciudad.nombre + "</option>");
        $("#destino").append("<option value='" + ciudad.id + "'>" + ciudad.nombre + "</option>");
    });
}

function recargarTablaVuelosBuscados(listadoVuelos) {
    $("#resultado-busqueda").html("");
    var tabla = $("#resultado-busqueda");
    listadoVuelos.forEach(vuelo => {
        var row = crearFila(vuelo);
        console.log(row);
        tabla.append(row);
    });
}
function getVueloSeleccionado(id) {
    $.ajax({
        url: "/aerolinea/api/vuelos/get/" + id,
        type: "GET",
        success: function (vuelo) {
            sessionStorage.setItem("vueloSelected", JSON.stringify(vuelo));
            window.location.href = "/aerolinea/views/usuario/compra.jsp";
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });

}
function crearFila(vuelo) {
    var modalidad = ""
    if (vuelo.modalidad == 1) {
        modalidad = 'Solo ida'
    } else {
        modalidad = 'Ida y vuelta'
    }
    var row = '<div class="row  tabla-vuelos"><div class="col-10 "><div class="card card-info"><div class="card-body"><div class="container-fluid">' +
            '<div class="row"><div class="col-2"><img src="/aerolinea/resources/images/logoBanner.png" alt="" class="w-100"></div>' +
            '<div class="col-7 d-flex justify-content-between align-items-center"> <div class="texto-azul d-flex ">' + vuelo.rutaId.ciudadOrigen.nombre +
            '<div class="separate-city"> </div>' + vuelo.rutaId.ciudadDestino.nombre + '</div></div><div class="col-3 d-flex justify-content-end text-celeste">' +
            vuelo.fecha + ' - ' + modalidad + '</div> </div><div class="row pt-3 d-flex justify-content-end">' +
            '<div class="col-6 d-flex justify-content-start align-items-center"><span class="horario-tabla">' + vuelo.rutaId.horarioId.diaSemana +
            ' <i class="fas fa-long-arrow-alt-right"></i> ' + vuelo.rutaId.horarioId.horaLlegada + '</span><span class="duracion-tabla">' +
            '<i class="fas fa-history "></i>&nbsp;' + vuelo.duracion + '</span></div>' +
            '<div class="col-4 d-flex justify-content-end align-items-center text-muted avion-info">' +
            '<span><i class="fas fa-plane text-celeste"></i></span><span><i class="fas fa-wifi"></i>' +
            '</span><span><i class="fas fa-plug"></i></span><span><i class="fas fa-mug-hot"></i></span>' +
            '<span><i class="fas fa-desktop"></i></span></div></div></div></div></div></div>' +
            '<div class="col-2 p-0 "><div class="card w-100  d-flex justify-content-center align-items-center">' +
            '<div class="card-body d-flex align-items-center flex-column "><div class="precio">$' + vuelo.rutaId.precio + '</div>' +
            '<div class="precio-info">Precio por cada tiquete </div><div>' +
            '<button class="btn btn-outline-celeste fw-bold" onclick="getVueloSeleccionado(' + vuelo.id + '); notRegister();">Ver asientos</button></div></div></div></div></div>';
    return row;
}

function notRegister(){
    if (sessionStorage.getItem('usuario') === null) {
        window.location.href = "/aerolinea/views/global/login.jsp";
    }
}

listarCiudades();
listarVuelos();
listarAviones();
listarRutas();