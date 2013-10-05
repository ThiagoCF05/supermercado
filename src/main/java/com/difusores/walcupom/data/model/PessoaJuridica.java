package com.difusores.walcupom.data.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PessoaJuridica{
	@Id
	private String id;
	private String razaoSocial;
	private String nomeFantasia;
	private String cNPJ;
	private String inscricaoEstadual;
	private boolean isento;
	
	private Map<String, Endereco> enderecos;
	private Map<String, Telefone> telefones;
	
	private String site;
	
	private String cidade;
	private String estado;
	
	@DBRef
	private User user;
	
	@Override
	public String toString() {
		return "PessoaJuridica [id=" + id + ", razaoSocial=" + razaoSocial + ", nomeFantasia="
				+ nomeFantasia + ", CNPJ=" + cNPJ + ", inscricaoEstadual=" + inscricaoEstadual + 
				", isento=" + isento + ", enderecos=" + enderecos + 
				", telefones=" + telefones + ", site=" + site + ", user=" + user + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCNPJ() {
		return cNPJ;
	}

	public void setCNPJ(String cNPJ) {
		this.cNPJ = cNPJ;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public boolean isIsento() {
		return isento;
	}

	public void setIsento(boolean isento) {
		this.isento = isento;
	}

	public Map<String, Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Map<String, Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Map<String, Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Map<String, Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
