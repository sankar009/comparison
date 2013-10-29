
function SortByPrice(a, b) { 
    return ((a.price < b.price) ? -1 : ((a.price > b.price) ? 1 : 0));
}

$("#search-form").submit(function(event) {

    NProgress.start();
    
    // avoid form to submit normally
    event.preventDefault();

    var $form = $(this);
    var url = $form.attr("action");
    var query = $("#query").val();
    
    $('#result').html('');
    
    $.when(
        // TODO dunit to use jsonp la..
        $.ajax({
            type:"GET", 
            url:url, 
            data: {query:query, size:5, source:"Lazada"}, 
            dataType:"jsonp", 
            success:function(data) { NProgress.inc(); }}),
        $.ajax({
            type:"GET", 
            url:url, 
            data: {query:query, size:5, source:"Lelong"}, 
            dataType:"jsonp", 
            success:function(data) { NProgress.inc(); }}),
        $.ajax({
            type:"GET", 
            url:url, 
            data:{query:query, size:5, source:"Rakuten"}, 
            dataType:"jsonp", 
            success:function(data) { NProgress.inc(); }}),
        $.ajax({
            type:"GET", 
            url:url, 
            data:{query:query, size:5, source:"Superbuy"}, 
            dataType:"jsonp", 
            success:function(data) { NProgress.inc(); }}),
        $.ajax({
            type:"GET", 
            url:url, 
            data:{query:query, size:5, source:"Zalora"}, 
            dataType:"jsonp", 
            success:function(data) { NProgress.inc(); }})
            
    ).done(function(lazada, lelong, rakuten, superbuy, zalora) {
        NProgress.inc();
        
        // combine data
        var list = lazada[0].concat(lelong[0])
                         .concat(rakuten[0])
                         .concat(superbuy[0])
                         .concat(zalora[0]);
        list.sort(SortByPrice);

        // generating output to put into result div
        var output = '';
        for (var i = 0; i < list.length; i += 4) {
            output += '<div class="row">';
            for (var j = 0; i + j < list.length && j < 4; j++) {
                var title = (list[i+j].title.length < 80) 
                        ? list[i+j].title 
                        : list[i+j].title.substring(0, 75) + "...";
                output += '<div class="col-lg-3">'
                       + '  <a href="' + list[i+j].link + '" target="_blank">'
                       + '    <div class="panel panel-default">'
                       + '      <div class="panel-body">'
                       + '        <div><img src="' + list[i+j].img + '" height="100%" width="100%" /></div>'
                       + '        <div class="space">' + title + '</div>'
                       + '        <hr class="line">'
                       + '        <div class="pull-right price">RM ' + list[i+j].price.toFixed(2) + '</div>'
                       + '        <div class="pull-left source">' + list[i+j].source + '</div>'
                       + '      </div>'
                       + '    </div>'
                       + '  </a>'
                       + '</div>';
            }
            output += '</div>';
        }
        
        // display result
        $('#result').html(output);
        
        NProgress.done();
    });
});