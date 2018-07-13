(function($) {
    $(function() {
    	
    	printOrder();
    	  
    });
   
    function printOrder(){
    	$("#printOrder").click(function(e){
    		var $btn = $(this).button('loading')
            var checks=$('input:checkbox[class=isConfirm]:checked');
    		var orderIds=[];
    		for(var i=0;i<checks.length;i++){
    			var t =checks[i];
    			orderIds.push( $(t).closest('tr').eq(0).attr('order-id'));
    		}
    		$.ajax({
                url : ctx + "/order/confirmOrder",
                type : "POST",
                 data:"orderId="+orderIds.join(),
                success : function(result) {
                	location.reload();
                },
                error:function(){
                	$btn.button('reset');
                }
                
            });
    	});  
    }
})(jQuery);