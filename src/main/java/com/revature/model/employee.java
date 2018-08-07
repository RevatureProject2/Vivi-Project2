package com.revature.model;

public class employee {
	private String username;
	private String password;
	private int accountID;
	private int admin;
	private int login;
	
	public employee() {
		this.username = "";
		this.password = "";
	}
	public employee(String username, String password, int admin, int login) {
		super();
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.login = login;
	}

	public employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.admin= 0;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int isAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int isLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	
	
}
