package com.difusores.walcupom.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.difusores.walcupom.data.model.User;
import com.difusores.walcupom.web.data.UserUI;


public class UserMapper {
	public UserUI toUIBean(User user) {
		UserUI userUI = null;
		
		if(user != null){
			userUI = new UserUI();
			userUI.setId(user.getId());
			userUI.setUserName(user.getUserName());
			userUI.setEmail(user.getEmail());
			userUI.setPassword(user.getPassword());
			userUI.setAdmin(user.isAdmin());
			userUI.setBanned(user.isBanned());
		}
		return userUI;
	}
	
	public List<UserUI> toUIBean(List<User> users) {
		List<UserUI> usersUI = new ArrayList<UserUI>();
		
		for(User user : users){
			usersUI.add(this.toUIBean(user));
		}
		
		return usersUI;
		
	}
	
	public Page<UserUI> toUIBean(Page<User> users, Pageable pageable) {
		Page<UserUI> usersUI = new PageImpl<UserUI>(
				this.toUIBean(users.getContent()), pageable,
				users.getTotalElements());
		
		return usersUI;
		
	}
	
	public User toPersistenceBean(UserUI userUI) {
		User user = null;
		
		if(userUI != null){
			user = new User();
			user.setId(userUI.getId());
			user.setUserName(userUI.getUserName());
			user.setEmail(userUI.getEmail());
			user.setPassword(userUI.getPassword());
			user.setAdmin(userUI.isAdmin());
			user.setBanned(userUI.isBanned());
		}
		
		return user;
	}
	
	public List<User> toPersistenceBean(List<UserUI> usersUI) {
		List<User> users = new ArrayList<User>();
		
		for(UserUI userUI : usersUI)
			users.add(this.toPersistenceBean(userUI));
		
		return users;
		
	}

}
