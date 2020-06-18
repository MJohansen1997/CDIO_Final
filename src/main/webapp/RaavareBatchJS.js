$(document).ready(function () {
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

                    $("#oprRaavare").show();
                    $("#visRaavare").show();
                } else return alert("ingen laborant fundet med dette ID");
            }
        });
    });

    //ikke done
    $("#visRaavareBatch").click(function () {
        $.ajax({
            url: "rest/RaavareBatch/verifyRB",
            data: $('#prolederidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            dataType: "JSON",
            method: 'POST',
            success: function (data) {
                if (data != null) {
                    $("#visRaavareBatch").remove();
                    $("#verificerRBid").show();


                    $("#rbid").prop("readonly", true);
                    $("[name='raaID']").show();
                    $("#raavareid").val(data.raavareid);

                    visRaavareBatch();
                } else return alert("ingen RaavareBatch matcher dette id");
            }
        });
    });

    $("#oprRaavareBatch").click(function () {
        $.ajax({
            url: "rest/RaavareBatch/verifyRB",
            data: $('#prolederidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            dataType: "JSON",
            method: 'POST',
            success: function (data) {
                if (data != null) {
                    $("#oprRaavareBatch").remove();
                    $("#verificerRBid").show();


                    $("#rbid").prop("readonly", true);
                    $("[name='raaID']").show();
                    $("#raavareid").val(data.raavareid);

                    opretRaavareBatch();
                } else return alert("nået er tastet forkert");
            }
        });
    });

});

var raaBatch;
function saver(o) {
    console.log(o);
    raaBatch = o;
}

var showraaBatch;
function saver2(o) {
    raa = o;
}


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
function visRaavareBatch() {
    $.ajax({
        url: "rest/RaavareBatch/loadRaavareBatch",
        data: $('#prolederidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: function (data) {
            if (data != null) {
                saver2(data);
            }
            else alert("RB failed")
        }
    });
}