package myy803.traineeship_app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.CommitteeService;

@Controller
// ---------- Committee User Stories
@RequestMapping("/committee")
public class CommitteeController{
       
    @Autowired
    private CommitteeService committeeService;

    @RequestMapping("/dashboard")
    public String getDashboard(){
        return "committee/dashboard";
    }

	@RequestMapping("/list_traineeship_applications")
	public String listTraineeshipApplications(Model model) {
		List<Student> traineeshipApplications = committeeService.getTraineeshipApplications();

		model.addAttribute("traineeship_applications", traineeshipApplications);
		return "committee/traineeship_applications";
	}
	
	@RequestMapping("/find_positions")
	public String findPositions(
			@RequestParam("selected_student_id") String studentUsername, 
			@RequestParam("strategy") String strategy, Model model) {
				
		//PositionsSearchStrategy searchStrategy = positionsSearchFactory.create(strategy);
		List<TraineeshipPosition> positions = committeeService.findPositions(studentUsername);
		
		model.addAttribute("positions", positions);
		model.addAttribute("student_username", studentUsername);
		
		return "committee/available_positions";
	}
	
	@RequestMapping("/assign_position")
	public String assignPosition(
			@RequestParam("selected_position_id") Integer positionId, 
			@RequestParam("applicant_username") String studentUsername, 
			Model model) {
				
		//Student student = studentMapper.findByUsername(studentUsername);
		//TraineeshipPosition position = positionsMapper.findById(positionId).get();
        committeeService.assignPosition(positionId, studentUsername);
        model.addAttribute("position_id", positionId);		
		
		return "committee/supervisor_assignment";
	}

	@RequestMapping("/assign_supervisor")
	public String assignSupervisor(
			@RequestParam("selected_position_id") Integer positionId, 
			@RequestParam("strategy") String strategy, Model model) {
		
		//SupervisorAssignmentStrategy assignmentStrategy = supervisorAssigmentFactory.create(strategy);
		//assignmentStrategy.assign(positionId);
		committeeService.assignSupervisor(positionId, strategy);
    	return "committee/dashboard";
	}
	
}