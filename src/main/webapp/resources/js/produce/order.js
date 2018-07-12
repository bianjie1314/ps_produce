(function($) {
    $(function() {
    	  initUI();
    });

    function initUI() {       
    	$('#reservation').daterangepicker(null, function(start, end, label) {
        });
    	
    	$('#datatable').click(function(e){
    		if(!$(e.target).hasClass('product'))
    			return;
    	    var id = $(e.target).attr('order-id');
    		window.open(ctx+"/order/detail?id="+id);   
    	});   	
    }
})(jQuery);

function onSubmit(){
	$(this).submit();
	return false;
}

