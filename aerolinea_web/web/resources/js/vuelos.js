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
            console.log(meses.get(vuelo.fecha.substr("0", "3")) + vuelo.fecha.substr("3", vuelo.fecha.length));

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
    if (verificaCampoVacio($("#fecha").val())&& verificaCampoVacio($("#rutaId").val()) && verificaCampoVacio($("#modalidad").val()) && verificaCampoVacio($("#avionId").val()) && verificaCampoNum($("#duracion").val())) {
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

listarVuelos();
listarAviones();
listarRutas();