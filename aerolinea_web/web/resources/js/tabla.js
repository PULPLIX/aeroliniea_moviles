
function crearFila(vuelo) {
    var modalidad = ""
    if (vuelo.modalidad == 1) { modalidad = 'Solo ida' }
    else { modalidad = 'Ida y vuelta' }
    var row = '<div class="row  tabla-vuelos"><div class="col-9 "><div class="card card-info"><div class="card-body"><div class="container-fluid">' +
        '<div class="row"><div class="col-2"><img src="/aerolinea/resources/images/logoBanner.png" alt="" class="w-100"></div>' +
        '<div class="col-8 d-flex justify-content-between align-items-center"> <div class="texto-azul d-flex ">' + vuelo.cidadOrigen.nombre +
        '<div class="separate-city"> </div>' + vuelo.cidadDestino.nombre + '</div></div><div class="col-2 d-flex justify-content-end text-celeste">' +
        vuelo.fecha + ' - ' + modalidad + '</div> </div><div class="row pt-3 d-flex justify-content-end">' +
        '<div class="col-6 d-flex justify-content-start align-items-center"><span class="horario-tabla">' + vuelo.horario.diaSemana +
        '<i class="fas fa-long-arrow-alt-right"></i>' + vuelo.horario.horaLlegada + '</span><span class="duracion-tabla">' +
        '<i class="fas fa-history "></i>&nbsp;' + vuelo.duracion + '</span></div>' +
        '<div class="col-4 d-flex justify-content-end align-items-center text-muted avion-info">' +
        '<span><i class="fas fa-plane text-celeste"></i></span><span><i class="fas fa-wifi"></i>' +
        '</span><span><i class="fas fa-plug"></i></span><span><i class="fas fa-mug-hot"></i></span>' +
        '<span><i class="fas fa-desktop"></i></span></div></div></div></div></div></div>' +
        '<div class="col-3 p-0 "><div class="card w-100  d-flex justify-content-center align-items-center">' +
        '<div class="card-body d-flex align-items-center flex-column "><div class="precio">$' + vuelo.precio + '</div>' +
        '<div class="precio-info">Precio por tiquete c/u</div><div>' +
        '<button class="btn btn-outline-celeste fw-bold">Ver asientos</button></div></div></div></div></div>';
    return row;
}

