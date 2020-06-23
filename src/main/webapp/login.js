//@Author Jacob Kjærby Christensen S174130
$(document).ready(function() {
    verifyLogin()
});

function verifyLogin(){
    $("button").click(function () {
        updateGetBruger($('#loginform').serializeJSON().Username,$('#loginform').serializeJSON().Password)
    });
}

function updateGetBruger(Username, Password){
    $.get("rest/login/getUser?Username=" + Username + "&Password=" + Password, function (data, textStatus, req) {
        if (!data){
            alert("Der findes ikke nogen med det brugerID")
            return;
        }
        console.log("SETTING USERINFO")

        localStorage.setItem('userInfo', JSON.stringify(data))

        window.location.href = 'HomePage.html';
    });
}


