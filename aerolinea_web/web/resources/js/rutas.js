function include(file) {
    var script = document.createElement('script');
    script.src = file;
    script.type = 'text/javascript';
    script.defer = true;
    document.getElementsByTagName('head').item(0).appendChild(script);
}

include('/aerolinea/resources/js/twbsPagination.js');


function apply_pagination() {
    $pagination.twbsPagination({
        totalPages: totalPages,
        visiblePages: 6,
        onPageClick: function (event, page) {
            displayRecordsIndex = Math.max(page - 1, 0) * recPerPage;
            endRec = (displayRecordsIndex) + recPerPage;
            displayRecords = records.slice(displayRecordsIndex, endRec);
            recargarTabla(displayRecords);
        }
    });
}

var $pagination = $('#pagination'),
        totalRecords = 0,
        records = [],
        displayRecords = [],
        recPerPage = 10,
        page = 1,
        totalPages = 0;

function paginacion(data) {
    records = data;
    totalRecords = records.length;
    totalPages = Math.ceil(totalRecords / recPerPage);
    apply_pagination();

}

var elements = document.getElementsByClassName('list-group-item-action active');

while (elements.length > 0) {
    elements[0].classList.remove('active');
}

$("#item-rutas").addClass("active");
function mostrarDescuento(checkBox) {
    if (checkBox.checked == true) {
        $("#descuento-field").show();
    } else {
        $("#descuento").val('0');
        $("#descuento-field").hide();
    }
}

function mostrarDescuentoModal(checkBox) {
    if (checkBox.checked == true) {
        $("#descuento-field-modal").show();
    } else {
        $("#descuento-field-modal").hide();
    }
}

function listarHorarios() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/horarios/listar",
        type: "get",
        success: function (listadoHorarios) {
            llenarHorarios(listadoHorarios);
        },
        statusCode: {
            404: function () {
                mostrarMensaje('success',"Página no encontrada");
            },
            500: function () {
                mostrarMensaje('success',"Lista vacia");
            }
        }
    });
}


function listarCiudades() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/ciudades/listar",
        type: "get",
        success: function (listadoCiudades) {
            llenarCiudades(listadoCiudades);
        },
        statusCode: {
            404: function () {
                mostrarMensaje('success', "Página no encontrada");
            },
            500: function () {
                mostrarMensaje('success', "Lista vacia");
            }
        }
    });
}

function llenarCiudades(listadoCiudades) {
    listadoCiudades.forEach(ciudad => {
        $("#ciudadOrigen").append("<option value='" + ciudad.id + "'>" + ciudad.nombre + "</option>");
        $("#ciudadDestino").append("<option value='" + ciudad.id + "'>" + ciudad.nombre + "</option>");
        $("#ciudadOrigen-modal").append("<option value='" + ciudad.id + "'>" + ciudad.nombre + "</option>");
        $("#ciudadDestino-modal").append("<option value='" + ciudad.id + "'>" + ciudad.nombre + "</option>");
    });
}

function llenarHorarios(listadoHorarios) {
    listadoHorarios.forEach(horario => {
        var option = horario.diaSemana + " - " + horario.horaLlegada;
        $("#horarioId").append("<option value='" + horario.id + "'>" + option + "</option>");
        $("#horarioId-modal").append("<option value='" + horario.id + "'>" + option + "</option>");
    });
}

