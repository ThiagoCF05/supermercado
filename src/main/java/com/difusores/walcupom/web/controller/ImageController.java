package com.difusores.walcupom.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) throws IOException{
		if (!file.isEmpty()) {
			 BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			 File destination = new File("File directory with file name"); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
			 ImageIO.write(src, "png", destination);
			 //Save the id you have used to create the file name in the DB. You can retrieve the image in future with the ID.
			 } 
		return "";
	}


}
