package backend.kagukar.repository.user.profile;

import backend.kagukar.data.user.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

    boolean existsByEmail(String email);
}
