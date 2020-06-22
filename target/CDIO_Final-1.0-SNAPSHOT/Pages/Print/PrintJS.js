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


function generateStartetHTMLTable(pb){
    return '<tr>' +
        '<td>' + pb.pbID + '</td>' +
        '<td>' + pb.status + '</td>' +
        '<td>' + pb.recID + '</td>' +
        '<td>' + pb.startdato + '</td>' +
        '<td>' + pb.raavID + '</td>' +
        '<td>' + pb.maengde + '</td>' +
        '</tr>'
}

function generateUnderProduktionHTMLTable(pb){
    return '<tr>' +
        '<td>' + pb.pbID + '</td>' +
        '<td>' + pb.status + '</td>' +
        '<td>' + pb.recID + '</td>' +
        '<td>' + pb.rdID + '</td>' +
        '<td>' + pb.tara + '</td>' +
        '<td>' + pb.netto + '</td>' +
        '<td>' + pb.startdato + '</td>' +
        '<td>' + pb.raavID + '</td>' +
        '<td>' + pb.maengde + '</td>' +
        '</tr>'
}

//sjov og ballade
function getPB(recID, pbID) {
    return $.ajax({
        url: "rest/findPB/" + pbID,
        method: 'POST',
        success: function (data) {
            if (typeof data != "undefined") {
                $("#tilstand").empty().append(generateHTMLTable(data));
                $("#findForm-table").toggle();
            } else {
                alert("Fejl! pbID findes ikke");
            }
        }
    });
}



document.getElementById("#tilstand").innerHTML = localStorage.getItem("status");
$("#tilstand").show(localStorage.getItem("status"));

