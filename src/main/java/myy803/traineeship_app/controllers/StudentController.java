package myy803.traineeship_app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.service.StudentService;

@Controller
@RequestMapping("/student")
// ---------- Student User Stories
public class StudentController {

	@Autowired
	private StudentService studentService;	    
    	
    @RequestMapping("/dashboard")
    public String getDashboard(){
       
    	return "student/dashboard";
    }
    
    @RequestMapping("/profile")
    public String retrieveStudentProfile(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String studentUsername = authentication.getName();
    	System.err.println("Logged use: " + studentUsername);
    	    	
    	Student student = studentService.getOrCreateStudent(studentUsername);
    	model.addAttribute("student", student);
       	
    	return "student/profile";
    }
    
    @RequestMapping("/save_profile")
    public String saveProfile(@ModelAttribute("student") Student student, Model theModel) {
    	
    	studentService.saveProfile(student);
        
    	return "student/dashboard";
    }	
}
