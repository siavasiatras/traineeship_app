package myy803.traineeship_app.controllers.searchstrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.StudentMapper;

@Component
public class SearchBasedOnLocation extends AbstractSupervisorAssignmentStrategy {
	@Autowired
	private TraineeshipPositionsMapper positionsMapper;

	
	@Override
	protected void findMatchingPositions(Student applicant, Set<TraineeshipPosition> matchingPositions) {
		List<Company> companies = CompanyMapper.findByCompanyLocation(applicant.getPreferredLocation());

		for(Company company : companies) {			
			matchingPositions.addAll(company.getAvailablePositions());
		}
		
	}

}
