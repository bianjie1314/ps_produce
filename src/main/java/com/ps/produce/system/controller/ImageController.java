package com.ps.produce.system.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ps.produce.support.FileRespon;
import com.ps.produce.system.service.FileService;

@Controller
@RequestMapping(value = "/system/image")
public class ImageController {

    // private static String basePath = "/var/lib/tomcat6/img"+ File.separator;
    @Autowired
    FileService fileService;
  

    @RequestMapping("/{id:.+}")
    public ResponseEntity<byte[]> load(@PathVariable("id") String id) throws IOException {
        BufferedImage bufferedImage = fileService.load(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> upload(MultipartHttpServletRequest request) throws IllegalStateException, IOException {

        Map<String, Object> result = new HashMap<>();
        Iterator<String> itr = request.getFileNames();
        MultipartFile mf = request.getFile(itr.next());
        FileRespon file = fileService.save(mf);
        List<FileRespon> files = new ArrayList<FileRespon>();
        files.add(file);
        result.put("files", files);
        
        return result;
    }

  

    @RequestMapping("/delete/{eventId}/{id:.+}")
    public @ResponseBody Map<String, Object> delete(@PathVariable("id") String imgId,@PathVariable("eventId") Long eventId) throws IOException {
        //this.eventImgService.delete(imgId, eventId);
        FileRespon file = new FileRespon();
        List<FileRespon> files = new ArrayList<FileRespon>();
        files.add(file);
        Map<String, Object> result = new HashMap<>();
        result.put("files", files);
        return result;

    }

   
}
