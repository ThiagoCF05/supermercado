package com.difusores.supermercado.data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.supermercado.data.model.User;

public interface UserRepository extends MongoRepository<User, String>, 
								PagingAndSortingRepository<User, String>{
	User findByUserName(String userName);

}
