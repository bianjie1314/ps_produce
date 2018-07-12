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
    }

})(jQuery);
function queryOrderName(){
	$.ajax({
		url : ctx + "/order/queryOrderUser",
		type : "post",
		success : function(d) {
			var content=""
			for(var item in d){
				content+='<option value='+d[item]+'>'+d[item]+'</option>';
			}
			$('#category').html(content);
		}
	});
}
function add() {
	var $f = $('<div role="form"></div>');
    $f.append('<div class="input-prepend input-group">'+
    		'<span class="add-on input-group-addon">订单编号：</span>'+
    		'<input type="text"  style="width: 230px" name="time" id="number" class="form-control"/> '+
    		'<button type="button" class="btn btn-primary">添加订单</button>'+
    		'<button type="button" class="btn btn-default">取消</button>'+
    		'</div>');
   /* $f.append('<div class="col-sm-6">'+
    		'<button type="button" class="btn btn-primary">添加订单</button>'+
    		'<button type="button" class="btn btn-default">取消</button>'+
    		'</div>');*/
	   $f.dialog({
        onClose : function() {
            $(this).dialog("destroy");
        }
    });
}
function onSubmit(){
	$(this).submit();
	return false;
}

