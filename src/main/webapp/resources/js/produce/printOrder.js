(function($) {
    $(function() {
    	$("#printOrder").click(function(){
    		var checks=$('.isConfirm').attr('checked');
    		var orderId="";
    		for(var i;i<checks.length;i++){
    			orderId+= +","+checks[i].closest('tr').eq(0).attr('order-id')
    		}
    		$.ajax({
                url : ctx + "/order/confirmOrder",
                type : "POST",
                 data:"orderId="+orderId,
                success : function(result) {
                	location.reload();
                },
                
            });
    	});  
    	  
    	  
    });
    
});