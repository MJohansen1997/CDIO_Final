    $(document).ready(function () {
    $(".inlab").hide();
    $("#lablab").show();
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
                    $("#prodlab").show();
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
            dataType: "JSON",
            method: 'POST',
            success: function (data) {
                if (data != null) {
                    $("#subprod").remove();
                    $("#subkomp").show();
                    $("[name='reclab']").show();
                    $("#receptid").val(data.receptID);
                    $("#receptnavn").val(data.receptNavn);
                }
                else return alert("ingen Produktion matcher dette id");
            }
        });
    });
    $("#subkomp").click(function () {
        $("#subkomp").after(form);
    })

    });
var count = 0;
function counter() {
    return count + 1;
}
var form = "<form class=\"form\" action=\"rest/afvejning/verifylab\" method=\"POST\" id='labidform'>" +
    "<label class='inlab' id=\"" + counter() +"\"> Laborarnt ID : <input class=\"input\" type=\"text\" name=\"userid\" required /></label>" +
    "</form>";

