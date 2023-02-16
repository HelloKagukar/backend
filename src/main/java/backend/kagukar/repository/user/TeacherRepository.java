package backend.kagukar.repository.user;

import backend.kagukar.data.user.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
    Teacher findByEmail(String teacherEmail);
}
