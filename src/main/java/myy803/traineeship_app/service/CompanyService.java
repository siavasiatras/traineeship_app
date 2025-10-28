package myy803.traineeship_app.service;

import util.List;
import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.TraineeshipPosition;

public interface CompanyService {
    Company findByUsername(String username);
    void saveProfile(Company company);
    void savePosition(TraineeshipPosition position, String companyUsername);
    Company getOrCreateCompany(String username);
    List<TraineeshipPosition> getAllTraineeshipPositions(String companyUsername);
}