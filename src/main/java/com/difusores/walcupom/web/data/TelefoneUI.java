package com.difusores.walcupom.web.data;

public class TelefoneUI {
	private String id;
	private String user;
	private String ddd;
	private String number;
	private String type;
	
	@Override
	public String toString() {
		return "Telefone [id=" + id + ", user=" + user + ", ddd="
				+ ddd + ", number=" + number + ", type=" + type +  "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
