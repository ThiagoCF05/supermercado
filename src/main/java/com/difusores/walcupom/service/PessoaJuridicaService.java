package com.difusores.walcupom.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.difusores.walcupom.data.model.PessoaJuridica;
import com.difusores.walcupom.data.model.User;
import com.difusores.walcupom.data.repo.PessoaJuridicaRepository;
import com.difusores.walcupom.util.mapper.PessoaJuridicaMapper;
import com.difusores.walcupom.util.mapper.UserMapper;
import com.difusores.walcupom.web.data.PessoaJuridicaUI;
import com.difusores.walcupom.web.data.UserUI;



@Service
public class PessoaJuridicaService {
	@Autowired
	private PessoaJuridicaRepository pjRepository;
	private PessoaJuridicaMapper mapper = new PessoaJuridicaMapper();
	private UserMapper userMapper = new UserMapper();
	
	private Logger logger = Logger.getLogger(PessoaFisicaService.class);
	
	public List<PessoaJuridicaUI> findAll(){
		return mapper.toUIBean(pjRepository.findAll());
	}
	
	
	public PessoaJuridicaUI findByUser(UserUI userUI){
		User user = userMapper.toPersistenceBean(userUI);
		
		return mapper.toUIBean(pjRepository.findByUser(user));
	}
	
	public PessoaJuridicaUI create(PessoaJuridicaUI pfUI){
		PessoaJuridica pf = mapper.toPersistenceBean(pfUI);
		if(pf != null){
			pjRepository.save(pf);
			return pfUI;
		}
		
		return null;
	}
	
	public boolean delete(PessoaJuridicaUI pjUI){
		PessoaJuridica pf = pjRepository.findByCNPJ(pjUI.getCNPJ());
		
		if(pf != null){
			pjRepository.delete(pf);
			return true;
		}
		else
			return false;
	}

}