function getRuta(id) {
    $.ajax({
        url: "http://localhost:8081/Backend/api/rutas/get/" + id,
        type: "GET",
        success: function (ruta) {
            var rutaModal = {
                id: ruta.id,
                precio: ruta.precio,
                porcentajeDescuento: ruta.porcentajeDescuento,
                ciudadOrigen: ruta.ciudadOrigen.id,
                ciudadDestino: ruta.ciudadDestino.id,
                horarioId: ruta.horarioId.id
            };
            llenarModal(rutaModal);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}
function listarRutas() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/rutas/listar",
        type: "get",
        success: function (listadoRutas) {
            paginacion(listadoRutas);
        },
        statusCode: {
            404: function () {
                mostrarMensaje('success', "Página no encontrada");
            },
            500: function () {
                mostrarMensaje('success', "Lista vacia");
            }
        }
    });
}
function insertarRuta() {

    if (verificaCampoVacio($("#ciudadOrigen").val()) && verificaCampoVacio($("#ciudadDestino").val()) && verificaCampoVacio($("#horarioId").val())) {
        var ruta = crearRuta();
        $.ajax({
            url: "http://localhost:8081/Backend/api/rutas/insertar",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(ruta),
            success: function (listadoRutas) {
                recargarTabla(listadoRutas);
                mostrarMensaje("success", "Ruta agregado correctamente");
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

function actualizarRuta() {
    var ruta = crearRutaModal();
    $.ajax({
        url: "http://localhost:8081/Backend/api/rutas/actualizar",
        type: "put",
        contentType: "application/json",
        data: JSON.stringify(ruta),
        success: function (listadoRutas) {
            recargarTabla(listadoRutas);
            mostrarMensaje("success", "Actualizado correctamente");
            $("#cerrar-modal").trigger("click");
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "Ocurrió un error al agregar");
            }
        }
    });
}

function eliminarRuta(id) {

    $.ajax({
        url: "http://localhost:8081/Backend/api/rutas/eliminar",
        type: "delete",
        contentType: "application/json",
        data: JSON.stringify(id),
        success: function (listadoRutas) {
            mostrarMensaje("success", "Eliminado correctamente");
            recargarTabla(listadoRutas);
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "No se ha podido eliminar");
            }
        }
    });
}
function recargarTabla(listadoRutas) {
    $("#tabla-rutas").html("");
    var tabla = $("#tabla-rutas");
    listadoRutas.forEach(ruta => {
        var row = $('<tr></tr>');
        $('<td></td').html(ruta.id).appendTo(row);
        $('<td></td>').html(ruta.ciudadOrigen.nombre).appendTo(row);
        $('<td></td>').html(ruta.ciudadDestino.nombre).appendTo(row);
        $('<td></td>').html(ruta.horarioId.diaSemana + " - " + ruta.horarioId.horaLlegada).appendTo(row);
        $('<td></td>').html(ruta.precio).appendTo(row);
        $('<td></td>').html(ruta.porcentajeDescuento + "%").appendTo(row);

        var btn1 = "<button class='btn btn-warning btn-sm mx-2' data-bs-toggle='modal' data-bs-target='#staticBackdrop'" +
                "onclick='getRuta(" + ruta.id + ")'" + "><i class='fas fa-pencil-alt'></i> Editar</button>";
        var btn2 = "<button class='btn btn-danger btn-sm' onclick='eliminarRuta(" + ruta.id + ")'>" +
                " <i class='fas fa-times'></i>&nbsp;Eliminar</button>";
        var btn = btn1 + btn2;
        $('<td></td>').html(btn).appendTo(row);
        row.appendTo(tabla);
    });
}

function crearRuta() {
    var ciudadOrigenObj = {
        id: $("#ciudadOrigen").val()
    };
    var ciudadDestinoObj = {
        id: $("#ciudadDestino").val()
    };
    var horarioObj = {
        id: $("#horarioId").val()
    };

    var ruta = {
        precio: $("#precio").val(),
        porcentajeDescuento: $("#descuento").val(),
        ciudadOrigen: ciudadOrigenObj,
        ciudadDestino: ciudadDestinoObj,
        horarioId: horarioObj
    };
    return ruta;
}

function crearRutaModal() {
    var ciudadOrigenObj = {
        id: $("#ciudadOrigen-modal").val()
    };
    var ciudadDestinoObj = {
        id: $("#ciudadDestino-modal").val()
    };
    var horarioObj = {
        id: $("#horarioId-modal").val()
    };
    var ruta = {
        id: $("#id-modal").val(),
        precio: $("#precio-modal").val(),
        porcentajeDescuento: $("#descuento-modal").val(),
        ciudadOrigen: ciudadOrigenObj,
        ciudadDestino: ciudadDestinoObj,
        horarioId: horarioObj
    };
    return ruta;
}
listarRutas();
listarHorarios();
listarCiudades();