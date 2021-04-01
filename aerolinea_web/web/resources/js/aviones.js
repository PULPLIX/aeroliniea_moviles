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

function recargarTabla(listadoAviones) {
//.attr({class: ["class1", "class2", "class3"].join(' ')})
    $("#tabla-aviones").html("");
    var tabla = $("#tabla-aviones");
    var btn = "<button class='btn btn-warning btn-sm mx-2' data-bs-toggle='modal'" +
            "data-bs-target='#staticBackdrop' ><i class='fas fa-pencil-alt'></i>" +
            "Editar</button>" +
            "<button class='btn btn-danger btn-sm'> <i class='fas fa-times'></i>" +
            "Eliminar</button>";
    console.log("entro al metodo");

    listadoAviones.forEach(avion => {
        var row = $('<tr></tr>');
        $('<td></td').html(avion.id).appendTo(row);
        $('<td></td>').html(avion.tipo).appendTo(row);
        $('<td></td>').html(avion.capacidad).appendTo(row);
        $('<td></td>').html(avion.anio).appendTo(row);
        $('<td></td>').html(avion.marca).appendTo(row);
        $('<td></td>').html(avion.asientosFila).appendTo(row);
        $('<td></td>').html(avion.cantidadFilas).appendTo(row);
        $('<td></td>').html(btn).appendTo(row);
        row.appendTo(tabla);
        console.log(row);
    });
}



//});


