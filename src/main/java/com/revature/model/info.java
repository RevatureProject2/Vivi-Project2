package com.revature.model;

public class info {
	private String username;
	private String firstname;
	private String lastname;
	private int balance;
	private String email;
	
	public info(String username, String firstname, String lastname, int balance, String email) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.balance = balance;
		this.email = email;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "info [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", balance="
				+ balance + ", email=" + email + "]";
	}
	
	
	


}
