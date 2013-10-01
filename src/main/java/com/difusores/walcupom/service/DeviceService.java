package com.difusores.walcupom.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.difusores.walcupom.data.model.Device;
import com.difusores.walcupom.data.repo.DeviceRepository;

@Service
public class DeviceService {

	@Autowired
	DeviceRepository repo;
	
	public Device find(String regId){
		return repo.findOne(regId);
	}
	
	public Device save(Device device){
		Device old = this.find(device.getId());
		if(old == null){
			device.setDataAtualizacao(new Date(System.currentTimeMillis()));
			device.setDataCriacao(new Date(System.currentTimeMillis()));
			repo.save(device);
		}
			
		else{
			old.setDataAtualizacao(new Date(System.currentTimeMillis()));
			old.setLatitude(device.getLatitude());
			old.setLongitude(device.getLongitude());
			old.setPhoneNumber(device.getPhoneNumber());
			repo.save(old);
		}
		return device;
	}
	
	public boolean delete(String regId){
		Device device = repo.findOne(regId);
		if(device != null){
			repo.delete(device);
			return true;
		}
		else
			return false;
			
	}

}
