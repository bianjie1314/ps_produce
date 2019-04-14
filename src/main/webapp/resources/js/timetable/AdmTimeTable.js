

function loadClazz(_this){
	   $("#clazz").html("");
		for(i in clazzs){
			 var c=clazzs[i]
			 if(c.officeId==$(_this).val())
			 $("#clazz").append('<option value="'+c.id+'">'+c.clazzName+'</option>');
       } 
		
	}
function loadCourse(){
	$("#course").html("");
	var param = {
			  officeId:$("#office").val(),
	          term:$("#term").val()
	   }
	$.ajax({
          url : ctx + "/timeTable/querrCourse",
          type : "POST",
          contentType : 'application/json; charset=utf-8',
          dataType : 'json',
          data : JSON.stringify(param),
          success : function(result) {
        	   for(var item in result){
        		var c=result[item]
        		$("#course").append('<option value="'+c.courseId+'">'+c.course_name+'</option>');
        		}
  			 },
         
      });
}
function loadTea(_this){
	$("#teacher").html("");
	var param = {
			  courseId:$(_this).val()
	   }
	$.ajax({
          url : ctx + "/system/user/querrTea",
          type : "POST",
          contentType : 'application/json; charset=utf-8',
          dataType : 'json',
          data : JSON.stringify(param),
          success : function(result) {
        	   for(var item in result){
        		var c=result[item]
        		$("#teacher").append('<option value="'+c.teaId+'">'+c.name+'</option>');}
  			 },
         
      });
}

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
            "sAjaxSource" : ctx + '/timeTable/source',
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
                "title" : "专业",
                "mData" : function(data,type,row){
                    for(i in offices){
   				 var c=offices[i]
   				 if(c.id==data.office_id){
   					 return c.name;
   				 }}}
                 
            },{
            	"title" : "班级",
            	"mData" : function(data,type,row){
                    for(i in clazzs){
   				  var c=clazzs[i]
   				  if(c.id==data.clazz_id){
   					  return c.clazzName;
   				    }
   			
                   }
                   return ""; 
                   }},
                   {
                   	"title" : "学期",
                   	"mData" : function(data,type,row){
                          
                          return "第"+data.term+"学期"; 
                          }},{
            	"title" : "日期",
                "mData" : function(data,type,row){
                	return "星期"+data.day
                	
                }
            }, {
            	"title" : "节次",
                "mData" : function(data, type, row) {
                       return "第"+data.sort+"节"
                       }
            }, {
            	"title" : "课程",
            	"mData" : 'courseName'
            },
            
            {
            	"title" : "教室",
            	"mData" : 'classroom'},
            	 {
                	"title" : "任课老师",
                	"mData" : 'teaName'}
            ],
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
			 		   url : ctx + "/timeTable/delete1",
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
    	   $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">学期</label><select id="term" class="form-control" onclick="loadCourse()" "></select></div> </div>');
     		
    	   $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select id="office" class="form-control" onclick="loadClazz(this);loadCourse()" "></select></div> </div>');
  		 
  		 $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">班级</label><select id="clazz" class="form-control" ></select></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">课程</label><select id="course" class="form-control" onclick="loadTea(this) "></select></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">老师</label><select id="teacher" class="form-control"  "></select></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">节</label><select dialog-id="sort" class="form-control" "><option value="1">第一节</option><option value="2">第二节</option><option value="3">第三节</option><option value="4">第四节</option><option value="5">第五节</option><option value="6">第六节</option><option value="7">第七节</option><option value="8">第八节</option></select></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">星期</label><select dialog-id="day" class="form-control" "><option value="1">星期一</option><option value="2">星期二</option><option value="3">星期三</option><option value="4">星期四</option><option value="5">星期五</option></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">教室</label><input type="text" dialog-id="classroom" class="form-control"></div></div>');
  		 for(i in offices){
    			 var c=offices[i]
    			 $f.find('#office').append('<option value="'+c.id+'">'+c.name+'</option>');
             }
  		$f.find('#term').append('<option value="1">第一学期</option>');
  		$f.find('#term').append('<option value="2">第二学期</option>');
  		$f.find('#term').append('<option value="3">第三学期</option>');
  		$f.find('#term').append('<option value="4">第四学期</option>');
  		$f.find('#term').append('<option value="5">第五学期</option>');
  		$f.find('#term').append('<option value="6">第六学期</option>');
  		
  		
         $f.find('#teacher').append('<option value="'+data.teaId+'">'+data.teaName+'</option>');
    	
  		 for(i in courses){
    			 var c=courses[i]
    			 $f.find('#course').append('<option value="'+c.id+'">'+c.courseName+'</option>');
             }
  		for(i in clazzs){
			 var c=clazzs[i]
			 if(c.officeId==data.office_id)
				 $f.find("#clazz").append('<option value="'+c.id+'">'+c.clazzName+'</option>');
        } 
  		$f.find('#term').val(data.teaId);
  		$f.find('#term').val(data.term);
  		  $f.find('#office').val(data.office_id);
  		$f.find("#clazz").val(data.clazz_id);
  		 $f.find('#course').val(data.course_id);
  		$f.find('[dialog-id=sort]').val(data.sort);
  		$f.find('[dialog-id=day]').val(data.day);
  		$f.find('[dialog-id=classroom]').val(data.classroom)
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
  	         	  var officeId=$f.find("#office").val();
  	         	 var clazzId=$f.find("#clazz").val();
  	         	  var courseId=$f.find("#course").val();
  	         	  var sort=$f.find("[dialog-id=sort]").val();
  	         	  var day =$f.find("[dialog-id=day]").val();
  	         	  var classroom=$f.find("[dialog-id=classroom]").val();
  	         	  var term=$f.find("#term").val();
  	         	  var teaId=$f.find("#teacher").val();
  	         	 if(!clazzId){
  	         		 $.messager.popup("请选择班级!");
 	         		   return false;
  	         	 } 
  	         	 if(!teaId){
  	         		 $.messager.popup("请选择导师!");
 	         		   return false;
  	         	 } 
  	         	 if(!officeId) {
   	         		   $.messager.popup("请选择专业!");
   	         		   return false;
   	         	   }
  	         	 if(!classroom) {
 	         		   $.messager.popup("请填写教室!");
 	         		   return false;
 	         	     } 
  	         	  
  	         	   var param = {
  	         			   id:data.id,
  	         			  officeId :officeId,
  	         			  courseId:courseId,
  	         			  sort:sort,
  	         			  teaId:teaId,
  	         			  day:day,
  	         			  clazzId:clazzId,
  	         			  classroom:classroom,
  	         			  term:term
  	         	   }
  	         	   $.ajax({
  	                    url : ctx + "/timeTable/update1",
  	                    type : "POST",
  	                    contentType : 'application/json; charset=utf-8',
  	                    dataType : 'json',
  	                    data : JSON.stringify(param),
  	                    success : function(result) {
  	                        if (result.ret == 1) {
  	                            $.messager.popup("更新成功!");
  	                        } else {
  	                        	   $.messager.popup("课程冲突!");
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
    	   $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">学期</label><select id="term" class="form-control" onclick="loadCourse()"  "></select></div> </div>');
   		   $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select id="office" class="form-control" onclick="loadClazz(this);loadCourse()" "></select></div> </div>');
    	   $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">班级</label><select id="clazz" class="form-control"  "></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">课程</label><select id="course" class="form-control" onclick="loadTea(this) "></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">教师</label><select id="teacher"  class="form-control" "></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">节</label><select dialog-id="sort" class="form-control" "><option value="1">第一节</option><option value="2">第二节</option><option value="3">第三节</option><option value="4">第四节</option><option value="5">第五节</option><option value="6">第六节</option><option value="7">第七节</option><option value="8">第八节</option></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">星期</label><select dialog-id="day" class="form-control" "><option value="1">星期一</option><option value="2">星期二</option><option value="3">星期三</option><option value="4">星期四</option><option value="5">星期五</option></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">教室</label><input type="text" dialog-id="classroom" class="form-control"></div></div>');
    		 for(i in offices){
      			 var c=offices[i]
      			 $f.find('#office').append('<option value="'+c.id+'">'+c.name+'</option>');
               }
    		 $f.find('#term').append('<option value="1">第一学期</option>');
    	  		$f.find('#term').append('<option value="2">第二学期</option>');
    	  		$f.find('#term').append('<option value="3">第三学期</option>');
    	  		$f.find('#term').append('<option value="4">第四学期</option>');
    	  		$f.find('#term').append('<option value="5">第五学期</option>');
    	  		$f.find('#term').append('<option value="6">第六学期</option>');
    		 for(i in courses){
      			 var c=courses[i]
      			 $f.find('#course').append('<option value="'+c.id+'">'+c.courseName+'</option>');
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
    	         	  var officeId=$f.find("#office").val();
    	         	  var courseId=$f.find("#course").val();
    	         	  var sort=$f.find("[dialog-id=sort]").val();
    	         	  var day =$f.find("[dialog-id=day]").val();
    	         	  var classroom=$f.find("[dialog-id=classroom]").val()
    	         	  var clazzId=$f.find("#clazz").val()
    	         	  var term=$f.find("#term").val()
    	         	  var teaId=$f.find("[id=teacher]").val()
    	         	 if(!officeId) {
     	         		   $.messager.popup("请选择专业!");
     	         		   return false;
     	         	   }
    	         	 if(!teaId) {
   	         		   $.messager.popup("请选择导师!");
   	         		   return false;
   	         	   }
    	         	 if(!classroom) {
   	         		   $.messager.popup("请填写教室!");
   	         		   return false;
   	         	     } 
    	         	  
    	         	   var param = {
    	         			  officeId :officeId,
    	         			  courseId:courseId,
    	         			  sort:sort,
    	         			  day:day,
    	         			  teaId:teaId,
    	         			  classroom:classroom,
    	         			  clazzId:clazzId,
    	         			  term:term
    	         	   }
    	         	   $.ajax({
    	                    url : ctx + "/timeTable/save1",
    	                    type : "POST",
    	                    contentType : 'application/json; charset=utf-8',
    	                    dataType : 'json',
    	                    data : JSON.stringify(param),
    	                    success : function(result) {
    	                        if (result.ret == 1) {
    	                            $.messager.popup("创建成功!");
    	                        } else {
    	                        	   $.messager.popup("课程冲突!");
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
