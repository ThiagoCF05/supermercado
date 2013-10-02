package com.difusores.walcupom.data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.walcupom.data.model.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String>, 
PagingAndSortingRepository<Categoria, String>{

}
