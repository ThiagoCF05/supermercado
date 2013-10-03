package com.difusores.walcupom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.difusores.walcupom.data.model.Categoria;
import com.difusores.walcupom.data.repo.CategoriaRepository;
import com.difusores.walcupom.util.mapper.CategoriaMapper;
import com.difusores.walcupom.web.data.CategoriaUI;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository repo;
	CategoriaMapper mapper = new CategoriaMapper();
	
	public CategoriaUI create(CategoriaUI categoriaUI){
		Categoria categoria = mapper.toPersistenceBean(categoriaUI);
		if(categoria != null){
			repo.save(categoria);
			return categoriaUI;
		} else
			return null;
	}
	
	public List<CategoriaUI> findAll(){
		return mapper.toUIBean(repo.findAll());
	}
	
	public List<CategoriaUI> findByStatus(boolean removido){
		return mapper.toUIBean(repo.findByRemovido(removido));
	}
	
	public Page<CategoriaUI> findAll(Pageable pageable){
		return mapper.toUIBean(repo.findAll(pageable), pageable);
	}
	
	public Page<CategoriaUI> findByStatus(boolean removido, Pageable pageable){
		return mapper.toUIBean(repo.findByRemovido(removido, pageable), pageable);
	}
	
	public CategoriaUI find(String id){
		return mapper.toUIBean(repo.findOne(id));
	}
	
	public boolean delete(String id){
		Categoria categoria = repo.findOne(id);
		
		if(categoria != null){
			categoria.setRemovido(true);
			repo.save(categoria);
			return true;
		} else
			return false;
	}

}
