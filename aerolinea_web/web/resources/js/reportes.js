var elements = document.getElementsByClassName('list-group-item-action active');
while (elements.length > 0) {
    elements[0].classList.remove('active');
}
$("#item-dashboard").addClass("active");

let top5Rutas = null;
let facturadoXAnio = null;
let ultimos12Meses = null;

function getUltimos12Meses() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/reportes/ultimosMeses",
        type: "GET",
        success: function (dataSet) {
            console.log(dataSet);
            var labels = getLabels(dataSet);
            var data = getData(dataSet);
            var colors = getGraphColors(dataSet);
            createChart('doughnut', "Facturado en los últimos 12 meses", labels, data, colors, "ultimos12Meses");
        },
        statusCode: {
            404: function () {
                mostrarMensaje('success', "Página no encontrada");
            },
            500: function () {
                mostrarMensaje('success', "Lista vacia");
            }
        }
    });

}

function getfacturadoXAnio() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/reportes/facturadoXAnio",
        type: "GET",
        success: function (dataSet) {
            console.log(dataSet);
            var labels = getLabels(dataSet);
            var data = getData(dataSet);
            var colors = getGraphColors(dataSet);
            createChart('line', "Ingresos del año", labels, data, colors, "facturadoXAnio");

        },
        statusCode: {
            404: function () {
                mostrarMensaje('success', "Página no encontrada");
            },
            500: function () {
                mostrarMensaje('success', "Lista vacia");
            }
        }
    });

}


function getTop5Rutas() {
    $.ajax({
        url: "http://localhost:8081/Backend/api/reportes/top5Rutas",
        type: "GET",
        success: function (dataSet) {
            console.log(dataSet);
            var labels = getLabels(dataSet);
            var data = getData(dataSet);
            var colors = getGraphColors(dataSet);
            createChart('bar', "Top 5 rutas más vendidas", labels, data, colors, "top5Chart");

        },
        statusCode: {
            404: function () {
                mostrarMensaje('success', "Página no encontrada");
            },
            500: function () {
                mostrarMensaje('success', "Lista vacia");
            }
        }
    });
    

}

function createChart(tipo, title, labels, data, colors, id) {
    var top5Chart = document.getElementById(id);
    var myChart = new Chart(top5Chart, {
        type: tipo,
        data: {
            labels: labels,
            datasets: [{
                    label: title,
                    data: data,
                    backgroundColor: colors[0],
                    borderColor: colors[1],
//                    hoverBackgroundColor: colors[2],
                    borderWidth: 1,
                    fill: true,
                    hoverOffset: 10
                }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            pointBackgroundColor: '#fff',
            plugins: {
                filler: {
                    propagate: true,
                },
                legend: {
                    position: 'top',
                },
                title: {
                    text: title,
                    display: true
                }

            }

        }
    });
}

function getGraphColors(dataSet) {
    var graphColors = [];
    var graphOutlines = [];
    var hoverColor = [];

    var dataSetLength = dataSet.length;
    i = 0;
    while (i <= dataSetLength) {
        var randomR = Math.floor((Math.random() * 110) + 100);
        var randomG = Math.floor((Math.random() * 130) + 100);
        var randomB = Math.floor((Math.random() * 115) + 100);

        var graphBackground = "rgba("
                + randomR + ", "
                + randomG + ", "
                + randomB + ", "
                + "0.5)";
        graphColors.push(graphBackground);

        var graphOutline = "rgb("
                + (randomR - 80) + ", "
                + (randomG - 80) + ", "
                + (randomB - 80) + ")";
        graphOutlines.push(graphOutline);

        var hoverColors = "rgba("
                + (randomR + 25) + ", "
                + (randomG + 25) + ", "
                + (randomB + 25) + ",";
        +"0.5)";
        hoverColor.push(hoverColors);

        i++;
    }
    ;

    return [graphColors, graphOutlines, hoverColor];
}


function getLabels(dataSet) {
    var labels = [];
    dataSet.forEach(data => {
        labels.push(data.value);
    });
    return labels;
}

function getData(dataSet) {
    var array = [];
    dataSet.forEach(data => {
        array.push(data.key);
    });
    return array;
}

getTop5Rutas();
getfacturadoXAnio();
getUltimos12Meses();