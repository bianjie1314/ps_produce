
function loadClazz(){
    var officeId=$("#office").val();
    var clazzId=$("#clazz").val();
    $("#clazz").html('');
    for(i in clazzs){
        var c=clazzs[i]
        if(c.officeId==officeId){
            $("#clazz").append('<option value="'+c.id+'">'+c.clazzName+'</option>')
        }

    }
    $("#clazz").val(clazzId)
}
function loadCourse(){
	$("#course").html("");
	var param = {
			  officeId:$("#office").val()
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
function loadUser(_this){
	   $("#name").html("");
		for(i in students){
			 var c=students[i]
			 if(c.clazzId==$(_this).val())
			 $("#name").append('<option value="'+c.id+'">'+c.name+'</option>');
       } 
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
            "sAjaxSource" : ctx + '/score/source',
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
                 "mData" : 'officeName'
                 
            }, 
            {
            	"title" : "科目",
            	"mData" : function(data,type,row){
            		if(data.state==1){
            			return data.courseName;
            		}else if(data.state==2){
            			return data.courseName+"(补考)"
            		}else{
            			return data.courseName+"(重修)";
            		}
            	}
                
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
            	"title" : "姓名",
                "mData" : 'name'
            },
            {
            	"title" : "分数",
                "mData" : 'score'
            },
            {
            	"title" : "创建时间",
                "mData" : function(data, type, row) { if(data.create_date==null)return ""; return $.formatDate(data.create_date,'%Y-%M-%d %H:%m:%s')}
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
			 		   url : ctx + "/score/delete",
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
    	 $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select id="office" class="form-control" onclick="loadClazz(this);loadCourse()"></select></div> </div>');
     	 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">班级</label><select id="clazz" class="form-control" onclick="loadUser(this)"></select></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">课程</label><select id="course" class="form-control" "></select></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">姓名</label><select id="name" class="form-control" "></select></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">分数</label><input dialog-id="score" class="form-control" required></div> </div>');
  		//$f.append('<div class="form-group col-md-6"><div class="input-group">点击到<a href="'+ctx+'/system/dict">字典页</a>添加主题</div></div>');
   		for(i in offices){
   			 var c=offices[i]
   			 $f.find('#office').append('<option value="'+c.id+'">'+c.name+'</option>');
            }
 		 for(i in courses){
   			 var c=courses[i]
   			 $f.find('#course').append('<option value="'+c.id+'">'+c.courseName+'</option>');
            }
 		for(i in clazzs){
			 var c=clazzs[i]
			 if(c.officeId==data.office_id)
				 $f.find("#clazz").append('<option value="'+c.id+'">'+c.clazzName+'</option>');
        }
 		$f.find("#clazz").val(data.clazz_id);
 		$f.find("#name").html('<option value="'+data.stu_id+'">'+data.name+'</option>');
  		$f.find('#office').val(data.office_id);
  		$f.find('#course').val(data.course_id);
  		$f.find('[dialog-id=score]').val(data.score);
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
  	         	   var clazz=$f.find("#clazz").val();
  	         	   var officeId=$f.find("#office").val();
  	         	   var courseId=$f.find("#course").val();
  	         	   var score=$f.find("[dialog-id=score]").val();
  	         	   var stuId=$f.find("#name").val();
  	         	   if(!stuId) {
 	         		   $.messager.popup("请选择学生!");
 	         		   return false;
 	         	   }
  	         	   if(typeof(stuId)=="undefinded"){
  	         		stuId=data.stuId 
  	         	   }
  	         	   if(!clazz){
  	         		 $.messager.popup("请选择班级!");
 	         		   return false;
  	         	   }
  	         	 if(!officeId) {
   	         		   $.messager.popup("请选择专业!");
   	         		   return false;
   	         	   }
  	         	   
  	         	  if(!score) {
  	        		   $.messager.popup("请填写分数!");
  	        		   return false;
  	        	   }
  	         	   var param = {
  	         			  id:data.id,
  	         			  officeId:officeId,
  	     			      stuId:stuId,
  	     			      courseId:courseId,
  	     			      score:score,
  	         	          clazzId:clazz
  	         	   }
  	         	   $.ajax({
  	                    url : ctx + "/score/edit",
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
       
       $('#add').click(function() {
    	   var $f = $('<div role="form" class="row"></div>');
    	     $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select id="office" class="form-control" onclick="loadClazz(this);loadCourse()"></select></div> </div>');
  		     $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">班级</label><select id="clazz" class="form-control" onclick="loadUser(this)"></select></div> </div>');
  		     $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">课程</label><select id="course" class="form-control" "></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">姓名</label><select id="name" class="form-control" "></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">分数</label><input dialog-id="score" class="form-control" required></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">类型</label><select id="state" class="form-control" "><option value="1">统考</option><option value="2">补考</option><option value="3">重修</option></select></div> </div>');
    		 //$f.append('<div class="form-group col-md-6"><div class="input-group">点击到<a href="'+ctx+'/system/dict">字典页</a>添加主题</div></div>');
     		for(i in offices){
     			 var c=offices[i]
     			 $f.find('#office').append('<option value="'+c.id+'">'+c.name+'</option>');
              }
   		 for(i in courses){
     			 var c=courses[i]
     			 $f.find('#course').append('<option value="'+c.id+'">'+c.courseName+'</option>');
              }
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
    	         	   var score=$f.find("[dialog-id=score]").val();
    	         	   var stuId=$f.find("#name").val();
    	         	  var clazzId=$f.find("#clazz").val();
    	         	  var state=$f.find("#state").val();
    	         	  if(!clazzId){
    	         		 $.messager.popup("请选班级!");
     	         		   return false; 
    	         	  }
    	         	   if(!stuId) {
   	         		   $.messager.popup("请选择学生!");
   	         		   return false;
   	         	   }
    	         	 if(!officeId) {
     	         		   $.messager.popup("请选择专业!");
     	         		   return false;
     	         	   }
    	         	   if(!score) {
    	        		   $.messager.popup("请填写分数!");
    	        		   return false;
    	        	   }
    	         	   var param = {
    	         			  officeId:officeId,
    	     			      stuId:stuId,
    	     			      courseId:courseId,
    	     			      score:score,
    	     			      clazzId:clazzId,
    	     			      state:state
    	         	   }
    	         	   $.ajax({
    	                    url : ctx + "/score/save",
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
