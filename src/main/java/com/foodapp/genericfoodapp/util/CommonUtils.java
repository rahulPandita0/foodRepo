package com.foodapp.genericfoodapp.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class CommonUtils {

	public static String randomAlphaNumeric() {
		int count = 18;
		StringBuilder builder = new StringBuilder();
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public  static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
	    File convFile = new File(fileName);
	    multipart.transferTo(convFile);
	    return convFile;
	}
	
	public static ByteArrayInputStream retrieveByteArrayInputStream(File file) throws IOException {
	    return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
	}

}
