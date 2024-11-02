package com.hsf301.tri.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account {
	@Id
	private String Email;
	
	private String Password;
	
	private int RoleID;
	
	public Account() {
		super();
	}

	public Account(String email, String password, int roleID) {
		super();
		Email = email;
		Password = password;
		RoleID = roleID;
	}

	public String getEmail() {
		return Email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getRoleID() {
		return RoleID;
	}

	public void setRoleID(int roleID) {
		RoleID = roleID;
	}
	
	
}
