package com.difusores.supermercado.data.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cotacao {
	@Id
	private String id;
	@DBRef
	@Indexed
	private Supermercado supermercado;
	@DBRef
	@Indexed
	private Produto produto;
	@DBRef
	private User user;
	
	private double preco;
	private Date data;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Supermercado getSupermercado() {
		return supermercado;
	}
	public void setSupermercado(Supermercado supermercado) {
		this.supermercado = supermercado;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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