package com.ps.produce.support.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ZipImgs {
      public static void zipImgs( HttpServletResponse response,List<String>imgUrls) {
    	  try {
              String downloadFilename = "图片.zip";//文件的名称
              downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
              response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
              response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
              ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
              List<String> files = imgUrls;
              for (int i=0;i<files.size();i++) {
                  URL url = new URL(files.get(i));
                 zos.putNextEntry(new ZipEntry(i+".jpg"));
                 //FileInputStream fis = new FileInputStream(new File(files[i])); 
                 InputStream fis = url.openConnection().getInputStream();  
                 byte[] buffer = new byte[1024];    
                 int r = 0;    
                 while ((r = fis.read(buffer)) != -1) {    
                     zos.write(buffer, 0, r);    
                 }    
                 fis.close();  
                } 
              zos.flush();    
              zos.close();
          } catch (UnsupportedEncodingException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }
}
