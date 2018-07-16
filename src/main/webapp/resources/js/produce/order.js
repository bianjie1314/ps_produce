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
        	var $btn1= $(this).button('loading');
        	var orderId= $(this).closest('tr').eq(0).attr('order-id');
        	var div= $(this).parents(".parent").find(".status1");
        	
        	$.ajax({
                    url : ctx + "/order/cancalOrder",
                    type : "POST",
                     data:"orderId="+orderId,
                    success : function(result) {
                    	if(result==0){
                    	btn.remove();
                    	div.text("已取消");
                    	}
                    	else{
                    		$btn1.button('reset');
                    		$.messager.popup("订单同步失败!");
                    	}
                    },
                    error:function(){
                    	$btn1.button('reset');
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

