package com.difusores.walcupom.web.controller;

import java.util.Date;
import java.util.List;

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

import com.difusores.walcupom.service.CategoriaService;
import com.difusores.walcupom.util.PaginationInfo;
import com.difusores.walcupom.web.data.CategoriaUI;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	CategoriaService service;
	
	private Logger logger = Logger.getLogger(CategoriaController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(required = false) String type ,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model){
		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "categoria/list";
		
		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		Page<CategoriaUI> categorias = service.findByStatus(false,pageable);
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				categorias.getTotalElements(), pageLimit, listAction);
		
		model.addAttribute("categorias", categorias.getContent());
		model.addAttribute("pageInfo", pageInfo);
		
		logger.debug("Pageable :: total: " + categorias.getTotalElements()
				+ " questions: " + categorias.getContent());
		
		return listAction;
	}
	
	@RequestMapping(value = "/view/{id:.*}")
	public String view(@PathVariable String id, Model model)
			throws Exception {
		CategoriaUI categoria = service.find(id);
		
		model.addAttribute("categoria", categoria);
		
		return "categoria/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model){
		CategoriaUI categoriaUI = new CategoriaUI();
		
		model.addAttribute("categoria", categoriaUI);
		
		return "categoria/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("categoria") CategoriaUI categoria, Model model){
		Date data = new Date(System.currentTimeMillis());
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		categoria.setUser(user.getUsername());
		categoria.setDataAtualizacao(data);
		categoria.setDataCriacao(data);
		
		service.create(categoria);
		
		return "redirect:/categorias/list";
	}
	
	@RequestMapping(value = "/listJson", method = RequestMethod.GET)
	@ResponseBody
	public List<CategoriaUI> listJson(@RequestParam(required = true) String status ,
			Model model){
		boolean removido = (status.equals("inativo")) ? true : false;
		List<CategoriaUI> categorias = service.findByStatus(removido);
		
		return categorias;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id:.*}", method = RequestMethod.GET)
	public String delete(@PathVariable String id){
		boolean resultado = service.delete(id);
		
		if(resultado)
			return "OK";
		else
			return "Not OK";
	}

}
