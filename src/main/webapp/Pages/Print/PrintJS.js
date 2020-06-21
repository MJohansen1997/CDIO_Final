function printDiv(divName) {
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
}


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

//sjov og ballade
function getproduktBatch(pbID){
    $.get('rest/Print/PrintProduktBatch/'+pbID, function (data, textStatus, req) {
        if(typeof data != "undefined"){
            $("#tilstand").empty().append(generateHTMLTable(data));
        } else {
            alert("Ingen tilstand")
        }
    });
}

$("#tilstand").show(localStorage.getItem("status"));
