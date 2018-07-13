(function($) {
    $(function() {
    	
    	printOrder();
    	add();
    });
    function printOrder(){
    	$("#makeOrder").click(function(e){
    		var $btn = $(this).button('loading');
    		var checks=$('input:checkbox[class=isMake]:checked');
    		var orderIds=[];
    		for(var i=0;i<checks.length;i++){
    			var t =checks[i];
    			orderIds.push( $(t).closest('tr').eq(0).attr('order-id'));
    		}
    		$.ajax({
                url : ctx + "/order/makeOrder",
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
    function add() {
    	 $("#addOrder").click(function(){
    		 var $btn = $(this).button('loading');
    		 
    		 var $f = $('<div role="form"></div>');
    	        $f.append('<div class="form-group" style="padding: 10px 50px;"><div class="input-group"><label class="input-group-addon">订单编号</label><input type="text"   name="time" id="orderNo" class="form-control"/></div></div>');
    	           $f.dialog({
    	    		   title : "添加订单",
    	    		  
    	            onClose : function() {
    	                $(this).dialog("destroy");
    	            }, buttons : {
    	                "添加订单" : function() {
    	                	if(!$.validate($f))
    	            	   		return false;
    	                    var orderNo =  $f.find('[id=orderNo]').val();
    	                    
    	                    $.ajax({
    	                        url : ctx + "/order/waitMakeOrder",
    	                        type : "post",
    	                        data : "orderNo="+orderNo,
    	                        success : function(result) {
    	                            if (result== 1) {
    	                            	location.reload();
    	                                
    	                            } else if(result==-1){
    	                            	$btn.button('reset');
    	                            	$.messager.popup("暂无该订单，请于订单管理员联系!");
    	                            }else{
    	                            	$btn.button('reset');
    	                            	$.messager.popup("该订单已添加!");
    	                            }
    	                        },
    	                        complete : function() {
    	                        	
    	                            $f.dialog("destroy");
    	                            $btn.button('reset');
    	                        }
    	                    });
    	                    
    	                }, "取消" : function() {
    	                	 $(this).dialog("destroy");
    	                	 $btn.button('reset');
    	                }
    	            }
    	        });
    	 });
    	 
    }
    
})(jQuery);