package com.hsf301.tri.repo;

import java.util.List;

import com.hsf301.tri.dao.StudentDAO;
import com.hsf301.tri.pojo.Student;

public class StudentRepo implements IStudentRepo{
	private StudentDAO studentDAO;
	
	public StudentRepo() {
		studentDAO = new StudentDAO();
	}

	public StudentRepo(String fileConfig) {
		studentDAO = new StudentDAO(fileConfig);
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentDAO.findAll();
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentDAO.save(student);
		
	}

	@Override
	public void delete(int StudentID) {
		// TODO Auto-generated method stub
		studentDAO.delete(StudentID);
	}

	@Override
	public Student findById(int studentID) {
		// TODO Auto-generated method stub
		return studentDAO.findById(studentID);
	}

	@Override
	public Student findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		studentDAO.update(student);
	}
}
