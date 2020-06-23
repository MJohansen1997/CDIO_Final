/** @Author Mikkel Johansen s175194*/
let sumnetto = 0;
let sumtara = 0;
$(document).ready(function(){
    $(".loader").show();
    var id = getUrlVars()["pbid"];
    $.ajax({
        url: "rest/PB/IdBatch?pbID=" + id,
        method: 'POST',
        success: function (data) {
            console.log(data);
            $("#Udskrevet").text(curday("-"));
            $("#ProduktBatchID").text(data.prodID);
            $("#ReceptID").text(data.recID);
            $("#ProdStatus").text(data.status);
            $("#ProdStart").text(data.start);
            $("#ProdSlut").text(data.start);
            $.each(data.raavlist, function (i, list) {
                $("#raavarebatches").append(htmllist(list));
                sumnetto = Number(sumnetto) + Number(list.netto);
                sumtara = Number(sumtara) + Number(list.tara);
            });
            $("#SumTara").text(sumtara);
            $("#SumNetto").text(sumnetto);
            $(".loader").hide();
            window.print();
        }
    });
});


function htmllist(list) {
return "<div>" +
        "<table class='iwantbox' id=\"top\">" +
            "<tr>" +
                "<th class=\"bold\">Råvare id:</th>" +
                "<td id=\"RaavareID\">" + list.ravID + "</td>" +
            "</tr>" +
            "<tr>" +
                "<th class=\"bold\">Råvare navn:</th>" +
                "<td  class=\"txtlige\" id=\"RaavareNavn\">" + list.ravName + "</td>" +
            "</tr>" +
        "</table>" +
        "<hr class=\"line\">" +
        "<table>"+
        "<tr>"+
        "<th class=\"bold\">Mængde</th>"+
        "<th class=\"bold\">Tolerance</th>"+
        "<th class=\"bold\">Tara</th>"+
       "<th class=\"bold\">Netto</th>"+
        "<th class=\"bold\">Batch</th>"+
        "<th class=\"bold\">Opr.</th>"+
        "</tr>"+
        "<tr id=\"bots\">"+
        "<td id=\"Maengde\">" + list.maengde + "</td>"+
        "<td id=\"Tolerance\">" + list.tolerance + "</td>"+
        "<td id=\"Tara\">" + list.tara + "</td>"+
        "<td id=\"Netto\">" + list.netto + "</td>"+
        "<td id=\"Batch\">" + list.rb + "</td>"+
        "<td id=\"Opr\">" + list.ini + "</td>"+
        "</tr>"+
        "</table>"+
        "</div>";
}


var curday = function(sp){
    today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //As January is 0.
    var yyyy = today.getFullYear();

    if(dd<10) dd='0'+dd;
    if(mm<10) mm='0'+mm;
    return (mm+sp+dd+sp+yyyy);
};

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
