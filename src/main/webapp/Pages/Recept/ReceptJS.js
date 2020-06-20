$(document).ready(function() {
    loadRecepter()

    $('#opretForm').on('submit', function(e){
        e.preventDefault();
        createRecept($('#opretForm').serializeJSON().receptNavn)
    });

    $('#findForm').on('submit', function(e){
        e.preventDefault();
        getRecept($('#findForm').serializeJSON().receptID)
        $("#findForm-table").show();
    });

    $('#redigerForm').on('submit', function(e){
        e.preventDefault();
        updateGetRecept($('#redigerForm').serializeJSON().receptID)
        $("#redigerInfoForm").toggle();
    });

    $('#redigerInfoForm').on('submit', function(e){
        e.preventDefault();
        updateRecept()
    });

    $('#sletForm').on('submit', function(e){
        e.preventDefault();
        deleteBruger($('#sletForm').serializeJSON().receptID)
    });

    buttonOpret();
    buttonFind();
    buttonRediger();
    buttonSlet();
})

function createRecept(receptNavn) {
    var data = $('#opretForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/recept/createRecept/'+receptNavn,
        method:'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data){
            console.log(data)
            loadRecepter();
            $("#opretForm").toggle();
        }
    })
}

function loadRecepter() {
    $.get('rest/recept/allRecepts', function (data, textStatus, req) {
        console.log(data)
        $("#receptBody").empty();
        $.each(data, function (i, receptValues) {
            $('#receptBody').append(generateHTMLTable(receptValues));
        });
    });
}


function generateHTMLTable(bruger){
    return '<tr><td>' + bruger.receptID + '</td>' +
        '<td>' + bruger.receptNavn + '</td></tr>'
}

function updateGetRecept(receptID){
    $.get('rest/recept/findRecept/'+receptID, function (data, textStatus, req) {
        console.log("DATA", data)
        if (!data){
            alert("Der findes ikke nogen med det brugerID")
            return;
        }
        $form = $('#redigerInfoForm')
        $form.find('[name="receptID"]').val(data.receptID)
        $form.find('[name="receptNavn"]').val(data.receptNavn)
    });
}

function updateRecept(){
    var data = $('#redigerInfoForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/recept/updateRecept',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (){
            loadRecepter();
            $("#redigerForm").toggle();
            $("#redigerInfoForm").toggle();
        }
    })
}

function deleteBruger(receptID) {
    var data = $('#sletForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/recept/deleteRecept/'+ receptID,
        method: 'POST',
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function(){
            loadRecepter();
            $("#sletForm").toggle();
        }
    })
}

function getRecept(receptID){
    $.get('rest/recept/findRecept/'+receptID, function (data, textStatus, req) {
        if(typeof data != "undefined"){
            $("#findFormBody").empty().append(generateHTMLTable(data));
        } else {
            alert("Der findes ikke nogen med det brugerID")
        }
    });
}


function buttonOpret(){
    $("#buttonOpret").click(function () {
        $("#opretForm").toggle();
    });
}
function buttonRediger(){
    $("#buttonRediger").click(function () {
        $("#redigerForm").toggle();
    });
}
function buttonSlet() {
    $("#buttonSlet").click(function () {
        $("#sletForm").toggle();
    });
}
function buttonFind(){
    $("#buttonFind").click(function () {
        $("#findForm-table").toggle();
        $("#findForm").toggle();
    });
}