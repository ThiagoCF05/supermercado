package com.difusores.supermercado.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.difusores.supermercado.data.model.Cotacao;
import com.difusores.supermercado.service.ProdutoService;
import com.difusores.supermercado.service.SupermercadoService;
import com.difusores.supermercado.service.UserService;
import com.difusores.supermercado.web.data.CotacaoUI;

public class CotacaoMapper {
	@Autowired
	SupermercadoService supermercadoS;
	@Autowired
	ProdutoService produtoS;
	@Autowired
	UserService userS;
	
	public CotacaoUI toUIBean(Cotacao cotacao){
		CotacaoUI cotacaoUI = null;
		
		if(cotacao != null){
			cotacaoUI = new CotacaoUI();
			cotacaoUI.setData(cotacao.getData());
			cotacaoUI.setId(cotacao.getId());
			cotacaoUI.setPreco(cotacao.getPreco());
			cotacaoUI.setProduto(produtoS.find(cotacao.getProduto()));
			cotacaoUI.setSupermercado(supermercadoS.find(cotacao.getSupermercado()));
			cotacaoUI.setUser(userS.find(cotacao.getUser()));
		}
		
		return cotacaoUI;
	}
	
	public List<CotacaoUI> toUIBean(List<Cotacao> cotacoes){
		List<CotacaoUI> cotacaoUI = new ArrayList<CotacaoUI>();
		
		for(Cotacao cotacao : cotacoes)
			cotacaoUI.add(this.toUIBean(cotacao));
		
		return cotacaoUI;
	}
	
	public Page<CotacaoUI> toUIBean(Page<Cotacao> cotacoes, Pageable pageable) {
		Page<CotacaoUI> cotacaoUI = new PageImpl<CotacaoUI>(
				this.toUIBean(cotacoes.getContent()), pageable,
				cotacoes.getTotalElements());
		
		return cotacaoUI;
	}
	
	public Cotacao toPersistenceBean(CotacaoUI cotacaoUI){
		Cotacao cotacao = null;
		
		if(cotacaoUI != null){
			cotacao = new Cotacao();
			cotacao.setData(cotacaoUI.getData());
			cotacao.setId(cotacaoUI.getId());
			cotacao.setPreco(cotacaoUI.getPreco());
			cotacao.setProduto(cotacaoUI.getProduto().getId());
			cotacao.setSupermercado(cotacaoUI.getSupermercado().getId());
			cotacao.setUser(cotacaoUI.getUser().getId());
		}
		
		return cotacao;
	}
	
	public List<Cotacao> toPersistenceBean(List<CotacaoUI> cotacoesUI){
		List<Cotacao> cotacoes = new ArrayList<Cotacao>();
		
		for(CotacaoUI cotacaoUI : cotacoesUI)
			cotacoes.add(this.toPersistenceBean(cotacaoUI));
		
		return cotacoes;
	}

}
