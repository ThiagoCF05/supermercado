package com.difusores.walcupom.data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.difusores.walcupom.data.model.Device;

public interface DeviceRepository extends MongoRepository<Device, String>, 
PagingAndSortingRepository<Device, String>{
	Device findByPhoneNumber(String phoneNumber);

}
