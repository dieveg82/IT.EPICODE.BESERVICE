

function cambiaPagina(stringPagHtml) {
    location.assign(stringPagHtml);
}

function cercaFattura(event, inputNumeroFattura) {
    console.log(inputNumeroFattura.value);
    if (inputNumeroFattura.value === "") {
        // questa funziona previene non fa inviare i dati della form se la condizione if è rispettata ( ossia manca la
        // di un campo)
        event.preventDefault();
        alert(`non e' stato compilato un campo`);
    } else {
        sessionStorage.setItem("numeroFattura", inputNumeroFattura.value);
        location.assign("fattura.html");
        // cercaFattura(inputNumeroFattura.value);
    }
}


function inserisciFattura(event, inputFattura) {

    let cliente = false;
    for (i = 0; i < inputFattura.length; i++) {
        if (inputFattura[i].value === "") {
            alert(`non e' stato compilato un campo`);
            cliente = true;
            break;
        }
        if (cliente === false) {
            cliente = "";
            let connettore = new XMLHttpRequest();
            let partitaIva = document.getElementById("partitaIva");

            connettore.open("GET", "http://localhost:8080/controllercliente/findbypartitaiva?partitaIva=" + partitaIva.value, true);
            connettore.send();
            connettore.onreadystatechange = function () {
                if (connettore.readyState === 4 && connettore.status === 200) {
                    cliente = JSON.parse(this.responseText);
                    let fattura = new Object();
                    for (i = 0; i < inputFattura.length; i++) {
                        if (inputFattura[i].id === "dataFattura") {
                            data = inputFattura[i].value;
                            data = data.slice(0, 10);
                            fattura.data = data;
                        }
                        if (inputFattura[i].id === "numeroFattura") {
                            fattura.numeroFattura = inputFattura[i].value;
                        }
                        if (inputFattura[i].id === "anno") {
                            fattura.anno = inputFattura[i].value;
                        }
                        if (inputFattura[i].id === "importoFattura") {
                            fattura.importo = inputFattura[i].value;
                        }
                    }
                    fattura.stato = {
                        id: 1,
                        nome: "Da pagare"
                    }
                }
                fattura.cliente = cliente;
                fattura = JSON.stringify(fattura);
                console.log(fattura);

            }
        }
    }
}

