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
        $f.append('<div class="form-group" style="padding: 10px 50px;"><div class="input-group"><label class="input-group-addon">订单编号</label><input type="text"   name="time" id="number" class="form-control"/></div></div>');
    /*    $f.append('<div class="form-group"><button type="button" class="btn btn-primary">添加订单</button></div>');
    */
      /*  $f.append('<div class="row">'+
        		'<div class="input-prepend input-group">'+
        		'<span class="add-on input-group-addon">订单编号：</span>'+
        		'<input type="text"  style="width: 230px" name="time" id="number" class="form-control"/> '+
        		
        		'</div>'+
        		'</div>');
       $f.append('<div class="col-sm-6">'+
        		'<button type="button" class="btn btn-primary">添加订单</button>'+
        		'<button type="button" class="btn btn-default">取消</button>'+
        		'</div>');*/
    	   $f.dialog({
    		   title : "添加订单",
    		  
            onClose : function() {
                $(this).dialog("destroy");
            }, buttons : {
                "添加订单" : function() {
                	
             	   if(!$.validate($f))
            	   		return false;
                    var type =  $f.find('[dialog-id=type]').val();
                    var label = $f.find('[dialog-id=label]').val();
                    var value =  $f.find('[dialog-id=value]').val();
                    var description =  $f.find('[dialog-id=description]').val();                  
                    var sort = $f.find('[dialog-id=sort]').val();
                    var param = {
                 	   type : type,
                        label : label,
                        value : value,
                        description : description,
                        sort : sort,
                        id : data.id
                    };
                    $.ajax({
                        url : ctx + "/system/dict/update",
                        type : "POST",
                        contentType : 'application/json; charset=utf-8',
                        dataType : 'json',
                        data : JSON.stringify(param),
                        success : function(result) {
                            if (result.ret == 0) {
                                table.ajax.reload();
                                $.messager.popup("修改成功!");
                            } else {
                            	$.messager.popup("修改失败!");
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
    }
    function onSubmit(){
    	$(this).submit();
    	return false;
    }

})(jQuery);

