(function($) {
    $(function() {
    	
    	printOrder();
    	  add();
    	  addShipInfo();
    	  getDate();
    });
    function printOrder(){
    	$("#deliveryOrder").click(function(e){
    		var $btn = $(this).button('loading');
    		var checks=$('input:checkbox[class=isDelivery]:checked');
    		var orderIds=[];
    		if(checks.length<1){
    			$.messager.popup("请选中订单!");
    			$btn.button('reset');
    			return false;
    		}
    		for(var i=0;i<checks.length;i++){
    			var t =checks[i];
    			orderIds.push( $(t).closest('tr').eq(0).attr('order-id'));
    		}
    		$.ajax({
                url : ctx + "/order/ShippingOrder",
                type : "POST",
                 data:"orderId="+orderIds.join(),
                success : function(result) {
                	if(result==0){
                	location.reload();
                	}else{
                		$btn.button('reset');
                		$.messager.popup("订单同步失败!");
                	}
                },
                error:function(){
                	 $btn.button('reset');
                }
                
            });
    	});  
    }
    function getDate(){
 	   var d=new Date();
        var mydate=new Date(d.getTime()-86400000*7);
 	   var str = "" + mydate.getFullYear() + "-";
 	   str += (mydate.getMonth()+1)+"-" ;
 	   str += (mydate.getDate());
 	   var nowdate=new Date();
 	   var str1 =nowdate.getFullYear() + "-";
 	   str1 += (nowdate.getMonth()+1) + "-";
 	   str1 += (nowdate.getDate())+ "";
 	   var a=str+" ~ "+str1;
 	   $('#reservation').val(a);
 	   
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
   	                        	if (result== 0) {
   	                            	location.reload();
   	                                
   	                            } else{
   	                            	$.messager.popup("订单同步失败!");
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