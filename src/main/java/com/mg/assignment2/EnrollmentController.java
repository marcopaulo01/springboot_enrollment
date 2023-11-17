package com.mg.assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnrollmentController {

	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	ProgramRepository programRepository;
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/enroll/{studentId}")
	public String showEnroll(@PathVariable int studentId,Model model) {
		model.addAttribute("program",programRepository.findAll());
		return"enroll";
	}
	
	@PostMapping("/enroll/{studentId}")
	public String enroll(@PathVariable int studentId,Program program,Model model) {
		model.addAttribute("program",program);
		return"payment";
	}
	
	@PostMapping("/payment/{studentId}")
	public String payment(@PathVariable int studentId,Enrollment enrollment,Model model) {
		enrollmentRepository.save(enrollment);
		Student student = studentRepository.findById(studentId).orElse(null);
		Program program = enrollment.getProgramCode();
		model.addAttribute("student",student);
		model.addAttribute("enrollment",enrollment);
		model.addAttribute("program",program);
		return"home";
	}
}
