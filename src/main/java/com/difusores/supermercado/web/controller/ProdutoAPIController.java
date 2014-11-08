package com.difusores.supermercado.web.controller;

import com.difusores.supermercado.service.ProdutoService;
import com.difusores.supermercado.web.data.ProdutoUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/produtos")
public class ProdutoAPIController {
	@Autowired
	ProdutoService service;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseBody
	public ProdutoUI getProduto(@RequestParam(required = false) String formato,
			@RequestParam(required = false) String codigoBarra,
			Model model){
		return service.findByCodigoBarra(codigoBarra);
	}

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<ProdutoUI> list(){
        return service.findAll();
    }

}
