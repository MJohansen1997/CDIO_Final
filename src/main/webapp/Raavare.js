$(document).ready(function() {
    loadBrugere();
    $('#opretForm').on('submit', function(e){
        e.preventDefault();
        createRaavarer()
    });

    $('#findForm').on('submit', function(e){
        e.preventDefault();
        getBruger($('#findForm').serializeJSON().brugerID)
        $("#findForm-table").show();
    });

    $('#sletForm').on('submit', function(e){
        e.preventDefault();
        deleteBruger($('#sletForm').serializeJSON().brugerID)
    });

    $('#redigerForm').on('submit', function(e){
        e.preventDefault();
        updateGetBruger($('#redigerForm').serializeJSON().brugerID)
        $("#redigerInfoForm").toggle();
    });

    $('#redigerInfoForm').on('submit', function(e){
        e.preventDefault();
        updateBruger($('#redigerInfoForm').serializeJSON().brugerID)
    });

    buttonOpret();
    buttonFind();
    buttonRediger();
    buttonSlet();
});

function createRaavarer() {
    var data = $('#opretForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/Raavarer/createRaavarer',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (){
            loadBrugere();
            alert("Oprettet bruger GZ homie")
            $("#opretForm").toggle();
        }
    })
}

function updateBruger(){
    var data = $('#redigerInfoForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/brugere/updateUser',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (){
            loadBrugere();
            $("#redigerForm").toggle();
            $("#redigerInfoForm").toggle();
        }
    })
}

function loadBrugere() {
    $.get('rest/brugere/allUsers', function (data, textStatus, req) {
        $("#brugerBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#brugerBody').append(generateHTMLTable(brugerValue));
        });
    });
}

function updateGetBruger(brugerID){
    $.get('rest/brugere/findBruger/'+brugerID, function (data, textStatus, req) {
        console.log("DATA", data)
        if (!data){
            alert("Der findes ikke nogen med det brugerID")
            return;
        }


        $form = $('#redigerInfoForm')
        $form.find('[name="brugerID"]').val(data.brugerID)
        $form.find('[name="brugerNavn"]').val(data.brugerNavn)
        $form.find('[name="rolle"]').val(data.rolle)
        $form.find('[name="ini"]').val(data.ini)
        $form.find('[name="cpr"]').val(data.cpr)
        $form.find('[name="password"]').val(data.password)
    });
}

function getBruger(brugerID){
    $.get('rest/brugere/findBruger/'+brugerID, function (data, textStatus, req) {
        if(typeof data != "undefined"){
            $("#findFormBody").empty().append(generateHTMLTable(data));
        } else {
            alert("Der findes ikke nogen med det brugerID")
        }
    });
}

function deleteBruger(brugerID) {
    var data = $('#sletForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/brugere/deleteBruger/'+ brugerID,
        method: 'POST',
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function(){
            loadBrugere();
            $("#sletForm").toggle();
        }
    })
}

function generateHTMLTable(bruger){
    return '<tr><td>' + bruger.brugerID + '</td>' +
        '<td>' + bruger.brugerNavn + '</td>' +
        '<td>' + bruger.ini + '</td>' +
        '<td>' + bruger.cpr + '</td>' +
        '<td>' + bruger.rolle + '</td>' +
        '<td>' + bruger.password + '</td></tr>'
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
function submitUpdate(){
    $("#submit4").click(function () {
        $("#showForm").toggle();
    });
}

