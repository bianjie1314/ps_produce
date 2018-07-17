(function($) {
    $(function() {
    	
    	printOrder();
    	  add();
    	  addShipInfo();
    	  getDate();
    });
    function printOrder(){
    	$("#deliveryOrder").click(function(e){
    		
    		var checks=$('input:checkbox[class=isDelivery]:checked');
    		var orderIds=[];
    		if(checks.length<1){
    			$.messager.popup("请选中订单!");
    			
    			return false;
    		}
    		var $btn = $(this).button('loading');
    		for(var i=0;i<checks.length;i++){
    			var t =checks[i];
    			orderIds.push( $(t).closest('tr').eq(0).attr('order-id'));
    		}
    		$.ajax({
                url : ctx + "/order/ShippingOrder",
                type : "POST",
                 data:"orderId="+orderIds.join(),
                success : function(result) {
                	if(result.ret==0){
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
    	var time=$('#reservation').val();
    	if(time!=""){
    		return false;
    	}
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
   		
   		 var $f = $('<div role="form"></div>');
   	        $f.append('<form onSubmit="submit4(this) ;return false;"><div class="form-group" style="padding: 10px 50px;"><div class="input-group"><label class="input-group-addon">订单编号</label><input type="text"    id="orderNo" class="form-control"/></div></div></form>');
   	           $f.dialog({
   	    		   title : "添加订单",
   	    		  
   	            onClose : function() {
   	               
   	                $(this).dialog("destroy");
   	            }, buttons : {
   	                "添加订单" : function() {
   	                	if(!$.validate($f))
   	            	   		return false;
   	                    var orderNo =  $f.find('[id=orderNo]').val();
   	                    if(orderNo==""){
 	                	 $.messager.popup("请输入订单编号！");
 	                	 return false;
 	                   }
   	                    $.ajax({
   	                        url : ctx + "/order/waitShippingOrder",
   	                        type : "post",
   	                        data : "orderNo="+orderNo,
   	                        success : function(result) {
   	                        	if (result.ret!= 0) {
   	                        		$.messager.popup(result.msg);
   	                            	 } else {
   	                            	location.reload();
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
    function addShipInfo(){
    	$(".addShipInfo").click(function(){
    		var id= $(this).closest('tr').eq(0).attr('order-id');
    		var $f = $('<div role="form"></div>');
   	        $f.append('<div class="form-group" style="padding: 10px 50px;"><div class="input-group"><label class="input-group-addon">物流公司</label><input type="text"    id="shipName" class="form-control"/></div></div>');
   	        $f.append('<div class="form-group" style="padding: 10px 50px;"><div class="input-group"><label class="input-group-addon">运单编号</label><input type="text"    id="shipNo" class="form-control"/></div></div>');   
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
   	                   
   	                    if(shipName==""){
   	                    	$.messager.popup("请填写物流公司");
   	                    	return fase;
   	                    }
   	                    else if(shipNo==""){
   	                    	$.messager.popup("请填写运单编号");
   	                    	return false;
   	                    }
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
   	                        	if (result.ret!=0) {
   	                        		$.messager.popup(result.msg);
   	                            }else{
   	                            	location.reload();
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
function submit4(_this){
	 var orderNo =  $(_this).find('[id=orderNo]').val();
	 if(orderNo==""){
     	$.messager.popup("请输入订单编号！");
     	return false;
     }
        $.ajax({
            url : ctx + "/order/waitShippingOrder",
            type : "post",
            data : "orderNo="+orderNo,
            success : function(result) {
            	if (result!= 0) {
            		$.messager.popup(result.msg);
                	}else{
                		location.reload();
                	}
            	},complete : function() {
                    
                  }});             
}
