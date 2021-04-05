
function evtAsiento(btn) {
    if (btn.classList.contains('active')) {
        btn.innerHTML = '<i class="fas fa-check-circle"></i>'
        btn.classList.add("seleccionado");
    } else {
        btn.innerHTML = '<i class="fas fa-user"></i>'
        btn.classList.remove("seleccionado");
    }
    return row;
}

