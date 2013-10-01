package com.difusores.walcupom.data.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cupom {
	@Id
	private String id;
	private String campanha;
	private String device;
	private String codigo;
	private Date dataCriacao;
	private Date limiteAceite;
	private Date limiteUso; 
	
	private boolean aceito;
	private boolean usado;
	
	@Override
	public String toString() {
		return "Cupom [id=" + id + ", campanha=" + campanha + ", device="
				+ device + ", dataCriacao=" + dataCriacao +  ", limiteAceite=" 
				+ limiteAceite + ", limiteUso=" + limiteUso 
				+ ", aceito=" + aceito + ", usado=" + usado 
				+ ", codigo=" + codigo + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCampanha() {
		return campanha;
	}

	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getLimiteAceite() {
		return limiteAceite;
	}

	public void setLimiteAceite(Date limiteAceite) {
		this.limiteAceite = limiteAceite;
	}

	public Date getLimiteUso() {
		return limiteUso;
	}

	public void setLimiteUso(Date limiteUso) {
		this.limiteUso = limiteUso;
	}

	public boolean isAceito() {
		return aceito;
	}

	public void setAceito(boolean aceito) {
		this.aceito = aceito;
	}

	public boolean isUsado() {
		return usado;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
