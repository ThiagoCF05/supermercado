package com.difusores.walcupom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.difusores.walcupom.data.model.Cupom;
import com.difusores.walcupom.data.repo.CupomRepository;
import com.difusores.walcupom.util.mapper.CupomMapper;
import com.difusores.walcupom.web.data.CupomUI;

@Service
public class CupomService {
	@Autowired
	CupomRepository repo;
	
	CupomMapper mapper = new CupomMapper();
	
	public CupomUI create(CupomUI cupomUI){
		Cupom cupom = mapper.toPersistenceBean(cupomUI);
		if(cupom != null){
			repo.save(cupom);
			return cupomUI;
		} else
			return null;
	}
	
	public CupomUI find(String id){
		return mapper.toUIBean(repo.findOne(id));
	}
	
	public List<CupomUI> findByCampanha(String campanha){
		return mapper.toUIBean(repo.findByCampanha(campanha));
	}
	
	public List<CupomUI> findByDevice(String device){
		return mapper.toUIBean(repo.findByDevice(device));
	}
	
	public List<CupomUI> findByCampanhaAndCodigo(String campanha, String codigo){
		return mapper.toUIBean(repo.findByCampanhaAndCodigo(campanha, codigo));
	}
	
	public boolean changeDevice(String oldDeviceId, String newDeviceId){
		List<Cupom> cupons = mapper.toPersistenceBean(this.findByDevice(oldDeviceId));
		
		try{
			for(Cupom cupom : cupons){
				cupom.setDevice(newDeviceId);
				repo.save(cupom);
			}
		} catch(Exception ex){
			return false;
		}
		
		return true;
	}
	
	public boolean validarCupom(String cupomId, String campanhaId){
		Cupom cupom = repo.findOne(cupomId);
		
		if(cupom != null){
			if(cupom.getCampanha() == campanhaId){
				if(!cupom.isUsado()){
					cupom.setUsado(true);
					if(repo.save(cupom) != null)
						return true;
				}
			}	
		}
		return false;
	}
	
	public boolean aceitarCupom(String cupomId, String campanhaId){
		Cupom cupom = repo.findOne(cupomId);
		
		if(cupom != null){
			if(cupom.getCampanha() == campanhaId){
				if(!cupom.isAceito()){
					cupom.setAceito(true);
					if(repo.save(cupom) != null)
						return true;
				}
			}	
		}
		return false;
	}

}
