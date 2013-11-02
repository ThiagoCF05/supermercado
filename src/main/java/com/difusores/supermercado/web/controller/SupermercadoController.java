package com.difusores.supermercado.web.controller;


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

import com.difusores.supermercado.service.SupermercadoService;
import com.difusores.supermercado.service.UserService;
import com.difusores.supermercado.util.PaginationInfo;
import com.difusores.supermercado.web.data.SupermercadoUI;

@Controller
@RequestMapping(value = "/supermercados")
public class SupermercadoController {
	@Autowired
	SupermercadoService service;
	@Autowired
	UserService userService;
	
	private Logger logger = Logger.getLogger(SupermercadoController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize,
			@RequestParam(required = false) String msg,
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String cidade,
			@RequestParam(required = false) String bairro,
			@RequestParam(required = false) String rede,
			Model model){
		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "supermercados/list";
		
		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		Page<SupermercadoUI> supermercados = null;
		
		if(type == null)
			supermercados = service.findAll(pageable);
		else if(type.equals("bairro"))
			supermercados = service.findByBairro(bairro, pageable);
		else if(type.equals("rede"))
			supermercados = service.findByRede(rede, pageable);
		else
			supermercados = service.findByCidade(cidade, pageable);
		
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				supermercados.getTotalElements(), pageLimit, listAction);
		
		model.addAttribute("bairros", service.findBairros());
		model.addAttribute("cidades", service.findCidades());
		model.addAttribute("redes", service.findRedes());
		model.addAttribute("supermercados", supermercados.getContent());
		model.addAttribute("pageInfo", pageInfo);
		
		if(msg != null){
			if(msg.equals("cadastrado"))
				model.addAttribute("msg", "O supermercado já está cadastrado.");
		}

		logger.debug("Pageable :: total: " + supermercados.getTotalElements()
				+ " questions: " + supermercados.getContent());

		return "supermercado/list";
	}
	
	@RequestMapping(value = "/view/{id:.*}", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model){
		SupermercadoUI supermercado = service.find(id);
		
		model.addAttribute("supermercado", supermercado);
		return "supermercado/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model){
		SupermercadoUI supermercadoUI  = new SupermercadoUI();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		supermercadoUI.setUser(user.getUsername());
		
		model.addAttribute("supermercado", supermercadoUI);
		return "supermercado/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("endereco") SupermercadoUI supermercado, Model model){
		
		if(supermercado.getId() != null){
			if(supermercado.getId().isEmpty())
				supermercado.setId(null);
		}
		
		List<SupermercadoUI> supermercados = 
				service.findByCepAndNumero(supermercado.getCep(), supermercado.getNumero());
		if(supermercados.size() != 0)
			model.addAttribute("msg", "cadastrado");
		else{
			if(supermercado.getLatitude() == 0.0 && supermercado.getLongitude() == 0.0)
				supermercado = service.insertLocation(supermercado);
			service.create(supermercado);
		}
		
		return "redirect:/supermercados/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id:.*}", method = RequestMethod.GET)
	public String delete(@PathVariable String id, Model model){
		boolean resultado = service.delete(id);
		if(resultado)
			return "Endereço removido com sucesso.";
		else
			return "Falha ao remover endereço.";
	}

}
