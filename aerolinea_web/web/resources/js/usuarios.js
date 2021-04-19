/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            recargarTablaMisTiquetes(displayRecords);
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

(function (document) {
    'use strict';

    var LightTableFilter = (function (Arr) {

        var _input;

        function _onInputEvent(e) {
            _input = e.target;
            var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
            Arr.forEach.call(tables, function (table) {
                Arr.forEach.call(table.tBodies, function (tbody) {
                    Arr.forEach.call(tbody.rows, _filter);
                });
            });
        }

        function _filter(row) {
            var text = row.cells[1].textContent;
            var val = _input.value.toLowerCase();
            //console.log("row: "+row.cells[1].textContent.indexOf(val)+" val: "+ val)
            row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
        }

        return {
            init: function () {
                var inputs = document.getElementsByClassName('light-table-filter');
                Arr.forEach.call(inputs, function (input) {
                    input.oninput = _onInputEvent;
                });
            }
        };
    })(Array.prototype);

    document.addEventListener('readystatechange', function () {
        if (document.readyState === 'complete') {
            LightTableFilter.init();
        }
    });

})(document);

function insertarUsuario() {
    if (verificaCampoNum($("#id").val()) && verificaCampoNum($("#telefonoTrabajo").val()) && verificaCampoNum($("#celular").val())) {
        var usuario = {
            id: $("#id").val(),
            contrasena: $("#contrasena").val(),
            nombre: $("#nombre").val(),
            apellidos: $("#apellido").val(),
            correo: $("#correo").val(),
            fechaNacimiento: $("#fecha_nacimiento").val(),
            direccion: $("#direccion").val(),
            telefonoTrabajo: $("#telefonoTrabajo").val(),
            celular: $("#celular").val(),
            rol: 0
        };

        $.ajax({
            url: "http://localhost:8081/Backend/api/usuario/insertar",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(usuario),
            success: function () {
                window.location.href = "/aerolinea/views/admin/gestionAviones.jsp";
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


function getUsuario() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/usuario/get" + id,
        type: "get",
        success: function (listadoAviones) {
            recargarTabla(listadoAviones);
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "Página no encontrada");
            },
            500: function () {
                mostrarMensaje("error", "Error de servidor");
            }
        }
    });
}


function recargarUsuario(usuarioActual) {
    return usuarioActual.getId();
}



function showPerfil() {
    if (sessionStorage.getItem('usuario') !== null) {
        var usuario = JSON.parse(sessionStorage.getItem('usuario'));
        $("#idPerfil").val(usuario.id);
        $("#nombrePerfil").val(usuario.nombre);
        $("#apellidosPerfil").val(usuario.apellidos);
        $("#correoPerfil").val(usuario.correo);
        if (usuario.fechaNacimiento !== undefined) {
            $("#fechaNacimientoPerfil").val(usuario.fechaNacimiento.substr(0, 10));
        }
        $("#direccionPerfil").val(usuario.direccion);
        $("#telefonoTrabajoPerfil").val(usuario.telefonoTrabajo);
        $("#celularPerfil").val(usuario.celular);
        $("#contrasenaPerfil").val(usuario.contrasena);
    }
}

function actualizarUsuario() {
    var usuario = {
        id: $("#id").val(),
        contrasena: $("#contrasena").val(),
        nombre: $("#nombre").val(),
        apellidos: $("#apellido").val(),
        correo: $("#correo").val(),
        fechaNacimiento: $("#fecha").val(),
        direccion: $("#direccion").val(),
        telefonoTrabajo: $("#telefonoTrabajo").val(),
        celular: $("#celular").val(),
        rol: 0
    };
    $.ajax({
        url: "http://localhost:8081/Backend/api/usuario/actualizar",
        type: "put",
        contentType: "application/json",
        data: JSON.stringify(usuario),
        success: function (listadoUsuarios) {
            recargarTabla(listadoUsuarios);
            mostrarMensaje("success", "Actualizado correctamente");
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "Ocurrió un error");
            },
            500: function () {
                mostrarMensaje("error", "Error de servidor");
            }
        }
    });
}

function login() {

    if (verificaCampoNum($("#idLogin").val())) {
        var usuario = {
            id: $("#idLogin").val(),
            contrasena: $("#contrasena").val()
        };
        $.ajax({
            url: "http://localhost:8081/Backend/api/usuario/login",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(usuario),
            success: function (usuarioRest) {
                mostrarMensaje("success", "Logeado correctamente");
                sessionStorage.setItem("usuario", JSON.stringify(usuarioRest));
                if (usuarioRest.rol === 1) {
                    window.location.href = "/aerolinea/views/admin/gestionAviones.jsp";
                } else {
                    window.location.href = "/aerolinea/views/usuario/compraTiquetes.jsp";
                }
            },
            statusCode: {
                404: function () {
                    mostrarMensaje("error", "Ocurrió un error");
                },
                500: function () {
                    mostrarMensaje("error", "Ocurrió un error en el servidor");
                }
            }
        });
    } else {
        mostrarMensaje("error", "Datos incorrectos");
    }
}

function getHistorialTiquetes() {
    if (sessionStorage.getItem('usuario') !== null) {
        var id = JSON.parse(sessionStorage.getItem('usuario')).id;
        $.ajax({
            url: "http://localhost:8081/Backend/api/usuario/tiquetesUsuario/" + id,
            type: "get",
            success: function (listadoHistorialTiquetes) {
                paginacion(listadoHistorialTiquetes);
            },
            statusCode: {
                404: function () {
                    mostrarMensaje("error", "Página no encontrada");
                },
                500: function () {
                    mostrarMensaje("success", "Lista vacia");
                }
            }
        });
    }
}

function recargarTablaMisTiquetes(listadoHistorialTiquetes) {
    $("#tabla-historial").html("");
    var tabla = $("#tabla-historial");
    listadoHistorialTiquetes.forEach(tiquete => {
        var row = $('<tr></tr>');
        $('<td></td>').html(tiquete.vueloId.id).appendTo(row);
        $('<td></td').html(tiquete.id).appendTo(row);
        $('<td></td>').html(tiquete.usuarioId.id).appendTo(row);
        $('<td></td>').html(tiquete.precioFinal).appendTo(row);
        $('<td></td>').html(tiquete.filaAsisento).appendTo(row);
        $('<td></td>').html(tiquete.columnaAsiento).appendTo(row);
        $('<td></td>').html(tiquete.formaPago).appendTo(row);
        var btn = "<button class='btn btn-warning btn-sm mx-2' data-bs-toggle='modal' data-bs-target='#staticBackdrop'" +
                "onclick='getVuelo(" + tiquete.vueloId.id + ")'" + "><i class='fas fa-id-card'></i> Ver vuelo</button>";
        $('<td></td>').html(btn).appendTo(row);
        row.appendTo(tabla);
    });
}

function checkLogin() {
    var pathname = window.location.pathname;
    if (sessionStorage.getItem('usuario') === null) {
        window.location.href = pathname;
    }

}

function listarHistorialuUsuario() {
    var pathname = window.location.pathname;
    if (pathname === "/aerolinea/views/usuario/misTiquetes.jsp") {
        getHistorialTiquetes();
    }
}

showPerfil();
listarHistorialuUsuario();