package com.difusores.walcupom.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.difusores.walcupom.data.model.PessoaFisica;
import com.difusores.walcupom.web.data.PessoaFisicaUI;

public class PessoaFisicaMapper {
	TelefoneMapper phoneMapper = new TelefoneMapper();
	EnderecoMapper enderecoMapper = new EnderecoMapper();
	UserMapper userMapper = new UserMapper();
	
	public PessoaFisicaUI toUIBean(PessoaFisica pf){
		PessoaFisicaUI pfUI = null;
		
		if(pf != null){
			pfUI = new PessoaFisicaUI();
			pfUI.setBornDate(pf.getBornDate());
			pfUI.setCPF(pf.getCPF());
			pfUI.setEmail(pf.getUser().getEmail());
			pfUI.setFirstName(pf.getFirstName());
			pfUI.setGenre(pf.getGenre());
			pfUI.setId(pf.getUser().getId());
			pfUI.setIdPF(pf.getId());
			pfUI.setLastName(pf.getLastName());
			pfUI.setPassword(pf.getUser().getPassword());
			if(pf.getPhones() != null)
				pfUI.setPhones(phoneMapper.toUIBean(pf.getPhones()));
			if(pf.getEnderecos() != null)
				pfUI.setEnderecos(enderecoMapper.toUIBean(pf.getEnderecos()));
			pfUI.setUserName(pf.getUser().getUserName());
			pfUI.setCidade(pf.getCidade());
			pfUI.setEstado(pf.getEstado());
			
		}
		
		return pfUI;
	}
	
	public List<PessoaFisicaUI> toUIBean(List<PessoaFisica> pfs){
		List<PessoaFisicaUI> pfsUI = new ArrayList<PessoaFisicaUI>();
		
		for(PessoaFisica pf : pfs)
			pfsUI.add(this.toUIBean(pf));
		
		return pfsUI;
	}
	
	public Page<PessoaFisicaUI> toUIBean(Page<PessoaFisica> pfs, Pageable pageable){
		Page<PessoaFisicaUI> pfsUI = new PageImpl<PessoaFisicaUI>(
				this.toUIBean(pfs.getContent()), pageable,
				pfs.getTotalElements());
		
		return pfsUI;
	}
	
	public PessoaFisica toPersistenceBean(PessoaFisicaUI pfUI){
		PessoaFisica pf = null;
		
		if(pfUI != null){
			pf = new PessoaFisica();
			pf.setBornDate(pfUI.getBornDate());
			pf.setCPF(pfUI.getCPF());
			pf.setFirstName(pfUI.getFirstName());
			pf.setGenre(pfUI.getGenre());
			pf.setId(pfUI.getIdPF());
			pf.setLastName(pfUI.getLastName());
			if(pfUI.getPhones() != null)
				pf.setPhones(phoneMapper.toPersistenceBeanMap(pfUI.getPhones()));
			if(pfUI.getEnderecos() != null)
				pf.setEnderecos(enderecoMapper.toPersistenceBeanMap(pfUI.getEnderecos()));
			pf.setUser(userMapper.toPersistenceBean(pfUI));
			pf.setCidade(pfUI.getCidade());
			pf.setEstado(pfUI.getEstado());
		}
		
		return pf;
	}

}
