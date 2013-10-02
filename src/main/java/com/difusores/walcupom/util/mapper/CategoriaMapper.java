package com.difusores.walcupom.util.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.difusores.walcupom.data.model.Categoria;
import com.difusores.walcupom.web.data.CategoriaUI;


public class CategoriaMapper {
	public CategoriaUI toUIBean(Categoria categoria){
		CategoriaUI categoriaUI = null;
		
		if(categoria != null){
			categoriaUI = new CategoriaUI();
			categoriaUI.setDataAtualizacao(categoria.getDataAtualizacao());
			categoriaUI.setDataCriacao(categoria.getDataCriacao());
			categoriaUI.setDescricao(categoria.getDescricao());
			categoriaUI.setId(categoria.getId());
			categoriaUI.setNome(categoria.getNome());
			categoriaUI.setRemovido(categoria.isRemovido());
			categoriaUI.setUser(categoria.getUser());
		}
		
		return categoriaUI;
	}
	
	public List<CategoriaUI> toUIBean(List<Categoria> categorias){
		List<CategoriaUI> categoriasUI = new ArrayList<CategoriaUI>();
		
		for(Categoria categoria : categorias)
			categoriasUI.add(this.toUIBean(categoria));
		
		return categoriasUI;
	}
	
	public Page<CategoriaUI> toUIBean(Page<Categoria> categorias, Pageable pageable){
		Page<CategoriaUI> categoriasUI = new PageImpl<CategoriaUI>(
				this.toUIBean(categorias.getContent()), pageable, categorias.getTotalElements());
		
		return categoriasUI;
	}
	
	public Categoria toPersistenceBean(CategoriaUI categoriaUI){
		Categoria categoria = null;
		
		if(categoriaUI != null){
			categoria = new Categoria();
			categoria.setDataAtualizacao(categoriaUI.getDataAtualizacao());
			categoria.setDataCriacao(categoriaUI.getDataCriacao());
			categoria.setDescricao(categoriaUI.getDescricao());
			categoria.setId(categoriaUI.getId());
			categoria.setNome(categoriaUI.getNome());
			categoria.setRemovido(categoriaUI.isRemovido());
			categoria.setUser(categoriaUI.getUser());
		}
		
		return categoria;
	}
	
	public List<Categoria> toPersistenceBean(List<CategoriaUI> categoriasUI){
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		for(CategoriaUI categoriaUI : categoriasUI)
			categorias.add(this.toPersistenceBean(categoriaUI));
		
		return categorias;
	}

}
