(function($) {
    $(function() {
    	
    	printOrder();
    	  add();
    	  addShipInfo()
    });
    function printOrder(){
    	$("#deliveryOrder").click(function(e){
    		 var $btn = $(this).button('loading');
    		var checks=$('input:checkbox[class=isDelivery]:checked');
    		var orderIds=[];
    		for(var i=0;i<checks.length;i++){
    			var t =checks[i];
    			orderIds.push( $(t).closest('tr').eq(0).attr('order-id'));
    		}
    		$.ajax({
                url : ctx + "/order/ShippingOrder",
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
   	 $("#addWaitDeliveryOrder").click(function(){
   		var $btn = $(this).button('loading')
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
   	                        url : ctx + "/order/waitShippingOrder",
   	                        type : "post",
   	                        data : "orderNo="+orderNo,
   	                        success : function(result) {
   	                        	alert(result);
   	                            if (result== 1) {
   	                            	location.reload();
   	                                
   	                            } else if(result==-1){
   	                            	$.messager.popup("暂无该订单，请于订单管理员联系!");
   	                            	$btn.button('reset')
   	                            }else{
   	                            	$.messager.popup("该订单已添加!");
   	                            	$btn.button('reset')
   	                            }
   	                        },
   	                        complete : function() {
   	                            $f.dialog("destroy");
   	                            $btn.button('reset')
   	                          
   	                        }
   	                    });
   	                    
   	                }, "取消" : function() {
   	                     
   	                	 $(this).dialog("destroy");
   	                	 $btn.button('reset')
   	                }
   	            }
   	        });
   	 });
   	 
   }
    function addShipInfo(){
    	$(".addShipInfo").click(function(){
    		var id= $(this).closest('tr').eq(0).attr('order-id');
    		var $f = $('<div role="form"></div>');
   	        $f.append('<div class="form-group" style="padding: 10px 50px;"><div class="input-group"><label class="input-group-addon">物流公司</label><input type="text"   name="time" id="shipName" class="form-control"/></div></div>');
   	        $f.append('<div class="form-group" style="padding: 10px 50px;"><div class="input-group"><label class="input-group-addon">运单编号</label><input type="text"   name="time" id="shipNo" class="form-control"/></div></div>');   
   	        $f.dialog({
   	    		   title : "添加物流信息",
   	    		  
   	            onClose : function() {
   	                $(this).dialog("destroy");
   	            }, buttons : {
   	                "添加物流信息" : function() {
   	                	if(!$.validate($f))
   	            	   		return false;
   	                    var shipName =  $f.find('[id=shipName]').val();
   	                    var shipNo=  $f.find('[id=shipNo]').val();
   	                    var param = {
   	                    		expressName : shipName,
   	                    		expressNo : shipNo,
   	                    		id:id
                         };
   	                    $.ajax({
   	                        url : ctx + "/order/addShipInfo",
   	                        type : "POST",
                            contentType : 'application/json; charset=utf-8',
                            dataType : 'json',
                            data : JSON.stringify(param),
   	                        success : function(result) {
   	                        	if (result== 1) {
   	                            	location.reload();
   	                                
   	                            } else{
   	                            	$.messager.popup("请检查物流信息!");
   	                            }
   	                        },
   	                        complete : function() {
   	                            $f.dialog("destroy");
   	                        }
   	                    });
   	                    
   	                }, "取消" : function() {
   	                	 $(this).dialog("destroy");
   	                }
   	            }
   	        });
    	});
    }
})(jQuery);