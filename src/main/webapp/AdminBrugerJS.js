$(document).ready(function() {
   loadBrugere();
    $('#opretForm').on('submit', function(e){
        e.preventDefault();
        createBruger()
    });
    $('#findForm').on('submit', function(e){
        e.preventDefault();
        findBruger()
    });
});

jQuery(document).ready(function($){
})

function createBruger() {
    var data = $('#opretForm').serializeJSON();
    console.log(data)
    $.ajax({
        url:'rest/brugere/createUser',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (){
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

function loadBrugere() {
    $.get('rest/brugere/getUser', function (data, textStatus, req) {
        $("#brugerBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#brugerBody').append(generateHTML(brugerValue));
        });
    });
}


function findBruger() {
    var data = $('#findForm').serializeJSON();
    console.log(data)
    $.ajax({
        url: 'rest/brugere/getUser',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data) {
            console.log(data)
            if (data != null) {
                $("#findFormBody").empty();
                $.each(data, function (i, brugerValue) {
                    $('#findFormBody').append(generateHTML(brugerValue));
                })
            }
        },
    })
}

    /*

    function loadBruger() {
        $.get('rest/brugere/getUser', function(data,textStatus,req){
            $("#brugerBody").empty();
            $.each(data, function (i, brugerValue) {
                $('#brugerBody').append(generateHTML(brugerValue));
        })
    })
    */

    function generateHTML(bruger) {
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



