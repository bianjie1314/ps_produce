package com.ps.produce.system.service;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ps.produce.amazon.s3.S3Service;
import com.ps.produce.support.Encodes;
import com.ps.produce.support.FileRespon;
@Service
public class FileService {

	@Autowired
	private S3Service s3Service;
	
	public BufferedImage load(String path) {

//		try {
//			return ImageIO.read(new File(this.imageStorePath + path));
//		} catch (IOException e) {
//			logger.error("Can't read input file {0}", path);
//
//			return null;
//		}
		return null;
	}


	

	public FileRespon save(MultipartFile mf)  throws IOException {
		String encodeFilename =  Encodes.encodeUrlSafeBase64(DigestUtils.md5DigestAsHex(mf.getBytes()).getBytes());
		String extension = FilenameUtils.getExtension(mf.getOriginalFilename());
		String id=encodeFilename+"."+extension;
		String url = s3Service.upload(mf.getInputStream(), id);
		
		FileRespon file = new FileRespon();
		file.setName(mf.getOriginalFilename());
		
		file.setId(id);
		file.setUrl(url);
		file.setThumbnailUrl(url);
		file.setDeleteUrl(url);
		file.setDeleteType("POST");
		return file;
		
	}




	public void setS3Service(S3Service s3Service) {
		this.s3Service = s3Service;
	}

	

}
