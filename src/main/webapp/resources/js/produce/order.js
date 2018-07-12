(function($) {
    $(function() {
    	  initUI();
    });

    function initUI() {
       
    	$('#reservation').daterangepicker(null, function(start, end, label) {
        });
    	
    	
    	
    }
})(jQuery);

function onSubmit(){
	$(this).submit();
	return false;
}

