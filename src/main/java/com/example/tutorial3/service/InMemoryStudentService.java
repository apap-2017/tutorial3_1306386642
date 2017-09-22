package com.example.tutorial3.service;

import java.util.ArrayList;
import java.util.List;
import com.example.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService {
	private static List<StudentModel> studentList = new ArrayList<StudentModel>();

	@Override
	public StudentModel selectStudent(String npm) {
		// TODO Auto-generated method stub
		StudentModel siswa = null;
		for (int i=0; i < studentList.size(); i++) {
			if (studentList.get(i).getNpm().equals(npm)) {
				siswa = studentList.get(i);
				break;
			}
		}
		return siswa;
	}

	@Override
	public List<StudentModel> selectAllStudents() {
		// TODO Auto-generated method stub
		return studentList;
	}

	@Override
	public void student(StudentModel student) {
		// TODO Auto-generated method stub
		studentList.add(student);
	}

	@Override
	public void delete(String npm) {
		// TODO Auto-generated method stub
		for (int i=0; i < studentList.size(); i++) {
			if (studentList.get(i).getNpm().equals(npm)) {
				studentList.remove(i);
				break;
			}
		}
	}
}
