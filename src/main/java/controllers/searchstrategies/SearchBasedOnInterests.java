package myy803.traineeship_app.controllers.searchstrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionsMapper;

@Component
public class SearchBasedOnInterests extends AbstractSupervisorAssignmentStrategy {
	@Autowired
	private TraineeshipPositionsMapper positionsMapper;

	
	@Override
	protected void findMatchingPositions(Student applicant, Set<TraineeshipPosition> matchingPositions) {
		String[] interests = applicant.getInterests().split("[,\\s+\\.]");
		for(int i = 0; i < interests.length; i++) {
			List<TraineeshipPosition> positions = positionsMapper.findByTopicsContainingAndIsAssignedFalse(interests[i]);
			matchingPositions.addAll(positions);
		}
	}

}