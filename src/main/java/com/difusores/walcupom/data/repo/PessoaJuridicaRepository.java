package com.difusores.walcupom.data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.walcupom.data.model.PessoaJuridica;
import com.difusores.walcupom.data.model.User;

public interface PessoaJuridicaRepository extends MongoRepository<PessoaJuridica, String>, 
PagingAndSortingRepository<PessoaJuridica, String>{
	PessoaJuridica findByCNPJ(String CNPJ);
	
	PessoaJuridica findByUser(User user);

}
