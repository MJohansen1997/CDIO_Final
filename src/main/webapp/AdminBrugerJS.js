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




$("#myopret").click(function(){
    $("#opret").after($(#test).append("" +
        "<span class=\"close\">&times;</span>\n" +
        "    <form action=\"rest/login/admin\" method=\"POST\" id='adminopret'>\n" +
        "        <p>Vælg userid : <input type=\"text\" id=\"userid\" name=\"userid\" required/></p>\n" +
        "        <p>Vælg username : <input type=\"text\" id=\"username\" name=\"username\" required/></p>\n" +
        "        <p>Vælg userini : <input type=\"text\" id=\"userini\" name=\"userini\" required/></p>\n" +
        "        <p>Vælg usercpr : <input type=\"text\" id=\"usercpr\" name=\"usercpr\" required/></p>\n" +
        "        <p>Vælg password : <input type=\"text\" id=\"userpword\" name=\"userpword\" required/></p>\n" +
        "    </form>\n" +
        "    <button>Gem</button>"));
});

$("#myrediger").click(function(){
    $(".modal2").after(append("" +
        "<span class=\"close\">&times;</span>\n" +
        "    <form action=\"rest/login/admin\" method=\"PUT\" id='adminrediger'>\n" +
        "        <p>Vælg userid : <input type=\"text\" id=\"userid\" name=\"userid\" required/></p>\n" +
        "    </form>\n" +
        "    <button>Gem</button>"))});


$("#myslet").click(function(){
    $("modal3").append("" +
        "<span class=\"close\">&times;</span>\n" +
        "    <form action=\"rest/login/admin\" method=\"GET\" id='adminslet'>\n" +
        "        <p>Vælg userid : <input type=\"text\" id=\"userid\" name=\"userid\" required/></p>\n" +
        "    </form>\n" +
        "    <button>Gem</button>")});

$("#myfind").click(function(){
    $("modal4").append("" +
        "<span class=\"close\">&times;</span>\n" +
        "    <form action=\"rest/login/admin\" method=\"GET\" id='adminfind'>\n" +
        "        <p>Vælg userid : <input type=\"text\" id=\"userid\" name=\"userid\" required/></p>\n" +
        "    </form>\n" +
        "    <button>Gem</button>")});







<!--pop up opret-->
var modal1 = document.getElementById("#opret");
var opret = document.getElementById("#myopret");
var span = document.getElementsByClassName("close")[0];

opret.onclick = function() {
    modal1.style.display = "block";
};
span.onclick = function() {
    modal1.style.display = "none";
};

    <!--pop up rediger-->
var modal2 = document.getElementById("rediger");
var rediger = document.getElementById("myrediger");

rediger.onclick = function() {
    modal2.style.display = "block";
};
span.onclick = function() {
    modal2.style.display = "none";
};


    <!--pop up find-->
var modal3 = document.getElementById("find");
var find = document.getElementById("myfind");

find.onclick = function() {
    modal3.style.display = "block";
};
span.onclick = function() {
    modal3.style.display = "none";
};


    <!--pop up slet-->
var modal4 = document.getElementById("slet");
var slet = document.getElementById("myslet");

slet.onclick = function() {
    modal4.style.display = "block";
};
span.onclick = function() {
    modal4.style.display = "none";
};

