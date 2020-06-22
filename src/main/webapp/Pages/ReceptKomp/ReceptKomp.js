//@Author Mikkel Johansen s175194
$(document).ready(function() {
    loadBrugere();
    $('#opretForm').on('submit', function(e){
        e.preventDefault();
        createKomp()
    });

    $('#findForm').on('submit', function(e){
        e.preventDefault();
        getKomp($('#findForm').serializeJSON().receptID);
        $("#findForm-table").show();
    });

    $('#sletForm').on('submit', function(e){
        e.preventDefault();
        deleteKomp($('#sletForm').serializeJSON().brugerID)
    });

    buttonOpret();
    buttonFind();
    buttonSlet();
});

function createKomp() {
    var data = $('#opretForm').serializeJSON();
    console.log(data);
    $.ajax({
        url:'rest/rk/createKomp',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data){
            if (data == 'true'){
                loadBrugere();
                alert("Oprettet receptkomp");
                $("#opretForm").toggle();
            }
            else alert("valider venligst dit input og prøv igen")
        }
    })
}

function loadBrugere() {
    $.get('rest/rk/all', function (data, textStatus, req) {
        console.log(data);
        $("#receptbody").empty();
        $.each(data, function (i, brugerValue) {
            $('#receptbody').append(generateHTMLTable(brugerValue));
        });
    });
}

function getKomp(receptID){
    $.get('rest/rk/findKomps/'+receptID, function (data, textStatus, req) {
        console.log(data);
        if(data != null){
            $("#findFormBody").empty();
            $.each(data, function (i, receptValue) {
                $("#findFormBody").append(generateHTMLTable(receptValue));
            });
        } else {
            alert("Der findes ikke noget med dette receptID")
        }
    });
}

function deleteKomp() {
    $.ajax({
        url:'rest/rk/deletekomp',
        method: 'POST',
        data: $('#sletForm').serialize(),
        contentType: "application/x-www-form-urlencoded",
        success: function(){
            loadBrugere();
            $("#sletForm").toggle();
        }
    })
}

function generateHTMLTable(reckomp){
    return '<tr><td>' + reckomp.receptID + '</td>' +
        '<td>' + reckomp.raavareID + '</td>' +
        '<td>' + reckomp.nonNetto + '</td>' +
        '<td>' + reckomp.tolerance + '</td></tr>'
}

function buttonOpret(){
    $("#buttonOpret").click(function () {
        hideAllForms();
        $("#opretForm").toggle();
    });
}
function buttonSlet() {
    $("#buttonSlet").click(function () {
        hideAllForms();
        $("#sletForm").toggle();
    });
}
function buttonFind(){
    $("#buttonFind").click(function () {
        hideAllForms();
        $("#findForm").toggle();
    });
}

function hideAllForms(){
    $('form').each(function(){
        if ( $(this).css('display') == 'block')
        {
            $(this).toggle();
        }
    });
}



