var messages = document.getElementById("messages");

var objeto = {
    nombre: "David",
    apellidos: "Aguilar Rojas",
    numerosTelefono: [22340, 2312]
};

console.log(JSON.stringify(objeto));


const webSocket = new WebSocket("ws://http://localhost:8081/Backend/proceso");

webSocket.addEventListener("message", function (event) {
    writeResponse(event.data);
});

webSocket.addEventListener("open", function (event) {
    var message = ["conexion", JSON.stringify(objeto)];
    webSocket.send(JSON.stringify(message));
});

function deleteUser() {
    var message = ["/delete", JSON.stringify(objeto)];
    webSocket.send(JSON.stringify(message));
}

function insert() {
    var message = ["/insert", JSON.stringify(objeto)];

    webSocket.send(JSON.stringify(message));

}
function update() {
    var message = ["/update", JSON.stringify(objeto)];
    webSocket.send(JSON.stringify(message));
}
function send() {
    var text = document.getElementById("messageinput").value;
    webSocket.send(text);
}

function closeSocket() {
    webSocket.close();
}

function writeResponse(text) {
    messages.innerHTML += "<br />" + text;
}

