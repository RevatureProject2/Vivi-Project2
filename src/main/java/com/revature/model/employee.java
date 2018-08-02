package com.revature.model;

public class employee {
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private int accountID;
	private int admin;
	private int login;
	private int balance;
	
	
	public employee(String username, String password, int admin, int login) {
		super();
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.login = login;
	}
	
	public employee(String username, String firstname, String lastname, String email, int balance) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.balance = balance;
	}
	

	public employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
}
