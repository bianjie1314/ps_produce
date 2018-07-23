(function($) {
    $(function() {
    	
    	printOrder();
    	downImg();
    	add();
    	getDate();
    });
    function printOrder(){
    	$("#makeOrder").click(function(e){
    		
    		var checks=$('input:checkbox[class=isMake]:checked');
    		var orderIds=[];
    		if(checks.length<1){
    			$.messager.popup("请选中订单!");
    			return false;
    		}
    		for(var i=0;i<checks.length;i++){
    			var t =checks[i];
    			orderIds.push( $(t).closest('tr').eq(0).attr('order-id'));
    		}
    		var $btn = $(this).button('loading');
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
    function downImg(){
    	$(".downImg").click(function(){
    		var $btn = $(this).button('loading');
    		var productId= $(this).closest('div').eq(0).attr('product-id');
    		var materials= $(this).closest('div').eq(0).attr('product-materials');
    		if(!materials){
    			  $btn.button('reset');
    			$.messager.popup("该订单无素材！");
    			return false;
    		}
    	
    		var temp = document.createElement("form");        
		    temp.action = ctx+"/order/downImg";        
		    temp.method = "post";
		    var opt1 = document.createElement("textarea");        
		    opt1.name = "productId";        
		    opt1.value = productId;        
		    temp.appendChild(opt1); 
		    temp.style.display = "none";        
		    document.body.appendChild(temp);        
		    temp.submit(); 
		    $btn.button('reset');
    	});
    }
    
    function add() {
    	 $("#addOrder").click(function(){
    		 
    		 
    		 var $f = $('<div role="form"></div>');
    		 
    	        $f.append('<form onSubmit="submit2(this);return false;"><div class="form-group" style="padding: 10px 50px;"><div class="input-group"><label class="input-group-addon">订单编号</label><input type="text"  id="orderNo" class="form-control"/></div></div></form>');
    	           $f.dialog({
    	    		   title : "添加订单",
    	    		  
    	            onClose : function() {
    	            	
    	                $(this).dialog("destroy");
    	            }, buttons : {
    	                "添加订单" : function(){
    	                	
    	                	if(!$.validate($f))
    	               		return false;
    	                var orderNo =  $f.find('#orderNo').val();
    	                if(orderNo==""){
    	                	$.messager.popup("请输入订单编号！");
    	                	return false;
    	                }
    	                $.ajax({
    	                    url : ctx + "/order/waitMakeOrder",
    	                    type : "post",
    	                    data : "orderNo="+orderNo,
    	                    success : function(result) {
    	                        if (result.ret!=0 ) {
    	                        	$.messager.popup(result.msg);

    	                        }else{ 
    	                            location.reload();
    	                        }
    	                    },
    	                    complete : function() {
    	                    	$f.dialog("destroy");
    	                       
    	                    }
    	                });}, 
    	                "取消" : function() {
    	                	 $(this).dialog("destroy");
    	                	 
    	                }
    	            }
    	            
    	        });
    	 });
    	 
    }
    
    
})(jQuery);

function submit2(_this){
	
    var orderNo =  $(_this).find('[id=orderNo]').val();
    if(orderNo==""){
    	$.messager.popup("请输入订单编号！");
    	return false;
    }
    $.ajax({
        url : ctx + "/order/waitMakeOrder",
        type : "post",
        data : "orderNo="+orderNo,
        success : function(result) {
            if(result.ret!=0){
            	$.messager.popup(result.msg);

            }else{
            	$.messager.popup("添加订单成功");
          	  $(_this).find('#orderNo').val('');

            }
        },
        complete : function() {
        	
        }
    });
}