package com.difusores.walcupom.web.data;

import java.util.Date;
import java.util.List;

import com.difusores.walcupom.util.enums.TipoCampanha;

public class CampanhaUI {
	private String id;
	private TipoCampanha tipo;
	private List<String> imagens;
	private List<EnderecoUI> enderecos;
	private double distancia;
	private int quantidadeCupons;
	private String descricao;
	
	private Date dataCriacao;
	private Date dataInicio;
	private Date dataEncerramento;
	
	private Date limiteAceite;
	private Date limiteUso;
	
	private UserUI user;
	
	@Override
	public String toString() {
		return "CampanhaUI [id=" + id + ", tipo=" + tipo + ", imagens="
				+ imagens + ", enderecos=" + enderecos 
				+ ", distancias=" + distancia +
				", quantidadeCupons=" + quantidadeCupons +
				", dataCriacao=" + dataCriacao + 
				 ", dataInicio=" + dataInicio + 
				 ", dataEncerramento=" + dataEncerramento + 
				 ", limiteAceite=" + limiteAceite +
				 ", limiteUso=" + limiteUso + ", user=" + user +
				 ", descricao=" + descricao +"]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoCampanha getTipo() {
		return tipo;
	}

	public void setTipo(TipoCampanha tipo) {
		this.tipo = tipo;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public List<EnderecoUI> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoUI> enderecos) {
		this.enderecos = enderecos;
	}

	public int getQuantidadeCupons() {
		return quantidadeCupons;
	}

	public void setQuantidadeCupons(int quantidadeCupons) {
		this.quantidadeCupons = quantidadeCupons;
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

	public UserUI getUser() {
		return user;
	}

	public void setUser(UserUI user) {
		this.user = user;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
