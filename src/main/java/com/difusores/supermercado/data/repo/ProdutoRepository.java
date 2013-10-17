package com.difusores.supermercado.data.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.supermercado.data.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String>, 
PagingAndSortingRepository<Produto, String>{
	List<Produto> findByCategoria(String categoria);
	
	Page<Produto> findByCategoria(String categoria, Pageable pageable);
	
	Produto findByCodigoBarra(String codigoBarra);
}