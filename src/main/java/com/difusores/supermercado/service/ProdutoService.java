package com.difusores.supermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.difusores.supermercado.data.model.Produto;
import com.difusores.supermercado.data.repo.ProdutoRepository;
import com.difusores.supermercado.util.mapper.ProdutoMapper;
import com.difusores.supermercado.web.data.ProdutoUI;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository repo;
	@Autowired
	MongoTemplate template;
	ProdutoMapper mapper = new ProdutoMapper();
	
	public List<ProdutoUI> findAll(){
		return mapper.toUIBean(repo.findAll());
	} 
	
	public Page<ProdutoUI> findAll(Pageable pageable){
		return mapper.toUIBean(repo.findAll(pageable), pageable);
	}
	
	public ProdutoUI find(String id){
		return mapper.toUIBean(repo.findOne(id));
	}
	
	public ProdutoUI findByCodigoBarra(String codigoBarra){
		return mapper.toUIBean(repo.findByCodigoBarra(codigoBarra));
	}
	
	public ProdutoUI create(ProdutoUI produtoUI){
		if(produtoUI != null)
			repo.save(mapper.toPersistenceBean(produtoUI));
		return produtoUI;	
	}
	
	public boolean delete(String produtoId){
		Produto produto = repo.findOne(produtoId);
		
		if(produto != null){
			repo.delete(produto);
			return true;
		}
		else
			return false;
	}

}
