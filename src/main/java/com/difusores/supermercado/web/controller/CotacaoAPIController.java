package com.difusores.supermercado.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.difusores.supermercado.service.CotacaoService;
import com.difusores.supermercado.web.data.CotacaoUI;

@Controller
@RequestMapping(value = "/api/cotacoes")
public class CotacaoAPIController {
	@Autowired
	CotacaoService service;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@ResponseBody
	public CotacaoUI addCotacao(Model model,
			@RequestParam(required = true) String supermercadoId,
			@RequestParam(required = true) String produtoId,
			@RequestParam(required = true) String userId,
			@RequestParam(required = true) double preco){
		
		CotacaoUI cotacao = service.create(supermercadoId, produtoId, userId, preco);
		
		return cotacao;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseBody
	public CotacaoUI getCotacao(Model model,
			@RequestParam(required = true) String id){
		
		CotacaoUI cotacao = service.find(id);
		
		return cotacao;
	}

}
