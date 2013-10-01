package com.difusores.walcupom.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.difusores.walcupom.data.model.Device;
import com.difusores.walcupom.service.CampanhaService;
import com.difusores.walcupom.service.CupomService;
import com.difusores.walcupom.service.DeviceService;
import com.difusores.walcupom.web.data.CampanhaUI;
import com.difusores.walcupom.web.data.CupomUI;

@Controller
@RequestMapping("/api")
public class APIController {

	@Autowired
	DeviceService service;
	@Autowired
	CampanhaService campanhaService;
	@Autowired
	CupomService cupomService;
	
	@RequestMapping(value = "/setInformation/{regid:.*}&{latitude:.*}&{longitude:.*}&{phoneNumber:.*}", 
			method = RequestMethod.GET)
	@ResponseBody
	public String setDeviceInformation(@PathVariable String regid, 
			@PathVariable String latitude,
			@PathVariable String longitude,
			@PathVariable String phoneNumber){
		if(regid != null || regid != ""){
			Device device = new Device(regid, Double.parseDouble(latitude), 
					Double.parseDouble(longitude), phoneNumber);
			service.save(device);
		}
		
		String ok = "OK";
		return ok;
	}
	
	@ResponseBody
	@RequestMapping(value = "/aceitarCupom/{cupomId:.*}&{campanhaId:.*}", method = RequestMethod.GET)
	public String aceitarCupom(@PathVariable String cupomId, @PathVariable String campanhaId){
		boolean resultado = cupomService.aceitarCupom(cupomId, campanhaId);
		
		if(resultado)
			return "OK";
		else
			return "Not OK";
	}
	
	@RequestMapping(value = "/campaign/{id:.*}")
	@ResponseBody
	public CampanhaUI getCampanha(@PathVariable String id){
		CampanhaUI campanha = campanhaService.find(id);
		return campanha;
	}
	
	@RequestMapping(value = "/cupons/list/{device:.*}")
	@ResponseBody
	public List<CupomUI> getCuponsByDevice(@PathVariable String device){
		List<CupomUI> cupons = cupomService.findByDevice(device);
		return cupons;
	}

}
