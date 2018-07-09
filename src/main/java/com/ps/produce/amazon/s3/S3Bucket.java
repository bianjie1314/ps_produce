package com.ps.produce.amazon.s3;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.StorageClass;

public class S3Bucket {

	private static Log logger = LogFactory.getLog(S3Bucket.class);
	private static final String SUFFIX = "/";
	public static AmazonS3 s3client = null;
	
	private static String BUCKET_NAME="trade-sta.ziguangcn.com";
	private static final String S3_DOMAIN ="s3.cn-north-1.amazonaws.com.cn";
	private static final String ACCESS_KEY_ID="AKIAPVBWOVBEAALFT6QA";
	private static final String SECRET_ACCESS_KEY="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";
	


	

	public static void main(String[] args) {
		try {
			S3Bucket s3Service = new S3Bucket();
			AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID,SECRET_ACCESS_KEY);
			s3client = new AmazonS3Client(credentials);
			s3client.setRegion(Region.CN_Beijing.toAWSRegion());
			s3Service.getCount(BUCKET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCount(String bucketName) {
		ObjectListing listing = s3client.listObjects(bucketName);
		int objectNum = 0;
		while (listing.isTruncated()) {
			listing = s3client.listNextBatchOfObjects(listing);
			for (S3ObjectSummary objectSummary : listing.getObjectSummaries()) {
				objectNum++;
				System.out.println(objectNum);
			}
		}
		System.out.println("total " + objectNum + " object(s).");
	}

	

}
