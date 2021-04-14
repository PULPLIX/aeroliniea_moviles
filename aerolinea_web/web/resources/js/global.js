/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function verificaCampoVacio(valor) {
    if (valor !== "") {
        return true;
    } else {
        return false;
    }
}

function verificaCampoNum(valor) {
    var reg = new RegExp('^[0-9]+$');
    if (reg.test(valor)) {
        return true;
    } else {
        return false;
    }
}

function llenarModal(objeto) {

    for (const atributo in objeto) {
        if (atributo === "id") {
            $("#id-modal").html(objeto[atributo]);
        }
        if (atributo === "fecha") {
            console.log(objeto[atributo]);
            document.getElementById("fecha-modal").valueAsDate = objeto[atributo];
        } else {
            $("#" + atributo + "-modal").val(objeto[atributo]);
        }
    }
}

function mostrarMensaje(tipoMensaje, contenido) {
    $("#texto-mensaje").html(contenido);
    if (tipoMensaje === "success") {
        $("#icono-mensaje").css({'background-image': "url('/aerolinea/images/Iconos/success.png')", 'color': ''});
        $("#texto-mensaje").css({'color': "#046704e8"});
    } else {
        $("#icono-mensaje").css({'background-image': "url('/aerolinea/images/Iconos/error.png')", 'color': ''});
        $("#texto-mensaje").css({'color': "#dc3545"});
    }

    $("#mensaje-info").addClass("d-flex");
    $("#mensaje-info").show();
    $("#mensaje-info").css("animation-name", "mostrar-mensaje");

    setTimeout(function () {
        $("#mensaje-info").css("animation-name", "esconder-mensaje");
    }, 4000);
    setTimeout(function () {
        $("#mensaje-info").removeClass("d-flex");
        $("#mensaje-info").hide();
        window.history.replaceState(
                {},
                "/" + window.location.href.split("?")[0]
                );
    }, 4790);
}

function setUsuario() {
    if (sessionStorage.getItem('usuario') !== null) {
        $("#idUsuarioSession").html(JSON.parse(sessionStorage.getItem('usuario')).id);
    }
}

function logout() {
    sessionStorage.clear();
    sessionStorage.removeItem("usuario");
    window.location.href = "/aerolinea/views/index.jsp";
}

function islogin() {
    if (sessionStorage.getItem('usuario') !== null) {
        $('#iniciarSession').hide();
        $('#registrarse').hide();
        $('#loginTrue').show();
        $('#item_compraTiquetes').show();
        $('#item_misTiquetes').show();
        if (JSON.parse(sessionStorage.getItem('usuario')).rol === 1){
            $('#item_administrar').show();
        }else{
            $('#item_administrar').hide();
        }
    } else {
        $('#iniciarSession').show();
        $('#registrarse').show();
        $('#loginTrue').hide();
        $('#item_compraTiquetes').show();
        $('#item_misTiquetes').hide();
        $('#item_administrar').hide();
    }
}


islogin();
setUsuario();