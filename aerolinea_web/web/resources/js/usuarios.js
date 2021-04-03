/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getUsuario() {
    $.ajax({
        url: "/aerolinea/api/usuario/get" + id,
        type: "get",
        success: function (listadoAviones) {
            recargarTabla(listadoAviones);
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}


function recargarUsuario(usuarioActual) {
    return usuarioActual.getId();
}

function insertarUsuario() {

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
        url: "/aerolinea/api/usuario/insertar",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(usuario),
        success: function (agregado) {
            console.log(agregado);
            mostrarMensaje("success", "Usuario agregado correctamente");
            window.location.href = "/aerolinea/views/admin/gestionAviones.jsp";
        },
        statusCode: {
            404: function () {
                alert("Hubo un error");
            }
        }
    });
}

function showPerfil() {
    if (sessionStorage.getItem('usuario') !== null) {
        var usuario = JSON.parse(sessionStorage.getItem('usuario'));
        $("#idPerfil").val(usuario.id);
        $("#nombrePerfil").val(usuario.nombre);
        $("#apellidosPerfil").val(usuario.apellidos);
        $("#correoPerfil").val(usuario.correo);
        $("#fechaNacimientoPerfil").val(usuario.fechaNacimiento.substr(0, 10));
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
        url: "/aerolinea/api/usuario/actualizar",
        type: "put",
        contentType: "application/json",
        data: JSON.stringify(usuario),
        success: function (listadoUsuarios) {
            recargarTabla(listadoUsuarios);
            mostrarMensaje("success", "Actualizado correctamente");
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "Ocurrió un error al agregar");
            }
        }
    });
}

function login() {
    var usuario = {
        id: $("#id").val(),
        contrasena: $("#contrasena").val()
    };
    $.ajax({
        url: "/aerolinea/api/usuario/login",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(usuario),
        success: function (usuarioRest) {
            mostrarMensaje("success", "Logeado correctamente");
            console.log(usuarioRest);
            sessionStorage.setItem("usuario", JSON.stringify(usuarioRest));
            if (usuarioRest.rol === 1) {
                window.location.href = "/aerolinea/views/admin/gestionAviones.jsp";
            } else {
                window.location.href = "/aerolinea/views/index.jsp";
            }
        },
        statusCode: {
            404: function () {
                mostrarMensaje("error", "Ocurrió un error al agregar");
            },
            500: function () {
                mostrarMensaje("error", "Ocurrió un error en el servidor");
            }
        }
    });
}

showPerfil();

