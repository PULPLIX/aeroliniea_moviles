const webSocket = new WebSocket("ws://localhost:8081/Backend/tiquetesSocket");

webSocket.addEventListener("open", function (event) {
    console.log("SE HA ABIERTO UN SOCKET TIQUETES");
});

webSocket.addEventListener("message", function (event) {
    actualizarAsientos(event.data);
});

function include(file) {
    var script = document.createElement('script');
    script.src = file;
    script.type = 'text/javascript';
    script.defer = true;
    document.getElementsByTagName('head').item(0).appendChild(script);
}


function actualizarTiquetes(asientosSeleccionados) {
    var vuelo = JSON.parse(sessionStorage.getItem("vueloSelected"));
    var asientos = [vuelo.id, JSON.stringify(asientosSeleccionados)];
    var message = [JSON.stringify(asientos)];
    webSocket.send(message);
}

function actualizarAsientos(message) {
    var array = JSON.parse(message);
    var idVuelo = array[0];
    var vuelo = JSON.parse(sessionStorage.getItem("vueloSelected"));
    renderizarAsientos(vuelo.avionId, vuelo.id)

//    if (vuelo.id === idVuelo) {
//        var asientosSeleccionados = JSON.parse(array[1]);
//
//        asientosSeleccionados.forEach(asiento => {
//            var asientoId = asiento[0] + "" + asiento[1];
//
//            $("#" + asientoId).attr("onclick", "").unbind("click");
//            $("#" + asientoId).removeClass("btn");
//
//            $("#" + asientoId).removeClass("btn-azul-avion");
//            $("#" + asientoId).addClass("ocupado");
//            var btn = document.getElementById(asientoId);
//            delete btn.dataset.bsToggle;
//            $("#" + asientoId).html('<i class="fas fa-times-circle"></i>');
//        });
//    }
}