package com.difusores.supermercado.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.difusores.supermercado.service.UserService;
import com.difusores.supermercado.util.PaginationInfo;
import com.difusores.supermercado.web.data.UserUI;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model) {
		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "user/list";
		
		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		Page<UserUI> users = service.findAll(pageable);
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				users.getTotalElements(), pageLimit, listAction);
		
		model.addAttribute("users", users.getContent());
		model.addAttribute("pageInfo", pageInfo);

		logger.debug("Pageable :: total: " + users.getTotalElements()
				+ " questions: " + users.getContent());

		return listAction;
	}
	
	@RequestMapping(value = "/view/{userName:.*}")
	public String view(@PathVariable String userName, Model model)
			throws Exception {
		UserUI user = service.findByUsername(userName);
		
		model.addAttribute("user", user);
		
		return "user/view";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "user/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(UserUI data) {
		System.out.println("In add post method");
		String resultPage = "access/login";

		if (service.findByUsername(data.getUserName()) != null) {
			logger.debug("Account Name already exists");
		}

		UserUI savedUser = service.create(data);
		if (savedUser != null) {
			logger.debug("Account Created Succesfully");
			resultPage = "access/login";
		} else {
			logger.debug("Error trying to create account.");
			resultPage = "user/add";
		}

		return resultPage;
	}
	
	
	@InitBinder
	public void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		binder.registerCustomEditor(Date.class, "bornDate", new CustomDateEditor(dateFormat, true));
	}

}
