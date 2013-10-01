package com.difusores.walcupom.util.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.difusores.walcupom.data.model.Telefone;
import com.difusores.walcupom.web.data.TelefoneUI;


public class TelefoneMapper {
	public TelefoneUI toUIBean(Telefone phone) {
		TelefoneUI phoneUI = null;
		
		if(phone != null){
			phoneUI = new TelefoneUI();
			phoneUI.setDdd(phone.getDdd());
			phoneUI.setId(phone.getId());
			phoneUI.setNumber(phone.getNumber());
			phoneUI.setType(phone.getType());
			phoneUI.setUser(phone.getUser());
		}
		
		return phoneUI;
	}
	
	public List<TelefoneUI> toUIBean(List<Telefone> phones){
		List<TelefoneUI> phonesUI = new ArrayList<TelefoneUI>();
		
		for(Telefone phone : phones)
			phonesUI.add(this.toUIBean(phone));
		
		return phonesUI;
	}
	
	public List<TelefoneUI> toUIBean(Map<String, Telefone> phones){
		List<TelefoneUI> phonesUI = new ArrayList<TelefoneUI>();
		
		for(String key : phones.keySet())
			phonesUI.add(this.toUIBean(phones.get(key)));
		
		return phonesUI;
	}
	
	public Telefone toPersistenceBean(TelefoneUI phoneUI){
		Telefone phone = null;
		
		if(phoneUI != null){
			phone = new Telefone();
			phone.setId(phoneUI.getId());
			phone.setDdd(phone.getDdd());
			phone.setNumber(phoneUI.getNumber());
			phone.setType(phoneUI.getType());
			phone.setUser(phoneUI.getUser());
		}
		
		return phone;
	}
	
	public List<Telefone> toPersistenceBean(List<TelefoneUI> phonesUI){
		List<Telefone> phones = new ArrayList<Telefone>();
		
		for(TelefoneUI phoneUI : phonesUI)
			phones.add(this.toPersistenceBean(phoneUI));
		
		return phones;
	}
	
	public Map<String, Telefone> toPersistenceBeanMap(List<TelefoneUI> phonesUI){
		Map<String, Telefone> phones = new HashMap<String, Telefone>();
		
		for(TelefoneUI phoneUI : phonesUI){
			phones.put(phoneUI.getId(),	 this.toPersistenceBean(phoneUI));
		}
		
		return phones;
	}

}
