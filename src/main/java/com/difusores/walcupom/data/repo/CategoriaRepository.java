package com.difusores.walcupom.data.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.walcupom.data.model.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String>, 
PagingAndSortingRepository<Categoria, String>{
	List<Categoria> findByRemovido(boolean removido);
	
	Page<Categoria> findByRemovido(boolean removido, Pageable pageable);

}
