package myy803.traineeship_app.service;

import util.List;
import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.TraineeshipPosition;

public interface ProfessorService {
    Professor findByUsername(String username);
    void saveProfile(Professor professor);
    Professor getOrCreateProfessor(String username);
}