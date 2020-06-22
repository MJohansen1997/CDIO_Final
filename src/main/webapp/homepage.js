$(document).ready(function () {
    var home = $("#container").html();
    $("#hjem").click(function () {
        console.log(home);
        $("#container").empty().append(home);
    });
    $("#afvejning").click(function () {
        $("#container").load("Afvejning.html");
        $.getScript("Afvejning.js");
        $.getScript("jquery.serializejson.js");
    });
    $("#rvbatches").click(function () {
        $("#container").load("Pages/RaavareBatches/RaavareBatch.html");
    });
    $("#admin").click(function () {
        $("#container").load("Pages/Admin/AdminBruger.html");
    });
    $("#raavarer").click(function () {
        $("#container").load("Pages/Raavare/Raavare.html");
    });
    $("#recept").click(function () {
        $("#container").load("Pages/Recept/Recept.html");
    });
    $("#receptkomp").click(function () {
        $("#container").load("Pages/ReceptKomp/ReceptKomp.html");
    });
    $("#pdbatches").click(function () {
        $("#container").load("Pages/ProduktBatches/ProduktBatch.html");
    });
    $("#pbKomp").click(function () {
        $("#container").load("Pages/ProduktBatchKomp/ProduktBatchKomp.html");
    });
    $("#logud").click(function () {
        window.location.href = 'index.html';
    });

    userInfo = JSON.parse(localStorage.getItem('userInfo'))
    console.log("LOADING USERINFO", userInfo)
    //console.log("DOES THIS WORK" + window.userInfo.brugerNavn)
    $('#userinfo').html(userInfo.brugerNavn)

    $('#hjem').hide();
    $('#admin').hide();
    $('#afvejning').hide();
    $('#rvbatches').hide();
    $('#pdbatches').hide();
    $('#pbKomp').hide();
    $('#recept').hide();
    $("receptkomp").hide();
    $('#raavarer').hide();
    $('#logud').hide();

    pbKomp

    if (userInfo.rolle === 'Brugeradminstrator'){
        $('#hjem').show();
        $('#admin').show();
        $('#logud').show();
    } else if (userInfo.rolle === 'Farmaceut') {
        $('#hjem').show();
        $('#afvejning').show();
        $('#rvbatches').show();
        $('#pdbatches').show();
        $('#pbKomp').show();
        $('#recept').show();
        $("receptkomp").hide();
        $('#raavarer').show();
        $('#logud').show();
    } else if (userInfo.rolle === 'Produktionsleder') {
        $('#hjem').show();
        $('#afvejning').show();
        $('#rvbatches').show();
        $('#pdbatches').show();
        $('#pbKomp').show();
        $('#logud').show();
    } else if (userInfo.rolle === 'Laborant') {
        $('#hjem').show();
        $('#afvejning').show();
        $('#logud').show();
    }

})