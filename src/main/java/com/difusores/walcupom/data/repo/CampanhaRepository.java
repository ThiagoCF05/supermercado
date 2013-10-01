package com.difusores.walcupom.data.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.walcupom.data.model.Campanha;
import com.difusores.walcupom.data.model.User;


public interface CampanhaRepository extends MongoRepository<Campanha, String>, 
PagingAndSortingRepository<Campanha, String>{
	List<Campanha> findByUser(User user);
	
	Page<Campanha> findByUser(User user, Pageable pageable);

}
