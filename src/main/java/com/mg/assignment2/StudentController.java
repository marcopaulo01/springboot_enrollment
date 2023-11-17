package com.mg.assignment2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@GetMapping("/register")
	public String showRegister() {
		return"register";
	}
	
	@PostMapping("/register")
	public String register(@Valid Student student,Model model,BindingResult result) {
		if(result.hasErrors()||student.getPassword().length()<5) {
			return"register";
		}
		if(!student.getPassword().equals(student.getRetypePassword())) {
			result.rejectValue("retypePassword", "error.password", "Passwords do not match");
			return"register";
		}
		studentRepository.save(student);
		model.addAttribute(student);
		return"home";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam int studentId,@RequestParam String password,Model model) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student !=null && student.getPassword().equals(password)) {
			
			List<Enrollment> enrollment = student.getEnrollment();
			Enrollment activeEnrollment = enrollment.stream().filter(Enrollment::isStatus).findFirst().orElse(null);
			if(activeEnrollment != null) {
				Program program = activeEnrollment.getProgramCode();
				model.addAttribute("program",program);
				model.addAttribute("enrollment", activeEnrollment);
			}
			model.addAttribute("student",student);
			return"home";
		}else {
			return"index";
		}
	}
	
	@GetMapping("/edit/{studentId}")
	public String showEdit(@PathVariable int studentId, Model model) {
		Student student = studentRepository.findById(studentId).orElse(null);
		model.addAttribute("student",student);
		return"edit";
	}
	
	@PostMapping("/update/{studentId}")
	public String update(@PathVariable int studentId, @Valid Student student,Model model) {
		if(!student.getPassword().equals(student.getRetypePassword())) {
			model.addAttribute("error","Password do not match");
			return"edit";
		}
		
		if (student.getPassword().length() < 5) {
		    model.addAttribute("error", "Password must be at least 5 characters long");
		    return "edit";
		}
		
		Student existingStudent = studentRepository.findById(studentId).orElse(null);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setPassword(student.getPassword());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setCity(student.getCity());
		existingStudent.setPostalCode(student.getPostalCode());
		existingStudent.setRetypePassword(student.getRetypePassword());
		
		List<Enrollment> enrollment = existingStudent.getEnrollment();
		Enrollment activeEnrollment = enrollment.stream().filter(Enrollment::isStatus).findFirst().orElse(null);
		if(activeEnrollment != null) {
			Program program = activeEnrollment.getProgramCode();
			model.addAttribute("program",program);
			model.addAttribute("enrollment", activeEnrollment);
		}
		
		studentRepository.save(existingStudent);
		model.addAttribute("student",existingStudent);
		return"home";
	}
}
