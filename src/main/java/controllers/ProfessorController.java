package myy803.traineeship_app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.service.ProfessorService;

@Controller
@RequestMapping("/professor")
// ---------- Professor User Stories
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;	    
    	
    @RequestMapping("/dashboard")
    public String getDashboard(){
       
    	return "professor/dashboard";
    }
    
    @RequestMapping("/profile")
    public String retrieveProfessorProfile(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String username = authentication.getName();
    	System.err.println("Logged use: " + username);
    	    	
    	Professor professor = professorService.getOrCreateProfessor(username);
    	
    	model.addAttribute("professor", professor);
    	
    	return "professor/profile";
    }
    
    @RequestMapping("/save_profile")
    public String saveProfile(@ModelAttribute("profile") Professor professor, Model theModel) {
    	
        professorService.saveProfile(professor);
    	
    	return "professor/dashboard";
    }
	
}