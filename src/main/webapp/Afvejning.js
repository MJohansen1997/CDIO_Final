var rec;
var prod;

$(document).ready(function () {
    $(".inlab").hide();
    $(".resulttitle").hide();
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
                    $("#subprod").remove();
                    $("#subkomp").show();
                    $("#produktionsid").prop("readonly", true);
                    $("[name='reclab']").show();
                    $("#receptid").val(data.receptID);
                    $("#receptnavn").val(data.receptNavn);
                    $(".resulttitle").show();
                    await findreckomps();
                    await findprodKomps();
                    insertkomps();

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
        "<button id='but" + raavnavn + "'> Click me</button>";
    return form;
}
async function findreckomps() {
    console.log("findrec starts");
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
                console.log("prod : " + prod);
            }
            else alert("prodfailed")
        }
    });
}
function insertkomps() {
    console.log(rec);
    rec.forEach((r_item) => {
        prod.forEach((p_item) => {
            $.ajax({
                url: "rest/afvejning/IdBatch?rid=" + r_item.raavareID + "&rbid=" + p_item.rbId,
                method: 'POST',
                success: function (data) {
                    console.log(data);
                    console.log(JSON.stringify(data));
                    if(data.status == "false"){
                        $("#mangler").append(formunfilled(data.name, parseFloat(r_item.tolerance), $("#userid").val(),
                            r_item.raavareID, $("#produktionsid").val(), r_item.nonNetto));
                        $(document).find("#but" + data.name + "").on("click",function () {
                            var formname = "#" + data.name + "form";
                            var tada = $(document).find(formname).serializeJSON();
                            console.log(tada);
                            $.ajax({
                                url:'rest/afvejning/createpbk',
                                method: 'POST',
                                contentType: "application/json",
                                data: JSON.stringify(tada),
                                success: function (){
                                    alert("Oprettet bruger GZ homie");
                                    $("#mangler").empty();
                                    $("#faerdig").empty();
                                    insertkomps();
                                }
                            })
                        });
                    }
                    if(data.status == "true"){
                        $("#faerdig").append(formfilled(data.name, p_item.pbId, p_item.rbId, p_item.tara,
                            p_item.netto, p_item.labID));
                    }
                }
            });
        })
    })
}




