$(document).ready(function(){
    console.log(secret);
    $.get("rest/PB/IdBatch" + secret , function (data){
        console.log(data);
        //$("#Udskrevet").text(/*curday("-")*/);
        //$("#ProduktBatchID").text(data.prodID);
        //$("#ReceptID").text(data.recID);
        //$("#ProdStatus").text(data.status);
        //$("#ProdStart").text(data.start);
        //$("#ProdSlut").text(data.start);
        /*$.each(data.raavlist, function (i, list) {
            $("#raavarebatches").append(htmllist(list))
        })*/
    })
});


function htmllist(list) {
return "<div>" +
        "<table id=\"top\">" +
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
        "<td id=\"Maengde\">" + data.maengde + "</td>"+
        "<td id=\"Tolerance\">" + data.tolerance + "</td>"+
        "<td id=\"Tara\"> + data.tara + </td>"+
        "<td id=\"Netto\"> + data.netto + </td>"+
        "<td id=\"Batch\"> + data.tolerance + </td>"+
        "<td id=\"Opr\"> + data.init + </td>"+
        "</tr>"+
        "</table>"+
        "</div>";
}


/*var curday = function(sp){
    today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //As January is 0.
    var yyyy = today.getFullYear();

    if(dd<10) dd='0'+dd;
    if(mm<10) mm='0'+mm;
    return (mm+sp+dd+sp+yyyy);
};*/
