$("#login").ready(function(){

});

$(document).ready(function() {

    var userInfo;
    verifyLogin()

});

function verifyLogin(){
    $("button").click(function () {
        $.ajax({
            url: "rest/login/verify",
            data: $('#loginform').serialize(),
            contentType: "application/x-www-form-urlencoded",
            method: 'POST',
            success: function (data) {
                console.log(data)
                if (data == 'true') {
                    console.log(data);
                    window.location.href = 'HomePage.html';
                }
            }
        });

    });
}



/*function updateGetBruger(){
    $.get('rest/login/getUser', function (data, textStatus, req) {
        console.log("DATA", data)
        if (!data){
            alert("Der findes ikke nogen med det brugerID")
            return;
        }
    });
}*/
