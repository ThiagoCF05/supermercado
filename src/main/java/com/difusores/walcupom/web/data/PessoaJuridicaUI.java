package com.difusores.walcupom.web.data;

import java.util.List;


public class PessoaJuridicaUI extends UserUI{
	private String idPJ;
	private String razaoSocial;
	private String nomeFantasia;
	private String CNPJ;
	private String inscricaoEstadual;
	private boolean isento;
	
	private List<EnderecoUI> enderecos;
	private List<TelefoneUI> telefones;
	
	private String site;
	
	private String cidade;
	private String estado;
	
	@Override
	public String toString() {
		return "PessoaJuridicaUI [idPJ=" + idPJ + ", razaoSocial=" + razaoSocial + ", nomeFantasia="
				+ nomeFantasia + ", CNPJ=" + CNPJ + ", inscricaoEstadual=" + inscricaoEstadual + 
				", isento=" + isento + ", enderecos=" + enderecos + 
				", telefones=" + telefones + ", site=" + site + "]";
	}

	public String getIdPJ() {
		return idPJ;
	}

	public void setIdPJ(String idPJ) {
		this.idPJ = idPJ;
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
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
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

	public List<EnderecoUI> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoUI> enderecos) {
		this.enderecos = enderecos;
	}

	public List<TelefoneUI> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneUI> telefones) {
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
}
