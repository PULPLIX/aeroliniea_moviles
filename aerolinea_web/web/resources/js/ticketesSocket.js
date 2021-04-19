const webSocket = new WebSocket("ws://localhost:8081/aerolinea/tiquetesSocket");


webSocket.addEventListener("open", function (event) {
    console.log("SE HA ABIERTO UN SOCKET");
});

webSocket.addEventListener("message", function (event) {
    actualizarAsientos(event.data);
});

function actualizarTiquetes(asientosSeleccionados) {
    var vuelo = JSON.parse(sessionStorage.getItem("vueloSelected"));
    var asientos = [vuelo.id, JSON.stringify(asientosSeleccionados)];
    var message = [JSON.stringify(asientos)];
    webSocket.send(message);
}

function actualizarAsientos(message) {
    console.log(message);
    var array = JSON.parse(message);
    var idVuelo = array[0];
    var vuelo = JSON.parse(sessionStorage.getItem("vueloSelected"));

    if (vuelo.id === idVuelo) {
        var asientosSeleccionados = JSON.parse(array[1]);
        console.log(asientosSeleccionados);

        asientosSeleccionados.forEach(asiento => {
            console.log(asiento);
            var asientoId = asiento[0] + "" + asiento[1];
//            console.log(asiento);

            $("#" + asientoId).attr("onclick", "").unbind("click");
            $("#" + asientoId).removeClass("btn");

            $("#" + asientoId).removeClass("btn-azul-avion");
            $("#" + asientoId).addClass("ocupado");
            var btn = document.getElementById(asientoId);
            delete btn.dataset.bsToggle;
            $("#" + asientoId).html('<i class="fas fa-times-circle"></i>');
        });
    }
}