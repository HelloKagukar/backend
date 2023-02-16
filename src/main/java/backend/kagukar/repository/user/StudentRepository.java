package backend.kagukar.repository.user;

import backend.kagukar.data.user.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    default  void updateStudent(Student student){
        student.setModifiedDate(LocalDateTime.now());
        save(student);
    }

}
