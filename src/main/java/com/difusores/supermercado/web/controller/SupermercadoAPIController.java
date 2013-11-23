package com.difusores.supermercado.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.difusores.supermercado.service.SupermercadoService;
import com.difusores.supermercado.web.data.SupermercadoUI;

@Controller
@RequestMapping(value = "/api/supermercados")
public class SupermercadoAPIController {
	@Autowired
	SupermercadoService service;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseBody
	public List<SupermercadoUI> getSupermercados(@RequestParam(required = false) double latitude,
			@RequestParam(required = false) double longitude,
			Model model){
		
		List<SupermercadoUI> supermercados = new ArrayList<SupermercadoUI>();
		int i = 10;
		while(i < 100){
			supermercados = service.findByBoundery(latitude, longitude, i);
			
			if(supermercados.size() > 0)
				break;
			
			i = i + 10;
		}
		
		return supermercados;
		
	}

}
