package com.difusores.walcupom.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.difusores.walcupom.service.FileUploadService;
import com.difusores.walcupom.web.data.FileUploadUI;

@Controller
@RequestMapping("/files")
public class FileUploadController {
	@Autowired
	FileUploadService service;
	
	@ResponseBody
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(@RequestParam(required = false) String campanhaId, 
			@ModelAttribute("form") FileUploadUI form){
		
		String resultado = service.save(form.getImage());
		
		return resultado;
	}

}
