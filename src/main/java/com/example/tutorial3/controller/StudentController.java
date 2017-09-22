package com.example.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tutorial3.model.StudentModel;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController() {
		studentService = new InMemoryStudentService();
	}

	@RequestMapping("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm,
					@RequestParam(value = "name", required = true) String name,
					@RequestParam(value = "gpa", required = true) double gpa) {
		StudentModel student = new StudentModel (npm, name, gpa);
		studentService.student(student);
		return "add";
	}
	@RequestMapping("/student/view")
	public String view(@RequestParam(value = "npm", required = true) String npm, Model model) {
		StudentModel student = studentService.selectStudent(npm);
		model.addAttribute("student", student);
		return "view";
	}
	@RequestMapping("/student/viewall")
	public String viewall(Model model) {
		List<StudentModel> student = studentService.selectAllStudents();
		model.addAttribute("student", student);
		return "viewall";
	}
	@RequestMapping(value = {"/student/view/{npm}", "/student/view/"})
	public String viewnpm(@PathVariable Optional <String> npm, Model model) {
		if (npm.isPresent()) {
			String search = npm.get();
			StudentModel student = studentService.selectStudent(search);	
			if (student == null) {
				return "errorpage";
			} else {
				model.addAttribute("student", student);
				return "view";
			}
		} else {
			return "errorpage";
		}
	}
	@RequestMapping(value = {"/student/delete/{npm}", "/student/delete/"})
	public String deletenpm(@PathVariable Optional <String> npm, Model model) {
		if (npm.isPresent()) {
			String search = npm.get();
			StudentModel student = studentService.selectStudent(search);	
			if (student == null) {
				return "errorpage";
			} else {
				model.addAttribute("student", student);
				return "hapus";
			}
		} else {
			return "errorpage";
		}
	}
}
