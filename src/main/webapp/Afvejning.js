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

var count = 0;
function formfilled(temp, prodid, rbid, tara, vaegt, lab) {
    count = count + 1;
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

function findreckomps() {
    alert("recstarted");
    $.ajax({
        url: "rest/afvejning/loadreckomps",
        data: $('#labidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: function (data) {
            if (data != null) {
                /*saver(JSON.stringify(data));*/
                saver(data);
            }
            else alert("recfailed")
        }
    });
}
function findprodKomps() {
    alert("prodstarted");
    $.ajax({
        url: "rest/afvejning/loadprodkomps",
        data: $('#labidform').serialize(),
        contentType: "application/x-www-form-urlencoded",
        dataType: "JSON",
        method: 'POST',
        success: function (data) {
            if (data != null) {
                saver2(data);
            /*x½saver2(JSON.stringify(data));*/
                insertKomps();
            }
            else alert("prodfailed")
        }
    });
}
    /*[{"pbId":"PB00001","rbId":"RB00001","tara":12.0,"netto":1000.0,"labID":"B00001"}]*/
    /*[{"receptID":"REC00001","raavareID":"R00001","nonNetto":1000.0,"tolerance":3.0},{"receptID":"REC00001","raavareID":"R00002","nonNetto":1000.0,"tolerance":3.0}]*/
function insertKomps() {
    alert("insertstarted");
    /*var jsonrec = JSON.parse(rec);*/
    alert(rec[0].receptID + " : " + prod[0].rbId);
    for (i = 0; i < Object.keys(rec).length; i++) {
        for (j = 0; j < Object.keys(prod).length; j++) {
            getraavID(prod[j].rbId);
            alert(rec[i].raavareID + " : " + holder);
            if (rec[i].raavareID == holder)
                $("#faerdig").append(formfilled(getraavnavn(prod[j].rbId), prod.pbId, prod.rbId, prod.tara, prod.netto, prod.labID));
        }
    }
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

    function getraavnavn(ravid) {
        $.ajax({
            url: "rest/afvejning/getrnavn",
            data: ravid,
            dataType : "text",
            method: 'POST',
            success: function (data) {
                saver3(data);
            }
        });
    }



