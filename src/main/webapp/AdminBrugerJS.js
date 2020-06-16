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


jQuery(document).ready(function () {
    $("#buttonOpret").click(function () {
        $("#opretForm").toggle();
    });
});

jQuery(document).ready(function () {
    $("#buttonRediger").click(function () {
        $("#redigerForm").toggle();
    });
});

jQuery(document).ready(function () {
    $("#buttonSlet").click(function () {
        $("#sletForm").toggle();
    });
});

jQuery(document).ready(function () {
    $("#buttonFind").click(function () {
        $("#findForm").toggle();
    });
});

jQuery(document).ready(function () {
    $("#submit4").click(function () {
        $("#showForm").toggle();
    });
})

