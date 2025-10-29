package myy803.traineeship_app.service;

import util.List;
import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.domain.TraineeshipPosition;

public interface CommitteeService {
    List<Student> getAllTraineeshipPositions();
    List<TraineeshipPosition> findPositions(String studentUsername,String strategy);
    void assignPosition(Integer positionId, String studentUsername);
    void assignSupervisor(Integer positionId, String strategy);
}