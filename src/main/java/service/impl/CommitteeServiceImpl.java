package myy803.traineeship_app.service.impl;

import java.utinl.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchFactory;
import myy803.traineeship_app.controllers.searchstrategies.PositionsSearchStrategy;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssigmentFactory;
import myy803.traineeship_app.controllers.supervisorsearchstrategies.SupervisorAssignmentStrategy;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.TraineeshipPositionMapper;
import myy803.traineeship_app.mappers.CommitteeService;

@Service
public class CommitteeServiceImpl implements CommitteeService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TraineeshipPositionMapper positionMapper;

    @Autowired
    private PositionsSearchFactory positionsSearchFactory;

    @Autowired
    private SupervisorAssigmentFactory positionsSearchFactory;

    @Override
    public List<Student> getTraineeshipApplications() {
        return studentMapper.findByLookingForTraineeshipTrue();
    }

    @Override
    public List<TraineeshipPosition> findPositions(String studentUsername, String strategy) {
        PositionsSearchStrategy searchStrategy = PositionsSearchFactory.create(strategy);
        return searchStrategy.search(studentUsername);
    }

    @Override
    public void assignPosition(Integer positionId, String studentUsername) {
        Student student = studentMapper.findByUsername(studentUsername);
        TraineeshipPosition position = positionMapper.findById(positionId).get();

        position.setAssigned(true);
        position.setStudent(student);

        student.setAssigneedTraineeeship(position);
        student.setLookingForTraineeship(false);

        positionMapper.save(position);

    }

    @Override
    public void assignSupervisor(Integer positionId, String strategy) {
        SupervisorAssignmentStrategy assignmentStrategy = SupervisorAssigmentFactory.getStrategy(strategy);
        assignmentStrategy.assignSupervisor(positionId, positionMapper);
    }
}