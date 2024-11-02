package com.hsf301.tri.service;

import java.util.List;

import com.hsf301.tri.pojo.Student;

public interface IStudentService {
	public List<Student> findAll();

	public void save(Student student);

	public void delete(int StudentID);

	public Student findById(int studentID);

	public Student findByFirstName(String firstName);

	public void update(Student student);
}
