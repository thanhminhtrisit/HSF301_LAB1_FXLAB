package com.hsf301.tri.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hsf301.tri.pojo.Student;

public class StudentDAO {
	private SessionFactory sessionFactory = null;
	private Configuration cfg = null;
	private static EntityManager em;
	private static EntityManagerFactory emf;

	public StudentDAO() {
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public StudentDAO(String persistanceName) {
		emf = Persistence.createEntityManagerFactory(persistanceName);
	}

	@SuppressWarnings("unchecked")
	public List<Student> findAll() {
		Session s = sessionFactory.openSession();
		try {
			return s.createQuery("from Student").list();
		} catch (Exception e) {
			throw e;
		} finally {
			s.close();
		}
	}

	public void save(Student student) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(student);
			t.commit();
		} catch (Exception ex) {
			t.rollback();
		} finally {
			session.close();
		}
	}

	public void delete(int studentId) {
		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		try {
			t.begin();
			Student student = session.find(Student.class, studentId);
			if (student == null) {
				System.out.println("Student không tồn tại");
				return;
			}
			session.delete(student);
			t.commit();
		} catch (RuntimeException e) {
			t.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public Student findById(int studentId) {
		Session session = sessionFactory.openSession();
		try {
			return (Student) session.get(Student.class, studentId);
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public void update(Student student) {
		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		try {
			t.begin();
			session.update(student);
			t.commit();
		} catch (Exception ex) {
			t.rollback();
		} finally {
			session.close();
		}
	}
}
