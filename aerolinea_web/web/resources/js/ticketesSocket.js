const webSocket = new WebSocket("ws://localhost:8081/aerolinea/tiquetesSocket");


webSocket.addEventListener("open", function (event) {
    console.log("SE HA ABIERTO UN SOCKET");
});

webSocket.addEventListener("message", function (event) {
    actualizarAsientos(event.data);
});

function actualizarTiquetes(asientoId) {
    var vuelo = JSON.parse(sessionStorage.getItem("vueloSelected"));
    var asiento = [vuelo.id, asientoId];
    var message = [JSON.stringify(asiento)];
    webSocket.send(message);
}

function actualizarAsientos(message) {
    console.log(message);
    var array = JSON.parse(message);
    var idVuelo = array[0];
    var asientoId = array[1];
    var vuelo = JSON.parse(sessionStorage.getItem("vueloSelected"));
    console.log(vuelo.id === idVuelo);
    if (vuelo.id === idVuelo) {
        $("#" + asientoId).html('<i class="fas fa-times-circle"></i></a>');
    }
}