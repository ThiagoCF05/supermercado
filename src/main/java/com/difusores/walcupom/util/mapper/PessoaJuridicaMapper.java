package com.difusores.walcupom.util.mapper;

import java.util.ArrayList;
import java.util.List;

import com.difusores.walcupom.data.model.PessoaJuridica;
import com.difusores.walcupom.web.data.PessoaJuridicaUI;


public class PessoaJuridicaMapper {
	TelefoneMapper phoneMapper = new TelefoneMapper();
	EnderecoMapper enderecoMapper = new EnderecoMapper();
	UserMapper userMapper = new UserMapper();
	
	public PessoaJuridicaUI toUIBean(PessoaJuridica pj){
		PessoaJuridicaUI pjUI = null;
		
		if(pj != null){
			pjUI = new PessoaJuridicaUI();
			pjUI.setCidade(pj.getCidade());
			pjUI.setCNPJ(pj.getCNPJ());
			if(pj.getTelefones() != null)
				pjUI.setTelefones(phoneMapper.toUIBean(pj.getTelefones()));
			if(pj.getEnderecos() != null)
				pjUI.setEnderecos(enderecoMapper.toUIBean(pj.getEnderecos()));
			pjUI.setEstado(pj.getCidade());
			pjUI.setId(pj.getUser().getId());
			pjUI.setIdPJ(pj.getId());
			pjUI.setPassword(pj.getUser().getPassword());
			pjUI.setInscricaoEstadual(pj.getInscricaoEstadual());
			pjUI.setIsento(pj.isIsento());
			pjUI.setNomeFantasia(pj.getNomeFantasia());
			pjUI.setRazaoSocial(pj.getRazaoSocial());
			pjUI.setSite(pj.getSite());
			pjUI.setUserName(pj.getUser().getUserName());
			pjUI.setEmail(pj.getUser().getEmail());
		}
		
		return pjUI;
	}
	
	public List<PessoaJuridicaUI> toUIBean(List<PessoaJuridica> pjs){
		List<PessoaJuridicaUI> pjsUI = new ArrayList<PessoaJuridicaUI>();
		
		for(PessoaJuridica pj : pjs)
			pjsUI.add(this.toUIBean(pj));
		
		return pjsUI;
	}
	
	public PessoaJuridica toPersistenceBean(PessoaJuridicaUI pjUI){
		PessoaJuridica pj = null;
		
		if(pjUI != null){
			pj = new PessoaJuridica();
			pj.setCidade(pjUI.getCidade());
			pj.setCNPJ(pjUI.getCNPJ());
			if(pjUI.getTelefones() != null)
				pj.setTelefones(phoneMapper.toPersistenceBeanMap(pjUI.getTelefones()));
			if(pjUI.getEnderecos() != null)
				pj.setEnderecos(enderecoMapper.toPersistenceBeanMap(pjUI.getEnderecos()));
			pj.setEstado(pjUI.getEstado());
			pj.setId(pjUI.getIdPJ());
			pj.setUser(userMapper.toPersistenceBean(pjUI));
			pj.setInscricaoEstadual(pjUI.getInscricaoEstadual());
			pj.setIsento(pjUI.isIsento());
			pj.setNomeFantasia(pjUI.getNomeFantasia());
			pj.setRazaoSocial(pjUI.getRazaoSocial());
			pj.setSite(pjUI.getSite());
		}
		
		return pj;
	}

}
