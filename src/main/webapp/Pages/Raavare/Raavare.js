/** @author s195485, Nikolai Kristensen */
$(document).ready(function()
{
    loadRaavarer();

    $('#opretForm').on('submit', function(e) {
        e.preventDefault();
        createRaavarer()
    });

    $('#findForm').on('submit', function(e) {
        e.preventDefault();
        getRaavarer($('#findForm').serializeJSON().raavID)
        $("#findForm-table").show();
    });

    $('#sletForm').on('submit', function(e) {
        e.preventDefault();
        deleteRaavarer($('#sletForm').serializeJSON().raavID)
    });

    $('#redigerForm').on('submit', function(e) {
        e.preventDefault();
        updateGetRaavarer($("#raavId").val())
        $("#redigerInfoForm").toggle();
    });

    $('#redigerInfoForm').on('submit', function(e) {
        e.preventDefault();
        updateRaavarer($('#redigerInfoForm').serializeJSON().raavID)
    });

    buttonOpret();
    buttonFind();
    buttonRediger();
    buttonSlet();
    submitUpdate();
});

function createRaavarer() {
    var data = $('#opretForm').serializeJSON();
    console.log(data)
    if(hasNumber(data.raavNavn) && hasNumber(data.leverandor) || hasNumber(data.raavNavn)){
        alert("We in the if statement fam")
        return
    }
    $.ajax({
        url:'rest/raavarer/createRaavarer',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data) {
            console.log(data)
            if (!data) {
                loadRaavarer();
                alert("Oprettet Råvarer")
                $("#opretForm").toggle();
            }
        }
    });
}

function hasNumber(myString) {
    return /\d/.test(myString);
}

function updateRaavarer() {
    var data = $('#redigerInfoForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/raavarer/updateRaavere',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data){
            console.log(data)
            if(data == "true") {
                loadRaavarer();
                $("#redigerForm").toggle();
                $("#redigerInfoForm").toggle();
                alert("Ændring Gemt");
            }
            else {
                alert("Kunne ikke tage inputtet");
            }
        }
    });
}

function loadRaavarer() {
    $.get('rest/raavarer/allRaavarer', function (data, textStatus, req) {
        console.log("DATA", data);
        $("#raavBody").empty();
        $.each(data, function (i, raavValue) {
            console.log(raavValue);
            $('#raavBody').append(generateHTMLTable(raavValue));
        });
    });
}

function updateGetRaavarer(raavID) {
    console.log(raavID);
    $.get('rest/raavarer/findRaavarer/' + raavID, function (data, textStatus, req) {
        console.log("DATA", data)
        if (data) {
            $form = $('#redigerInfoForm')
            $form.find('[name="raavID"]').val(data.raavID)
            $form.find('[name="raavNavn"]').val(data.raavNavn)
            $form.find('[name="leverandor"]').val(data.leverandor)
        }
        else{
        alert("Der findes ikke nogen med den Råvarer ID")
        }
    });
}

function getRaavarer(raavID) {
    console.log(raavID);
    $.get('rest/raavarer/findRaavarer/' + raavID, function (data, textStatus, req) {
        if(typeof data != "undefined") {
            $("#findFormBody").empty().append(generateHTMLTable(data));
        }
        else {
            alert("Der findes ikke nogen med den Råvarer ID");
        }
    });
}

function deleteRaavarer(raavID) {
    var data = $('#sletForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/raavarer/deleteRaavarer/'+ raavID,
        method: 'POST',
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function() {
            loadRaavarer();
            $("#sletForm").toggle();
        }
    })
}

function generateHTMLTable(raavarer) {
    return '<tr><td>' + raavarer.raavID + '</td>' +
        '<td>' + raavarer.raavNavn + '</td>' +
        '<td>' + raavarer.leverandor + '</td></tr>'
}

function buttonOpret() {
    $("#buttonOpret").click(function () {
        hideAllForms()
        console.log("Hvad sker der?")
        $("#opretForm").toggle();
    });
}
function buttonRediger() {
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
function buttonFind() {
    $("#buttonFind").click(function () {
        hideAllForms()
        $("#findForm-table").toggle();
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



