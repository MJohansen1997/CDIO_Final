$(document).ready(function () {
    loadBrugere();

    $(".inproleder").hide();
    $("#prolederid").show();
    $("button").hide();
});

$(document).ready(function () {
    $("#verificer").show().click(function () {
        $.ajax({
            url: "rest/RaavareBatch/verifyProjektleder",
            data: $('#prolederidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            success: function (data) {
                if (data == 'true') {
                    $("#verificer").remove();
                    $("#userid").prop("readonly", true);

                    $("#oprRBbutton").show();
                    $("#visRBbutton").show();
                } else return alert("ingen laborant fundet med dette ID");
            }
        });
    });

    //ikke done
    $("#visRBbutton").click(function () {
        $.ajax({
            url: "rest/RaavareBatch/verifyRB",
            data: $('#prolederidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            dataType: "JSON",
            method: 'GET',
            success: function (data) {
                if (data != null) {
                    $("#visRBbutton").remove();
                    $("#verificerRBid").show();

                    $("#rbid").prop("readonly", true);
                    $("[name='vis']").show();
                    $("#raaID").val(data.raavareid);
                    $("#maen").val(data.maengde);


                    visRaavareBatch();
                } else return alert("ingen RaavareBatch matcher dette id");
            }
        });
    });

    $("#oprRBbutton").click(function () {
        $.ajax({
            url: "rest/RaavareBatch/createRB",
            data: $('#prolederidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            dataType: "JSON",
            method: 'POST',
            success: function (data) {
                if (data != null) {
                    $("#oprRBbutton").remove();

                    $("#rbid").prop("readonly", true);
                    $("[name='opret']").show();
                    $("#raaID").val(data.raavareid);
                    $("#maen").val(data.maengde);

                    opretRaavareBatch();
                } else return alert("nået er tastet forkert");
            }
        });
    });

});

//hvad gør dette?
var raaBatch;
function saver(o) {
    console.log(o);
    raaBatch = o;
}

//ufærdig
function opretRaavareBatch() {
    $.ajax({
        url: "rest/RaavareBatch/createRB",
        data: $('#prolederidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: function (data) {
            if (data != null) {
                saver(data);
            }
            else alert("RB failed")
        }
    });
}




function visRaavareBatch(rbid) {
    $.get('rest/RaavareBatch/findRaavareBatch'+rbid, function (data, textStatus, req) {
        if(typeof data != "undefined"){
            $("#raavareBatchBody").empty().append(generateHTMLTable(data));
        } else {
            alert("Der findes ikke nogen med det raavarebatch ID")
        }
    });
}





function generateHTMLTable(raavarebatch){
    return'<tr><td>' + raavarebatch.rbID + '</td>' +
        '<td>' + raavarebatch.raavareId + '</td>' +
        '<td>' + raavarebatch.maengde + '</td></tr>'
}

function loadBrugere() {
    $.get('rest/brugere/allUsers', function (data, textStatus, req) {
        console.log(data)
        $("#brugerBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#brugerBody').append(generateHTMLTable(brugerValue));
        });
    });
}

function loadRaavareBatch() {
    console.log("Abe")
    $.get('rest/raavarebatch/allRaavareBatches', function (data, textStatus, req) {
        console.log(data);
        $("#raavareBatchBody").empty();
        $.each(data, function (i, raavareBatchValue) {
            $('#raavareBatchBody').append(generateHTMLTable(raavareBatchValue));
        });
    });
}