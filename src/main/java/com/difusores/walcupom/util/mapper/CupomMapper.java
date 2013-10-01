package com.difusores.walcupom.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.difusores.walcupom.data.model.Campanha;
import com.difusores.walcupom.data.model.Cupom;
import com.difusores.walcupom.service.CampanhaService;
import com.difusores.walcupom.web.data.CampanhaUI;
import com.difusores.walcupom.web.data.CupomUI;

public class CupomMapper {
	@Autowired
	CampanhaService campanhaService;
	
	public CupomUI toUIBean(Cupom cupom){
		CupomUI cupomUI = null;
		
		if(cupom!= null){
			cupomUI = new CupomUI();
			cupomUI.setAceito(cupom.isAceito());
			cupomUI.setCampanha(campanhaService.find(cupom.getCampanha()));
			cupomUI.setCodigo(cupom.getCodigo());
			cupomUI.setDataCriacao(cupom.getDataCriacao());
			cupomUI.setDevice(cupom.getDevice());
			cupomUI.setId(cupom.getId());
			cupomUI.setLimiteAceite(cupom.getLimiteAceite());
			cupomUI.setLimiteUso(cupom.getLimiteUso());
			cupomUI.setUsado(cupom.isUsado());
		}
		
		return cupomUI;
	}
	
	public List<CupomUI> toUIBean(List<Cupom> cupons){
		List<CupomUI> cuponsUI = new ArrayList<CupomUI>();
		
		for(Cupom cupom : cupons)
			cuponsUI.add(this.toUIBean(cupom));
		
		return cuponsUI;
	}
	
	public Cupom toPersistenceBean(CupomUI cupomUI){
		Cupom cupom = null;
		
		if(cupomUI != null){
			cupom = new Cupom();
			cupom.setAceito(cupomUI.isAceito());
			cupom.setCampanha(cupomUI.getCampanha().getId());
			cupom.setCodigo(cupomUI.getCodigo());
			cupom.setDataCriacao(cupomUI.getDataCriacao());
			cupom.setDevice(cupomUI.getDevice());
			cupom.setId(cupomUI.getId());
			cupom.setLimiteAceite(cupomUI.getLimiteAceite());
			cupom.setLimiteUso(cupomUI.getLimiteUso());
			cupom.setUsado(cupomUI.isUsado());
		}
		
		return cupom;
	}
	
	public List<Cupom> toPersistenceBean(List<CupomUI> cuponsUI){
		List<Cupom> cupons = new ArrayList<Cupom>();
		
		for(CupomUI cupomUI : cuponsUI)
			cupons.add(this.toPersistenceBean(cupomUI));
		
		return cupons;
	}

}
