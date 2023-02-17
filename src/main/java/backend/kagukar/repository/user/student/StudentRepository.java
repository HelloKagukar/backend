package backend.kagukar.repository.user.student;


import backend.kagukar.data.user.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends MongoRepository<Student, String> {


}