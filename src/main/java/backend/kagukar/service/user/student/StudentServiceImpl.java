package backend.kagukar.service.user.student;

import backend.kagukar.data.user.Profile;
import backend.kagukar.data.user.student.Student;
import backend.kagukar.repository.user.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void createStudent(Profile studentProfile) {
        Student student = new Student();
        student.setProfile(studentProfile);
        studentRepository.save(student);
    }
}
