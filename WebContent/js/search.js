//$(document).ready(function() {
//    $("search-form").submit(function() {
//        alert("Submitted");
//    });
//});

/* attach a submit handler to the form */
$("#search-form").submit(function(event) {
    /* stop form from submitting normally */
    event.preventDefault();

    /* get some values from elements on the page: */
    var $form = $(this);
    var url = $form.attr("action");
    var data = {
        query : $("#query").val(),
        size : 5,
        source : "zalora",
        callback : "done",
    };
    
    /* Send the data using post */
    var what = $.get(url, data, function(data) {
        console.log(data);
        console.log("gaodim?");
    });
    
    what.done(function(data) {
        console.log("ini macam boleh?");
    });
    
    /* Put the results in a div */
//    posting.done(function(data) {
//        alert('success');
//    });
});