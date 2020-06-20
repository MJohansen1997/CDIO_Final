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
    $("#pdbatches").click(function () {
        $("#container").load("Pages/ProduktBatches/ProduktBatch.html");
    });

})