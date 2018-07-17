(function($) {
    $(function() {
    	  initUI();
    	  queryOrderName();
    	  getDate();
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
    	
    	
    	$('.cancle').click(function(e){
        	var btn=$(this);
        	var orderId= $(this).closest('tr').eq(0).attr('order-id');
        	var div= $(this).parents(".parent").find(".status1");
        	var $f = $('<div role="form">确认取消订单？</div>');
        	$f.dialog({
	    		   title : "添加订单",
	    		  
	            onClose : function() {
	            	$(this).dialog("destroy");
	                
	            }, buttons : {
	                "取消订单" : function(){
	                	if(!$.validate($f))
	               		return false; 	
	                	$.ajax({
	                        url : ctx + "/order/cancalOrder",
	                        type : "POST",
	                         data:"orderId="+orderId,
	                        success : function(result) {
	                        	if(result==0){
	                        	$f.dialog("destroy");	
	                        	btn.remove();
	                        	div.text("已取消");
	                        	}
	                        	else{
	                        		$.messager.popup("订单同步失败!");
	                        	}
	                        },
	                        error:function(){
	                         }
	                        
	                    });}, 
	                "取消" : function() {
	                	 $(this).dialog("destroy");
	                	 
	                }
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

    function queryOrderName(){
    	var orderUsername=$("#category").attr('value');
    	console.log(orderUsername);
    	$.ajax({
    		url : ctx + "/order/queryOrderUser",
    		type : "post",
    		success : function(d) {
    			var content="<option value=''>全部</option>"
    			for(var item in d){
    				content+='<option value='+d[item]+'>'+d[item]+'</option>';
    			}
    			$('#category').html(content);
    			if(orderUsername){
    	    		$('#category').find("option[value = '"+orderUsername+"']").attr("selected","selected");
    	    	}
    		}
    	});
    }
    
    
    function onSubmit(){
    	
    	$(this).submit();
    	return false;
    }
    
})(jQuery);

