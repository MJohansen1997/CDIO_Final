//@Author Jacob Kjærby Christensen S174130
$(document).ready(function() {
    loadRecepter();

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
    $.ajax({
        url:'rest/recept/createRecept/'+receptNavn,
        method:'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data){
            if (data != null) {
                loadRecepter();
                $("#opretForm").toggle();
            } else alert("Det indtastede pbID kan ikke findes!");
        }
    })
}

function loadRecepter() {
    $.get('rest/recept/allRecepts', function (data, textStatus, req) {
        console.log(data);
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
        console.log("DATA", data);
        if (!data){
            alert("Der findes ikke nogen med det brugerID");
            return;
        }
        $form = $('#redigerInfoForm');
        $form.find('[name="receptID"]').val(data.receptID);
        $form.find('[name="receptNavn"]').val(data.receptNavn);
        $("#redigerInfoForm").toggle();
    });
}

function updateRecept(){
    var data = $('#redigerInfoForm').serializeJSON();
    $.ajax({
        url:'rest/recept/updateRecept',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data){
            if (data != null) {
                loadRecepter();
                $("#redigerForm").toggle();
                $("#redigerInfoForm").toggle()
            } else alert("Det indtastede pbID kan ikke findes!");
        }
    })
}

function deleteBruger(receptID) {
    $.ajax({
        url:'rest/recept/deleteRecept/'+ receptID,
        method: 'POST',
        contentType:"application/json",
        data: JSON.stringify(receptID),
        success: function(data){
            if (data != null) {
                loadRecepter();
                $("#sletForm").toggle();
            } else alert("Det indtastede pbID kan ikke findes!")

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
        hideAllForms()
        $("#opretForm").toggle();
    });
}
function buttonRediger(){
    $("#buttonRediger").click(function () {
        hideAllForms()
        $("#redigerForm").toggle();
    });
}
function buttonSlet() {
    $("#buttonSlet").click(function () {
        hideAllForms()
        $("#sletForm").toggle();
    });
}
function buttonFind(){
    $("#buttonFind").click(function () {
        hideAllForms()
        $("#findForm").toggle();
    });
}

function hideAllForms(){
    $('form').each(function(){
        if ( $(this).css('display') == 'block') {
            $(this).toggle();
        }
    });
}
