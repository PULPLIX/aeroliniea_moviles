/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//$(function () {

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
        cantidadFilas: $("#asientosColumna").val()
    };

    $.ajax({
        url: "/aerolinea/aviones/insertar",
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
        url: "/aerolinea/api/aviones/get/"+id,
        type: "GET",
        success: function (avion) {
            console.log(avion);
            llenarModal(avion);
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
        url: "/aerolinea/api/aviones/eliminar",
        type: "delete",
        contentType: "application/json",
        data: JSON.stringify(id),
        success: function (listadoAviones) {
            mostrarMensaje("success", "Eliminado correctamente");
            recargarTabla(listadoAviones);
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
        console.log(row);
    });
}

function listarAviones() {
    $.ajax({
        url: "/aerolinea/api/aviones/listar",
        type: "get",
        success: function (listadoAviones) {
            recargarTabla(listadoAviones);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function insertarAvion() {
    var avion = {
        id: 0,
        tipo: $("#tipo").val(),
        capacidad: $("#capacidad").val(),
        anio: $("#anio").val(),
        marca: $("#marca").val(),
        asientosFila: $("#asientosFila").val(),
        cantidadFilas: $("#cantidadFilas").val(),
    };
    $.ajax({
        url: "/aerolinea/api/aviones/insertar",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(avion),
        success: function (listadoAviones) {
            recargarTabla(listadoAviones);
            mostrarMensaje("success", "Avion agregado correctamente");
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function actualizarAvion() {
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
        url: "/aerolinea/api/aviones/actualizar",
        type: "put",
        contentType: "application/json",
        data: JSON.stringify(avion),
        success: function (listadoAviones) {
            recargarTabla(listadoAviones);
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

listarAviones();

