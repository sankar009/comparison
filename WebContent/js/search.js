//$(document).ready(function() {
//    $("search-form").submit(function() {
//        alert("Submitted");
//    });

// attach a submit handler to the form
$("#search-form").submit(function(event) {
    // stop form from submitting normally
    event.preventDefault();

    // get some values from elements on the page
    var $form = $(this);
    var url = $form.attr("action");
    var data = {
        query : $("#query").val(),
        size : 5,
        source : "lazada",
        //callback : "done",
    };
    
    $('#result').html('');
    console.log('clear');

    // Send the data using get
    var posting = $.ajax({
        type : "GET",
        url : url,
        // crossDomain: true,
        data : data,
        dataType : "jsonp",
        success : function(list) {
            var output = '';
            for (var i = 0; i < list.length; i += 4) {
                console.log('row');
                output += '<div class="row">';
                for (var j = 0; i + j < list.length && j < 4; j++) {
                    console.log('col');
                    output += '<div class="col-lg-3">'
                        + '  <a href="' + list[i+j].link + '" target="_blank">'
                        + '    <div class="panel panel-default">'
                        + '      <div class="panel-body">'
                        + '        <div><img src="' + list[i+j].img + '" height="100%" width="100%" /></div>'
                        + '        <div>' + list[i+j].title + '</div>'
                        + '        <hr class="line">'
                        + '        <div class="pull-right price">RM ' + list[i+j].price + '</div>'
                        + '        <div class="pull-left source">' + list[i+j].source + '</div>'
                        + '      </div>'
                        + '    </div>'
                        + '  </a>'
                        + '</div>';
                }
                output += '</div>';
            }
            
            $('#result').html(output);
            console.log('done?'); 
        }
    });
    
    // TODO posting.success? or posting.callbackName?
    
    // Send the data using post
//    var posting = $.post(url, data, function(data) {});
//    posting.done(function(data) {
//        alert('success');
//    });
});

//});