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
            "sAjaxSource" : ctx + '/system/dict/source',
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
                "title" : "类型",
                "mData" : 'type'
            }, {
            	"title" : "标签",
                "mData" : 'label'
            }, {
                "title" : "值",
                "mData" : 'value'
            }, {
                "title" : "排序",
                "mData" : 'sort'
            }, {
                "title" : "备注",
                "mData" : 'description'
            }],
            "footerCallback" : function(row, data, start, end, display) {
            },
            initComplete : function() {
            }
        });
        
       var toolbar =$('<ul class="dropdown-menu"></ul>');
       toolbar.append(' <li><a href="#" id="del">删除</a></li>');
       toolbar.append('<li><a href="#" id="edit">修改</a></li>');
       toolbar.append('<li><a href="#" id="copy">复制</a></li>');
      // toolbar.append('<li role="separator" class="divider"></li>');
    
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
				 		   url : ctx + "/system/dict/del",
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
           if(datas.length!=1){
               $.messager.popup("请选1个择任务!");               
               return false;
           }
           var data = datas[0];
           var $f = $('<div role="form"></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">类型</label><select dialog-id="type" class="form-control" required="true"><option value="">请选择</option></select></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">标签名</label><input type="text" dialog-id="label" class="form-control" required="true"></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">值</label><input type="text" dialog-id="value" class="form-control"></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">描述</label><input type="text" dialog-id="description" class="form-control"></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">排序</label><input type="number" dialog-id="sort" class="form-control" required="true"></div></div>' );
           for(i in dictType){
				 var c=dictType[i]
				 $f.find('[dialog-id=type]').append('<option value="'+c.value+'">'+c.label+'</option>');
			 }
           $f.find('[dialog-id=type]').val(data.type);
           $f.find('[dialog-id=label]').val(data.label);
           $f.find('[dialog-id=value]').val(data.value);
           $f.find('[dialog-id=description]').val(data.description);
           $f.find('[dialog-id=sort]').val(data.sort);
    	   $f.dialog({
               title : "修改字典",
               onClose : function() {
                   $(this).dialog("destroy");
               },
               buttons : {
                   "确定" : function() {
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
                       
                   }
               }
           });
           $('.dialog').click(function(e) {
              if(!$(e.target).hasClass('close')&&!$(e.currentTarget).hasClass('in'))
                   $f.dialog("destroy");
           });
       });
       $('#copy').click(function() {
    	   var rows = table.rows('.selected');
           var datas = rows.data();           
           if(datas.length!=1){
               $.messager.popup("请选1个择任务!");               
               return false;
           }
           var data = datas[0];
           var $f = $('<div role="form"></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">类型</label><select dialog-id="type" class="form-control"><option value="">请选择</option></select></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">标签名</label><input type="text" dialog-id="label" class="form-control" required></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">值</label><input type="text" dialog-id="value" class="form-control" required></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">描述</label><input type="text" dialog-id="description" class="form-control"></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">排序</label><input type="number" dialog-id="sort" class="form-control" required></div></div>' );
           for(i in dictType){
				 var c=dictType[i]
				 $f.find('[dialog-id=type]').append('<option value="'+c.value+'">'+c.label+'</option>');
			 }
           $f.find('[dialog-id=type]').val(data.type);
           $f.find('[dialog-id=label]').val(data.label);
           $f.find('[dialog-id=value]').val(data.value);
           $f.find('[dialog-id=description]').val(data.description);
           $f.find('[dialog-id=sort]').val(data.sort);
    	   $f.dialog({
               title : "新增字典",
               onClose : function() {
                   $(this).dialog("destroy");
               },
               buttons : {
                   "确定" : function() {
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
                           sort : sort
                       };
                       $.ajax({
                           url : ctx + "/system/dict/add",
                           type : "POST",
                           contentType : 'application/json; charset=utf-8',
                           dataType : 'json',
                           data : JSON.stringify(param),
                           success : function(result) {
                               if (result.ret == 0) {
                                   table.ajax.reload();
                                   $.messager.popup("添加成功!");
                               } else {
                               	$.messager.popup("添加失败!");
                               }
                           },
                           complete : function() {
                               $f.dialog("destroy");
                           }
                       });
                   }
               }
           });
           $('.dialog').click(function(e) {
              if(!$(e.target).hasClass('close')&&!$(e.currentTarget).hasClass('in'))
                   $f.dialog("destroy");
           });
       });
        $('#add').click(function() {
            var $f = $('<div role="form"></div>');
            $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">类型</label><select dialog-id="type" class="form-control"><option value="">请选择</option></select></div></div>');
            $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">标签名</label><input type="text" dialog-id="label" class="form-control" required></div></div>');
            $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">值</label><input type="text" dialog-id="value" class="form-control" required></div></div>');
            $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">描述</label><input type="text" dialog-id="description" class="form-control"></div></div>');
            $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">排序</label><input type="number" dialog-id="sort" class="form-control" required></div></div>' );
           
            for(i in dictType){
				 var c=dictType[i]
				 $f.find('[dialog-id=type]').append('<option value="'+c.value+'">'+c.label+'</option>');
			 }
            $f.dialog({
                title : "新增字典",
                onClose : function() {
                    $(this).dialog("destroy");
                },
                buttons : {
                    "确定" : function() {
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
                            sort : sort
                        };
                        $.ajax({
                            url : ctx + "/system/dict/add",
                            type : "POST",
                            contentType : 'application/json; charset=utf-8',
                            dataType : 'json',
                            data : JSON.stringify(param),
                            success : function(result) {
                                if (result.ret == 0) {
                                    table.ajax.reload();
                                    $.messager.popup("添加成功!");
                                } else {
                                	$.messager.popup("添加失败!");
                                }
                            },
                            complete : function() {
                                $f.dialog("destroy");
                            }
                        });
                    }
                }
            });
            $('.dialog').click(function(e) {
               if(!$(e.target).hasClass('close')&&!$(e.currentTarget).hasClass('in'))
                    $f.dialog("destroy");
            });
        });
       
    }
})(jQuery);
