package com.difusores.walcupom.data.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PessoaFisica {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String CPF;
	private Date bornDate;
	private String genre;
	
	private String cidade;
	private String estado;
	
	private Map<String, Telefone> phones;
	private Map<String, Endereco> enderecos;
	
	@DBRef
	private User user;

	@Override
	public String toString() {
		return "PessoaFisica [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", CPF=" + CPF + ", bornDate=" + bornDate + 
				", genre=" + genre + ", phones=" + phones + ", enderecos=" + enderecos +
				", user=" + user +
				", cidade=" + cidade +
				", estado=" + estado +"]";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Map<String, Telefone> getPhones() {
		return phones;
	}

	public void setPhones(Map<String, Telefone> phones) {
		this.phones = phones;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Map<String, Endereco> enderecos) {
		this.enderecos = enderecos;
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

}
