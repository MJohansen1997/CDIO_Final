$(document).ready(function() {
    loadProduktBatchKomp();

    $('#findBesForm').on('submit', function(e)
    {
        e.preventDefault();
        getProduktBactKomp($('#findBesForm').serializeJSON().pbID)
        $("#findForm-table1").show();
    });
    $('#findPBRBForm').on('submit', function(e)
    {
        e.preventDefault();
        getProduktBactKompPBRB($('#findPBRBForm').serializeJSON().pbID)
        $("#findForm-table").show();
    });

    buttonFindPB();
    buttonFindPBRB();

});

    function loadProduktBatchKomp()
    {
        $.get('rest/produktbatches/allProduktBatchKomp', function (data, textStatus, req)
        {
            console.log("DATA", data);
            $("#pbkdBody").empty();
            $.each(data, function (i, pbkdValue)
            {
                console.log(pbkdValue);
                $('#pbkdBody').append(generateHTMLTable(pbkdValue));
            });
        });
    }

    function getProduktBactKomp(pbID)
    {
        console.log(pbID);
        $.get('rest/produktbatches/findProduktBatchKomp/' + pbID, function (data, textStatus, req)
        {
            console.log(data);
            if(typeof data != "undefined")
            {
                $("#findFormBody1").empty();
                $.each(data, function (i, raavValue)
                {
                    console.log(raavValue);
                    $('#findFormBody1').append(generateHTMLTable(raavValue));
                });
            }
            else
            {
                alert("Der findes ikke nogen Produkt Batch Komp ID")
            }
        });
    }

    function getProduktBactKompPBRB(pbID,rbID)
    {
        console.log(pbID);
        $.get('rest/produktbatches/findProduktBatchKomp?pbID=' + pbID + "&rbID=" + rbID, function (data, textStatus, req)
        {
            console.log(data);
            if(typeof data != "undefined")
            {
                $("#findFormBody").empty().append(generateHTMLTable(data));
            }
            else
            {
                alert("Der findes ikke nogen Produkt Batch Komp ID")
            }
        });
    }

    function generateHTMLTable(ProduktBatchKomp)
    {
        return '<tr><td>' + ProduktBatchKomp.pbId + '</td>' +
            '<td>' + ProduktBatchKomp.rbId + '</td>' +
            '<td>' + ProduktBatchKomp.labID + '</td>' +
            '<td>' + ProduktBatchKomp.tara + '</td>' +
            '<td>' + ProduktBatchKomp.netto + '</td></tr>'
    }

    function buttonFindPB()
    {
        $("#buttonFind").click(function ()
        {
            hideAllForms()
            $("#findForm-table1").toggle();
            $("#findBesForm").toggle();
        });
    }

    function buttonFindPBRB()
    {
        $("#buttonRediger").click(function ()
        {
            hideAllForms()
            $("#findForm-table").toggle();
            $("#findPBRBForm").toggle();
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