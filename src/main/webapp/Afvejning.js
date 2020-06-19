var rec;
var prod;
var exists;

$(document).ready(function () {
    $(".inlab").hide();
    $("#result").hide();
    $("#lablab").show();
    $("button").hide();
});
$(document).ready(function () {
    $("#labbut").show().click(function () {
        $.ajax({
            url: "rest/afvejning/verifylab",
            data: $('#labidform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            success: function (data) {
                if (data == 'true') {
                    $("#labbut").remove();
                    $("#userid").prop("readonly", true);
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
            success: async function (data) {
                if (data != null) {
                    $("#receptid").val(data.receptID);
                    $("#receptnavn").val(data.receptNavn);
                    await findreckomps();
                    await findprodKomps();
                    await insertkomps();
                    $("#subprod").remove();
                    $("#subkomp").show();
                    $("#produktionsid").prop("readonly", true);
                    $("[name='reclab']").show();
                    $("#result").show();
                    if (Object.keys(rec).length == Object.keys(prod).length){
                        changeStatus("Afsluttet");
                        alert("Denne Produktion er Fuldført og Afsluttet")
                    }
                } else return alert("ingen Produktion matcher dette id");
            }
        });
    });
});

function saver(o) {
    return rec = o;
}
function saver2(o) {
    return prod = o;
}
function saver3(o) {
    return exists = o;
}
function formfilled(temp, prodid, rbid, tara, vaegt, lab) {
    var form =
        "<form class='form' action='rest/afvejning/verifylab' method='POST' id='labidform'>" +
        "<h3>" +temp+ "</h3>"+
        "<label class='batch'> Råvare Batch : <input class='input' type='text' value='" + rbid + "' readonly /></label>" +
        "<label class='batch'> Tara Vægt : <input class='input' type='text' value='" + tara +"' readonly /></label>" +
        "<label class='batch'> Råvare Mængde : <input class='input' type='text' value='" + vaegt +"' readonly /></label>" +
        "<label class='batch'> Laborant : <input class='input' type='text' value='" + lab + "' readonly /></label>" +
        "<input class='input' type='hidden' id='prodid' value='" + prodid + "'"+
        "</form>";
    return form;
}
function formunfilled(raavnavn, tolerance, lab, raavid, prodid, req) {
    var form =
        "<form class='form' method='POST' id='" +raavnavn+ "form'>" +
        "<h3>" +raavnavn+ "</h3>"+
        "<label class='batch'> Råvare Batch : <input class='input' id='" + raavnavn + "rb' name='rbId' type='text' required /></label>" +
        "<label class='batch'> Tara Vægt : <input class='input' id='" + raavnavn + "tara' name='tara' type='number' step='0.01' required /></label>" +
        "<label class='batch'> Påkrævet tilsat mængde : <input class='input' id='" + raavnavn + "req' type='text' value='" + req + "' readonly /></label>" +
        "<label class='batch'> Tolerance (%) : <input class='input' id='" + raavnavn + "tol' type='text' value='" + tolerance + "' readonly /></label>" +
        "<label class='batch'> Tilsat mængde : <input class='input' id='" + raavnavn + "maengde' name='netto' type='number' step='0.01' required /></label>" +
        "<input class='input' id='" + raavnavn + "lab' name='labID' type='hidden' value='" + lab + "' readonly />" +
        "<input class='input' id='" + raavnavn + "id' type='hidden' value='" + raavid + " ' readonly/>" +
        "<input class='input' id='" + raavnavn + "prod' name='pbId' type='hidden' value='" + prodid + "' readonly/>" +
        "</form>" +
        "<button id='but" + raavnavn + "'> TIlføj til Produktionen</button>";
    return form;
}
async function findreckomps() {
    return $.ajax({
        url: "rest/afvejning/loadreckomps",
        data: $('#labidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: async function (data) {
            if (data != null) {
                await saver(data);
                console.log("rec : " + JSON.stringify(rec))
            }
            else alert("recfailed")
        }
    });
}
async function findprodKomps() {
    console.log("findprod starts");
    return $.ajax({
        url: "rest/afvejning/loadprodkomps",
        data: $('#labidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: async function (data) {
            if (data != null) {
                await saver2(data);
            }
            else alert("Server fejl:")
        }
    });
}
async function insertkomps() {
    outer:
    for (let i = 0; i < Object.keys(rec).length; i++) {
        inner:
        for (let j = 0; j < Object.keys(prod).length; j++) {
            await getStatus(rec[i].raavareID,prod[j].rbId);
            if(exists.status == "false" && j == Object.keys(prod).length-1) {
                $("#mangler").append(formunfilled(exists.name, parseFloat(rec[i].tolerance), $("#userid").val(),
                    rec[i].raavareID, $("#produktionsid").val(), rec[i].nonNetto));
                $(document).find("#but" + exists.name + "").on("click", function () {
                    var formname = "#" + exists.name + "form";
                    var tada = $(document).find(formname).serializeJSON();
                    console.log(tada);
                    if ($("#produktionsid").val() == $.get("rest/afvejning/findraavid/" +
                        $(document).find("#" + exists.name + "rb"))){
                        $.ajax({
                            url: 'rest/afvejning/createpbk',
                            method: 'POST',
                            contentType: "application/json",
                            data: JSON.stringify(tada),
                            success: function () {
                                alert("Batchen er nu tilsat produktet");
                                $("#mangler").empty();
                                $("#faerdig").empty();
                                insertkomps();
                                changeStatus("Under produktion")
                            }
                        })
                    }
                    else alert("Råvarebatchen matcher ikke til den påkrævede råvare")

                });
            }
            if(exists.status == "true"){
                $("#faerdig").append(formfilled(exists.name, prod[j].pbId, prod[j].rbId, prod[j].tara,
                    prod[j].netto, prod[j].labID));
                continue outer;
            }
        }
    }
}
function getStatus(raavid, raavbatchid) {
    return $.ajax({
        url: "rest/afvejning/IdBatch?rid=" + raavid + "&rbid=" + raavbatchid,
        method: 'POST',
        success: async function (data) {
            await saver3(data);
        }
    });
}
function changeStatus(change) {
    $.ajax({
        url: "rest/afvejning/updatestatus?prodid=" + $("#produktionsid").val() + "&status=" + change,
        method: 'PUT',
    });
}




