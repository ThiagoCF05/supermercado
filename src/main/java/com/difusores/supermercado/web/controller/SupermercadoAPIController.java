package com.difusores.supermercado.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.difusores.supermercado.service.SupermercadoService;
import com.difusores.supermercado.web.data.SupermercadoUI;

@Controller
@RequestMapping(value = "/api/supermercados")
public class SupermercadoAPIController {
	@Autowired
	SupermercadoService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
	public List<SupermercadoUI> list(Model model){
        return service.findAll();
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseBody
	public List<SupermercadoUI> getSupermercados(@RequestParam(required = false) double latitude,
			@RequestParam(required = false) double longitude,
			Model model){
		
		List<SupermercadoUI> supermercados = new ArrayList<SupermercadoUI>();
		double i = 0.05;
		while(i < 1){
			supermercados = service.findByBoundery(latitude, longitude, i);
			
			if(supermercados.size() > 0)
				break;
			
			i = i * 2;
		}
		return supermercados;
	}

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SupermercadoUI create(@RequestBody final SupermercadoUI supermercado){
        SupermercadoUI supermercadoUI = service.create(supermercado);
        return supermercadoUI;
    }

}
