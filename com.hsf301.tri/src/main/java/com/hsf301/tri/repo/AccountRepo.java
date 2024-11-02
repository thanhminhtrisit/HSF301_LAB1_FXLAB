package com.hsf301.tri.repo;

import com.hsf301.tri.dao.AccountDAO;
import com.hsf301.tri.pojo.Account;

public class AccountRepo implements IAccountRepo {
	private AccountDAO accountDAO;

	public AccountRepo() {
		accountDAO = new AccountDAO();
	}

	@Override
	public Account login(String email, String password) {
		// TODO Auto-generated method stub
		return accountDAO.findAll().stream()
				.filter(acc -> acc.getEmail().equals(email) && acc.getPassword().equals(password)).findFirst()
				.orElse(null);
	}

}
