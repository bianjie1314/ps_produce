

(function($) {
	$(function() {
		initUI();
     });
	
    function initUI() {
        var table = $('#datatable').DataTable({
            "dom" : '<"toolbar-btn"> <"toolbar-aws" <"toolbar-search"f> <"toolbar-refresh">>tr<"row"<"col-xs-6"<"col-xs-6"l><"col-xs-6"i>><"col-xs-6"p>>',
            "lengthMenu" : [ [ 50, 100, 200, 500, 2000, 1000000 ], [ 50, 100, 200, 500, 2000, "All" ] ],
            "bStateSave" : true,
            language: {
                search: "",
                searchPlaceholder: "Search..."
            },
            "bDeferRender" : true,
            "bServerSide" : false,
            "bProcessing":true,
            'bAutoWidth' : false,
            "aoColumnDefs": [
            	{ 'bSortable': false, 'aTargets': [ 0 ],"sWidth": "50px"}
             ],
            "createdRow" : function(row, data, dataIndex) {
                row.id = data.id
            },
            "sAjaxSource" : ctx + '/clazz/source',
            "fnServerData" : function(sSource, aoData, fnCallback) {
                for ( var d in aoData) {
                    if (aoData[d].name == "sSearch") {
                        aoData[d].value = encodeURI(aoData[d].value);
                    }
                }
                $.ajax({
                    "data" : aoData,
                    "contentType" : "application/json; charset=utf-8",
                    "success" : fnCallback,
                    "url" : sSource
                });
            },
            "fnDrawCallback" : function() {
            },
            "aoColumns" : [{
                "title" : "<input type='checkbox' name='check-all'>",
                "mRender" : function(data, type, row) {
                    return "<input type='checkbox' name='check-item'>";
                }
            },{
                "title" : "班级名称",
                 "mData" : 'clazzName'
                 
            }, {
            	"title" : "专业",
            	"mData" : function(data,type,row){
                    for(i in offices){
   				 var c=offices[i]
   				 if(c.id==data.officeId){
   					 return c.name;
   				    }
   			
                   }
                   return ""; 
                   }}],
            "footerCallback" : function(row, data, start, end, display) {
            },
            initComplete : function() {
            }
        });
        
       var toolbar =$('<ul class="dropdown-menu"></ul>');
       toolbar.append('<li><a href="#" id="del">删除</a></li>');
       toolbar.append('<li><a href="#" id="edit">修改</a></li>');
    
       $("div.toolbar-btn").append('<button  class=" btn btn-primary " id="add">添加</button> ');
       $("div.toolbar-btn").append('<button  class=" btn btn-default btn-dropdown">更多操作</button> ');
       $("div.toolbar-btn").append(toolbar);
       $.bindEvent(table);//绑定各种事件
       
       $('#del').click(function() {
			var rows = table.rows('.selected');
			var data = rows.data();
			var ids = new Array();
			for(var i=0;i<data.length;i++){	
				ids.push(data[i].id);
			}	
			if(ids.length<1){
				$.messager.popup 
				("请选择任务!");
				return false;
			}
			$.messager.model = { 
		        ok:{ text: "确定",'classed': ' btn-primary' },
		        cancel: { text: "取消", 'classed': ' btn-default' }
	        };
		    $.messager.confirm("", "确认选中"+ids.length+"删除？", function() { 
		    	  $.ajax({
			 		   url : ctx + "/clazz/delete",
			 		   type:"post",			 		  
			 		   data :"ids="+ids.join(),
			 		   success : function(d) {	
			 			   table.ajax.reload();
			 			   $.messager.popup("删除成功!");
			 		   }
				});
		    });
	    });
       $('#edit').click(function() {
    	   var rows = table.rows('.selected');
		   var datas = rows.data();
		   var data=datas[0];
		   var $f = $('<div role="form" class="row"></div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select dialog-id="office" class="form-control""></select></div> </div>');
  		 $f.append('<div class="form-group col-md-6"><div class="input-group"><label class="input-group-addon">班级名称</label><input dialog-id="clazzName" class="form-control" required></div></div>');
   		 for(i in offices){
    			 var c=offices[i]
    			 $f.find('[dialog-id=office]').append('<option value="'+c.id+'">'+c.name+'</option>');
             }
  		
  		 
  		$f.find('[dialog-id=office]').val(data.officeId);
  		$f.find('[dialog-id=clazzName]').val(data.clazzName);
  		
  	    //$f.append('<div class="form-group col-md-6"><div class="input-group">点击到<a href="'+ctx+'/system/dict">字典页</a>添加主题</div></div>');
  	    $f.dialog({
  		       modelSize:"modal-lg",
  	        title : "填写",
  	        onClose : function() {
  	            $(this).dialog("destroy");
  	        },
  	        buttons : [{
  	     	   text : "取消",
  	     	   click : function() {
  	     		   $f.dialog("destroy");
  	     	   },
  	        },
  	        {
  	     	   text : "提交",
  	     	   'class' : "btn-primary btn-submit",
  	     	   click : function() {
  	         	   if(!$.validate($f))
  	        	   			return false;
  	         	   var officeId=$f.find("[dialog-id=office]").val();
  	         	var clazzName= $f.find("[dialog-id=clazzName]").val();
  	         	    if(!officeId) {
 	         		   $.messager.popup("请选择专业!");
 	         		   return false;
 	         	   }
  	         	 if(!clazzName) {
   	         		   $.messager.popup("请填写班级名称!");
   	         		   return false;
   	         	   }
  	         	  
  	         	   var param = {
  	         			  id:data.id,
  	         			  officeId:officeId,
  	     			      clazzName:clazzName
  	         	   }
  	         	   $.ajax({
  	                    url : ctx + "/clazz/update",
  	                    type : "POST",
  	                    contentType : 'application/json; charset=utf-8',
  	                    dataType : 'json',
  	                    data : JSON.stringify(param),
  	                    success : function(result) {
  	                        if (result.ret == 1) {
  	                            $.messager.popup("更新成功!");
  	                        } else {
  	                        	   $.messager.popup("更新失败!");
  	                        }
  	                    },
  	                    complete : function() {
  	                        $f.dialog("destroy");
  	                    }
  	                });
  	            }
  	        }]
  	    });
  	    $('.dialog').click(function(e) {
  	       if(!$(e.target).hasClass('close')&&!$(e.currentTarget).hasClass('in'))
  	            $f.dialog("destroy");
  	    });
       
     });
       
       $('#add').click(function() {
    	   var $f = $('<div role="form" class="row"></div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select dialog-id="office" class="form-control" onclick="load(this)"></select></div> </div>');
    		 $f.append('<div class="form-group col-md-6"><div class="input-group"><label class="input-group-addon">班级名称</label><input dialog-id="clazzName" class="form-control" required></div></div>');
     		for(i in offices){
      			 var c=offices[i]
      			 $f.find('[dialog-id=office]').append('<option value="'+c.id+'">'+c.name+'</option>');
               }
    		
    		 
    	    //$f.append('<div class="form-group col-md-6"><div class="input-group">点击到<a href="'+ctx+'/system/dict">字典页</a>添加主题</div></div>');
    	    $f.dialog({
    		       modelSize:"modal-lg",
    	        title : "填写",
    	        onClose : function() {
    	            $(this).dialog("destroy");
    	        },
    	        buttons : [{
    	     	   text : "取消",
    	     	   click : function() {
    	     		   $f.dialog("destroy");
    	     	   },
    	        },
    	        {
    	     	   text : "提交",
    	     	   'class' : "btn-primary btn-submit",
    	     	   click : function() {
    	         	   if(!$.validate($f))
    	        	   			return false;
    	         	   var clazzName= $f.find("[dialog-id=clazzName]").val();
    	         	   var officeId=$f.find("[dialog-id=office]").val();
    	         	   
    	         	  
    	         	 if(!officeId) {
     	         		   $.messager.popup("请选择专业!");
     	         		   return false;
     	         	   }
    	         	   if(!clazzName) {
    	         		   $.messager.popup("请填写班级!");
    	         		   return false;
    	         	   }
    	         	  
    	         	   var param = {
    	         			  clazzName : clazzName,
    	         			  officeId:officeId
    	         	   }
    	         	   $.ajax({
    	                    url : ctx + "/clazz/save",
    	                    type : "POST",
    	                    contentType : 'application/json; charset=utf-8',
    	                    dataType : 'json',
    	                    data : JSON.stringify(param),
    	                    success : function(result) {
    	                        if (result.ret == 1) {
    	                            $.messager.popup("创建成功!");
    	                        } else {
    	                        	   $.messager.popup("创建失败!");
    	                        }
    	                    },
    	                    complete : function() {
    	                        $f.dialog("destroy");
    	                    }
    	                });
    	            }
    	        }]
    	    });
    	    $('.dialog').click(function(e) {
    	       if(!$(e.target).hasClass('close')&&!$(e.currentTarget).hasClass('in'))
    	            $f.dialog("destroy");
    	    });
         
       });
       
    }
})(jQuery);
