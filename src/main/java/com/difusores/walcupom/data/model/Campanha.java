package com.difusores.walcupom.data.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.difusores.walcupom.util.enums.TipoCampanha;

@Document
public class Campanha {
	@Id
	private String id;
	private TipoCampanha tipo;
	private List<String> imagens;
	private Map<String, Endereco> enderecos;
	private double distancia;
	private int quantidadeCupons;
	
	
	private Date dataInicio;
	private Date dataEncerramento;
	
	private String limiteAceite;
	private String limiteUso;
	
	private Date dataCriacao;
	
	private String descricao;
	@DBRef
	private Categoria categoria;
	
	@Indexed
	@DBRef
	private User user;
	
	@Override
	public String toString() {
		return "Campanha [id=" + id + ", tipo=" + tipo + ", imagens="
				+ imagens + ", enderecos=" + enderecos +  ", distancias=" 
				+ distancia + ", quantidadeCupons=" + quantidadeCupons +
				", dataCriacao=" + dataCriacao +  
				", dataInicio=" + dataInicio + 
				 ", dataEncerramento=" + dataEncerramento + 
				 ", limiteAceite=" + limiteAceite +
				 ", limiteUso=" + limiteUso + ", user=" + user +
				 ", descricao=" + descricao +
				 ", categoria=" + categoria +"]";
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

	public Map<String, Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Map<String, Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public int getQuantidadeCupons() {
		return quantidadeCupons;
	}

	public void setQuantidadeCupons(int quantidadeCupons) {
		this.quantidadeCupons = quantidadeCupons;
	}

	public String getLimiteAceite() {
		return limiteAceite;
	}

	public void setLimiteAceite(String limiteAceite) {
		this.limiteAceite = limiteAceite;
	}

	public String getLimiteUso() {
		return limiteUso;
	}

	public void setLimiteUso(String limiteUso) {
		this.limiteUso = limiteUso;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
