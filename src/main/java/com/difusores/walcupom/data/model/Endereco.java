package com.difusores.walcupom.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Endereco {
	@Id
	private String id;
	private String cep;
	private String rua;
	private String avenida;
	private int numero;
	private String bloco;
	private double latitude;
	private double longitude;
	private String cidade;
	private String estado;
	private String nomeEstabelecimento;
	
	private String user;
	
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", rua="
				+ rua + ", avenida=" + avenida + ", numero=" + numero +
				 ", bloco=" + bloco + ", latitude=" + latitude +
				 ", longitude=" + longitude + ", user=" + user + 
				 ", cidade=" + cidade + ", estado=" + estado + 
				 ", nomeEstabelecimento=" + nomeEstabelecimento +"]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getAvenida() {
		return avenida;
	}
	public void setAvenida(String avenida) {
		this.avenida = avenida;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
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

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

}
