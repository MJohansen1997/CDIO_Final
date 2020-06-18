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
                    $("#verificerRBid").show();
                } else return alert("ingen laborant fundet med dette ID");
            }
        });
    });
    $("#verificerRBid").click(function () {
        $.ajax({
            url: "rest/RaavareBatch/verifyRB",
            data: $('#prolederidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            dataType: "JSON",
            method: 'POST',
            success: function (data) {
                if (data != null) {
                    $("#verificerRBid").remove();
                    $("#oprRaavare").show();
                    $("#visRaavare").show();

                    $("#rbid").prop("readonly", true);
                    $("[name='raaID']").show();
                    $("#raavareid").val(data.raavareid);

                    opretRaavare();
                    visRaavare();
                } else return alert("ingen Produktion matcher dette id");
            }
        });
    });
});


function opretRaavare() {
    $.ajax({
        url: "rest/RaavareBatch/loadreckomps",
        data: $('#labidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: function (data) {
            if (data != null) {
                saver(data);
            }
            else alert("recfailed")
        }
    });
}
function visRaavare() {
    $.ajax({
        url: "rest/RaavareBatch/loadprodkomps",
        data: $('#labidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: function (data) {
            if (data != null) {
                saver2(data);
                insertKomps();
            }
            else alert("prodfailed")
        }
    });
}

















function buttonOprRaavare(){
    $("#oprRaavare").click(function () {
        $("#resultOpret").toggle();
    });
}
function buttonVisRaavare(){
    $("#visRaavare").click(function () {
        $("#resultVis").toggle();
    });
}