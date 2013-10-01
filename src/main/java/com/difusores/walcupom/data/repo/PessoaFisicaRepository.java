package com.difusores.walcupom.data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.walcupom.data.model.PessoaFisica;
import com.difusores.walcupom.data.model.User;


public interface PessoaFisicaRepository extends MongoRepository<PessoaFisica, String>, 
PagingAndSortingRepository<PessoaFisica, String> {
	PessoaFisica findByCPF(String CPF);
	
	PessoaFisica findByUser(User user);
}
