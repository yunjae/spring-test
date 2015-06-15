package com.apress.prospringmvc.bookstore.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadOrderController {
	private Logger logger = LoggerFactory.getLogger(UploadOrderController.class);
	
	@RequestMapping(value = "/order/upload", method = RequestMethod.POST)
	public String handleUpload(MultipartHttpServletRequest request) throws Exception {
		Map<String, MultipartFile> files =request.getFileMap();
		
		for (MultipartFile file : files.values()) {
			logFile(file);
		}		
		return "redirect:/customer/account";
	}
	
	private void logFile(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		this.logger.debug("Recived order : {}. size : {}", file.getOriginalFilename(), file.getSize());
		
		String fileFullNm = "/home/chotom/" +  file.getOriginalFilename();
		
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();				
				
			BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(new File(fileFullNm)));
			outStream.write(bytes);
			outStream.close();				
		}
	}
		
}
