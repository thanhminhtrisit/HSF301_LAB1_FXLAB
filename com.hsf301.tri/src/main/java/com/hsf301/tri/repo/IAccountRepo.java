package com.hsf301.tri.repo;

import com.hsf301.tri.pojo.Account;

public interface IAccountRepo {
	Account login(String email, String password);
}
