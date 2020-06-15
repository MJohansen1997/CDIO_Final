$(document).ready(function () {
    $("button").hide();
    $("#labbut").show().click(function () {
        $.ajax({
            url: "rest/afvejning/verifylab",
            data: $('#labidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            success: function (data) {
                if (data == 'true') {
                    $("#labbut").remove();
                    $("#subprod").show();
                    $("#labidform").append("<label> Produktions ID : <input type=\"text\" name=\"produktionsid\" required /></label>");
                }
                else return alert("ingen laborant fundet med dette ID");
            }
        });
    });
    $("#subprod").click(function () {
        $.ajax({
            url: "rest/afvejning/verifyprod",
            data: $('#labidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            dataType: "text",
            method: 'POST',
            success: function (data) {
                if (data != '') {
                    $("#subprod").remove();
                    $("#subkomp").show();
                    $("#labidform")
                        .append("<label> Recept ID : <input type=\"text\" name=\"receptid\" readonly /> </label>")
                        .append("<label> Recept Navn : <input type=\"text\" name=\"receptnavn\" readonly /></label>");
                }
                else return alert("ingen Produktion matcher dette id");
            }
        });
    });

});

