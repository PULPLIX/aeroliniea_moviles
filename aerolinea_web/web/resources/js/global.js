/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
        $("#icono-mensaje").css({'background-image': "url('/aerolinea/resources/images/Iconos/success.png')", 'color': ''});
        $("#texto-mensaje").css({'color': "#046704e8"});
    } else {
        $("#icono-mensaje").css({'background-image': "url('/aerolinea/resources/images/Iconos/error.png')", 'color': ''});
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
