    $(document).ready(function () {
        var recobj = null;
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
                    } else return alert("ingen laborant fundet med dette ID");
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
                        findKomps();
                    } else return alert("ingen Produktion matcher dette id");
                }
            });
        });

        $.ajax({
            url: "rest/afvejning/loadreckomps",
            data: $('#labidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            dataType: "JSON",
            method: 'POST',
            success: function (data) {
                if (data != null) {
                    recobj = data;
                }
                alert("hi " + data[0].receptID)
            }
        });
    });
    var count = 0;
function formfilled(temp, prodid, rbid, tara, vaegt, lab) {
    count = count + 1;
    var form =
        "<form class='form' action='rest/afvejning/verifylab' method='POST' id='labidform'>" +
        "<h3>Recept Komponent</h3>"+
        "<label class='batch'> Råvare Batch : <input class='input' type='text' value='" + rbid + "' readonly /></label>" +
        "<label class='batch'> Tara Vægt : <input class='input' type='text' value='" + tara +"' readonly /></label>" +
        "<label class='batch'> Råvare Mængde : <input class='input' type='text' value='" + vaegt +"' readonly /></label>" +
        "<label class='batch'> Laborant : <input class='input' type='text' value='" + lab + "' readonly /></label>" +
        "<input class='input' type='hidden' id='prodid' value='" + prodid + "'"+
        "</form>";
    return form;
}
function getraavnavn(ravid) {
    var saved;
    $.ajax({
        url: "rest/afvejning/getrnavn?raavid=" + ravid,
        dataType: "text",
        method: 'GET',
        success: function (data) {
            saved = data;
        }
    });
    return saved
}

function findKomps() {
    $.ajax({
        url: "rest/afvejning/loadprodkomps",
        data: $('#labidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: function (data) {
            if (data != null) {
                $.each(data, function (i, data) {
                    $("#faerdig").append(formfilled(getraavnavn(data.rbid), data.pbId, data.rbId,
                        data.tara, data.netto, data.labID));
                })
            }
        }
    });
}


