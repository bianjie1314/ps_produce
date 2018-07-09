update think_flow f,
(
SELECT  id,user_name,name,udf_data,
 '加班'                  
 as 'flow_type',
CASE  
WHEN LOCATE('"267":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"267":',udf_data)+7,16)
WHEN LOCATE('"432":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"432":',udf_data)+7,16)
WHEN LOCATE('"435":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"435":',udf_data)+7,16)
WHEN LOCATE('"445":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"445":',udf_data)+7,16)

END as 'start',
CASE  
WHEN LOCATE('"268":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"268":',udf_data)+7,16)
WHEN LOCATE('"433":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"433":',udf_data)+7,16)
WHEN LOCATE('"436":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"436":',udf_data)+7,16)
WHEN LOCATE('"446":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"446":',udf_data)+7,16)

END as 'end'
 from think_flow  where  type in (61,102,108,109,113,115) and  step=40
)  a set f.flow_type=a.flow_type,f.start=a.start,f.end=a.end where f.id=a.id;


---------------xxx---------------------------
update think_flow f, (
SELECT  id,user_name,name,udf_data,
 '忘打卡'                  
 as 'flow_type',
CASE  
WHEN LOCATE('"371":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"371":',udf_data)+7,16)
WHEN LOCATE('"448":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"448":',udf_data)+7,16)

WHEN LOCATE('"450":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"450":',udf_data)+7,16)

WHEN LOCATE('"452":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"452":',udf_data)+7,16)
WHEN LOCATE('"454":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"454":',udf_data)+7,16)
WHEN LOCATE('"472":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"472":',udf_data)+7,16)

END as 'start',
CASE  
WHEN LOCATE('"265":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"265":',udf_data)+7,16)
WHEN LOCATE('"275":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"265":',udf_data)+7,16)
WHEN LOCATE('"280":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"280":',udf_data)+7,16)  
WHEN LOCATE('"418":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"418":',udf_data)+7,16)
WHEN LOCATE('"422":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"422":',udf_data)+7,16)    
WHEN LOCATE('"430":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"430":',udf_data)+7,16)  
WHEN LOCATE('"443":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"443":',udf_data)+7,16)  
WHEN LOCATE('"493":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"493":',udf_data)+7,16)           
END as 'end'
 from think_flow  where  type in (95,116,117,118,119,120,122) 
)  a  set f.flow_type=a.flow_type,f.start=a.start,f.end=a.end where f.id=a.id;



update think_flow f,
(
SELECT  id,user_name,name,udf_data,
CASE  
WHEN LOCATE('"病假"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"病假"',udf_data)+1,2)  
WHEN LOCATE('"事假"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"事假"',udf_data)+1,2) 
WHEN LOCATE('"调休"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"调休"',udf_data)+1,2)          
WHEN LOCATE('"年假"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"年假"',udf_data)+1,2) 
WHEN LOCATE('"加班申请"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"加班申请"',udf_data)+1,2)          
WHEN LOCATE('"陪产假"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"陪产假"',udf_data)+1,2)          
WHEN LOCATE('"婚假"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"婚假"',udf_data)+1,2)          
WHEN LOCATE('"产假"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"产假"',udf_data)+1,2)   
WHEN LOCATE('"出差"',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"出差"',udf_data)+1,2)   
else '事假'                  
END as 'flow_type',
CASE  
WHEN LOCATE('"264":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"264":',udf_data)+7,16)
WHEN LOCATE('"274":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"264":',udf_data)+7,16)    
WHEN LOCATE('"279":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"279":',udf_data)+7,16)    
WHEN LOCATE('"417":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"417":',udf_data)+7,16)   
WHEN LOCATE('"421":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"421":',udf_data)+7,16)    
WHEN LOCATE('"429":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"429":',udf_data)+7,16)  
WHEN LOCATE('"442":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"442":',udf_data)+7,16)  
WHEN LOCATE('"493":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"493":',udf_data)+7,16)          
END as 'start',
CASE  
WHEN LOCATE('"265":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"265":',udf_data)+7,16)
WHEN LOCATE('"275":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"265":',udf_data)+7,16)
WHEN LOCATE('"280":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"280":',udf_data)+7,16)  
WHEN LOCATE('"418":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"418":',udf_data)+7,16)
WHEN LOCATE('"422":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"422":',udf_data)+7,16)    
WHEN LOCATE('"430":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"430":',udf_data)+7,16)  
WHEN LOCATE('"443":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"443":',udf_data)+7,16)  
WHEN LOCATE('"494":',udf_data)>0 THEN SUBSTR(udf_data, LOCATE('"494":',udf_data)+7,16)           
END as 'end'
 from think_flow  where  type in (60,64,110,111,112,114,126) and  step=40
)  a set f.flow_type=a.flow_type,f.start=a.start,f.end=a.end where f.id=a.id;


select dept,user_name,sign_date,should_work_long,work_long,sign_num,late,leave_early,overtime,forget_sign_apply,
if(forget_sign_apply_id=0,'',CONCAT("http://oa.ziguangcn.com/index.php?m=&c=Flow&a=read&id=",forget_sign_apply_id)),
leave_apply_type,leave_apply,leave_long,
if(leave_apply_id=0,'',CONCAT("http://oa.ziguangcn.com/index.php?m=&c=Flow&a=read&id=",leave_apply_id)),overtime_apply,
if(overtime_apply_id=0,'',CONCAT("http://oa.ziguangcn.com/index.php?m=&c=Flow&a=read&id=",overtime_apply_id)),is_holiday,duty,s_date,start_date,end_date, jstart_date,jend_date,remark from think_sign_report