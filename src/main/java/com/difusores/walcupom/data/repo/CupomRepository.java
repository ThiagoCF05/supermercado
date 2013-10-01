package com.difusores.walcupom.data.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.walcupom.data.model.Cupom;

public interface CupomRepository extends MongoRepository<Cupom, String>, 
PagingAndSortingRepository<Cupom, String>{
	List<Cupom> findByDevice(String device);
	
	List<Cupom> findByCampanha(String campanha);
	
	List<Cupom> findByCampanhaAndCodigo(String campanha, String codigo);

}
