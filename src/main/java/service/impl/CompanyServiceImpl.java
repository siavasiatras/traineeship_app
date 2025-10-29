package myy803.traineeship_app.service.impl;

import java.utinl.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.traineeship_app.domain.Company;
import myy803.traineeship_app.domain.TraineeshipPosition;
import myy803.traineeship_app.mappers.CompanyMapper;
import myy803.traineeship_app.mappers.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company findByUsername(String username) {
        return companyMapper.findByUsername(username);
    }

    @Override
    public void saveProfile(Company company) {
        companyMapper.save(company);
    }

    @Override
    public void savePosition(TraineeshipPosition position, String companyUsername) {
        Company company = findByUsername(companyUsername);
        position.setCompany(company);
        company.addPosition(position);
        companyMapper.save(company);
    }

    @Override
    public Company getOrCreateCompany(String username) {
        Company company = findByUsername(username);
        if (company == null) {
            company = new Company(username);
        }
        return company;
    }

    @Override
    public List<TraineeshipPosition> getAllTraineeshipPositions(String companyUsername) {
        Company company = companyMapper.findByUsername(companyUsername);
        return company.getTraineeshipPositions();
    }
}