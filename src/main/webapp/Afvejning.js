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
                        findreckomps();
                        findprodKomps();



                    } else return alert("ingen Produktion matcher dette id");
                }
            });
        });


    });
var rec;
function saver(o) {
    rec = o;

}
var prod;
function saver2(o) {
    prod = o;
}
var holder;
function saver3(o) {
    holder = o;
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
function formunfilled(raavnavn) {
    var form =
        "<form class='form' method='POST' id='" +raavnavn+ "form'>" +
        "<h3>" +raavnavn+ "</h3>"+
        "<label class='batch'> Råvare Batch : <input class='input' type='text' readonly /></label>" +
        "<label class='batch'> Tara Vægt : <input class='input' type='text' readonly /></label>" +
        "<label class='batch'> Tolerance : <input class='input' type='text' readonly /></label>" +
        "<label class='batch'> Råvare Mængde : <input class='input' type='text' readonly /></label>" +
        "<label class='batch'> Laborant : <input class='input' type='text' readonly /></label>" +
        "<input class='input' type='hidden' id='prodid' readonly/>" +
        "</form>" +
        "<button id='but" + raavnavn + "'> Click me</button>";
    return form;
}

function findreckomps() {
    $.ajax({
        url: "rest/afvejning/loadreckomps",
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
function findprodKomps() {
    $.ajax({
        url: "rest/afvejning/loadprodkomps",
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
    /*[{"pbId":"PB00001","rbId":"RB00001","tara":12.0,"netto":1000.0,"labID":"B00001"}]*/
    /*[{"receptID":"REC00001","raavareID":"R00001","nonNetto":1000.0,"tolerance":3.0},{"receptID":"REC00001","raavareID":"R00002","nonNetto":1000.0,"tolerance":3.0}]*/
function insertKomps() {
    insertprodkomps(insertForm);
}
function getraavID(ravid) {
    $.ajax({
        url: "rest/afvejning/getrid",
        data: ravid,
        dataType : "text",
        method: 'POST',
        success: function (data) {
            alert(data);
            saver3(data);
        }
    });
}

function insertForm() {
    for (i = 0; i < Object.keys(rec).length; i++) {
        for (j = 0; j < Object.keys(prod).length; j++) {
            $.ajax({
                url: "rest/afvejning/IdBatch?rid=" + rec[i].raavareID + "&rbid=" + prod[j].rbId,
                method: 'POST',
                success: function (data) {
                    console.log(data);
                    if(data != ""){
                        $("#mangler").append(formunfilled(data));
                        $(document).find("#but" + data + "").on("click",function () {
                            console.log("lol")
                        });
                    }


                    /*$("#mangler").append(formfilled(data, prod[0].pbId, prod[0].rbId, prod[0].tara, prod[0].netto, prod[0].labID))*/

                }
            });
        }
    }
}

function insertprodkomps(callback) {
    for (i = 0; i < Object.keys(prod).length; i++){
        $.ajax({
            url: "rest/afvejning/getrnavn",
            data: prod[i].rbId,
            dataType: "text",
            method: 'POST',
            success: function (data) {
                $("#faerdig").append(formfilled(data, prod[0].pbId, prod[0].rbId, prod[0].tara, prod[0].netto, prod[0].labID))
            }
        });
    }
    callback()
}




