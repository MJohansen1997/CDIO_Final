$(document).ready(function () {
    $.getScript("jquery.serializejson.js");
    loadListPB();
    loadRecepter();

    $('#opretForm').on('submit', function (e) {
        e.preventDefault();
        createPB()
    });

    $('#findForm').on('submit', function (e) {
        e.preventDefault();
        getPB($('#findForm').serializeJSON().pbID);
        $("#findForm-table").show();
    });

    $('#sletForm').on('submit', function (e) {
        e.preventDefault();
        deletePB($('#sletForm').serializeJSON().pbID)
    });

    $('#redigerForm').on('submit', function (e) {
        e.preventDefault();
        updateGetBruger($('#redigerForm').serializeJSON().pbID);

    });

    $('#redigerInfoForm').on('submit', function (e) {
        e.preventDefault();
        updatePB($('#redigerInfoForm').serializeJSON())
    });

    $('#printForm').on('submit', function(e) {
        e.preventDefault();
        secret = $("#onlyusefullthing").val();
        window.open("Print.html?pbid=" + secret , "_blank");
    });

    buttonOpret();
    buttonFind();
    buttonRediger();
    buttonSlet();
    buttonVaelg();
});

function createPB() {
    $("#chosenStatus").val($("#chooseStatus").val());
    $("#chosenRecept").val($("#chooseRecept").val());
    var data = $('#opretForm').serializeJSON();
    console.log(data);
    $.ajax({
        url: 'rest/PB/createPB',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            if (data != null) {
                loadListPB();
                alert("Oprettet produktbatch gz homie");
                $("#opretForm").toggle();
            } else alert("Fejl! Kunne ikke oprette produktbatch")

        }
    })
}

function updatePB() {
    var data = $('#redigerInfoForm').serializeJSON();
    console.log(data);
    $.ajax({
        url: 'rest/PB/updatePB',
        method: 'PUT',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            if (data != null) {
                loadListPB();
                $("#redigerForm").toggle();
                $("#redigerInfoForm").toggle();
            } else alert("Fejl! Kunne ikke opdatere produktbatch")

        }
    })
}

function updateGetBruger(pbID) {
    $.get('rest/PB/findPB/' + pbID, function (data, textStatus, req) {
        console.log("DATA", data)
        if (!data) {
            alert("Fejl! kan ikke finde pbID");
            return;
        }
        $form = $('#redigerInfoForm');
        $form.find('[name="pbID"]').val(data.pbID);
        $form.find('[name="status"]').val(data.status);
        $("#redigerInfoForm").toggle();
        });
    }

function getPB(pbID) {
    $.get('rest/PB/findPB/' + pbID, function (data, textStatus, req) {
        if (typeof data != "undefined") {
            $("#pbBody").empty().append(generateHTMLTable(data));
            $("#findForm-table").toggle();
        } else {
            alert("Fejl! pbID findes ikke");
        }
    });
}

function deletePB(pbID) {
    $.ajax({
        url: 'rest/PB/deletePB/' + pbID,
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(pbID),
        success: function (data) {
            if (data != null) {
                loadListPB();
                $("#sletForm").toggle();
            } else alert("Det indtastede pbID kan ikke findes!")
            }
        }
    )
}

function loadListPB() {
    $.get('rest/PB/allPB', function (data, textStatus, req) {
        console.log(data);
        $("#pbAllBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#pbAllBody').append(generateHTMLTable(brugerValue));
        });
    });
}

function generateHTMLTable(pbatch) {
    if (pbatch.slutdato != null) {
        return '<tr><td>' + pbatch.pbID + '</td>' +
            '<td>' +  pbatch.status + '</td>' +
            '<td>' + pbatch.receptID + '</td>' +
            '<td>' + (new Date(pbatch.startdato)).toLocaleString() + '</td>' +
            '<td>' + (new Date(pbatch.slutdato)).toLocaleString()+ '</td></tr>'
    } else {
        return '<tr><td>' + pbatch.pbID + '</td>' +
            '<td>' + pbatch.status + '</td>' +
            '<td>' + pbatch.receptID + '</td>' +
            '<td>' + (new Date(pbatch.startdato)).toLocaleString() + '</td>' +
            '<td>' + '</td></tr>'
    }
}



function loadRecepter() {
    let cr  = $("#chooseRecept");
    $.get('rest/recept/allRecepts', function (data, textStatus, req) {
        console.log(data);
        $.each(data, function (i, receptValues) {
            cr.append(new Option(receptValues.receptNavn + " : " + receptValues.receptID, receptValues.receptID));
        });

    });
}

function buttonOpret() {
    $("#buttonOpret").click(function () {
        hideAllForms()
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
        $("#findForm").toggle();
    });
}

function buttonVaelg() {
    $("#buttonPrint").click(function () {
        hideAllForms()
        $("#printForm").toggle();
    });
}

function hideAllForms(){
    $('form').each(function(){
        if ( $(this).css('display') == 'block') {
            $(this).toggle();
        }
    });
}

var secret;
var prev;
