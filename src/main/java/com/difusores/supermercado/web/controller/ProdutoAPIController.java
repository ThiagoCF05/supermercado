package com.difusores.supermercado.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.difusores.supermercado.service.ProdutoService;
import com.difusores.supermercado.web.data.ProdutoUI;

@Controller
@RequestMapping("/api/produtos")
public class ProdutoAPIController {
	@Autowired
	ProdutoService service;
	
	public ProdutoUI getProduto(@RequestParam(required = false) String formato,
			@RequestParam(required = false) String codigoBarra,
			Model model){
		return service.findByCodigoBarra(codigoBarra);
		
	}

}
