(function($) {
    $(function() {
    	  initUI();
    	  queryOrderName();
    	  
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
        	var $btn1= $(this).button('loading');
        	var orderId= $(this).closest('tr').eq(0).attr('order-id');
        	$.ajax({
                    url : ctx + "/order/cancalOrder",
                    type : "POST",
                     data:"orderId="+orderId,
                    success : function(result) {
                    	btn.remove();
                    },
                    error:function(){
                    	$btn1.button('reset');
                    }
                    
                });
        	
        });

    }
    
    

    function queryOrderName(){
    	$.ajax({
    		url : ctx + "/order/queryOrderUser",
    		type : "post",
    		success : function(d) {
    			var content="<option value=''>全部</option>"
    			for(var item in d){
    				content+='<option value='+d[item]+'>'+d[item]+'</option>';
    			}
    			$('#category').html(content);
    		}
    	});
    }
    
    
    function onSubmit(){
    	
    	$(this).submit();
    	return false;
    }
    
})(jQuery);

