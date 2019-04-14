(function($) {
    $(function() {
    	  initUI();
    	  
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
        	var leaveId= $(this).closest('tr').eq(0).attr('leave-id');
        	var div= $(this).parents(".parent").find(".status1");
        	 var $f = $('<div role="form" class="row"></div>');
        	$f.append('<div class="form-group col-md-12"><div class="input-group"><label class="input-group-addon">驳回原因</label><input dialog-id="rejection" class="form-control" required></div></div>');
       	 
        	$f.dialog({
	    		   title : "驳回",
	    		  
	            onClose : function() {
	            	$(this).dialog("destroy");
	                
	            }, buttons : {
	                "确认" : function(){
	                	if(!$.validate($f))
	                	return false; 
	                	var rejection= $f.find("[dialog-id=rejection]").val();
	              	   if(!rejection) {
	              		   $.messager.popup("请填写原因!");
	              		   return false;
	              	   }
	              	 var param = {
	              			  rejection : rejection,
	            			  id :leaveId
	            	   }
	                	$.ajax({
	                        url : ctx + "/leave/cancalLeave",
	                        type : "POST",
	                         data:param,
	                        success : function(result) {
	                        	if(result.ret==1){
	                        	$f.dialog("destroy");	
	                        	btn.parents(".parent").remove();
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
    	$('.sure').click(function(e){
        	var btn=$(this);
        	var leaveId= $(this).closest('tr').eq(0).attr('leave-id');
        	var div= $(this).parents(".parent").find(".status1");
        	var $f = $('<div role="form">确认通过？</div>');
        	$f.dialog({
	    		   title : "请假",
	    		  
	            onClose : function() {
	            	$(this).dialog("destroy");
	                
	            }, buttons : {
	                "确认" : function(){
	                	if(!$.validate($f))
	               		return false; 	
	                	$.ajax({
	                        url : ctx + "/leave/sureLeave",
	                        type : "POST",
	                         data:"leaveId="+leaveId,
	                        success : function(result) {
	                        	if(result.ret==1){
	                        	$f.dialog("destroy");	
	                        	btn.parents(".parent").remove();
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

    
    
    function onSubmit(){
    	
    	$(this).submit();
    	return false;
    }
    
    
})(jQuery);


