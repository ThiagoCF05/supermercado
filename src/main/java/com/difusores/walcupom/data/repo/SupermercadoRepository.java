package com.difusores.walcupom.data.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.walcupom.data.model.Endereco;


public interface EnderecoRepository extends MongoRepository<Endereco, String>, 
PagingAndSortingRepository<Endereco, String>{
	

}
