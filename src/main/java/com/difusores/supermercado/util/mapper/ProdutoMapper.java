package com.difusores.supermercado.util.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.difusores.supermercado.data.model.Produto;
import com.difusores.supermercado.web.data.ProdutoUI;

public class ProdutoMapper {
	public ProdutoUI toUIBean(Produto produto){
		ProdutoUI produtoUI = null;
		
		if(produto != null){
			produtoUI = new ProdutoUI();
			produtoUI.setCategoria(produto.getCategoria());
			produtoUI.setCodigoBarra(produto.getCodigoBarra());
			produtoUI.setId(produto.getId());
			produtoUI.setMarca(produto.getMarca());
			produtoUI.setNome(produto.getNome());
			produtoUI.setNomeTecnico(produto.getNomeTecnico());
			produtoUI.setPeso(produto.getPeso());
			produtoUI.setTipoCodigoBarra(produto.getTipoCodigoBarra());
			produtoUI.setUser(produto.getUser());
			produtoUI.setFormato(produto.getFormato());
		}
		
		return produtoUI;
	}
	
	public List<ProdutoUI> toUIBean(Collection<Produto> produtos){
		List<ProdutoUI> produtosUI = new ArrayList<ProdutoUI>();
		
		for(Produto produto : produtos)
			produtosUI.add(this.toUIBean(produto));
		
		return produtosUI;
	}
	
	public Page<ProdutoUI> toUIBean(Page<Produto> produtos, Pageable pageable) {
		Page<ProdutoUI> produtosUI = new PageImpl<ProdutoUI>(
				this.toUIBean(produtos.getContent()), pageable,
				produtos.getTotalElements());
		
		return produtosUI;
	}
	
	public List<ProdutoUI> toUIBean(Map<String, Produto> produtos){
		List<ProdutoUI> produtosUI = new ArrayList<ProdutoUI>();
		
		for(String key : produtos.keySet()){
			produtosUI.add(this.toUIBean(produtos.get(key)));
		}
		
		return produtosUI;
	}
	
	public Produto toPersistenceBean(ProdutoUI produtoUI){
		Produto produto = null;
		
		if(produtoUI != null){
			produto = new Produto();
			produto.setCategoria(produtoUI.getCategoria());
			produto.setCodigoBarra(produtoUI.getCodigoBarra());
			produto.setId(produtoUI.getId());
			produto.setMarca(produtoUI.getMarca());
			produto.setNome(produtoUI.getNome());
			produto.setNomeTecnico(produtoUI.getNomeTecnico());
			produto.setPeso(produtoUI.getPeso());
			produto.setTipoCodigoBarra(produtoUI.getTipoCodigoBarra());
			produto.setUser(produtoUI.getUser());
			produto.setFormato(produtoUI.getFormato());
		}
		
		return produto;
	}
	
	public List<Produto> toPersistenceBean(List<ProdutoUI> produtosUI){
		List<Produto> produtos = new ArrayList<Produto>();
		
		for(ProdutoUI produtoUI : produtosUI)
			produtos.add(this.toPersistenceBean(produtoUI));
		
		return produtos;
	}
	
	public Map<String, Produto> toPersistenceBeanMap(List<ProdutoUI> produtosUI){
		Map<String, Produto> produtos = new HashMap<String, Produto>();
		
		for(ProdutoUI produtoUI : produtosUI){
			produtos.put(produtoUI.getId(), this.toPersistenceBean(produtoUI));
		}
		
		return produtos;
	}

}
