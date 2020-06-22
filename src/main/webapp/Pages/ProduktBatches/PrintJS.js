var prodbest;
var ProdBatch;
var raav;
var raavB;
var brugerIni;

$(document).ready(function(){
    buttonVaelg();

    $('#printForm').on('submit', function(e) {
        e.preventDefault();

        if(prodbest.status === 'Afsluttet'){
            $("#findFormBody").append(generateAfsluttetHTMLTable(prodbest, ProdBatch,raavB,brugerIni));
        }



    });
})

/*function savePB(o) {
    return prodbest = o;
}
function saveRaav(o) {
    return raav = o;
}
function saveRaavB(o) {
    return raavB = o;
}
function savePBK(o) {
    return PB = o;
}
function saveBruger(o) {
    return brugerIni = o;
}*/

function generateStartetHTMLTable(pb, raavB){
    return '<tr>' +
        '<td>' + pb.pbID + '</td>' +
        '<td>' + pb.status + '</td>' +
        '<td>' + pb.recID + '</td>' +
        '<td>' + (new Date(pb.startdato)).toLocaleString() + '</td>' +
        '<td>' + raavB.raavID + '</td>' +
        '<td>' + raavB.maengde + '</td>' +
        '</tr>'
}

function generateUnderProduktionHTMLTable(pb, pbk, raavB, bruger){
    return'<tr><td>' + pb.pbID + '</td>' +
        '<td>' + pb.status + '</td>' +
        '<td>' + pb.recID + '</td>' +
        '<td>' + pbk.rbID + '</td>' +
        '<td>' + pbk.tara + '</td>' +
        '<td>' + pbk.netto + '</td>' +
        '<td>' + bruger.ini + '</td>' +
        '<td>' + (new Date(pb.startdato)).toLocaleString() + '</td>' +
        '<td>' + raavB.raavID + '</td>' +
        '<td>' + raavB.maengde + '</td></tr>'
}

function generateUnderProduktionHTMLTable(prod, pbk, raavB, bruger){
    return '<tr><td>' + prod.pbID + '</td>' +
        '<td>' + prod.status + '</td>' +
        '<td>' + prod.recID + '</td>' +
        '<td>' + pbk.rbID + '</td>' +
        '<td>' + pbk.tara + '</td>' +
        '<td>' + pbk.netto + '</td>' +
        '<td>' + bruger.ini + '</td>' +
        '<td>' + (new Date(prod.startdato)).toLocaleString() + '</td>' +
        '<td>' + (new Date(prod.slutdato)).toLocaleString()+ '</td>' +
        '<td>' + raavB.raavID + '</td>' +
        '<td>' + raavB.maengde + '</td></tr>'
}

function generateAfsluttetHTMLTable(pb, pbk, raavB, bruger){
    return '<tr>' +
        '<td>' + pb.pbID + '</td>' +
        '<td>' + pb.status + '</td>' +
        '<td>' + pb.recID + '</td>' +
        '<td>' + pbk.rdID + '</td>' +
        '<td>' + pbk.tara + '</td>' +
        '<td>' + pbk.netto + '</td>' +
        '<td>' + bruger.ini + '</td>' +
        '<td>' + pb.startdato + '</td>' +
        '<td>' + pb.slutdato + '</td>' +
        '<td>' + pb.raavID + '</td>' +
        '<td>' + pb.maengde + '</td>' +
        '</tr>'
}

//sjov og ballade
/*async function getBruger(brugerID){
    $.get('rest/brugere/findBruger/'+brugerID, async function (data, textStatus, req) {
        if(typeof data != "undefined"){
            await saveBruger(data);
        } else {
            alert("Der findes ikke nogen med det brugerID")
        }
    });
}

async function getPB(pbID,recID) {
    $.ajax({
        url: 'rest/PB/getPBRec?pbID=' + pbID + '&recID=' + recID,
        method: 'POST',
        success: async function (data) {
            console.log("Data",data);
                await savePB(data);
        }
    });
}

async function getRaavarer(raavID) {
    console.log(raavID);
    $.get('rest/raavarer/findRaavarer/' + raavID, async function (data, textStatus, req) {
        if(typeof data != "undefined") {
            await saveRaav(data)
        }
        else {
            alert("Der findes ikke nogen med den Råvarer ID")
        }
    });
}

async function getRaavarerBatch(rbID) {
    console.log(rbID);
    $.get('rest/raavarebatch/getRaavareBatch/' + rbID, async function (data, textStatus, req) {
        if (typeof data != "undefined") {
            await saveRaavB(data)
        } else {
            alert("Der findes ikke nogen med den Råvarer ID");
        }
    });
}

async function getProduktBactKomp(pbID) {
    $.get('rest/produktbatches/findProduktBatchKomp/' + pbID, async function (data, textStatus, req) {
        if(typeof data != "undefined") {
                await savePBK(data)
            } else {
            alert("Der findes ikke nogen Produkt Batch Komp ID")
        }
    });
}*/

var curday = function(sp){
    today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //As January is 0.
    var yyyy = today.getFullYear();

    if(dd<10) dd='0'+dd;
    if(mm<10) mm='0'+mm;
    return (mm+sp+dd+sp+yyyy);
};

function buttonVaelg() {
    $("#buttonPrint").click(function () {
        $("#printForm").toggle();
    });
}

document.getElementById("#tilstand").innerHTML = localStorage.getItem("status");
$("#tilstand").show(localStorage.getItem("status"));

