package com.difusores.walcupom.web.data;

public class UserUI {
	private String id;

	private String userName;
	private String password;
	private String email;
	private boolean admin;
	private boolean banned;
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public String toString() {
		return "UserUI [id=" + id + ", userName=" + userName + ", password="
				+ password + ", email=" + email +
				", admin=" + admin + ", banned=" + banned + "]";
	}

}
