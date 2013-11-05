package com.difusores.supermercado.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.difusores.supermercado.service.ProdutoService;
import com.difusores.supermercado.util.PaginationInfo;
import com.difusores.supermercado.web.data.ProdutoUI;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService service;
	
	private Logger logger = Logger.getLogger(ProdutoController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model){
		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "produtos/list";
		
		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		Page<ProdutoUI> produtos = service.findAll(pageable);
		
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				produtos.getTotalElements(), pageLimit, listAction);
		
		model.addAttribute("produtos", produtos.getContent());
		model.addAttribute("pageInfo", pageInfo);

		logger.debug("Pageable :: total: " + produtos.getTotalElements()
				+ " questions: " + produtos.getContent());

		return "produtos/list";
	}
	
	@RequestMapping(value = "/view/{id:.*}", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model){
		ProdutoUI produto = service.find(id);
		
		model.addAttribute("produto", produto);
		return "produto/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model){
		ProdutoUI produtoUI  = new ProdutoUI();
		
		model.addAttribute("produto", produtoUI);
		return "produto/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("produto") ProdutoUI produto, Model model){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		produto.setUser(user.getUsername());
		
		if(produto.getId() != null){
			if(produto.getId().isEmpty())
				produto.setId(null);
		}
		
		service.create(produto);
		
		return "redirect:/produtos/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id:.*}", method = RequestMethod.GET)
	public String delete(@PathVariable String id, Model model){
		boolean resultado = service.delete(id);
		if(resultado)
			return "Produto removido com sucesso.";
		else
			return "Falha ao remover produto.";
	}
}
