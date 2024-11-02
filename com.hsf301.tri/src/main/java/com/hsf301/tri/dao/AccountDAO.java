package com.hsf301.tri.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hsf301.tri.pojo.Account;


public class AccountDAO {
	private SessionFactory sessionFactory = null;
	private Configuration cfg = null;
	
	public AccountDAO() {
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		sessionFactory = cfg.buildSessionFactory();
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> findAll(){
		Session s = sessionFactory.openSession();
		try {
			return s.createQuery("from Account").list();
		}catch(Exception e){
			throw e;
		}finally {
			s.close();
		}
	}
	
}
