$(document).ready(function () {
    loadListPB();
    loadRecepter();

    $('#opretForm').on('submit', function (e) {
        e.preventDefault();
        createPB()

    });

    $('#findForm').on('submit', function (e) {
        e.preventDefault();
        getPB($('#findForm').serializeJSON().brugerID);
        $("#findForm-table").show();
    });

    // $('#sletForm').on('submit', function (e) {
    //     e.preventDefault();
    //     deleteBruger($('#sletForm').serializeJSON().brugerID)
    // });

    // $('#redigerForm').on('submit', function (e) {
    //     e.preventDefault();
    //     updateGetBruger($('#redigerForm').serializeJSON().brugerID)
    //     $("#redigerInfoForm").toggle();
    // });

    $('#redigerInfoForm').on('submit', function (e) {
        e.preventDefault();
        updatePB($('#redigerInfoForm').serializeJSON().brugerID)
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
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            loadListPB();
            $("#redigerForm").toggle();
            $("#redigerInfoForm").toggle();
        }
    })
}



// function updateGetBruger(brugerID) {
//     $.get('rest/brugere/findBruger/' + brugerID, function (data, textStatus, req) {
//         console.log("DATA", data)
//         if (!data) {
//             alert("Der findes ikke nogen med det brugerID")
//             return;
//         }
//
//
//         $form = $('#redigerInfoForm')
//         $form.find('[name="brugerID"]').val(data.brugerID)
//         $form.find('[name="brugerNavn"]').val(data.brugerNavn)
//         $form.find('[name="rolle"]').val(data.rolle)
//         $form.find('[name="ini"]').val(data.ini)
//         $form.find('[name="cpr"]').val(data.cpr)
//         $form.find('[name="password"]').val(data.password)
//     });
// }

function getPB(pbID) {
    $.get('rest/PB/findPB' + pbID, function (data, textStatus, req) {
        if (typeof data != "undefined") {
            $("#findFormBody").empty().append(generateHTMLTable(data));
        } else {
            alert("Der findes ikke nogen med det brugerID");
        }
    });
}

// function deleteBruger(brugerID) {
//     var data = $('#sletForm').serializeJSON();
//     console.log(data)
//     $.ajax({
//         url: 'rest/brugere/deleteBruger/' + brugerID,
//         method: 'POST',
//         contentType: "application/json",
//         data: JSON.stringify(data),
//         success: function () {
//             loadBrugere();
//             $("#sletForm").toggle();
//         }
//     })
// }
function loadListPB() {
    $.get('rest/PB/allPB', function (data, textStatus, req) {
        $("#pbBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#pbBody').append(generateHTMLTable(brugerValue));
        });
    });
}
function generateHTMLTable(pbatch) {
    return '<tr><td>' + pbatch.produktbatchID + '</td>' +
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

// function generateReceptListe(pbatch) {
//     let $dropdown = $("#chooseRecept")
//     $dropdown.append($("<option />").text(pbatch.receptID));
//     // return '<option>' + pbatch.receptID + '</option>'
// }

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

