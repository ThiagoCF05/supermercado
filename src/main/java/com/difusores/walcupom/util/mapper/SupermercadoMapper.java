package com.difusores.walcupom.util.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.difusores.walcupom.data.model.Endereco;
import com.difusores.walcupom.web.data.EnderecoUI;

public class EnderecoMapper {
	public EnderecoUI toUIBean(Endereco endereco){
		EnderecoUI enderecoUI = null;
		
		if(endereco != null){
			enderecoUI = new EnderecoUI();
			enderecoUI.setId(endereco.getId());
			enderecoUI.setAvenida(endereco.getAvenida());
			enderecoUI.setBloco(endereco.getBloco());
			enderecoUI.setCep(endereco.getCep());
			enderecoUI.setNumero(endereco.getNumero());
			enderecoUI.setRua(endereco.getRua());
			enderecoUI.setUser(endereco.getUser());
			enderecoUI.setCidade(endereco.getCidade());
			enderecoUI.setEstado(endereco.getEstado());
			enderecoUI.setLatitude(endereco.getLatitude());
			enderecoUI.setLongitude(endereco.getLongitude());
			enderecoUI.setNomeEstabelecimento(endereco.getNomeEstabelecimento());
		}
		
		return enderecoUI;
	}
	
	public List<EnderecoUI> toUIBean(Collection<Endereco> enderecos){
		List<EnderecoUI> enderecosUI = new ArrayList<EnderecoUI>();
		
		for(Endereco endereco : enderecos)
			enderecosUI.add(this.toUIBean(endereco));
		
		return enderecosUI;
	}
	
	public List<EnderecoUI> toUIBean(Map<String, Endereco> enderecos){
		List<EnderecoUI> enderecosUI = new ArrayList<EnderecoUI>();
		
		for(String key : enderecos.keySet()){
			enderecosUI.add(this.toUIBean(enderecos.get(key)));
		}
		
		return enderecosUI;
	}
	
	public Endereco toPersistenceBean(EnderecoUI enderecoUI){
		Endereco endereco = null;
		
		if(enderecoUI != null){
			endereco = new Endereco();
			endereco.setId(enderecoUI.getId());
			endereco.setAvenida(enderecoUI.getAvenida());
			endereco.setBloco(enderecoUI.getBloco());
			endereco.setCep(enderecoUI.getCep());
			endereco.setRua(enderecoUI.getRua());
			endereco.setNumero(enderecoUI.getNumero());
			endereco.setUser(enderecoUI.getUser());
			endereco.setLatitude(enderecoUI.getLatitude());
			endereco.setLongitude(enderecoUI.getLongitude());
			endereco.setCidade(enderecoUI.getCidade());
			endereco.setEstado(enderecoUI.getEstado());
			endereco.setNomeEstabelecimento(enderecoUI.getNomeEstabelecimento());
		}
		
		return endereco;
	}
	
	public List<Endereco> toPersistenceBean(List<EnderecoUI> enderecosUI){
		List<Endereco> enderecos = new ArrayList<Endereco>();
		
		for(EnderecoUI enderecoUI : enderecosUI)
			enderecos.add(this.toPersistenceBean(enderecoUI));
		
		return enderecos;
	}
	
	public Map<String, Endereco> toPersistenceBeanMap(List<EnderecoUI> enderecosUI){
		Map<String, Endereco> enderecos = new HashMap<String, Endereco>();
		
		for(EnderecoUI enderecoUI : enderecosUI){
			enderecos.put(enderecoUI.getId(), this.toPersistenceBean(enderecoUI));
		}
		
		return enderecos;
	}

}
