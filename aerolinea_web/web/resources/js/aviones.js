const webSocket = new WebSocket("ws://localhost:8081/Backend/avionesSocket");

webSocket.onopen = function(event) {
    console.log("SE HA ABIERTO UN SOCKET AVIONES");
    };

webSocket.addEventListener("message", function (event) {
    console.log(event.data)
    
    recargarTabla(JSON.parse(event.data));
});

webSocket.onerror = function(event) {
  console.error("WebSocket error observed:", event);
};

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
$("#item-aviones").addClass("active");

function registrar() {
    var avion = {
        tipo: $("#tipo").val(),
        capacidad: $("#capacidad").val(),
        anio: $("#anio").val(),
        marca: $("#marca").val(),
        asientosFila: $("#asientosFila").val(),
        cantidadFilas: $("#cantidadFilas").val()
    };

    $.ajax({
        url: "http://localhost:8081/Backend/aviones/insertar",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(avion),
        success: function (listaAviones) {
            recargarTabla(listaAviones);
        },
        statusCode: {
            404: function () {
                $("#no-existe-estudiante").html("El estudiante no existe.");
                $("#no-existe-estudiante").fadeTo(2000, 500).slideUp(500, function () {
                    $("#no-existe-estudiante").slideUp(500);
                });
            }
        }
    });
}

function getAvion(id) {
    $.ajax({
        url: "http://localhost:8081/Backend/api/aviones/get/" + id,
        type: "GET",
        success: function (usuario) {
            llenarModal(usuario);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });

}

function eliminarAvion(id) {

    $.ajax({
        url: "http://localhost:8081/Backend/api/aviones/eliminar",
        type: "delete",
        contentType: "application/json",
        data: JSON.stringify(id),
        success: function (listadoAviones) {
            mostrarMensaje("success", "Eliminado correctamente");
            recargarTabla(listadoAviones);
            webSocket.send(JSON.stringify(listadoAviones));
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "No se ha podido eliminar");
            }
        }
    });
}

function recargarTabla(listadoAviones) {
//.attr({class: ["class1", "class2", "class3"].join(' ')})
    $("#tabla-aviones").html("");
    var tabla = $("#tabla-aviones");

    listadoAviones.forEach(avion => {
        var row = $('<tr></tr>');
        $('<td></td').html(avion.id).appendTo(row);
        $('<td></td>').html(avion.tipo).appendTo(row);
        $('<td></td>').html(avion.capacidad).appendTo(row);
        $('<td></td>').html(avion.anio).appendTo(row);
        $('<td></td>').html(avion.marca).appendTo(row);
        $('<td></td>').html(avion.asientosFila).appendTo(row);
        $('<td></td>').html(avion.cantidadFilas).appendTo(row);
        var btn = "<button class='btn btn-warning btn-sm mx-2' data-bs-toggle='modal'" +
                "data-bs-target='#staticBackdrop' onclick='getAvion(" + avion.id + ")'" + "><i class='fas fa-pencil-alt'></i>" +
                "Editar</button>" +
                "<button class='btn btn-danger btn-sm' onclick='eliminarAvion(" + avion.id + ")'>" + "<i class='fas fa-times'></i>" +
                "Eliminar</button>";
        $('<td></td>').html(btn).appendTo(row);
        row.appendTo(tabla);
    });
}

function listarAviones() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/aviones/listar",
        type: "get",
        success: function (listadoAviones) {
            paginacion(listadoAviones);
            
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function insertarAvion() {
    if (verificaCampoNum($("#capacidad").val()) && verificaCampoNum($("#anio").val()) && verificaCampoNum($("#asientosFila").val()) && verificaCampoNum($("#cantidadFilas").val())) {
        var avion = {
            id: 0,
            tipo: $("#tipo").val(),
            capacidad: $("#capacidad").val(),
            anio: $("#anio").val(),
            marca: $("#marca").val(),
            asientosFila: $("#asientosFila").val(),
            cantidadFilas: $("#cantidadFilas").val()
        };
        $.ajax({
            url: "http://localhost:8081/Backend/api/aviones/insertar",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(avion),
            success: function (listadoAviones) {
                recargarTabla(listadoAviones);
                mostrarMensaje("success", "Avion agregado correctamente");   
                webSocket.send(JSON.stringify(listadoAviones));
            },
            statusCode: {
                404: function () {
                    alert("Hubo un error");
                }
            }
        });
    } else {
        mostrarMensaje("error", "Inserte datos correctamente");
    }
}

function actualizarAvion() {
    if (verificaCampoNum($("#capacidad-modal").val()) && verificaCampoNum($("#anio-modal").val()) && verificaCampoNum($("#asientosFila-modal").val()) && verificaCampoNum($("#cantidadFilas-modal").val())) {
        var avion = {
            id: $("#id-modal").text(),
            tipo: $("#tipo-modal").val(),
            capacidad: $("#capacidad-modal").val(),
            anio: $("#anio-modal").val(),
            marca: $("#marca-modal").val(),
            asientosFila: $("#asientosFila-modal").val(),
            cantidadFilas: $("#cantidadFilas-modal").val(),
        };

        $.ajax({
            url: "http://localhost:8081/Backend/api/aviones/actualizar",
            type: "put",
            contentType: "application/json",
            data: JSON.stringify(avion),
            success: function (listadoAviones) {
                recargarTabla(listadoAviones);
                mostrarMensaje("success", "Actualizado correctamente");
                $("#cerrar-modal").trigger("click");
                webSocket.send(JSON.stringify(listadoAviones));
            },
            statusCode: {
                404: function () {
                    mostrarMensaje("error", "Ocurrió un error al agregar");
                }
            }
        });
    } else {
        mostrarMensaje("error", "Inserte datos correctamente");
    }
}

listarAviones();


