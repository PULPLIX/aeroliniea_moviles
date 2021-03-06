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
$("#item-horarios").addClass("active");


function getHorario(id) {
    $.ajax({
        url: "http://localhost:8081/Backend/api/horarios/get/"+id,
        type: "GET",
        success: function (horario) {
            console.log(horario);
            llenarModal(horario);
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

function listarHorarios() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/horarios/listar",
        type: "get",
        success: function (listadoHorarios) {
            paginacion(listadoHorarios);
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

function insertarHorario() {
    var horario = {
        diaSemana: $("#diaSemana").val(),
        horaLlegada: $("#horaLlegada").val(),
    };
    $.ajax({
        url: "http://localhost:8081/Backend/api/horarios/insertar",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(horario),
        success: function (listadoHorarios) {
            recargarTabla(listadoHorarios);
            mostrarMensaje("success", "Horario agregado correctamente");
        },
        statusCode: {
            404: function () {
                mostrarMensaje('error',"No insertado");
            }
        }
    });
}

function actualizarHorario() {
    var horario = {
        id: $("#id-modal").text(),
        diaSemana: $("#diaSemana-modal").val(),
        horaLlegada: $("#horaLlegada-modal").val(),
    };

    $.ajax({
        url: "http://localhost:8081/Backend/api/horarios/actualizar",
        type: "put",
        contentType: "application/json",
        data: JSON.stringify(horario),
        success: function (listadoHorarios) {
            recargarTabla(listadoHorarios);
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

function eliminarHorario(id) {

    $.ajax({
        url: "http://localhost:8081/Backend/api/horarios/eliminar",
        type: "delete",
        contentType: "application/json",
        data: JSON.stringify(id),
        success: function (listadoHorarios) {
            mostrarMensaje("success", "Eliminado correctamente");
            recargarTabla(listadoHorarios);
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "No se ha podido eliminar");
            }
        }
    });
}

function recargarTabla(listadoHorarios) {
//.attr({class: ["class1", "class2", "class3"].join(' ')})
    $("#tabla-horarios").html("");
    var tabla = $("#tabla-horarios");
    listadoHorarios.forEach(horario => {
        var row = $('<tr></tr>');
        $('<td></td').html(horario.id).appendTo(row);
        $('<td></td>').html(horario.diaSemana).appendTo(row);
        $('<td></td>').html(horario.horaLlegada).appendTo(row);
        var btn1 = "<button class='btn btn-warning btn-sm mx-2' data-bs-toggle='modal' data-bs-target='#staticBackdrop'" +
                "onclick='getHorario(" + horario.id + ")'" + "><i class='fas fa-pencil-alt'></i> Editar</button>";
        var btn2 = "<button class='btn btn-danger btn-sm' onclick='eliminarHorario(" + horario.id + ")'>" +
                " <i class='fas fa-times'></i>&nbsp;Eliminar</button>";
        var btn = btn1 + btn2;
        $('<td></td>').html(btn).appendTo(row);
        row.appendTo(tabla);
    });
}

listarHorarios();
