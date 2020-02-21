$(function() {
    // Since there's no list-group/tab integration in Bootstrap
    $('.list-group-item').on('click',function(e){
     	  var previous = $(this).closest(".list-group").children(".active");
     	  var current = $(this).attr("href");
     	  $(previous.attr("href")).hide("slow");
     	  $(current).show("slow");
     	  previous.removeClass('active'); // previous list-item
     	  $(e.target).addClass('active'); // activated list-item
   	});
});