package com.hsf301.tri.service;

import java.util.List;

import com.hsf301.tri.pojo.Student;
import com.hsf301.tri.repo.IStudentRepo;
import com.hsf301.tri.repo.StudentRepo;

public class StudentService implements IStudentService{
	private IStudentRepo iStudentRepo;
	
	public StudentService() {
		iStudentRepo = new StudentRepo();
	}
	
	public StudentService(String fileName) {
		iStudentRepo = new StudentRepo(fileName);
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return iStudentRepo.findAll();
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		iStudentRepo.save(student);
	}

	@Override
	public void delete(int StudentID) {
		// TODO Auto-generated method stub
		iStudentRepo.delete(StudentID);
	}

	@Override
	public Student findById(int studentID) {
		// TODO Auto-generated method stub
		return iStudentRepo.findById(studentID);
	}

	@Override
	public Student findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		iStudentRepo.update(student);
	}
}
