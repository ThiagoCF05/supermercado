package com.difusores.supermercado.web.data;

import java.util.Date;

public class CotacaoUI {
	private String id;
	private SupermercadoUI supermercado;
	private ProdutoUI produto;
	private UserUI user;
	
	private double preco;
	private Date data;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SupermercadoUI getSupermercado() {
		return supermercado;
	}
	public void setSupermercado(SupermercadoUI supermercado) {
		this.supermercado = supermercado;
	}
	public ProdutoUI getProduto() {
		return produto;
	}
	public void setProduto(ProdutoUI produto) {
		this.produto = produto;
	}
	public UserUI getUser() {
		return user;
	}
	public void setUser(UserUI user) {
		this.user = user;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
