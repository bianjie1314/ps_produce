package com.ps.produce.amazon.s3;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.s3.model.StorageClass;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;

@Service
public class S3Service {
	
	private static Log logger = LogFactory.getLog(S3Service.class);
	private static final String SUFFIX = "/";
	public AmazonS3 s3client = null;
	private static String BUCKET_NAME="trade-sta.ziguangcn.com";
	private static final String S3_DOMAIN ="";
	private static final String ACCESS_KEY_ID="AKIAPVBWOVBEAALFT6QA";
	private static final String SECRET_ACCESS_KEY="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";

	@PostConstruct
	public void initialize() {
		AWSCredentials credentials = new BasicAWSCredentials(
				ACCESS_KEY_ID,
				SECRET_ACCESS_KEY);
		s3client = new AmazonS3Client(credentials);
		s3client.setRegion(Region.CN_Beijing.toAWSRegion());
	
	}
	

	public String upload(InputStream uploadFileStream,String fileName)  {
		PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME,
				fileName,uploadFileStream , new ObjectMetadata()).withStorageClass(StorageClass.ReducedRedundancy);
		this.s3client.putObject(putObjectRequest);
		
		StringBuffer sb = new StringBuffer();
		sb.append("http://");
		sb.append(BUCKET_NAME).append(S3_DOMAIN).append(SUFFIX).append(fileName);
		return sb.toString();
	}
	
	public String uploadFolder(File file)  {
		TransferManager tm = new TransferManager(s3client);
		String fileName = String.valueOf(new Date().getTime());
		MultipleFileUpload multipleFileUpload =	tm.uploadDirectory(BUCKET_NAME,fileName , file, true);
		while(!multipleFileUpload.isDone()){
			
		}
		tm.shutdownNow(false);
		
		StringBuffer sb = new StringBuffer();
		sb.append("http://");
		sb.append(BUCKET_NAME).append(".").append(S3_DOMAIN).append(SUFFIX).append(fileName);
		return sb.toString();
	}
	





	
	

}
