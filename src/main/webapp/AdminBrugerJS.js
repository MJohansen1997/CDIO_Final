$(document).ready(function() {
   loadBrugere();
    $('#opretBrugerForm').on('submit', function(e){
        console.log("Stan is gay")
        e.preventDefault();
        createBruger()
    });

    $("button").click(function () {
        $.ajax({
            url: "rest/login/admin",
            data: $('#loginform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            success: function (data) {
                alert(data);
                if (data == 'true') {
                    /*$('#maincontainer').load("HomePage.html");*/
                    window.location.href = 'HomePage.html';
                }
            }
        });

    });
});

jQuery(document).ready(function($){
})

function createBruger() {
    var data = $('#opretBrugerForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/brugere/createUser',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data){
            alert(JSON.stringify(data));
            loadBrugere();
        }
    })
}

function loadBrugere() {
    $.get('rest/brugere/allUsers', function (data, textStatus, req) {
        $("#brugerBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#brugerBody').append(generateHTML(brugerValue));
        });
    });
}

function generateHTML(bruger){
 return '<tr><td>' + bruger.brugerID + '</td>' +
        '<td>' + bruger.brugerNavn + '</td>' +
        '<td>' + bruger.ini + '</td>' +
        '<td>' + bruger.cpr + '</td>' +
        '<td>' + bruger.rolle + '</td>' +
        '<td>' + bruger.password + '</td></tr>'
    //@TODO Make a button which creates, updates and remove
}

jQuery(document).ready(function() {
    $("#formButton").click(function() {
        $("#form1").toggle();
    });
});


$("#buttonOpret").click(function(){
    $("#modal1").append(generateMyOpretHtml());
});

$("#buttonRediger").click(function(){
    $(".modal2").append(("" +
        "<span class=\"close2\">&times;</span>\n" +
        "    <form action=\"rest/login/admin\" method=\"PUT\" id='adminrediger'>\n" +
        "        <p>Vælg userid : <input type=\"text\" id=\"userid\" name=\"userid\" required/></p>\n" +
        "    </form>\n" +
        "    <button>Gem</button>"))});


$("#buttonSlet").click(function(){
    $("modal3").append("" +
        "<span class=\"close\">&times;</span>\n" +
        "    <form action=\"rest/login/admin\" method=\"GET\" id='adminslet'>\n" +
        "        <p>Vælg userid : <input type=\"text\" id=\"userid\" name=\"userid\" required/></p>\n" +
        "    </form>\n" +
        "    <button>Gem</button>")});

$("#buttonFind").click(function(){
    $("modal4").append("" +
        "<span class=\"close\">&times;</span>\n" +
        "    <form action=\"rest/login/admin\" method=\"GET\" id='adminfind'>\n" +
        "        <p>Vælg userid : <input type=\"text\" id=\"userid\" name=\"userid\" required/></p>\n" +
        "    </form>\n" +
        "    <button>Gem</button>")});







<!--pop up opret-->
var modal1 = document.getElementById("#opretForm");
var opret = document.getElementById("#buttonOpret");
var span = document.getElementsByClassName("close")[0];

jQuery(opret).click(function() {
    modal1.style.display = "block";
});
jQuery(span).click(function() {
    modal1.style.display = "none";
});

    <!--pop up rediger-->
var modal2 = document.getElementById("rediger");
var rediger = document.getElementById("buttonRediger");

jQuery(rediger).click(function() {
    modal2.style.display = "block";
});

jQuery(span).click(function() {
    modal2.style.display = "none";
});


    <!--pop up find-->
var modal3 = document.getElementById("find");
var find = document.getElementById("buttonFind");

find.onclick = function() {
    modal3.style.display = "block";
};
span.onclick = function() {
    modal3.style.display = "none";
};


    <!--pop up slet-->
var modal4 = document.getElementById("slet");
var slet = document.getElementById("buttonSlet");

slet.onclick = function() {
    modal4.style.display = "block";
};
span.onclick = function() {
    modal4.style.display = "none";
};

