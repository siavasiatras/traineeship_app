package myy803.traineeship_app.service.impl;

import java.utinl.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.traineeship_app.domain.Student;
import myy803.traineeship_app.mappers.StudentMapper;
import myy803.traineeship_app.mappers.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findByUsername(String username) {
        return studentMapper.findByUsername(username);
    }

    @Override
    public void saveProfile(Student student) {
        student.setLookingForTraineeship(true);
        studentMapper.save(student);
    }

    @Override
    public Student getOrCreateStudent(String username) {
        Student student = findByUsername(username);
        if (student == null) {
            student = new Student(username);
        }
        return student;
    }
}