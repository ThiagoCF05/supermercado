package com.difusores.supermercado.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.difusores.supermercado.data.model.Cotacao;
import com.difusores.supermercado.web.data.CotacaoUI;

public class CotacaoMapper {
	SupermercadoMapper supermercadoMapper = new SupermercadoMapper();
	ProdutoMapper produtoMapper = new ProdutoMapper();
	UserMapper userMapper = new UserMapper();
	
	public CotacaoUI toUIBean(Cotacao cotacao){
		CotacaoUI cotacaoUI = null;
		
		if(cotacao != null){
			cotacaoUI = new CotacaoUI();
			cotacaoUI.setData(cotacao.getData());
			cotacaoUI.setId(cotacao.getId());
			cotacaoUI.setPreco(cotacao.getPreco());
			cotacaoUI.setProduto(produtoMapper.toUIBean(cotacao.getProduto()));
			cotacaoUI.setSupermercado(supermercadoMapper.toUIBean(cotacao.getSupermercado()));
			cotacaoUI.setUser(userMapper.toUIBean(cotacao.getUser()));
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
			cotacao.setProduto(produtoMapper.toPersistenceBean(cotacaoUI.getProduto()));
			cotacao.setSupermercado(supermercadoMapper.toPersistenceBean(cotacaoUI.getSupermercado()));
			cotacao.setUser(userMapper.toPersistenceBean(cotacaoUI.getUser()));
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
