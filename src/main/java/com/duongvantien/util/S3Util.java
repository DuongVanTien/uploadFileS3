package com.duongvantien.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class S3Util {

	private static final Logger logger = LoggerFactory.getLogger(S3Util.class);

	private String awsId;
	private String awsKey;
	private String bucketName;
	private String urlReturn;

	public String upload(String fileName, String pathFile) throws IOException {
		getProperties();
		AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(awsId, awsKey));
		try {
			File file = new File(pathFile);
			s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
			return urlReturn + fileName;

		} catch (AmazonServiceException e) {
			logger.error("Error Message: ", e.getMessage());
			logger.error("HTTP Status Code: ", e.getStatusCode());
			logger.error("AWS Error Code: ", e.getErrorCode());
			logger.error("Request ID: ", e.getRequestId());
			return "Error";
		} catch (AmazonClientException ace) {
			logger.error("AmazonClientException: ", ace.getMessage());
			return "Error";
		}

	}

	public void getProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String filename = "com/framgia/util/amazon.properties";
			input = S3Util.class.getClassLoader().getResourceAsStream(filename);
			if (input != null) {
				prop.load(input);
				awsId = prop.getProperty("aws_access_key_id");
				awsKey = prop.getProperty("aws_secret_access_key");
				bucketName = prop.getProperty("aws_namecard_bucket");
				urlReturn = prop.getProperty("aws_url_return_prefix");
			}
		} catch (IOException ex) {
			logger.error("getProperties: ", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("getProperties: ", e);
				}
			}
		}
	}
}