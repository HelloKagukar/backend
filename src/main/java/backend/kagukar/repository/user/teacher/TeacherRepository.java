package backend.kagukar.repository.user.teacher;


import backend.kagukar.data.user.teacher.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {


}