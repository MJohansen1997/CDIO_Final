//@author Hau, Shania s195477
// ASDF
$(document).ready(function () {
    loadRaavarerBatch();
    $('#opretForm').on('submit', function (e) {
        e.preventDefault();
        createRaavarerBatch()
    });

    $('#findForm').on('submit', function (e) {
        e.preventDefault();
        getRaavarerBatch($('#findForm').serializeJSON().rbID)
        $("#findForm-table").show();
    });

    $('#redigerForm').on('submit', function (e) {
        e.preventDefault();
        updateGetRaavarerBatch($('#redigerForm').serializeJSON().rbID)
        $("#redigerInfoForm").toggle();
    });

    $('#redigerInfoForm').on('submit', function (e) {
        e.preventDefault();
        updateRaavarerBatch()
    });

    $('#sletForm').on('submit', function (e) {
        e.preventDefault();
        deleteRaavarerBatch($('#sletForm').serializeJSON().rbID)
    });

    buttonOpret();
    buttonFind();
    buttonRediger();
    buttonSlet();
    submitUpdate();
});

function createRaavarerBatch() {
    var data = $('#opretForm').serializeJSON();
    console.log(data)
    $.ajax({
        url: 'rest/raavarebatch/createRaavarerBatch',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            if(data != null){
                loadRaavarerBatch();
                alert("Oprettet bruger GZ homie")
                $("#opretForm").toggle();
            } else alert("Kunne ikke oprette dette råvarebatch")

        }
    });
}

function updateRaavarerBatch() {
    var data = $('#redigerInfoForm').serializeJSON();
    console.log(data)
    $.ajax
    ({
        url: 'rest/raavarebatch/updateRaavereBatch',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            if(data != null){
                loadRaavarerBatch();
                $("#redigerForm").toggle();
                $("#redigerInfoForm").toggle();
            }else alert("Kunne ikke redigere denne råvarebatch")
        }
    })
}

function loadRaavarerBatch() {
    $.get('rest/raavarebatch/allRaavareBatches', function (data, textStatus, req) {
        console.log(data)
        $("#raavBatchBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#raavBatchBody').append(generateHTMLTable(brugerValue));
        });
    });
}


function updateGetRaavarerBatch(rbID) {
    console.log(rbID);
    $.get('rest/raavarebatch/getRaavareBatch/' + rbID, function (data, textStatus, req) {
        console.log("DATA", data)
        if (!data) {
            alert("Der findes ikke nogen med den Råvarer ID")
            return;
        }


        $form = $('#redigerInfoForm');
        $form.find('[name="rbID"]').val(data.rbID);
        $form.find('[name="raavID"]').val(data.raavID);
        $form.find('[name="maengde"]').val(data.maengde);
    });
}

function deleteRaavarerBatch(rbID){
    console.log(rbID);

    var data = $('#sletForm').serializeJSON();
    console.log(data)
    $.ajax
    ({
        url:'rest/raavarebatch/deleteRB/'+ rbID,
        method: 'POST',
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function(){
            if (data!=null){
                loadRaavarerBatch();
                $("#sletForm").toggle();
            }else alert("Kunne ikke slette råvarebatchen")
        }
    })
}


function getRaavarerBatch(rbID) {
    console.log(rbID);
    $.get('rest/raavarebatch/getRaavareBatch/' + rbID, function (data, textStatus, req) {
        if (typeof data != "undefined") {
            $("#findFormBody").empty().append(generateHTMLTable(data));
        } else {
            alert("Der findes ikke nogen med den Råvarer ID");
        }
    });
}


function generateHTMLTable(raavarerBatch) {

    localStorage.setItem("rbID", raavarerBatch.rbID );
    console.log(localStorage.getItem("rbId"));

    localStorage.setItem("raavId", raavarerBatch.raavId);
    console.log(localStorage.getItem("raavId"));

    localStorage.setItem("maengde", raavarerBatch.maengde );
    console.log(localStorage.getItem("maengde"));

    return '<tr><td>' + raavarerBatch.rbID + '</td>' +
        '<td>' + raavarerBatch.raavId + '</td>' +
        '<td>' + raavarerBatch.maengde + '</td></tr>'

}


function buttonOpret() {
    $("#buttonOpret").click(function () {
        hideAllForms()
        console.log("Click Opret Knap")
        $("#opretForm").toggle();
    });
}

function buttonRediger() {
    $("#buttonRediger").click(function () {
        hideAllForms()
        $("#redigerForm").toggle();
    });
}

function buttonFind() {
    $("#buttonFind").click(function () {
        hideAllForms()
        $("#findForm-table").toggle();
        $("#findForm").toggle();
    });
}

function buttonSlet() {
    $("#buttonSlet").click(function () {
        hideAllForms()
        $("#sletForm").toggle();
    })
}

function hideAllForms() {
    $('form').each(function () {
        if ($(this).css('display') == 'block') {
            $(this).toggle();
        }
    });
}