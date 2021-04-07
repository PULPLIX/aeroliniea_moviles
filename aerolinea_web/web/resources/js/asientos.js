
function evtAsiento(btn) {
    if (btn.classList.contains('active')) {
        btn.innerHTML = '<i class="fas fa-check-circle"></i>'
    } else {
        btn.innerHTML = '<i class="fas fa-user px-m"></i>'
    }
    return row;
}


function getVuelo(id) {
    $.ajax({
        url: "/aerolinea/api/vuelos/get/" + id,
        type: "GET",
        success: function (vuelo) {
            renderizarAsientos(vuelo.avionId, vuelo.id);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function renderizarAsientos(avion, id_vuelo) {
    $.ajax({
        url: "/aerolinea/api/vuelos/asientosOcupados/" + id_vuelo,
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
                asiento = '<a href="#" class="asiento ocupado" data-fila="' + i + '" data-columna="' + j + '"><i class="fas fa-times-circle"></i></a>'
            } else {
                asiento = '<a href="#" class="btn btn-azul-avion asiento" role="button" data-bs-toggle="button"' +
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

getVuelo(1);