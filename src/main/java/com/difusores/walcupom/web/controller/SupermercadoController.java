package com.difusores.walcupom.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.difusores.walcupom.service.EnderecoService;
import com.difusores.walcupom.service.UserService;
import com.difusores.walcupom.web.data.EnderecoUI;

@Controller
@RequestMapping(value = "/enderecos")
public class EnderecoController {
	@Autowired
	EnderecoService service;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model){
		EnderecoUI endereco  = new EnderecoUI();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		endereco.setUser(user.getUsername());
		
		List<EnderecoUI> enderecos = service.findByUser(user.getUsername());
		
		model.addAttribute("endereco", endereco);
		model.addAttribute("enderecos", enderecos);
		return "endereco/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("endereco") EnderecoUI endereco, Model model){
		
		endereco = service.insertLocation(endereco);
		service.create(endereco);
		
		return "endereco/add";
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
