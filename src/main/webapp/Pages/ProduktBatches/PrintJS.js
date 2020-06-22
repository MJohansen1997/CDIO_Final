var PB;
var PBK;
var raav;
var raavB;


$(document).ready(function(){

    $('#printForm').on('submit', function(e) {
        e.preventDefault();
        getPB($('#printForm').serializeJSON().pbID,('#printForm').serializeJSON().recID);
        getProduktBactKomp($('#printForm').serializeJSON().pbID)
        getRaavarerBatch(PBK.rbID);
        getRaavarer(raavB.raavID)
    });
})

function generateHTMLTable(pb){
    return '<tr>' +
        '<td>' + pb.pbID + '</td>' +
        '<td>' + pb.status + '</td>' +
        '<td>' + pb.recID + '</td>' +
        '<td>' + pb.rdID + '</td>' +
        '<td>' + pb.tara + '</td>' +
        '<td>' + pb.netto + '</td>' +
        '<td>' + pb.labID + '</td>' +
        '<td>' + pb.startdato + '</td>' +
        '<td>' + pb.slutdato + '</td>' +
        '<td>' + pb.raavID + '</td>' +
        '<td>' + pb.maengde + '</td>' +
        '</tr>'
}

function savePB(o) {
    return PB = o;
}
function saveRaav(o) {
    return raav = o;
}
function saveRaavB(o) {
    return raavB = o;
}
function savePBK(o) {
    return PBK = o;
}

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

function generateUnderProduktionHTMLTable(pb, pbk, raavB){
    return '<tr>' +
        '<td>' + pb.pbID + '</td>' +
        '<td>' + pb.status + '</td>' +
        '<td>' + pb.recID + '</td>' +
        '<td>' + pbk.rbID + '</td>' +
        '<td>' + pbk.tara + '</td>' +
        '<td>' + pbk.netto + '</td>' +
        '<td>' + (new Date(pb.startdato)).toLocaleString() + '</td>' +
        '<td>' + raavB.raavID + '</td>' +
        '<td>' + raavB.maengde + '</td>' +
        '</tr>'
}

//sjov og ballade


async function getPB(pbID,rbID) {
    $.ajax({
        url: 'rest/produktbatches/BatchKompPost?pbID=' + pbID + '&rbID=' + rbID,
        method: 'POST',
        success: async function (data) {
            if (typeof data != "undefined") {
                await savePB(data)
            } else {
                alert("Der findes ikke nogen Produkt Batch Komp ID")
            }
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
            }
        else {
            alert("Der findes ikke nogen Produkt Batch Komp ID")
        }
    });
}

async function saveAllVariables(){

}

function buttonVaelg() {
    $("#buttonPrint").click(function () {
        $("#printForm").toggle();
    });
}





document.getElementById("#tilstand").innerHTML = localStorage.getItem("status");
$("#tilstand").show(localStorage.getItem("status"));

