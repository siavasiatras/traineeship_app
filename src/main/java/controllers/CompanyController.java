package myy803.traineeship_app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.service.CompanyService;

@Controller
@RequestMapping("/company")
// ---------- Company User Stories
public class CompanyController {

	@Autowired
	private CompanyService companyService;	    
    	
    @RequestMapping("/dashboard")
    public String getDashboard(){
       
    	return "company/dashboard";
    }
    
    @RequestMapping("/profile")
    public String retrieveCompanyProfile(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String username = authentication.getName();
    	System.err.println("Logged use: " + username);    	
    	
    	Company company = companyMapper.getOrCreateCompany(username);
        model.addAttribute("company", company);

        return "company/profile";
    }
    
    @RequestMapping("/save_profile")
    public String saveProfile(@ModelAttribute("profile") Company company, Model theModel) {
    	companyService.saveProfile(company);		
		
    	return "company/dashboard";
    }
    
    @RequestMapping("/list_available_positions")
    public String listAvailablePositions(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String username = authentication.getName();
    	System.err.println("Logged use: " + username);
    	    	
    	List<TraineeshipPosition> positions = company.companyService.getAvailablePositions(username);
    	model.addAttribute("positions", positions);
    	
    	return "company/available_positions";
    }

    @RequestMapping("/show_position_form")
    String showPositionForm(Model model) {
    	model.addAttribute("position", new TraineeshipPosition());
    	
		return "company/position";
    	
    }
    
    @RequestMapping("/save_position")
    public String savePosition(@ModelAttribute("position") TraineeshipPosition position, Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
    	String username = authentication.getName();

    	companyService.savePosition(position, username);
        		
		return "redirect:/company/dashboard";
    }

}
