package com.difusores.walcupom.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.difusores.walcupom.data.model.Campanha;
import com.difusores.walcupom.web.data.CampanhaUI;

public class CampanhaMapper {
	EnderecoMapper enderecoMapper = new EnderecoMapper();
	UserMapper userMapper = new UserMapper();
	CategoriaMapper categoriaMapper = new CategoriaMapper();
	
	public CampanhaUI toUIBean(Campanha campanha){
		CampanhaUI campanhaUI = null;
		
		if(campanha != null){
			campanhaUI = new CampanhaUI();
			campanhaUI.setDataCriacao(campanha.getDataCriacao());
			campanhaUI.setEnderecos(enderecoMapper.toUIBean(campanha.getEnderecos().values()));
			campanhaUI.setDistancia(campanha.getDistancia());
			campanhaUI.setId(campanha.getId());
			campanhaUI.setImagens(campanha.getImagens());
			campanhaUI.setLimiteAceite(campanha.getLimiteAceite());
			campanhaUI.setLimiteUso(campanha.getLimiteUso());
			campanhaUI.setQuantidadeCupons(campanha.getQuantidadeCupons());
			campanhaUI.setTipo(campanha.getTipo());
			campanhaUI.setUser(userMapper.toUIBean(campanha.getUser()));
			campanhaUI.setDataInicio(campanha.getDataInicio());
			campanhaUI.setDataEncerramento(campanha.getDataEncerramento());
			campanhaUI.setDescricao(campanha.getDescricao());
			campanhaUI.setCategoria(categoriaMapper.toUIBean(campanha.getCategoria()));
		}
		
		return campanhaUI;
	}
	
	public List<CampanhaUI> toUIBean(List<Campanha> campanhas){
		List<CampanhaUI> campanhasUI = new ArrayList<CampanhaUI>();
		
		for(Campanha campanha : campanhas)
			campanhasUI.add(this.toUIBean(campanha));
		
		return campanhasUI;
	}
	
	public Page<CampanhaUI> toUIBean(Page<Campanha> campanhas, Pageable pageable){
		Page<CampanhaUI> campanhasUI = new PageImpl<CampanhaUI>(
				this.toUIBean(campanhas.getContent()), pageable, campanhas.getTotalElements());
		
		return campanhasUI;
	}
	
	public Campanha toPersistenceBean(CampanhaUI campanhaUI){
		Campanha campanha = null;
		
		if(campanhaUI != null){
			campanha = new Campanha();
			campanha.setDataCriacao(campanhaUI.getDataCriacao());
			campanha.setDistancia(campanhaUI.getDistancia());
			campanha.setEnderecos(enderecoMapper.toPersistenceBeanMap(campanhaUI.getEnderecos()));
			campanha.setId(campanhaUI.getId());
			campanha.setImagens(campanhaUI.getImagens());
			campanha.setLimiteAceite(campanhaUI.getLimiteAceite());
			campanha.setLimiteUso(campanhaUI.getLimiteUso());
			campanha.setQuantidadeCupons(campanhaUI.getQuantidadeCupons());
			campanha.setTipo(campanhaUI.getTipo());
			campanha.setUser(userMapper.toPersistenceBean(campanhaUI.getUser()));
			campanha.setDataInicio(campanhaUI.getDataInicio());
			campanha.setDataEncerramento(campanhaUI.getDataEncerramento());
			campanha.setDescricao(campanhaUI.getDescricao());
			campanha.setCategoria(categoriaMapper.toPersistenceBean(campanhaUI.getCategoria()));
		}
		
		return campanha;
	}
	
	public List<Campanha> toPersistenceBean(List<CampanhaUI> campanhasUI){
		List<Campanha> campanhas = new ArrayList<Campanha>();
		
		for(CampanhaUI campanhaUI : campanhasUI)
			campanhas.add(this.toPersistenceBean(campanhaUI));
		
		return campanhas;
	}

}
