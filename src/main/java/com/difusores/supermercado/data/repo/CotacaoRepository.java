package com.difusores.supermercado.data.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.supermercado.data.model.Cotacao;


public interface CotacaoRepository extends MongoRepository<Cotacao, String>, 
PagingAndSortingRepository<Cotacao, String>{
	
	List<Cotacao> findBySupermercado(String supermercado);
	
	Page<Cotacao> findBySupermercado(String supermercado, Pageable pageable);
	
	List<Cotacao> findByProduto(String produto);
	
	Page<Cotacao> findByProduto(String produto, Pageable pageable);

}
