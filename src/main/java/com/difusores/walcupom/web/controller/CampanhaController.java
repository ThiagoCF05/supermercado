package com.difusores.walcupom.web.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.difusores.walcupom.service.CampanhaService;
import com.difusores.walcupom.service.CupomService;
import com.difusores.walcupom.service.EnderecoService;
import com.difusores.walcupom.service.UserService;
import com.difusores.walcupom.util.PaginationInfo;
import com.difusores.walcupom.util.enums.TipoCampanha;
import com.difusores.walcupom.util.mapper.UserMapper;
import com.difusores.walcupom.web.data.CampanhaUI;
import com.difusores.walcupom.web.data.CupomUI;
import com.difusores.walcupom.web.data.UserUI;

@Controller
@RequestMapping("/campaigns")
public class CampanhaController {
	@Autowired
	CampanhaService service;
	@Autowired
	UserService userService;
	@Autowired
	EnderecoService enderecoService;
	@Autowired
	CupomService cupomService;
	
	UserMapper userMapper = new UserMapper();
	
	private Logger logger = Logger.getLogger(CampanhaController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(required = false) String type ,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model){
		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "campanha/list";
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserUI userUI = userService.findByUsername(user.getUsername());
		
		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		List<CampanhaUI> camps = service.findByType(userUI, type, pageable);
		Page<CampanhaUI> campanhas = new PageImpl<CampanhaUI>(camps, pageable, pageLimit);
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				campanhas.getTotalElements(), pageLimit, listAction);
		
		model.addAttribute("campanhas", campanhas.getContent());
		model.addAttribute("pageInfo", pageInfo);
		
		logger.debug("Pageable :: total: " + campanhas.getTotalElements()
				+ " questions: " + campanhas.getContent());
		
		return listAction;
	}
	
	@RequestMapping(value = "/listJson", method = RequestMethod.GET)
	@ResponseBody
	public List<CampanhaUI> listJson(@RequestParam(required = false) String type ,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model){
		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "campanha/list";
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserUI userUI = userService.findByUsername(user.getUsername());
		
		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		List<CampanhaUI> camps = service.findByType(userUI, type, pageable);
		Page<CampanhaUI> campanhas = new PageImpl<CampanhaUI>(camps, pageable, pageLimit);
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				campanhas.getTotalElements(), pageLimit, listAction);
		
		//model.addAttribute("campanhas", campanhas.getContent());
		model.addAttribute("pageInfo", pageInfo);
		
		logger.debug("Pageable :: total: " + campanhas.getTotalElements()
				+ " questions: " + campanhas.getContent());
		
		return camps;
	}
	
	@RequestMapping(value = "/view/{id:.*}", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model){
		CampanhaUI campanha = service.find(id);
		List<CupomUI> cupons = cupomService.findByCampanha(campanha.getId());	
		
		model.addAttribute("campanha", campanha);
		model.addAttribute("cupons", cupons);
		
		return "campanha/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model){
		CampanhaUI campanha = new CampanhaUI();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserUI userUI = userService.findByUsername(user.getUsername());
		
		String[] horas = new String[]{"00", "01", "02", "03", "04", "05",
				"06","07","08","09","10","11","12"};
		String[] minutos = new String[]{"00", "05", "10", "15", "20", "25", "30", 
				"35", "40", "45", "50", "55"};
		String[] limiteUso = new String[]{"01", "02", "03", "04", "05",
				"06","07","08","09","10","11","12"};
		String[] distancias = new String[]{"05", "10", "15", "20", "25", "30", 
				"35", "40", "45", "50", "55"};
		
		campanha.setUser(userUI);
		
		model.addAttribute("limiteUso", limiteUso);
		model.addAttribute("horas", horas);
		model.addAttribute("minutos", minutos);
		model.addAttribute("campanha", campanha);
		model.addAttribute("distancias", distancias);
		model.addAttribute("enderecosUsuario", enderecoService.findByUser(userUI.getUserName()));
		
		return "campanha/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("campanha") CampanhaUI campanha, Model model){
		if(campanha.getEnderecos() == null){
			model.addAttribute("msg", "Para adicionar uma campanha, favor inserir um Endereço");
			return "redirect:/campaigns/add";
		}
		else{
			if(campanha.getEnderecos().size() == 0){
				model.addAttribute("msg", "Para adicionar uma campanha, favor inserir um Endereço");
				return "redirect:/campaigns/add";
			}
		}
		
		campanha.setDataCriacao(new Date(System.currentTimeMillis()));
		if(service.create(campanha) != null){
			model.addAttribute("campanha", campanha);
			return "redirect:/campaigns/list";
		}
		else
			return "redirect:/list";
	}
	
	@RequestMapping(value = "/delete/{id:.*}", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable String id, Model model){
		boolean delete = service.delete(id);
		String msg = "";
		if(delete)
			msg = "Campanha removida com sucesso.";
		else
			msg = "Não foi possível remover a campanha.";
		
		return msg;
	}
	
	@RequestMapping(value = "/validarCupom/{cupomId:.*}&{campanhaId:.*}", method = RequestMethod.GET)
	@ResponseBody
	public String validarCupom(@PathVariable String cupomId, @PathVariable String campanhaId){
		String msg = "";
		
		boolean validado = cupomService.validarCupom(cupomId, campanhaId);
		
		if(validado)
			msg = "validado";
		else
			msg = "nao validado";
		
		return msg;
	}
	
	@InitBinder
	public void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		binder.registerCustomEditor(TipoCampanha.class, "tipo", new PropertyEditorSupport() {
			@Override
			public void setAsText(final String id) {
				TipoCampanha tipo = TipoCampanha.valueOf(id);
				setValue(tipo);
			}
		});
		
		binder.registerCustomEditor(UserUI.class, "user", new PropertyEditorSupport() {
			@Override
			public void setAsText(final String id) {
				UserUI user = userService.find(id);
				setValue(user);
			}
		});
		
		binder.registerCustomEditor(List.class, "enderecos", new CustomCollectionEditor(List.class) {
			@Override
			protected Object convertElement(final Object element) {
				return (element == null ? null : enderecoService.find((String) element));
			}
		});
	}

}
