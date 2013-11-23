package com.difusores.supermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.difusores.supermercado.data.model.Cotacao;
import com.difusores.supermercado.data.repo.CotacaoRepository;
import com.difusores.supermercado.util.mapper.CotacaoMapper;
import com.difusores.supermercado.web.data.CotacaoUI;

@Service
public class CotacaoService {
	@Autowired
	CotacaoRepository repo;
	@Autowired
	MongoTemplate template;
	CotacaoMapper mapper = new CotacaoMapper();
	
	public List<CotacaoUI> findAll(){
		return mapper.toUIBean(repo.findAll());
	} 
	
	public Page<CotacaoUI> findAll(Pageable pageable){
		return mapper.toUIBean(repo.findAll(pageable), pageable);
	}
	
	public CotacaoUI find(String id){
		return mapper.toUIBean(repo.findOne(id));
	}
	
	
	public CotacaoUI create(CotacaoUI cotacaoUI){
		if(cotacaoUI != null)
			repo.save(mapper.toPersistenceBean(cotacaoUI));
		return cotacaoUI;	
	}
	
	public boolean delete(String cotacaoId){
		Cotacao cotacao = repo.findOne(cotacaoId);
		
		if(cotacao != null){
			repo.delete(cotacao);
			return true;
		}
		else
			return false;
	}

}