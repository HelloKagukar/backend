package backend.kagukar.repository.user;

import backend.kagukar.data.user.Parent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends MongoRepository<Parent, String> {
    Parent findByEmail(String parentEmail);
}
