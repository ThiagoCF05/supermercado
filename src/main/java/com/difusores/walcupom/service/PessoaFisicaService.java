package com.difusores.walcupom.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.difusores.walcupom.data.model.PessoaFisica;
import com.difusores.walcupom.data.model.User;
import com.difusores.walcupom.data.repo.PessoaFisicaRepository;
import com.difusores.walcupom.util.mapper.PessoaFisicaMapper;
import com.difusores.walcupom.util.mapper.UserMapper;
import com.difusores.walcupom.web.data.PessoaFisicaUI;
import com.difusores.walcupom.web.data.UserUI;

@Service
public class PessoaFisicaService {
	@Autowired
	private PessoaFisicaRepository pfRepository;
	private PessoaFisicaMapper mapper = new PessoaFisicaMapper();
	private UserMapper userMapper = new UserMapper();
	
	private Logger logger = Logger.getLogger(PessoaFisicaService.class);
	
	public List<PessoaFisicaUI> findAll(){
		return mapper.toUIBean(pfRepository.findAll());
	}
	
	public Page<PessoaFisicaUI> findAll(Pageable pageable){
		return mapper.toUIBean(pfRepository.findAll(pageable), pageable);
	}
	
	public PessoaFisicaUI findByUser(UserUI userUI){
		User user = userMapper.toPersistenceBean(userUI);
		
		return mapper.toUIBean(pfRepository.findByUser(user));
	}
	
	public PessoaFisicaUI create(PessoaFisicaUI pfUI){
		PessoaFisica pf = mapper.toPersistenceBean(pfUI);
		if(pf != null){
			pfRepository.save(pf);
			return pfUI;
		}
		
		return null;
	}
	
	public boolean delete(PessoaFisicaUI pfUI){
		PessoaFisica pf = pfRepository.findByCPF(pfUI.getCPF());
		
		if(pf != null){
			pfRepository.delete(pf);
			return true;
		}
		else
			return false;
	}

}
