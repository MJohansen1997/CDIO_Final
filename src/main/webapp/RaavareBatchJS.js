$(document).ready(function () {
    loadRaavarerBatch();

    $('#opretForm').on('submit', function(e)
    {
        e.preventDefault();
        createRaavarerBatch()
    });

    $('#findForm').on('submit', function(e)
    {
        e.preventDefault();
        getRaavarerBatch($('#findForm').serializeJSON().raavID)
        $("#findForm-table").show();
    });

    $('#redigerForm').on('submit', function(e)
    {

        e.preventDefault();
        updateGetRaavarerBatch($("#raavBatchID").val())
        $("#redigerInfoForm").toggle();
    });

    $('#redigerInfoForm').on('submit', function(e)
    {
        e.preventDefault();
        updateRaavarerBatch($('#redigerInfoForm').serializeJSON().raavID)
    });

    buttonOpret();
    buttonFind();
    buttonRediger();
    submitUpdate();
});

    function createRaavarerBatch()
    {
        var data = $('#opretForm').serializeJSON();
        console.log(data)
        $.ajax
        ({
            url:'rest/raavarebatch/createRaavarerBatch',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function ()
            {
                loadRaavarerBatch();
                alert("Oprettet bruger GZ homie")
                $("#opretForm").toggle();
            }
        });
    }

    function updateRaavarerBatch()
    {
        var data = $('#redigerInfoForm').serializeJSON();
        console.log(data)
        $.ajax
        ({
            url:'rest/raavarebatch/updateRaavereBatch',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function ()
            {
                loadRaavarerBatch();
                $("#redigerForm").toggle();
                $("#redigerInfoForm").toggle();
            }

        })
    }

function loadRaavarerBatch() {
    $.get('rest/raavarebatch/allRaavareBatches', function (data, textStatus, req) {
        console.log(data)
        $("#raavBatchBody").empty();
        $.each(data, function (i, brugerValue) {
            $('#raavBatchBody').append(generateHTMLTable(brugerValue));
        });
    });
}


    function updateGetRaavarerBatch(rbID)
    {
        console.log(rbID);
        $.get('rest/raavarebatch/getRaavareBatch/' + rbID, function (data, textStatus, req)
        {
            console.log("DATA", data)
            if (!data)
            {
                alert("Der findes ikke nogen med den Råvarer ID")
                return;
            }

            $form = $('#redigerInfoForm');
            $form.find('[name="rbID"]').val(data.rbID);
            $form.find('[name="raavID"]').val(data.raavID);
            $form.find('[name="maengde"]').val(data.maengde);
        });
    }

    function getRaavarerBatch(rbID)
    {
        console.log(rbID);
        $.get('rest/raavarebatch/findRaavareBatch/' + rbID, function (data, textStatus, req)
        {
            if(typeof data != "undefined")
            {
                $("#findFormBody").empty().append(generateHTMLTable(data));
            }
            else
            {
                alert("Der findes ikke nogen med den Råvarer ID");
            }
        });
    }


    function generateHTMLTable(raavarerBatch)
    {
        return '<tr><td>' + raavarerBatch.rbID + '</td>' +
            '<td>' + raavarerBatch.raavID + '</td>' +
            '<td>' + raavarerBatch.maengde + '</td></tr>'

    }

    function buttonOpret()
    {
        $("#buttonOpret").click(function () {
            hideAllForms()
            console.log("Click Opret Knap")
            $("#opretForm").toggle();
        });
    }
    function buttonRediger()
    {
        $("#buttonRediger").click(function ()
        {
            hideAllForms()
            $("#redigerForm").toggle();
        });
    }

function submitUpdate() {
    $("#submit4").click(function () {
        $("#showForm").toggle();
    });
}


    function buttonFind()
    {
        $("#buttonFind").click(function ()
        {
            hideAllForms()
            $("#findForm-table").toggle();
            $("#findForm").toggle();
        });
    }

    function hideAllForms(){
        $('form').each(function(){
            if ( $(this).css('display') == 'block')
            {
                $(this).toggle();
            }
        });
    }