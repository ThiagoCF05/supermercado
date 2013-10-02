package com.difusores.walcupom.web.data;

import java.util.Date;

public class CategoriaUI {
	private String id;
	private String nome;
	private String descricao;
	
	private String user;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private boolean removido;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public boolean isRemovido() {
		return removido;
	}
	public void setRemovido(boolean removido) {
		this.removido = removido;
	}
	
	@Override
	public String toString() {
		return "CategoriaUI [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + ", user=" + user +
				", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao +
				", removido=" + removido +"]";
	}

}
