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
                    insertprodkomps();
                    insertForm();

                } else return alert("ingen Produktion matcher dette id");
            }
        });
    });
});
var rec;
function saver(o) {
    rec = o;
    console.log("saver1 done");
}
var prod;
function saver2(o) {
    prod = o;
    console.log("saver2 done");
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
function formunfilled(raavnavn, tolerance, lab, raavid, prodid) {
    var form =
        "<form class='form' method='POST' id='" +raavnavn+ "form'>" +
        "<h3>" +raavnavn+ "</h3>"+
        "<label class='batch'> Råvare Batch : <input class='input' id='" + raavnavn + "rb' name='rbId' type='text' required /></label>" +
        "<label class='batch'> Tara Vægt : <input class='input' id='" + raavnavn + "tara' name='tara' type='number' step='0.01' required /></label>" +
        "<label class='batch'> Tolerance (%) : <input class='input' id='" + raavnavn + "tol' type='text' value='" + tolerance + "' readonly /></label>" +
        "<label class='batch'> Råvare Mængde : <input class='input' id='" + raavnavn + "maengde' name='netto' type='number' step='0.01' required /></label>" +
        "<input class='input' id='" + raavnavn + "lab' name='labID' type='text' value='" + lab + "' readonly />" +
        "<input class='input' id='" + raavnavn + "id' type='text' value='" + raavid + " ' readonly/>" +
        "<input class='input' id='" + raavnavn + "prod' name='pbId' type='text' value='" + prodid + "' readonly/>" +
        "</form>" +
        "<button id='but" + raavnavn + "'> Click me</button>";
    return form;
}

async function findreckomps() {
    console.log("findrec starts");
    $.ajax({
        url: "rest/afvejning/loadreckomps",
        data: $('#labidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: async function (data) {
            if (data != null) {
                await saver(data);
                console.log("rec : " + rec)
            }
            else alert("recfailed")
        }
    });
    return "";
}
async function findprodKomps() {
    console.log("findprod starts");
    $.ajax({
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
    return "";
}

function insertForm() {
    console.log(rec);
    rec.forEach((r_item) => {
        prod.forEach((p_item) => {
            $.ajax({
                url: "rest/afvejning/IdBatch?rid=" + r_item.raavareID + "&rbid=" + p_item.rbId,
                method: 'POST',
                success: function (data) {
                    if(data != ""){
                        $("#mangler").append(formunfilled(data, parseFloat(r_item.tolerance), $("#userid").val(),
                            r_item.raavareID, $("#produktionsid").val()));
                        $(document).find("#but" + data + "").on("click",function () {
                            var formname = "#" + data + "form";
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
                                    findreckomps();
                                }
                            })
                        });
                    }
                }
            });
        })
    })
}

function insertprodkomps() {
    console.log(prod + " : insertprod");
    prod.forEach(function (item) {
        $.ajax({
            url: "rest/afvejning/getrnavn",
            data: item.rbId,
            dataType: "text",
            method: 'POST',
            success: function (data) {
                $("#faerdig").append(formfilled(data, item.pbId, item.rbId, item.tara,
                    item.netto, item.labID));
            }
        });
    });
}




