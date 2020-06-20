$(document).ready(function () {
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
        $("#redigerInfoForm").toggle();
    });

    $('#redigerInfoForm').on('submit', function (e) {
        e.preventDefault();
        updatePB($('#redigerInfoForm').serializeJSON())
    });

    buttonOpret();
    buttonFind();
    buttonRediger();
    buttonSlet();
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
            loadListPB();
            alert("Oprettet produktbatch gz homie");
            $("#opretForm").toggle();
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
            loadListPB();
            $("#redigerForm").toggle();
            $("#redigerInfoForm").toggle();
        }
    })
}

function updateGetBruger(pbID) {
    $.get('rest/PB/findPB/' + pbID, function (data, textStatus, req) {
        console.log("DATA", data)
        if (!data) {
            alert("Der findes ikke nogen med det brugerID")
            return;
        }
        $form = $('#redigerInfoForm');
        $form.find('[name="pbID"]').val(data.pbID);
        $form.find('[name="status"]').val(data.status);

        });
    }

function getPB(pbID) {
    $.get('rest/PB/findPB/' + pbID, function (data, textStatus, req) {
        if (typeof data != "undefined") {
            $("#pbBody").empty().append(generateHTMLTable(data));
        } else {
            alert("Der findes ikke nogen med det brugerID");
        }
    });
}

function deletePB(pbID) {
    var data = $('#sletForm').serializeJSON();
    console.log(data)
    $.ajax({
        url: 'rest/PB/deletePB/' + pbID,
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            loadListPB();
            $("#sletForm").toggle();
        }
    })
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
    return '<tr><td>' + pbatch.pbID + '</td>' +
        '<td>' + pbatch.status + '</td>' +
        '<td>' + pbatch.receptID + '</td></tr>'
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
        $("#opretForm").toggle();
    });
}

function buttonRediger() {
    $("#buttonRediger").click(function () {
        $("#redigerForm").toggle();
    });
}

function buttonSlet() {
    $("#buttonSlet").click(function () {
        $("#sletForm").toggle();
    });
}

function buttonFind() {
    $("#buttonFind").click(function () {
        $("#findForm-table").toggle();
        $("#findForm").toggle();
    });
}

function submitUpdate() {
    $("#submit4").click(function () {
        $("#showForm").toggle();
    });
}

