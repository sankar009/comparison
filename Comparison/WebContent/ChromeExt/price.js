$(document).ready(function(){

    var timer = null;
    $("#search").change(function() {
        // clearTimeout(timer);
        // timer = setTimeout(loadRecord, 1000);

             var kw = $("#search").val();
            if(kw != '')
             {

              $.ajax
              ({
                 type: "GET",
                 url: "http://localhost:8080/DCode/search",
                 //crossDomain: true,
                 data: "q="+ kw + "&s=" + 20,
                 dataType: "jsonp",
                 success: function(result)
                 {
                    console.log(result);

    $('#productList').html('');

    for(var i = 0; i < result.length; i++) {
        $('#productList').append('<li title="' + result[i].title + '" alt="' + result[i].title + '" >' +
            '<img src="'+ result[i].img +'" width="80px" height="80px" />' +
            '<h3>'+ result[i].source +'</h3>' +
            //'<div class="ftag"><span>RM</span></div>' +
            '<p>RM '+ result[i].price +'</p>' +
            '<div class="fget"><a href="' + result[i].link + '" target="_blank">View</a></div>' +
        '<br/></li>');
    }



                 }
              });


             }
             else
             {
               //$("#results").html("no output");
             }
            return false;

    });


});

function loadRecord() {

    var array = [['Lazada', 100, 'Desc'], ['Zalora', 111, 'Desc'], ['Superbuy', 50, 'Desc']];
    alert(array);

}
