package com.difusores.supermercado.util.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.difusores.supermercado.data.model.Supermercado;
import com.difusores.supermercado.web.data.SupermercadoUI;

public class SupermercadoMapper {
	public SupermercadoUI toUIBean(Supermercado supermercado){
		SupermercadoUI supermercadoUI = null;
		
		if(supermercado != null){
			supermercadoUI = new SupermercadoUI();
			supermercadoUI.setId(supermercado.getId());
			supermercadoUI.setBairro(supermercado.getBairro());
			supermercadoUI.setBloco(supermercado.getBloco());
			supermercadoUI.setCep(supermercado.getCep());
			supermercadoUI.setNumero(supermercado.getNumero());
			supermercadoUI.setRua(supermercado.getRua());
			supermercadoUI.setUser(supermercado.getUser());
			supermercadoUI.setCidade(supermercado.getCidade());
			supermercadoUI.setEstado(supermercado.getEstado());
			supermercadoUI.setLatitude(supermercado.getLatitude());
			supermercadoUI.setLongitude(supermercado.getLongitude());
			supermercadoUI.setLocation(supermercado.getLocation());
			supermercadoUI.setNomeEstabelecimento(supermercado.getNomeEstabelecimento());
		}
		
		return supermercadoUI;
	}
	
	public List<SupermercadoUI> toUIBean(Collection<Supermercado> enderecos){
		List<SupermercadoUI> enderecosUI = new ArrayList<SupermercadoUI>();
		
		for(Supermercado endereco : enderecos)
			enderecosUI.add(this.toUIBean(endereco));
		
		return enderecosUI;
	}
	
	public Page<SupermercadoUI> toUIBean(Page<Supermercado> supermercados, Pageable pageable) {
		Page<SupermercadoUI> supermercadosUI = new PageImpl<SupermercadoUI>(
				this.toUIBean(supermercados.getContent()), pageable,
				supermercados.getTotalElements());
		
		return supermercadosUI;
		
	}
	
	public List<SupermercadoUI> toUIBean(Map<String, Supermercado> enderecos){
		List<SupermercadoUI> enderecosUI = new ArrayList<SupermercadoUI>();
		
		for(String key : enderecos.keySet()){
			enderecosUI.add(this.toUIBean(enderecos.get(key)));
		}
		
		return enderecosUI;
	}
	
	public Supermercado toPersistenceBean(SupermercadoUI supermercadoUI){
		Supermercado supermercado = null;
		
		if(supermercadoUI != null){
			supermercado = new Supermercado();
			supermercado.setId(supermercadoUI.getId());
			supermercado.setBairro(supermercadoUI.getBairro());
			supermercado.setBloco(supermercadoUI.getBloco());
			supermercado.setCep(supermercadoUI.getCep());
			supermercado.setRua(supermercadoUI.getRua());
			supermercado.setNumero(supermercadoUI.getNumero());
			supermercado.setUser(supermercadoUI.getUser());
			supermercado.setLatitude(supermercadoUI.getLatitude());
			supermercado.setLongitude(supermercadoUI.getLongitude());
			supermercado.setLocation(supermercadoUI.getLocation());
			supermercado.setCidade(supermercadoUI.getCidade());
			supermercado.setEstado(supermercadoUI.getEstado());
			supermercado.setNomeEstabelecimento(supermercadoUI.getNomeEstabelecimento());
		}
		
		return supermercado;
	}
	
	public List<Supermercado> toPersistenceBean(List<SupermercadoUI> enderecosUI){
		List<Supermercado> enderecos = new ArrayList<Supermercado>();
		
		for(SupermercadoUI enderecoUI : enderecosUI)
			enderecos.add(this.toPersistenceBean(enderecoUI));
		
		return enderecos;
	}
	
	public Map<String, Supermercado> toPersistenceBeanMap(List<SupermercadoUI> enderecosUI){
		Map<String, Supermercado> enderecos = new HashMap<String, Supermercado>();
		
		for(SupermercadoUI enderecoUI : enderecosUI){
			enderecos.put(enderecoUI.getId(), this.toPersistenceBean(enderecoUI));
		}
		
		return enderecos;
	}

}
