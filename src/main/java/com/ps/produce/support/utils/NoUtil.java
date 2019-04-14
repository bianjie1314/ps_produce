package com.ps.produce.support.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class NoUtil {
	public static String generateCode(String billType,String DbBillCode){

		//存放最终生成的单据编号的字符串
		        String billCode=new String();

		        String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date());

		        if("".equals(DbBillCode)||DbBillCode==null){
		//如果单号不存在,则设置每天的第一个单号
		            billCode=billType+dateString+"001";
		        }else{

		//取出单据号中的固定位
		            String str=billType+DbBillCode.substring(0,8);

		//取出流水号
		            String temp=DbBillCode.substring(DbBillCode.length()-3,DbBillCode.length());


		//取出当天的所有单号中最大的单号截取后自增1
		       if(Integer.parseInt(temp)>=1&&Integer.parseInt(temp)<999){
		           temp=String.valueOf(Integer.parseInt(temp)+1);
		             }
		           switch (temp.length()) {
		           case 1:
		           temp="00"+temp;
		           break;
		           case 2:
		           temp="0"+temp;
		           break;
		           default:
		            break;
		             }
		          billCode=str+temp;
		          }
		        System.out.println(billCode);
		        return billCode;
		      }
}
