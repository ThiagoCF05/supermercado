package com.difusores.walcupom.web.data;

import java.util.Date;
import java.util.List;


public class PessoaFisicaUI extends UserUI{
	private String idPF;
	private String firstName;
	private String lastName;
	private String CPF;
	private Date bornDate;
	private String genre;
	
	private String cidade;
	private String estado;
	
	private List<TelefoneUI> phones;
	private List<EnderecoUI> enderecos;
	
	@Override
	public String toString() {
		return "PessoaFisicaUI [idPF=" + idPF + ", firstName=" + firstName + ", lastName="
				+ lastName + ", CPF=" + CPF + ", bornDate=" + bornDate + 
				", genre=" + genre + ", phones=" + phones + 
				", enderecos=" + enderecos +
				", cidade=" + cidade +
				", estado=" + estado +"]";
	}

	public String getIdPF() {
		return idPF;
	}

	public void setIdPF(String idPF) {
		this.idPF = idPF;
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

	public List<TelefoneUI> getPhones() {
		return phones;
	}

	public void setPhones(List<TelefoneUI> phones) {
		this.phones = phones;
	}

	public List<EnderecoUI> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoUI> enderecos) {
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
