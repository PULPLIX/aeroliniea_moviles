
const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
}

var asientosSeleccionados = [];
function evtAsiento(btn) {
    if (btn.classList.contains('active')) {
        btn.innerHTML = '<i class="fas fa-check-circle"></i>';
        console.log(btn.id);
        actualizarTiquetes(btn.id);
    } else {
        btn.innerHTML = '<i class="fas fa-user px-m"></i>'
    }
}


function getVuelo() {
    let vuelo = sessionStorage.getItem("vueloSelected");
    if (vuelo !== null && vuelo !== undefined) {
        vuelo = JSON.parse(vuelo);
        console.log(vuelo);
        llenarInfo(vuelo);
        renderizarAsientos(vuelo.avionId, vuelo.id);
    }

}

function llenarInfo(vuelo) {
    document.getElementById("origen-title").append(vuelo.rutaId.ciudadOrigen.nombre);
    document.getElementById("destino-title").append(vuelo.rutaId.ciudadDestino.nombre);
    document.getElementById("fecha-title").append(vuelo.fecha);
    document.getElementById("columns-card").innerHTML = (vuelo.avionId.asientosFila);
    document.getElementById("rows-card").innerHTML = (vuelo.avionId.cantidadFilas);
    document.getElementById("capacity-card").innerHTML = (vuelo.avionId.asientosFila * vuelo.avionId.cantidadFilas);
    let usuario = JSON.parse(sessionStorage.getItem("usuario"));
    llenarModalCompra(vuelo, usuario);
}
function llenarModalCompra(vuelo, usuario) {

// LLenar datos del tiquete de avion 
    let modalidad = (vuelo.modalidad === 1 ? "Solo ida" : "Ida y vuelta");
    document.getElementById("origen-modal").append(vuelo.rutaId.ciudadOrigen.nombre);
    document.getElementById("destino-modal").append(vuelo.rutaId.ciudadDestino.nombre);
    document.getElementById("fecha-modal").append(vuelo.fecha);
    document.getElementById("modalidad-modal").append(modalidad);
    document.getElementById("duracion-modal").append(vuelo.duracion);
    document.getElementById("avion-modal").append(vuelo.avionId.tipo + " ( " + vuelo.avionId.marca + " - " + vuelo.avionId.anio + " )");
    console.log(usuario);
    //Llenar datos referentes al usuario que va a comprar el vuelo
    document.getElementById("nombre-modal").append(usuario.nombre + " " + usuario.apellidos);
    document.getElementById("correo-modal").append(usuario.correo);
    document.getElementById("celular-modal").append(usuario.celular);
    document.getElementById("telefono-modal").append(usuario.telefonoTrabajo);
    document.getElementById("direccion-modal").append(usuario.direccion);
    //Llenar datos de compra
    let modalidad2 = (vuelo.modalidad === 2
            ? '<i class="far fa-check-circle texto-verde"></i>'
            : '<i class="far fa-times-circle text-danger"></i>');
    document.getElementById("precio-modal").append(vuelo.rutaId.precio);
    document.getElementById("descuento-modal").append(vuelo.rutaId.porcentajeDescuento + " %");
    document.getElementById("modalidad2-modal").innerHTML = modalidad2;
}
function calcularTotal(vuelo) {
    var descuento = vuelo.rutaId.precio * (vuelo.rutaId.porcentajeDescuento * 0.01);
    return (vuelo.rutaId.precio - descuento) * asientosSeleccionados.length;
}
function getcantAsientos() {
    asientosSeleccionados = [];
    $('.active').each(function (i, obj) {
        asientosSeleccionados.push([obj.dataset.fila, obj.dataset.columna])
    });
    if (asientosSeleccionados.length < 1) {
        mostrarMensaje("error", "Debe seleccionar al menos 1 asiento");
    } else {
        llenarAsientosInfo();
        var comprarModal = new bootstrap.Modal(document.getElementById('comprarModal'), {
            keyboard: false
        })

        comprarModal.toggle();
    }
}

function llenarAsientosInfo() {
    var asientos = "";
    asientosSeleccionados.forEach(asiento => {
        asientos += "[" + asiento[0] + " , " + asiento[1] + "]  ";
    });
    console.log(asientos)
    document.getElementById("asientos-modal").innerHTML = (asientos);
    document.getElementById("cantidadTiquetes-modal").innerHTML = (asientosSeleccionados.length);
    var vuelo = JSON.parse(sessionStorage.getItem("vueloSelected"));
    document.getElementById("total-modal").innerHTML = (calcularTotal(vuelo, asientosSeleccionados.length));
}


function renderizarAsientos(avion, id_vuelo) {
    $.ajax({
        url: "http://localhost:8081/Backend/api/vuelos/asientosOcupados/" + id_vuelo,
        type: "GET",
        success: function (hashAsientos) {
            if ((avion.cantidadFilas % 3) == 0) {
                crearAsientos(3, avion.asientosFila, avion.cantidadFilas, hashAsientos);
            } else {
                crearAsientos(2, avion.asientosFila, avion.cantidadFilas, hashAsientos);
            }
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function crearAsientos(numAsientos, columns, rows, hashAsientos) {
    $("#asientos-container").html("");
    hashAsientos = JSON.parse(hashAsientos);
    for (var i = 1; i <= rows; i++) {
        var $rowElement = $("<div>", {"class": "row"});
        var $colElement = $("<div>", {"class": "col-5 d-flex"}).appendTo($rowElement);
        for (var j = 1; j <= columns; j++) {
            var asiento = "";
            if (hashAsientos[i] != undefined && hashAsientos[i].includes(j)) {
                asiento = '<a href="#" class="asiento ocupado" data-fila="' + i + '" id="'+i+j+'" data-columna="' + j + '"><i class="fas fa-times-circle"></i></a>'
            } else {
                asiento = '<a href="#" class="btn btn-azul-avion asiento" id="'+i+j+'" role="button" data-bs-toggle="button"' +
                        'onclick="evtAsiento(this)" data-fila="' + i + '" data-columna="' + j + '"><i class="fas fa-user  px-m"></i></a>';
            }
            $colElement.append(asiento);
            if (j % numAsientos - 1 && j != columns) {
                if (parseInt(i / 10) > 0) {
                    $colElement.append('<div class="px-d">' + i + '</div>');
                } else {
                    $colElement.append('<div class="px-3">' + i + '</div>');
                }
            }
        }
        $("#asientos-container").append($rowElement);
    }

}

function comprar() {
    var vuelo = JSON.parse(sessionStorage.getItem("vueloSelected"));
    var formaPago = document.getElementById("formaPago-modal").value;
    var data = [
        sessionStorage.getItem("usuario"),
        JSON.stringify(vuelo.id),
        JSON.stringify(asientosSeleccionados),
        formaPago
    ]
    console.log(data);
    $.ajax({
        url: "http://localhost:8081/Backend/api/tiquetes/comprar",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(data),
        beforeSend: mostrarCarga(),
        success: function (asientos) {
            sleep(500).then(() => {
                window.location.href = "/aerolinea/views/index.jsp";
            })
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}
function mostrarCarga() {
    $("#loader").css("display: d-flex");
    $("#loader").show();

}

$("#loader").css("display: none");
$("#loader").hide();
getVuelo();
