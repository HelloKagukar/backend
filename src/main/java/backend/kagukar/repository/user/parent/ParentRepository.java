package backend.kagukar.repository.user.parent;

import backend.kagukar.data.user.parent.Parent;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends MongoRepository<Parent, String> {
}
