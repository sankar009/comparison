
function SortByPrice(a, b) { 
    return ((a.price < b.price) ? -1 : ((a.price > b.price) ? 1 : 0));
}

$("#search-form").submit(function(event) {
    
    event.preventDefault();  // avoid form to submit normally
    
    NProgress.start();
    
    $('#result').fadeOut(1000);
    
    var url = $(this).attr("action");
    var query = encodeURI($("#query").val());
    var size = 3;
    
    $.when(
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data: {query:query, size:size, source:"Lazada"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data: {query:query, size:size, source:"Lelong"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"Rakuten"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"Superbuy"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"Zalora"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"Expansys"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"MobileMegamall"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"IPMart"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"YouBeli"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"SengHeng"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"Retrons"},
            success:function(data) { NProgress.inc(); }}),
        $.ajax({type:"GET", url:url, dataType:"jsonp",
            data:{query:query, size:size, source:"Shashinki"},
            success:function(data) { NProgress.inc(); }})

    ).done(function(lazada, lelong, rakuten, superbuy, zalora, 
                    expansys, mmegamall, ipmart, youbeli, sengheng,
                    retrons, shashinki) {
        
        // combine data
        var list = lazada[0].concat(lelong[0]).concat(rakuten[0])
                  .concat(superbuy[0]).concat(zalora[0]).concat(expansys[0])
                  .concat(mmegamall[0]).concat(ipmart[0]).concat(youbeli[0])
                  .concat(sengheng[0]).concat(retrons[0]).concat(shashinki[0]);
        
        // generating output to put into result div
        var output = '';
        if (list != undefined && list.length != 0) {
            list.sort(SortByPrice);
            for (var i = 0; i < list.length; i += 4) {
                output += '<div class="row">';
                for (var j = 0; i + j < list.length && j < 4; j++) {
                    var title = (list[i+j].title.length < 65) 
                            ? list[i+j].title 
                            : list[i+j].title.substring(0, 60) + "...";
                    output += '<div class="col-lg-3">'
                           + '  <a href="' + list[i+j].url + '" target="_blank">'
                           + '    <div class="panel panel-default">'
                           + '      <div class="panel-body">'
                           + '        <div>'
                           + '          <div class="pull-left source">' + list[i+j].source + '</div>'
                           + '          <div class="pull-right price">RM ' + list[i+j].price.toFixed(2) + '</div>'
                           + '        </div>'
                           + '        <div class="margin1">'
                           + '          <img src="' + list[i+j].img + '" style="width:100%;height:100%">'
                           + '        </div>'
                           + '        <div class="margin2">' + title + '</div>'
                           + '      </div>'
                           + '    </div>'
                           + '  </a>'
                           + '</div>';
                }
                output += '</div>';
            }
        } else {
            output += '<div class="panel panel-default">'
                   + '  <div class="panel-body no-result">'
                   + '    Nothing found :( Perhaps try different keywords?'
                   + '  </div>'
                   + '</div>';
        }
        
        // display result
        $('#result').html(output).fadeIn();
        
        NProgress.done();
    });
});