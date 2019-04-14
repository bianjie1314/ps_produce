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
            "sAjaxSource" : ctx + '/system/user/source/14',
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
                "title" : "归属部门",
                 "mData" : function(data, type, row) {
                	 if(data.office&&data.office.name)
                		 return data.office.name;
                	 else
                		 return "";
                		 
                }
            }, {
            	"title" : "用户名",
                "mData" : 'username'
            }, 
            {
            	"title" : "姓名",
                "mData" : 'name'
            },{
            	"title" : "角色",
                "mData" : 'roleNames'
            }, {
            	"title" : "邮箱",
                "mData" : 'email'
            }, {
            	"title" : "手机",
                "mData" : 'phone'
            }],
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
			 		   url : ctx + "/system/user/delete",
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
           window.location.href = ctx + "/system/user/form/14?id="+data.id;
         
       });
       
       $('#add').click(function() {
    	    var $f = $('<div role="form"></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">用户名</label><input type="text" dialog-id="username" class="form-control"></div></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">姓名</label><input type="text" dialog-id="name" class="form-control"></div></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">密码</label><input type="password" dialog-id="password" class="form-control"></div></div></div>');
           $f.append('<div class="form-group"><div class="input-group"><label class="input-group-addon">确认密码</label><input type="password" dialog-id="confirmPassword" class="form-control"></div></div></div>');
           
    	   $f.dialog({
               title : "添加用户",
               onClose : function() {
                   $(this).dialog("destroy");
               },
               buttons : {
                   "确定" : function() {
                       var username =  $f.find('[dialog-id=username]').val();
                       var name =  $f.find('[dialog-id=name]').val();
                       var password = $f.find('[dialog-id=password]').val();
                       var confirmPassword =  $f.find('[dialog-id=confirmPassword]').val();
                       if(!password||password!=confirmPassword){
                    	   $.messager.popup("密码与确认密码不一致!");               
                           return false;
                       }
                       if(!name){
                    	   $.messager.popup("请填写姓名!");               
                           return false;
                       }
                      var param = {
                    		  name:name,
                    		username : username,
                    		password : password,
                    		confirmPassword : confirmPassword
                       };
                       $.ajax({
                           url : ctx + "/system/user/add/14",
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
