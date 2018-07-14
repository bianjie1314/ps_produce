(function($) {
    $(function() {
    	
    	printOrder();
    	  
    });
   
    function printOrder(){
    	$("#printOrder").click(function(e){
    		var $btn = $(this).button('loading')
            var checks=$('input:checkbox[class=isConfirm]:checked');
    		var orderIds=[];
    		for(var i=0;i<checks.length;i++){
    			var t =checks[i];
    			orderIds.push( $(t).closest('tr').eq(0).attr('order-id'));
    		}
    		$.ajax({
                url : ctx + "/order/confirmOrder",
                type : "POST",
                 data:"orderId="+orderIds.join(),
                success : function(result) {
                	//location.reload();
                	$btn.button('reset');
                	print("http://127.0.0.1:8080/ps_produce/printPdf/sdf");
                },
                error:function(){
                	
                	$btn.button('reset');
                }
                
            });
    	});  
    }
    
    function print(url){
			var timestamp=Math.round(new Date().getTime()/1000);
			//if($('#PDFtoPrint'))
			if(document.getElementById("PDFtoPrint")!=null && typeof(document.getElementById("PDFtoPrint"))!='undefined'){
				document.body.removeChild(document.getElementById("PDFtoPrint"))
			}
			var iframe = document.createElement("IFRAME");
			//var iframe=$('<iframe src="" id="" style="display: none;"></iframe>');
			//$("body").append(iframe);
			iframe.style.display="none";
			iframe.id="PDFtoPrint";
			 iframe.src=url+"&timestamp="+timestamp;
			iframe.onload = function() {
		        return function() {
		        	iframe.focus();
		        	iframe.contentWindow.print();
		        	iframe.style.display="none";
		//         	setTimeout(function(){ document.body.removeChild(iframe);},10000);
		        }
		    }();
		    document.body.appendChild(iframe);
	
	}
})(jQuery);