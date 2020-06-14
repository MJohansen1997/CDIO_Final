$(document).read(function() {
   loadBrugere();
});


function loadBrugere() {
    $.get('rest/brugere/allUsers', function (data, textStatus, req) {
        $("#brugerBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#brugerBody').append(generateHTML(brugerValue));
        });
    });
}


function generateHTML(bruger){
    return '<tr><td>' +
}


