package myy803.traineeship_app.service.impl;

import java.utinl.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.traineeship_app.domain.Professor;
import myy803.traineeship_app.mappers.ProfessorMapper;
import myy803.traineeship_app.mappers.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorMapper professorMapper;

    @Override
    public Professor findByUsername(String username) {
        return professorMapper.findByUsername(username);
    }

    @Override
    public void saveProfile(Professor professor) {
        professorMapper.save(professor);
    }

    @Override
    public Professor getOrCreateProfessor(String username) {
        Professor professor = findByUsername(username);
        if (professor == null) {
            professor = new Professor(username);
        }
        return professor;
    }
}