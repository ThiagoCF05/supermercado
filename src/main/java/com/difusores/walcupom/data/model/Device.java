package com.difusores.walcupom.data.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Device {
	
	private String id;
	private double latitude;
	private double longitude;
	private String phoneNumber;
	private Date dataCriacao;
	private Date dataAtualizacao;
	
	public Device(String id, double latitude, double longitude, String phoneNumber, long time){
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phoneNumber = phoneNumber;
	}
	
	public Device(){
		
	}
	
	@Override
	public String toString() {
		return "Device [id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + ", dataCriacao=" + dataCriacao +  ", dataAtualizacao=" 
				+ dataAtualizacao + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
