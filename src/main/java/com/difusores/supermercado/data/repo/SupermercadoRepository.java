package com.difusores.supermercado.data.repo;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.supermercado.data.model.Supermercado;

public interface SupermercadoRepository extends MongoRepository<Supermercado, String>, 
PagingAndSortingRepository<Supermercado, String>{
	List<Supermercado> findByBairro(String bairro);
	
	Page<Supermercado> findByBairro(String bairro, Pageable pageable);
	
	List<Supermercado> findByCidade(String cidade);
	
	Page<Supermercado> findByCidade(String cidade, Pageable pageable);
	
	List<Supermercado> findByNomeEstabelecimento(String nomeEstabelecimento);
	
	Page<Supermercado> findByNomeEstabelecimento(String nomeEstabelecimento, Pageable pageable);
	
	List<Supermercado> findByCepAndNumero(String cep, int numero);
	
	Page<Supermercado> findByCepAndNumero(String cep, int numero, Pageable pageable);

}
