var elements = document.getElementsByClassName('list-group-item-action active');
while (elements.length > 0) {
    elements[0].classList.remove('active');
}
$("#item-tiquetes").addClass("active");

$('input[name="dates"]').daterangepicker();

function mostrarMensajeCompra(){
    if(sessionStorage.getItem('mensajeCompra') !== null){
        var mensaje = sessionStorage.getItem('mensajeCompra');
        mostrarMensaje('success',mensaje);
        sessionStorage.removeItem('mensajeCompra');
    }
}

$(function () {
    $('input[name="daterange"]').daterangepicker({
        opens: 'left'
    }, function (start, end, label) {
        console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
    });
});

(function(document) {
    'use strict';

    var LightTableFilter = (function(Arr) {

      var _input;

      function _onInputEvent(e) {
        _input = e.target;
        var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
        Arr.forEach.call(tables, function(table) {
          Arr.forEach.call(table.tBodies, function(tbody) {
            Arr.forEach.call(tbody.rows, _filter);
          });
        });
      }

      function _filter(row) {
        var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
        row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
      }

      return {
        init: function() {
          var inputs = document.getElementsByClassName('light-table-filter');
          Arr.forEach.call(inputs, function(input) {
            input.oninput = _onInputEvent;
          });
        }
      };
    })(Array.prototype);

    document.addEventListener('readystatechange', function() {
      if (document.readyState === 'complete') {
        LightTableFilter.init();
      }
    });

  })(document);

function listarTiquetes() {
    $.ajax({
        url: "/aerolinea/api/tiquetes/listar",
        type: "get",
        success: function (listadoTiquetes) {
            recargarTabla(listadoTiquetes);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function recargarTabla(listadoTiquetes) {
    $("#tabla-tiquetes").html("");
    var tabla = $("#tabla-tiquetes");
    
    listadoTiquetes.forEach(tiquete => {
        var row = $('<tr></tr>');
        $('<td></td>').html(tiquete.vueloId.id).appendTo(row);
        $('<td></td').html(tiquete.id).appendTo(row);
        $('<td></td>').html(tiquete.usuarioId.id ).appendTo(row);
        $('<td></td>').html(tiquete.precioFinal).appendTo(row);
        $('<td></td>').html(tiquete.filaAsisento).appendTo(row);
        $('<td></td>').html(tiquete.columnaAsiento).appendTo(row);
        $('<td></td>').html(tiquete.formaPago).appendTo(row);
        var btn = "<button class='btn btn-warning btn-sm mx-2' data-bs-toggle='modal' data-bs-target='#staticBackdrop'" +
                "onclick='getUsuario(" + tiquete.usuarioId.id + ")'" + "><i class='fas fa-id-card'></i> Ver usuario</button>";
        $('<td></td>').html(btn).appendTo(row);
        row.appendTo(tabla);
    });
}

function getUsuario(id) {
    $.ajax({
        url: "/aerolinea/api/usuario/get/" + id,
        type: "GET",
        success: function (usuario) {
            var usuarioModal = {
                id: usuario.id,
                contrasena: usuario.contrasena,
                nombre: usuario.nombre,
                apellidos: usuario.apellidos,
                correo: usuario.correo,
                fechaNacimiento: usuario.fechaNacimiento,
                direccion: usuario.direccion,
                telefonoTrabajo: usuario.telefonoTrabajo,
                celular: usuario.celular,
                rol: usuario.rol
            };
            llenarModal(usuarioModal);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function listarHistorialAdmin() {
    var pathname = window.location.pathname;
    if (pathname === "/aerolinea/views/admin/gestionTiquetes.jsp") {
        listarTiquetes();
    }else{
        mostrarMensajeCompra();
    }
}


listarHistorialAdmin();